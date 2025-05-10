import junitbuild.extensions.capitalized
import junitbuild.release.VerifyBinaryArtifactsAreIdentical

val tempRepoName by extra("temp")
val tempRepoDir by extra {
	layout.buildDirectory.dir("repo").get().asFile
}

val clearTempRepoDir by tasks.registering {
	val dir = tempRepoDir
	doFirst {
		dir.deleteRecursively()
	}
}

val verifyArtifactsInStagingRepositoryAreReproducible by tasks.registering(VerifyBinaryArtifactsAreIdentical::class) {
	localRepoDir.set(tempRepoDir)
}

subprojects {
	pluginManager.withPlugin("maven-publish") {
		configure<PublishingExtension> {
			repositories {
				maven {
					name = tempRepoName
					url = uri(tempRepoDir)
				}
			}
		}
		val publishingTasks = tasks.withType<PublishToMavenRepository>()
			.named { it.endsWith("To${tempRepoName.capitalized()}Repository") }
		publishingTasks.configureEach {
			dependsOn(clearTempRepoDir)
		}
		verifyArtifactsInStagingRepositoryAreReproducible {
			dependsOn(publishingTasks)
		}
	}
}
