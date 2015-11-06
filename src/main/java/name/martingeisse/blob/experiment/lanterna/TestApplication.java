/**
 * Copyright (c) 2011 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.experiment.lanterna;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import name.martingeisse.blob.experiment.lanterna.filebrowser.FileBrowserWindow;

/**
 *
 */
public class TestApplication {

	private final Provider<FileBrowserWindow> fileBrowserWindowProvider;
	
	/**
	 * Constructor.
	 * @param fileBrowserWindowProvider ...
	 */
	@Inject
	public TestApplication(Provider<FileBrowserWindow> fileBrowserWindowProvider) {
		this.fileBrowserWindowProvider = fileBrowserWindowProvider;
	}

	/**
	 *
	 */
	public void run() {
		final Terminal terminal = new MySwingTerminal(100, 30);
		final Screen screen = new Screen(terminal);
		final GUIScreen gui = new GUIScreen(screen);
		gui.getScreen().startScreen();
		gui.showWindow(fileBrowserWindowProvider.get(), GUIScreen.Position.FULL_SCREEN);
		gui.getScreen().stopScreen();
	}

}
