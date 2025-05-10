/*
 * Copyright 2015-2025 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package org.junit.jupiter.params;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.aggregator.AggregatorIntegrationTests.CsvToPerson;
import org.junit.jupiter.params.aggregator.AggregatorIntegrationTests.Person;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.commons.util.ReflectionUtils;

/**
 * Unit tests for {@link ParameterizedTestMethodContext}.
 *
 * @since 5.2
 */
class ParameterizedTestMethodContextTests {

	@ParameterizedTest
	@ValueSource(strings = { "onePrimitive", "twoPrimitives", "twoAggregators", "twoAggregatorsWithTestInfoAtTheEnd",
			"mixedMode" })
	void validSignatures(String methodName) {
		assertTrue(createMethodContext(ValidTestCase.class, methodName).hasPotentiallyValidSignature());
	}

	@ParameterizedTest
	@ValueSource(strings = { "twoAggregatorsWithPrimitiveInTheMiddle", "twoAggregatorsWithTestInfoInTheMiddle" })
	void invalidSignatures(String methodName) {
		assertFalse(createMethodContext(InvalidTestCase.class, methodName).hasPotentiallyValidSignature());
	}

	private ParameterizedTestMethodContext createMethodContext(Class<?> testClass, String methodName) {
		var method = ReflectionUtils.findMethods(testClass, m -> m.getName().equals(methodName)).getFirst();
		return new ParameterizedTestMethodContext(method, method.getAnnotation(ParameterizedTest.class));
	}

	@SuppressWarnings("JUnitMalformedDeclaration")
	static class ValidTestCase {

		@ParameterizedTest
		void onePrimitive(int num) {
		}

		@ParameterizedTest
		void twoPrimitives(int num1, int num2) {
		}

		@ParameterizedTest
		void twoAggregators(@CsvToPerson Person person, ArgumentsAccessor arguments) {
		}

		@ParameterizedTest
		void twoAggregatorsWithTestInfoAtTheEnd(@CsvToPerson Person person1, @CsvToPerson Person person2,
				TestInfo testInfo) {
		}

		@ParameterizedTest
		void mixedMode(int num1, int num2, ArgumentsAccessor arguments1, ArgumentsAccessor arguments2,
				@CsvToPerson Person person1, @CsvToPerson Person person2, TestInfo testInfo1, TestInfo testInfo2) {
		}
	}

	@SuppressWarnings("JUnitMalformedDeclaration")
	static class InvalidTestCase {

		@ParameterizedTest
		void twoAggregatorsWithPrimitiveInTheMiddle(@CsvToPerson Person person1, int num, @CsvToPerson Person person2) {
		}

		@ParameterizedTest
		void twoAggregatorsWithTestInfoInTheMiddle(@CsvToPerson Person person1, TestInfo testInfo,
				@CsvToPerson Person person2) {
		}
	}

}
