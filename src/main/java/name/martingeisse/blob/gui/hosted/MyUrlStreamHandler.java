/**
 * Copyright (c) 2015 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.gui.hosted;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.function.BiFunction;
import name.martingeisse.blob.gui.GuiProvider;
import name.martingeisse.blob.gui.UrlMap;

/**
 * Custom URL stream handler implementation that recognizes GUI URLs.
 */
class MyUrlStreamHandler extends URLStreamHandler {

	private final UrlMap urlMap;
	private final BiFunction<GuiProvider, URL, GuiConnection> connectionConstructor;

	/**
	 * Constructor.
	 * @param urlMap the URL map
	 * @param connectionConstructor the constructor for connections
	 */
	public MyUrlStreamHandler(final UrlMap urlMap, final BiFunction<GuiProvider, URL, GuiConnection> connectionConstructor) {
		this.urlMap = urlMap;
		this.connectionConstructor = connectionConstructor;
	}

	// override
	@Override
	protected URLConnection openConnection(final URL url) throws IOException {
		GuiProvider guiProvider = urlMap.resolveUrl(url);
		if (guiProvider == null) {
			throw new IOException("unknown hostname " + url.getHost() + " for URL: " + url);
		}
		return connectionConstructor.apply(guiProvider, url);
	}

}
