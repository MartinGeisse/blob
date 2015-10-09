/**
 * Copyright (c) 2010 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.experiment.lanterna.filebrowser;

import java.io.File;
import name.martingeisse.blob.experiment.lanterna.filebrowser.FilePropertyExtension.Renderer;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 
 */
public class LastModifiedRenderer implements Renderer {

	private static final DateTimeFormatter formatter = DateTimeFormat.mediumDateTime();
	
	/* (non-Javadoc)
	 * @see name.martingeisse.blob.experiment.lanterna.filebrowser.FilePropertyExtension.Renderer#render(java.io.File)
	 */
	@Override
	public String render(final File file) {
		return formatter.print(file.lastModified());
	}

}
