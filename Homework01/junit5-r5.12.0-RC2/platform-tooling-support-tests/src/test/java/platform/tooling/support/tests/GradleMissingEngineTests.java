/*
 * Copyright 2015-2025 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package platform.tooling.support.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static platform.tooling.support.tests.Projects.copyToWorkspace;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.platform.reporting.testutil.FileUtils;
import org.junit.platform.tests.process.OutputFiles;
import org.opentest4j.TestAbortedException;

import platform.tooling.support.Helper;
import platform.tooling.support.MavenRepo;
import platform.tooling.support.ProcessStarters;

/**
 * @since 1.3
 */
class GradleMissingEngineTests {

	@Test
	void gradle_wrapper(@TempDir Path workspace, @FilePrefix("gradle") OutputFiles outputFiles) throws Exception {
		var result = ProcessStarters.gradlew() //
				.workingDir(copyToWorkspace(Projects.GRADLE_MISSING_ENGINE, workspace)) //
				.addArguments("-Dmaven.repo=" + MavenRepo.dir()) //
				.addArguments("build", "--no-daemon", "--stacktrace", "--no-build-cache", "--warning-mode=fail") //
				.putEnvironment("JDK8", Helper.getJavaHome("8").orElseThrow(TestAbortedException::new).toString()) //
				.redirectOutput(outputFiles).startAndWait();

		assertEquals(1, result.exitCode());
		assertThat(result.stdErrLines()) //
				.contains("FAILURE: Build failed with an exception.");

		var htmlFile = FileUtils.findPath(workspace, "glob:**/build/reports/tests/test/classes/*.html");
		assertThat(htmlFile).content() //
				.contains("Cannot create Launcher without at least one TestEngine");
	}
}
