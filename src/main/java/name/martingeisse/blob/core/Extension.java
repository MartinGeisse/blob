/**
 * Copyright (c) 2015 Martin Geisse
 */

package name.martingeisse.blob.core;

import com.google.gson.JsonObject;

/**
 * An extension to the system.
 */
public final class Extension {

	private final String extensionPointId;
	private final JsonObject data;

	/**
	 * Constructor.
	 * @param extensionPointId the ID of the extension point targeted by this extension
	 * @param data the extension data
	 */
	Extension(String extensionPointId, JsonObject data) {
		this.extensionPointId = extensionPointId;
		this.data = data;
	}

	/**
	 * Gets the ID of the extension point targeted by this extension.
	 *
	 * @return the extension point ID
	 */
	public String getExtensionPointId() {
		return extensionPointId;
	}

	/**
	 * Gets the data for this extension.
	 *
	 * @return the extension data
	 */
	public JsonObject getData() {
		return data;
	}

}
