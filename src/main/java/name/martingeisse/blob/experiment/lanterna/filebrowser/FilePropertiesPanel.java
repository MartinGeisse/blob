/**
 * Copyright (c) 2010 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.experiment.lanterna.filebrowser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.google.common.collect.ImmutableList;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;

/**
 * The panel that shows properties of the currently selected file.
 */
public class FilePropertiesPanel extends Panel implements FileSelectionListener {

	private final Label nameLabel = new Label();

	private final Label sizeLabel = new Label();
	
	private final List<Label> extensionLabels = new ArrayList<>();

	private final ImmutableList<FilePropertyExtension> extensions;

	/**
	 * Constructor.
	 * @param extensions the extensions used to display file properties
	 */
	public FilePropertiesPanel(final ImmutableList<FilePropertyExtension> extensions) {
		this.extensions = extensions;
		addComponent(nameLabel);
		addComponent(sizeLabel);
		for (@SuppressWarnings("unused") FilePropertyExtension extension : extensions) {
			Label label = new Label();
			extensionLabels.add(label);
			addComponent(label);
		}
	}

	/* (non-Javadoc)
	 * @see name.martingeisse.blob.experiment.lanterna.filebrowser.FileSelectionListener#onFileSelectionChanged(java.io.File, java.io.File)
	 */
	@Override
	public void onFileSelectionChanged(final File previousSelection, final File newSelection) {
		if (newSelection == null) {
			nameLabel.setText("");
			sizeLabel.setText("");
		} else {
			nameLabel.setText("Name: " + newSelection.getName());
			sizeLabel.setText("Size: " + newSelection.length());
		}
		for (int i=0; i<extensions.size(); i++) {
			FilePropertyExtension extension = extensions.get(i);
			Label label = extensionLabels.get(i);
			label.setText(extension.renderLabel(newSelection));
		}
	}

}
