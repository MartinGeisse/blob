/**
 * Copyright (c) 2011 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.core;

import java.util.List;
import java.util.Set;

/**
 * Contains data about all installed plugins.
 */
public final class PluginReport {

	private final Set<String> extensionPointIds;
	private final List<Extension> extensions;

	/**
	 * Constructor.
	 * @param extensionPointIds defined extension point IDs
	 * @param extensions all extensions
	 */
	public PluginReport(Set<String> extensionPointIds, List<Extension> extensions) {
		this.extensionPointIds = extensionPointIds;
		this.extensions = extensions;
	}

	/**
	 * Getter method for the extensionPointIds.
	 * @return the extensionPointIds
	 */
	public Set<String> getExtensionPointIds() {
		return extensionPointIds;
	}

	/**
	 * Getter method for the extensions.
	 * @return the extensions
	 */
	public List<Extension> getExtensions() {
		return extensions;
	}

}
