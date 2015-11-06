/**
 * Copyright (c) 2015 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.experiment.lanterna.filebrowser;

import java.io.File;
import name.martingeisse.blob.core.ExtensionPoint;

/**
 *
 */
@ExtensionPoint
public interface FileProperty {

	/**
	 * Getter method for the displayName.
	 * @return the displayName
	 */
	public String getDisplayName();

	/**
	 * Renders the property value for the specified file.
	 * 
	 * @param file the file
	 * @return the rendered property value
	 */
	public String render(File file);

}
