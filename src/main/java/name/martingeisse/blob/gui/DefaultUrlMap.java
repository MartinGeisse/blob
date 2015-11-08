/**
 * Copyright (c) 2015 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.gui;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.google.inject.Inject;

/**
 * The default implementation for {@link UrlMap}.
 */
public class DefaultUrlMap implements UrlMap {

	private final Map<String, GuiProvider> guiProviders = new HashMap<>();
	
	/**
	 * Constructor.
	 * @param injectedGuiProviders the GUI providers
	 */
	@Inject
	public DefaultUrlMap(Set<GuiProvider> injectedGuiProviders) {
		for (GuiProvider guiProvider : injectedGuiProviders) {
			guiProviders.put(guiProvider.getHostname(), guiProvider);
		}
	}

	// override
	@Override
	public GuiProvider resolveUrl(final URL url) {
		return guiProviders.get(url.getHost());
	}

}
