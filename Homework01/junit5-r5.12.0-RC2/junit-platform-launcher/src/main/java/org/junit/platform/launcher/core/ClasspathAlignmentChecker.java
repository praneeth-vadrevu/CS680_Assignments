/*
 * Copyright 2015-2025 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package org.junit.platform.launcher.core;

import static java.util.Collections.unmodifiableList;
import static java.util.Comparator.comparing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import org.junit.platform.commons.JUnitException;
import org.junit.platform.commons.support.ReflectionSupport;
import org.junit.platform.commons.util.ClassLoaderUtils;

/**
 * @since 1.12
 */
class ClasspathAlignmentChecker {

	// VisibleForTesting
	static final List<String> WELL_KNOWN_PACKAGES = unmodifiableList(Arrays.asList( //
		"org.junit.jupiter.api", //
		"org.junit.jupiter.engine", //
		"org.junit.jupiter.migrationsupport", //
		"org.junit.jupiter.params", //
		"org.junit.platform.commons", //
		"org.junit.platform.console", //
		"org.junit.platform.engine", //
		"org.junit.platform.jfr", //
		"org.junit.platform.launcher", //
		"org.junit.platform.reporting", //
		"org.junit.platform.runner", //
		"org.junit.platform.suite.api", //
		"org.junit.platform.suite.commons", //
		"org.junit.platform.suite.engine", //
		"org.junit.platform.testkit", //
		"org.junit.vintage.engine" //
	));

	static Optional<JUnitException> check(LinkageError error) {
		ClassLoader classLoader = ClassLoaderUtils.getClassLoader(ClasspathAlignmentChecker.class);
		Function<String, Package> packageLookup = name -> ReflectionSupport.findMethod(ClassLoader.class,
			"getDefinedPackage", String.class) //
				.map(m -> (Package) ReflectionSupport.invokeMethod(m, classLoader, name)) //
				.orElseGet(() -> getPackage(name));
		return check(error, packageLookup);
	}

	// VisibleForTesting
	static Optional<JUnitException> check(LinkageError error, Function<String, Package> packageLookup) {
		Map<String, List<Package>> packagesByVersions = new HashMap<>();
		WELL_KNOWN_PACKAGES.stream() //
				.map(packageLookup) //
				.filter(Objects::nonNull) //
				.forEach(pkg -> {
					String version = pkg.getImplementationVersion();
					if (version != null) {
						if (pkg.getName().startsWith("org.junit.platform") && version.contains(".")) {
							version = platformToJupiterVersion(version);
						}
						packagesByVersions.computeIfAbsent(version, __ -> new ArrayList<>()).add(pkg);
					}
				});
		if (packagesByVersions.size() > 1) {
			StringBuilder message = new StringBuilder();
			String lineBreak = System.lineSeparator();
			message.append("The wrapped ").append(error.getClass().getSimpleName()) //
					.append(" is likely caused by the versions of JUnit jars on the classpath/module path ") //
					.append("not being properly aligned. ") //
					.append(lineBreak) //
					.append("Please ensure consistent versions are used (see https://junit.org/junit5/docs/") //
					.append(platformToJupiterVersion(
						ClasspathAlignmentChecker.class.getPackage().getImplementationVersion())) //
					.append("/user-guide/#dependency-metadata).") //
					.append(lineBreak) //
					.append("The following conflicting versions were detected:").append(lineBreak);
			packagesByVersions.values().stream() //
					.flatMap(List::stream) //
					.sorted(comparing(Package::getName)) //
					.map(pkg -> String.format("- %s: %s%n", pkg.getName(), pkg.getImplementationVersion())) //
					.forEach(message::append);
			return Optional.of(new JUnitException(message.toString(), error));
		}
		return Optional.empty();
	}

	private static String platformToJupiterVersion(String version) {
		int majorVersion = Integer.parseInt(version.substring(0, version.indexOf("."))) + 4;
		return majorVersion + version.substring(version.indexOf("."));
	}

	@SuppressWarnings({ "deprecation", "RedundantSuppression" }) // only called when running on JDK 8
	private static Package getPackage(String name) {
		return Package.getPackage(name);
	}
}
