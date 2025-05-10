/*
 * Copyright 2015-2025 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package org.junit.jupiter.engine.config;

import static org.apiguardian.api.API.Status.INTERNAL;
import static org.junit.jupiter.api.io.CleanupMode.ALWAYS;
import static org.junit.jupiter.api.io.TempDir.DEFAULT_CLEANUP_MODE_PROPERTY_NAME;
import static org.junit.jupiter.api.io.TempDir.DEFAULT_FACTORY_PROPERTY_NAME;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.apiguardian.api.API;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.TestInstantiationAwareExtension.ExtensionContextScope;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDirFactory;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.platform.commons.util.ClassNamePatternFilterUtils;
import org.junit.platform.commons.util.Preconditions;
import org.junit.platform.engine.ConfigurationParameters;
import org.junit.platform.engine.reporting.OutputDirectoryProvider;

/**
 * Default implementation of the {@link JupiterConfiguration} API.
 *
 * @since 5.4
 */
@API(status = INTERNAL, since = "5.4")
public class DefaultJupiterConfiguration implements JupiterConfiguration {

	private static final EnumConfigurationParameterConverter<ExecutionMode> executionModeConverter = //
		new EnumConfigurationParameterConverter<>(ExecutionMode.class, "parallel execution mode");

	private static final EnumConfigurationParameterConverter<Lifecycle> lifecycleConverter = //
		new EnumConfigurationParameterConverter<>(Lifecycle.class, "test instance lifecycle mode");

	private static final InstantiatingConfigurationParameterConverter<DisplayNameGenerator> displayNameGeneratorConverter = //
		new InstantiatingConfigurationParameterConverter<>(DisplayNameGenerator.class, "display name generator");

	private static final InstantiatingConfigurationParameterConverter<MethodOrderer> methodOrdererConverter = //
		new InstantiatingConfigurationParameterConverter<>(MethodOrderer.class, "method orderer");

	private static final InstantiatingConfigurationParameterConverter<ClassOrderer> classOrdererConverter = //
		new InstantiatingConfigurationParameterConverter<>(ClassOrderer.class, "class orderer");

	private static final EnumConfigurationParameterConverter<CleanupMode> cleanupModeConverter = //
		new EnumConfigurationParameterConverter<>(CleanupMode.class, "cleanup mode");

	private static final InstantiatingConfigurationParameterConverter<TempDirFactory> tempDirFactoryConverter = //
		new InstantiatingConfigurationParameterConverter<>(TempDirFactory.class, "temp dir factory");

	private static final EnumConfigurationParameterConverter<ExtensionContextScope> extensionContextScopeConverter = //
		new EnumConfigurationParameterConverter<>(ExtensionContextScope.class, "extension context scope");

	private final ConfigurationParameters configurationParameters;
	private final OutputDirectoryProvider outputDirectoryProvider;

	public DefaultJupiterConfiguration(ConfigurationParameters configurationParameters,
			OutputDirectoryProvider outputDirectoryProvider) {
		this.configurationParameters = Preconditions.notNull(configurationParameters,
			"ConfigurationParameters must not be null");
		this.outputDirectoryProvider = outputDirectoryProvider;
	}

	@Override
	public Predicate<Class<? extends Extension>> getFilterForAutoDetectedExtensions() {
		String includePattern = getExtensionAutoDetectionIncludePattern();
		String excludePattern = getExtensionAutoDetectionExcludePattern();
		Predicate<String> predicate = ClassNamePatternFilterUtils.includeMatchingClassNames(includePattern) //
				.and(ClassNamePatternFilterUtils.excludeMatchingClassNames(excludePattern));
		return clazz -> predicate.test(clazz.getName());
	}

	private String getExtensionAutoDetectionIncludePattern() {
		return configurationParameters.get(EXTENSIONS_AUTODETECTION_INCLUDE_PROPERTY_NAME) //
				.orElse(ClassNamePatternFilterUtils.ALL_PATTERN);
	}

	private String getExtensionAutoDetectionExcludePattern() {
		return configurationParameters.get(EXTENSIONS_AUTODETECTION_EXCLUDE_PROPERTY_NAME) //
				.orElse(ClassNamePatternFilterUtils.BLANK);
	}

