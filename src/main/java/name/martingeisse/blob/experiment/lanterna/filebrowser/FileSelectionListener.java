/**
 * Copyright (c) 2010 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.experiment.lanterna.filebrowser;

import java.io.File;

/**
 *
 */
public interface FileSelectionListener {

	/**
	 * Called when the current file selection changes.
	 * 
	 * @param previousSelection the previous selection
	 * @param newSelection the new selection
	 */
	public void onFileSelectionChanged(File previousSelection, File newSelection);
	
}
