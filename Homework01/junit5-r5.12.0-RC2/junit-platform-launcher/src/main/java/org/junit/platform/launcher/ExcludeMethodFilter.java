/*
 * Copyright 2015-2025 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package org.junit.platform.launcher;

import static org.junit.platform.engine.FilterResult.excluded;
import static org.junit.platform.engine.FilterResult.included;

import java.util.regex.Pattern;

import org.junit.platform.engine.FilterResult;
import org.junit.platform.engine.TestDescriptor;

/**
 * {@link MethodFilter} that matches fully qualified method names against
 * patterns in the form of regular expressions.
 *
 * <p>If the fully qualified name of a method matches against at least one
 * pattern, the class will be excluded.
 *
 * @since 1.12
 */
class ExcludeMethodFilter extends AbstractMethodFilter {

	ExcludeMethodFilter(String... patterns) {
		super(patterns);
	}

	@Override
	public FilterResult apply(TestDescriptor descriptor) {
		String methodName = getFullyQualifiedMethodNameFromDescriptor(descriptor);
		return findMatchingPattern(methodName) //
				.map(pattern -> excluded(formatExclusionReason(methodName, pattern))) //
				.orElseGet(() -> included(formatInclusionReason(methodName)));
	}

	private String formatInclusionReason(String methodName) {
		return String.format("Method name [%s] does not match any excluded pattern: %s", methodName,
			patternDescription);
	}

	private String formatExclusionReason(String methodName, Pattern pattern) {
		return String.format("Method name [%s] matches excluded pattern: '%s'", methodName, pattern);
	}

	@Override
	public String toString() {
		return String.format("%s that excludes method names that match one of the following regular expressions: %s",
			getClass().getSimpleName(), patternDescription);
	}
}
