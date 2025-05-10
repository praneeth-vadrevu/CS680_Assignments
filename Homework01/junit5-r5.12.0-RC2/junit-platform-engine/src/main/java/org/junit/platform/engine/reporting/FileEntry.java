/*
 * Copyright 2015-2025 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package org.junit.platform.engine.reporting;

import static org.apiguardian.api.API.Status.EXPERIMENTAL;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Optional;

import org.apiguardian.api.API;
import org.junit.platform.commons.util.Preconditions;
import org.junit.platform.commons.util.ToStringBuilder;

/**
 * {@code FileEntry} encapsulates a file or directory to be published to the
 * reporting infrastructure.
 *
 * @since 1.12
 * @see #from(Path, String)
 */
@API(status = EXPERIMENTAL, since = "1.12")
public final class FileEntry {

	/**
	 * Factory for creating a new {@code FileEntry} from the supplied path and
	 * media type.
	 *
	 * @param path the path to publish; never {@code null}
	 * @param mediaType the media type of the path to publish; may be
	 * {@code null}
	 */
	public static FileEntry from(Path path, String mediaType) {
		return new FileEntry(path, mediaType);
	}

	private final LocalDateTime timestamp = LocalDateTime.now();
	private final Path path;
	private final String mediaType;

	private FileEntry(Path path, String mediaType) {
		this.path = Preconditions.notNull(path, "path must not be null");
		this.mediaType = mediaType;
	}

	/**
	 * Get the timestamp for when this {@code FileEntry} was created.
	 *
	 * @return when this entry was created; never {@code null}
	 */
	public LocalDateTime getTimestamp() {
		return this.timestamp;
	}

	/**
	 * Get the path to be published.
	 *
	 * @return the path to publish; never {@code null}
	 */
	public Path getPath() {
		return path;
	}

	/**
	 * Get the media type of the path to be published.
	 *
	 * @return the media type of the path to publish; never {@code null}
	 */
	public Optional<String> getMediaType() {
		return Optional.ofNullable(mediaType);
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("timestamp", this.timestamp);
		builder.append("path", this.path);
		if (this.mediaType != null) {
			builder.append("mediaType", this.mediaType);
		}
		return builder.toString();
	}

}
