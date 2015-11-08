/**
 * Copyright (c) 2015 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.gui;

import java.net.URL;

/**
 * Finds an appropriate {@link GuiProvider} for an URL.
 */
public interface UrlMap {

	/**
	 * Resolves the specified URL.
	 * 
	 * @param url the URL
	 * @return the GUI provider for the URL
	 */
	public GuiProvider resolveUrl(URL url);
	
}
