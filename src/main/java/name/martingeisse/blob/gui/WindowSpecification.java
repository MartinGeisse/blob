/**
 * Copyright (c) 2015 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.gui;

/**
 * Describes a GUI window.
 */
public final class WindowSpecification {

	private final String url;

	/**
	 * Constructor.
	 * @param url the URL to open
	 */
	public WindowSpecification(final String url) {
		this.url = url;
	}

	/**
	 * Getter method for the url.
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	
}
