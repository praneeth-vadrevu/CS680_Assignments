
import com.gradle.develocity.agent.gradle.internal.test.PredictiveTestSelectionConfigurationInternal
import com.gradle.develocity.agent.gradle.test.PredictiveTestSelectionMode
import org.gradle.api.tasks.PathSensitivity.RELATIVE
import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.internal.os.OperatingSystem
import java.io.OutputStream

plugins {
	`java-library`
	id("junitbuild.build-parameters")
}

var javaAgent = configurations.dependencyScope("javaAgent")
var javaAgentClasspath = configurations.resolvable("javaAgentClasspath") {
	extendsFrom(javaAgent.get())
}

var openTestReportingCli = configurations.dependencyScope("openTestReportingCli")
var openTestReportingCliClasspath = configurations.resolvable("openTestReportingCliClasspath") {
	extendsFrom(openTestReportingCli.get())
}

val generateOpenTestHtmlReport by tasks.registering(JavaExec::class) {
	mustRunAfter(tasks.withType<Test>())
	mainClass.set("org.opentest4j.reporting.cli.ReportingCli")
	args("html-report")
	classpath(openTestReportingCliClasspath)
	argumentProviders += objects.newInstance(HtmlReportParameters::class).apply {
		eventXmlFiles.from(tasks.withType<Test>().map {
			objects.fileTree()
				.from(it.reports.junitXml.outputLocation)
				.include("junit-*/open-test-report.xml")
		})
		outputLocation = layout.buildDirectory.file("reports/open-test-report.html")
	}
	if (buildParameters.testing.hideOpenTestReportHtmlGeneratorOutput) {
		standardOutput = object : OutputStream() {
			override fun write(b: Int) {
				// discard output
			}
		}
	}
	outputs.cacheIf { true }
}

abstract class HtmlReportParameters : CommandLineArgumentProvider {

	@get:InputFiles
	@get:PathSensitive(RELATIVE)
	@get:SkipWhenEmpty
	abstract val eventXmlFiles: ConfigurableFileCollection

	@get:OutputFile
	abstract val outputLocation: RegularFileProperty

	override fun asArguments() = listOf("--output", outputLocation.get().asFile.absolutePath) +
			eventXmlFiles.map { it.absolutePath }.toList()
}

tasks.withType<Test>().configureEach {
	useJUnitPlatform {
		includeEngines("junit-jupiter")
	}
	include("**/*Test.class", "**/*Tests.class")
	testLogging {
		events = setOf(FAILED)
		exceptionFormat = FULL
	}
	develocity {
		testRetry {
			maxRetries = buildParameters.testing.retries.orElse(if (buildParameters.ci) 2 else 0)
		}
		testDistribution {
			enabled.convention(buildParameters.junit.develocity.testDistribution.enabled && (!buildParameters.ci || !System.getenv("DEVELOCITY_ACCESS_KEY").isNullOrBlank()))
			maxLocalExecutors = buildParameters.junit.develocity.testDistribution.maxLocalExecutors
			maxRemoteExecutors = buildParameters.junit.develocity.testDistribution.maxRemoteExecutors
			if (buildParameters.ci) {
				when {
					OperatingSystem.current().isLinux -> requirements.add("os=linux")
					OperatingSystem.current().isWindows -> requirements.add("os=windows")
					OperatingSystem.current().isMacOsX -> requirements.add("os=macos")
				}
			}
		}
		predictiveTestSelection {
			enabled = buildParameters.junit.develocity.predictiveTestSelection.enabled

			if (buildParameters.junit.develocity.predictiveTestSelection.selectRemainingTests) {
				mode = PredictiveTestSelectionMode.REMAINING_TESTS
			}

			// Ensure PTS works when publishing Build Scans to scans.gradle.com
			this as PredictiveTestSelectionConfigurationInternal
			server = uri("https://ge.junit.org")

			mergeCodeCoverage = true
		}
	}
	systemProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager")
	// https://github.com/gradle/gradle/issues/30554
	systemProperty("log4j2.julLoggerAdapter", "org.apache.logging.log4j.jul.CoreLoggerAdapter")
	// Avoid overhead (see https://logging.apache.org/log4j/2.x/manual/jmx.html#enabling-jmx)
	systemProperty("log4j2.disableJmx", "true")
	// Required until ASM officially supports the JDK 14
	systemProperty("net.bytebuddy.experimental", true)
	if (buildParameters.testing.enableJFR) {
		jvmArgs(
			"-XX:+UnlockDiagnosticVMOptions",
			"-XX:+DebugNonSafepoints",
			"-XX:StartFlightRecording=filename=${reports.junitXml.outputLocation.get()},dumponexit=true,settings=profile.jfc",
			"-XX:FlightRecorderOptions=stackdepth=1024"
		)
	}

	// Track OS as input so that tests are executed on all configured operating systems on CI
	trackOperationSystemAsInput()

	// Avoid passing unnecessary environment variables to the JVM (from GitHub Actions)
	if (buildParameters.ci) {
		environment.remove("RUNNER_TEMP")
		environment.remove("GITHUB_ACTION")
	}

	jvmArgumentProviders += CommandLineArgumentProvider {
		listOf(
			"-Djunit.platform.reporting.open.xml.enabled=true",
			"-Djunit.platform.reporting.output.dir=${reports.junitXml.outputLocation.get().asFile.absolutePath}/junit-{uniqueNumber}",
		)
	}
	systemProperty("junit.platform.output.capture.stdout", "true")
	systemProperty("junit.platform.output.capture.stderr", "true")

	jvmArgumentProviders += objects.newInstance(JavaAgentArgumentProvider::class).apply {
		classpath.from(javaAgentClasspath)
	}

	val reportDirTree = objects.fileTree().from(reports.junitXml.outputLocation)
	doFirst {
		reportDirTree.visit {
			if (name.startsWith("junit-")) {
				file.deleteRecursively()
			}
		}
	}

	finalizedBy(generateOpenTestHtmlReport)
}

dependencies {
	testImplementation(dependencyFromLibs("assertj"))
	testImplementation(dependencyFromLibs("mockito-junit-jupiter"))
	testImplementation(dependencyFromLibs("testingAnnotations"))
	testImplementation(project(":junit-jupiter"))

	testRuntimeOnly(project(":junit-platform-engine"))
	testRuntimeOnly(project(":junit-platform-jfr"))
	testRuntimeOnly(project(":junit-platform-reporting"))

	testRuntimeOnly(bundleFromLibs("log4j"))
	testRuntimeOnly(dependencyFromLibs("jfrPolyfill")) {
		because("OpenJ9 does not include JFR")
	}
	testRuntimeOnly(dependencyFromLibs("openTestReporting-events")) {
		because("it's required to run tests via IntelliJ which does not consumed the shadowed jar of junit-platform-reporting")
	}

	openTestReportingCli(dependencyFromLibs("openTestReporting-cli"))
	openTestReportingCli(project(":junit-platform-reporting"))

	javaAgent(dependencyFromLibs("mockito-core")) {
		isTransitive = false
	}
}

abstract class JavaAgentArgumentProvider : CommandLineArgumentProvider {

	@get:Classpath
	abstract val classpath: ConfigurableFileCollection

	override fun asArguments() = listOf("-javaagent:${classpath.singleFile.absolutePath}")

}
