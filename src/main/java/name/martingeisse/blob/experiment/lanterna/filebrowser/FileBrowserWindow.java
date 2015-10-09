/**
 * Copyright (c) 2015 Martin Geisse
 */
package name.martingeisse.blob.experiment.lanterna.filebrowser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import name.martingeisse.blob.core.Extension;
import name.martingeisse.blob.core.PluginSystemClient;
import com.google.common.collect.ImmutableList;
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

	private final Panel mainPanel;
	
	private final FileBrowserListBox listBox;
	
	private final FilePropertiesPanel propertiesPanel;

	private File folder;

	/**
	 * @param pluginSystemClient ...
	 */
	public FileBrowserWindow(PluginSystemClient pluginSystemClient) {
		super("File Browser");
		listBox = new FileBrowserListBox(new TerminalSize(50, 9999));
		propertiesPanel = new FilePropertiesPanel(getFilePropertyExtensions(pluginSystemClient));
		listBox.setSelectionListener(propertiesPanel);
		setFolder(new File("."));

		mainPanel = new Panel(Orientation.HORISONTAL);
		mainPanel.addComponent(listBox);
		mainPanel.addComponent(propertiesPanel);
		addComponent(mainPanel);
	}
	
	private ImmutableList<FilePropertyExtension> getFilePropertyExtensions(PluginSystemClient pluginSystemClient) {
		try {
			Extension[] extensions = pluginSystemClient.getExtensionsForExtensionPoint("name.martingeisse.blob.filebrowser:fileProperty");
			List<FilePropertyExtension> filePropertyExtensions = new ArrayList<>();
			for (Extension extension : extensions) {
				String displayName = extension.getData().get("displayName").getAsString();
				String rendererClassName = extension.getData().get("renderer").getAsString();
				filePropertyExtensions.add(new FilePropertyExtension(displayName, rendererClassName, pluginSystemClient.getExtensionObjectProvider()));
			}
			return ImmutableList.copyOf(filePropertyExtensions);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
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
