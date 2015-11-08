/**
 * Copyright (c) 2015 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.gui.hosted;

import java.net.URL;
import name.martingeisse.blob.gui.GuiProvider;

/**
 *
 */
public class PrivateGuiConnection extends GuiConnection {

	PrivateGuiConnection(GuiProvider guiProvider, final URL url) {
		super(guiProvider, url);
	}

}
