/**
 * Copyright (c) 2010 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.experiment.lanterna.filebrowser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.google.inject.Inject;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;

/**
 * The panel that shows properties of the currently selected file.
 */
public class FilePropertiesPanel extends Panel implements FileSelectionListener {

	private final Label nameLabel = new Label();

	private final Label sizeLabel = new Label();
	
	private final List<Label> extensionLabels = new ArrayList<>();

	private final List<FileProperty> extensions;

	/**
	 * Constructor.
	 * @param extensions the extensions used to display file properties
	 */
	@Inject
	public FilePropertiesPanel(final Set<FileProperty> extensions) {
		List<FileProperty> extensionsList = new ArrayList<>(extensions);
		this.extensions = extensionsList;
		addComponent(nameLabel);
		addComponent(sizeLabel);
		for (@SuppressWarnings("unused") FileProperty extension : extensionsList) {
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
			FileProperty extension = extensions.get(i);
			Label label = extensionLabels.get(i);
			label.setText(extension.getDisplayName() + ": " + (newSelection == null ? "" : extension.render(newSelection)));
		}
	}

}
