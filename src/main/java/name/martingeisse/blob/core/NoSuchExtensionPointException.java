/**
 * Copyright (c) 2011 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.core;

/**
 * This exception type gets thrown when trying to access a non-existing extension point.
 */
public class NoSuchExtensionPointException extends RuntimeException {

	/**
	 * Constructor.
	 * @param extensionPointId the ID of the extension point that wasn't found
	 */
	public NoSuchExtensionPointException(String extensionPointId) {
		super("extension point not found: " + extensionPointId);
	}

}
