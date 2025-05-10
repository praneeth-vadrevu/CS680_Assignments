/*
 * Copyright 2015-2025 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package org.junit.jupiter.engine.descriptor;

import static java.util.stream.Collectors.toList;
import static org.apiguardian.api.API.Status.INTERNAL;
import static org.junit.jupiter.engine.descriptor.ExtensionUtils.populateNewExtensionRegistryFromExtendWithAnnotation;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.apiguardian.api.API;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstances;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;
import org.junit.jupiter.engine.config.JupiterConfiguration;
import org.junit.jupiter.engine.execution.JupiterEngineExecutionContext;
import org.junit.jupiter.engine.extension.ExtensionRegistry;
import org.junit.jupiter.engine.extension.MutableExtensionRegistry;
import org.junit.platform.commons.util.Preconditions;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.UniqueId;

/**
 * {@link TestDescriptor} for {@link org.junit.jupiter.api.TestTemplate @TestTemplate}
 * methods.
 *
 * @since 5.0
 */
@API(status = INTERNAL, since = "5.0")
public class TestTemplateTestDescriptor extends MethodBasedTestDescriptor implements Filterable {

	public static final String SEGMENT_TYPE = "test-template";
	private final DynamicDescendantFilter dynamicDescendantFilter = new DynamicDescendantFilter();

	public TestTemplateTestDescriptor(UniqueId uniqueId, Class<?> testClass, Method templateMethod,
			Supplier<List<Class<?>>> enclosingInstanceTypes, JupiterConfiguration configuration) {
		super(uniqueId, testClass, templateMethod, enclosingInstanceTypes, configuration);
	}

	// --- Filterable ----------------------------------------------------------

	@Override
	public DynamicDescendantFilter getDynamicDescendantFilter() {
		return dynamicDescendantFilter;
	}

	// --- TestDescriptor ------------------------------------------------------

	@Override
	public Type getType() {
		return Type.CONTAINER;
	}

	@Override
	public boolean mayRegisterTests() {
		return true;
	}

	// --- Node ----------------------------------------------------------------

	@Override
	public JupiterEngineExecutionContext prepare(JupiterEngineExecutionContext context) {
		MutableExtensionRegistry registry = populateNewExtensionRegistryFromExtendWithAnnotation(
			context.getExtensionRegistry(), getTestMethod());

		// The test instance should be properly maintained by the enclosing class's ExtensionContext.
		TestInstances testInstances = context.getExtensionContext().getTestInstances().orElse(null);

		ExtensionContext extensionContext = new TestTemplateExtensionContext(context.getExtensionContext(),
			context.getExecutionListener(), this, context.getConfiguration(), registry, testInstances);

		// @formatter:off
		return context.extend()
				.withExtensionRegistry(registry)
				.withExtensionContext(extensionContext)
				.build();
		// @formatter:on
	}

	@Override
	public JupiterEngineExecutionContext execute(JupiterEngineExecutionContext context,
			DynamicTestExecutor dynamicTestExecutor) throws Exception {

		ExtensionContext extensionContext = context.getExtensionContext();
		List<TestTemplateInvocationContextProvider> providers = validateProviders(extensionContext,
			context.getExtensionRegistry());
		AtomicInteger invocationIndex = new AtomicInteger();
		for (TestTemplateInvocationContextProvider provider : providers) {
			executeForProvider(provider, invocationIndex, dynamicTestExecutor, extensionContext);
		}
		return context;
	}

	private void executeForProvider(TestTemplateInvocationContextProvider provider, AtomicInteger invocationIndex,
			DynamicTestExecutor dynamicTestExecutor, ExtensionContext extensionContext) {

		int initialValue = invocationIndex.get();

		try (Stream<TestTemplateInvocationContext> stream = invocationContexts(provider, extensionContext)) {
			stream.forEach(invocationContext -> toTestDescriptor(invocationContext, invocationIndex.incrementAndGet()) //
					.ifPresent(testDescriptor -> execute(dynamicTestExecutor, testDescriptor)));
		}

		Preconditions.condition(
			invocationIndex.get() != initialValue
					|| provider.mayReturnZeroTestTemplateInvocationContexts(extensionContext),
			String.format(
				"Provider [%s] did not provide any invocation contexts, but was expected to do so. "
						+ "You may override mayReturnZeroTestTemplateInvocationContexts() to allow this.",
				provider.getClass().getSimpleName()));
	}

	private static Stream<TestTemplateInvocationContext> invocationContexts(
			TestTemplateInvocationContextProvider provider, ExtensionContext extensionContext) {
		return provider.provideTestTemplateInvocationContexts(extensionContext);
	}

	private List<TestTemplateInvocationContextProvider> validateProviders(ExtensionContext extensionContext,
			ExtensionRegistry extensionRegistry) {

		// @formatter:off
		List<TestTemplateInvocationContextProvider> providers = extensionRegistry.stream(TestTemplateInvocationContextProvider.class)
				.filter(provider -> provider.supportsTestTemplate(extensionContext))
				.collect(toList());
		// @formatter:on

		return Preconditions.notEmpty(providers,
			() -> String.format("You must register at least one %s that supports @TestTemplate method [%s]",
				TestTemplateInvocationContextProvider.class.getSimpleName(), getTestMethod()));
	}

	private Optional<TestDescriptor> toTestDescriptor(TestTemplateInvocationContext invocationContext, int index) {
		UniqueId uniqueId = getUniqueId().append(TestTemplateInvocationTestDescriptor.SEGMENT_TYPE, "#" + index);
		if (getDynamicDescendantFilter().test(uniqueId, index - 1)) {
			return Optional.of(new TestTemplateInvocationTestDescriptor(uniqueId, getTestClass(), getTestMethod(),
				invocationContext, index, configuration));
		}
		return Optional.empty();
	}

	private void execute(DynamicTestExecutor dynamicTestExecutor, TestDescriptor testDescriptor) {
		testDescriptor.setParent(this);
		dynamicTestExecutor.execute(testDescriptor);
	}
}
