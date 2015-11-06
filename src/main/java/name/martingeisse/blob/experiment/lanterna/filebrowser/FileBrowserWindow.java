/**
 * Copyright (c) 2015 Martin Geisse
 */
package name.martingeisse.blob.experiment.lanterna.filebrowser;

import java.io.File;
import com.google.inject.Inject;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Panel;
import com.googlecode.lanterna.gui.component.Panel.Orientation;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.Key.Kind;
import com.googlecode.lanterna.terminal.TerminalSize;

/**
 *
 */
public class FileBrowserWindow extends Window {

	private final FileBrowserListBox listBox;

	private final Panel mainPanel;
		
	private File folder;

	/**
	 * @param filePropertiesPanel ...
	 */
	@Inject
	public FileBrowserWindow(FilePropertiesPanel filePropertiesPanel) {
		super("File Browser");
		
		listBox = new FileBrowserListBox(new TerminalSize(50, 9999));
		listBox.setSelectionListener(filePropertiesPanel);
		
		mainPanel = new Panel(Orientation.HORISONTAL);
		mainPanel.addComponent(listBox);
		mainPanel.addComponent(filePropertiesPanel);
		addComponent(mainPanel);
		
		setFolder(new File("."));
	}
	
	// override
	@Override
	public void onKeyPressed(final Key key) {
		if (key.getKind() == Kind.F12) {
			close();
		} else {
			super.onKeyPressed(key);
		}
	}
	
	/**
	 * Getter method for the folder.
	 * @return the folder
	 */
	public File getFolder() {
		return folder;
	}

	/**
	 * Setter method for the folder.
	 * @param folder the folder to set
	 */
	public void setFolder(final File folder) {
		this.folder = folder;
		listBox.setRootFolder(folder);
		listBox.populate();
	}

}
