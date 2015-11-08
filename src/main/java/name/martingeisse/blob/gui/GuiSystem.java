/**
 * Copyright (c) 2015 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.gui;


/**
 * This is the main entry point for applications that have a GUI. Inject this object,
 * then use it to show GUI windows.
 */
public interface GuiSystem {

	/**
	 * Opens a window.
	 * 
	 * @param specification a specification object that describes the window to open
	 */
	public void openWindow(WindowSpecification specification);
	
}
