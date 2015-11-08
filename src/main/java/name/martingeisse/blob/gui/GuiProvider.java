/**
 * Copyright (c) 2015 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.gui;

import java.io.IOException;
import name.martingeisse.blob.core.ExtensionPoint;

/**
 * This is an extension point for modules that want to offer a GUI.
 */
@ExtensionPoint
public interface GuiProvider {

	/**
	 * Getter method for the "hostname" used to connect to the GUI. Each GUI
	 * provider must have a unique hostname.
	 * 
	 * @return the hostname
	 */
	public String getHostname();
	
	/**
	 * Handles a request cycle.
	 * 
	 * @param requestCycle the request cycle
	 * @throws IOException on I/O errors
	 */
	public void handle(GuiRequestCycle requestCycle) throws IOException;
	
}
