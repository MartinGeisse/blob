/**
 * Copyright (c) 2010 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.experiment.lanterna.filebrowser;

import java.io.File;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import name.martingeisse.blob.core.Extension;

/**
 *
 */
@Extension
public class LastModifiedProperty implements FileProperty {

	private static final DateTimeFormatter formatter = DateTimeFormat.mediumDateTime();

	// override
	@Override
	public String getDisplayName() {
		return "Last Modified";
	}

	/* (non-Javadoc)
	 * @see name.martingeisse.blob.experiment.lanterna.filebrowser.FilePropertyExtension.Renderer#render(java.io.File)
	 */
	@Override
	public String render(final File file) {
		return formatter.print(file.lastModified());
	}

}
