plugins {
	alias(libs.plugins.nexusPublish)
	id("junitbuild.base-conventions")
	id("junitbuild.build-metadata")
	id("junitbuild.checkstyle-nohttp")
	id("junitbuild.dependency-update-check")
	id("junitbuild.jacoco-aggregation-conventions")
	id("junitbuild.temp-maven-repo")
}

description = "JUnit 5"

val license by extra(License(
	name = "Eclipse Public License v2.0",
	url = uri("https://www.eclipse.org/legal/epl-v20.html"),
	headerFile = layout.projectDirectory.file("gradle/config/spotless/eclipse-public-license-2.0.java")
))

val platformProjects by extra(listOf(
		projects.junitPlatformCommons,
		projects.junitPlatformConsole,
		projects.junitPlatformConsoleStandalone,
		projects.junitPlatformEngine,
		projects.junitPlatformJfr,
		projects.junitPlatformLauncher,
		projects.junitPlatformReporting,
		projects.junitPlatformRunner,
		projects.junitPlatformSuite,
		projects.junitPlatformSuiteApi,
		projects.junitPlatformSuiteCommons,
		projects.junitPlatformSuiteEngine,
		projects.junitPlatformTestkit
).map { dependencyProject(it) })

val jupiterProjects by extra(listOf(
		projects.junitJupiter,
		projects.junitJupiterApi,
		projects.junitJupiterEngine,
		projects.junitJupiterMigrationsupport,
		projects.junitJupiterParams
).map { dependencyProject(it) })

val vintageProjects by extra(listOf(
	dependencyProject(projects.junitVintageEngine)
))

val mavenizedProjects by extra(platformProjects + jupiterProjects + vintageProjects)
val modularProjects by extra(mavenizedProjects - setOf(dependencyProject(projects.junitPlatformConsoleStandalone)))

dependencies {
	modularProjects.forEach {
		jacocoAggregation(it)
	}
	jacocoAggregation(projects.documentation)
	jacocoAggregation(projects.jupiterTests)
	jacocoAggregation(projects.platformTests)
}

nexusPublishing {
	packageGroup = "org.junit"
	repositories {
		sonatype()
	}
}
