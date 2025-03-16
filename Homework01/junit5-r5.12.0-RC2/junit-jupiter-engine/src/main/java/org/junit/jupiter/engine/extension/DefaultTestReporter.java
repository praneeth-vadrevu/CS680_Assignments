/*
 * Copyright 2015-2025 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package org.junit.jupiter.engine.extension;

import java.nio.file.Path;
import java.util.Map;

import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.MediaType;
import org.junit.jupiter.api.function.ThrowingConsumer;
import org.junit.platform.commons.util.Preconditions;

/**
 * @since 1.12
 */
class DefaultTestReporter implements TestReporter {

	private final ExtensionContext extensionContext;

	DefaultTestReporter(ExtensionContext extensionContext) {
		this.extensionContext = extensionContext;
	}

	@Override
	public void publishEntry(Map<String, String> map) {
		extensionContext.publishReportEntry(map);
	}

	@Override
	public void publishFile(String name, MediaType mediaType, ThrowingConsumer<Path> action) {
		extensionContext.publishFile(name, mediaType, action);
	}

	@Override
	public void publishDirectory(String name, ThrowingConsumer<Path> action) {
		Preconditions.notNull(name, "name must not be null");
		Preconditions.notNull(action, "action must not be null");
		extensionContext.publishDirectory(name, action);
	}
}