	@Override
	public Optional<String> getRawConfigurationParameter(String key) {
		return configurationParameters.get(key);
	}

	@Override
	public <T> Optional<T> getRawConfigurationParameter(String key, Function<String, T> transformer) {
		return configurationParameters.get(key, transformer);
	}

	@Override
	public boolean isParallelExecutionEnabled() {
		return configurationParameters.getBoolean(PARALLEL_EXECUTION_ENABLED_PROPERTY_NAME).orElse(false);
	}

	@Override
	public boolean isExtensionAutoDetectionEnabled() {
		return configurationParameters.getBoolean(EXTENSIONS_AUTODETECTION_ENABLED_PROPERTY_NAME).orElse(false);
	}

	@Override
	public boolean isThreadDumpOnTimeoutEnabled() {
		return configurationParameters.getBoolean(EXTENSIONS_TIMEOUT_THREAD_DUMP_ENABLED_PROPERTY_NAME).orElse(false);
	}

	@Override
	public ExecutionMode getDefaultExecutionMode() {
		return executionModeConverter.get(configurationParameters, DEFAULT_EXECUTION_MODE_PROPERTY_NAME,
			ExecutionMode.SAME_THREAD);
	}

	@Override
	public ExecutionMode getDefaultClassesExecutionMode() {
		return executionModeConverter.get(configurationParameters, DEFAULT_CLASSES_EXECUTION_MODE_PROPERTY_NAME,
			getDefaultExecutionMode());
	}

	@Override
	public Lifecycle getDefaultTestInstanceLifecycle() {
		return lifecycleConverter.get(configurationParameters, DEFAULT_TEST_INSTANCE_LIFECYCLE_PROPERTY_NAME,
			Lifecycle.PER_METHOD);
	}

	@Override
	public Predicate<ExecutionCondition> getExecutionConditionFilter() {
		return ClassNamePatternFilterUtils.excludeMatchingClasses(
			configurationParameters.get(DEACTIVATE_CONDITIONS_PATTERN_PROPERTY_NAME).orElse(null));
	}

	@Override
	public DisplayNameGenerator getDefaultDisplayNameGenerator() {
		return displayNameGeneratorConverter.get(configurationParameters, DEFAULT_DISPLAY_NAME_GENERATOR_PROPERTY_NAME) //
				.orElseGet(() -> DisplayNameGenerator.getDisplayNameGenerator(DisplayNameGenerator.Standard.class));
	}

	@Override
	public Optional<MethodOrderer> getDefaultTestMethodOrderer() {
		return methodOrdererConverter.get(configurationParameters, DEFAULT_TEST_METHOD_ORDER_PROPERTY_NAME);
	}

	@Override
	public Optional<ClassOrderer> getDefaultTestClassOrderer() {
		return classOrdererConverter.get(configurationParameters, DEFAULT_TEST_CLASS_ORDER_PROPERTY_NAME);
	}

	@Override
	public CleanupMode getDefaultTempDirCleanupMode() {
		return cleanupModeConverter.get(configurationParameters, DEFAULT_CLEANUP_MODE_PROPERTY_NAME, ALWAYS);
	}

	@Override
	public Supplier<TempDirFactory> getDefaultTempDirFactorySupplier() {
		Supplier<Optional<TempDirFactory>> supplier = tempDirFactoryConverter.supply(configurationParameters,
			DEFAULT_FACTORY_PROPERTY_NAME);
		return () -> supplier.get().orElse(TempDirFactory.Standard.INSTANCE);
	}

	@SuppressWarnings("deprecation")
	@Override
	public ExtensionContextScope getDefaultTestInstantiationExtensionContextScope() {
		return extensionContextScopeConverter.get(configurationParameters,
			DEFAULT_TEST_INSTANTIATION_EXTENSION_CONTEXT_SCOPE_PROPERTY_NAME, ExtensionContextScope.DEFAULT);
	}

	@Override
	public OutputDirectoryProvider getOutputDirectoryProvider() {
		return outputDirectoryProvider;
	}
}
