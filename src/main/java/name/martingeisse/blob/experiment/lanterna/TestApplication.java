/**
 * Copyright (c) 2011 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.experiment.lanterna;

import name.martingeisse.blob.core.PluginSystemClient;
import name.martingeisse.blob.experiment.lanterna.filebrowser.FileBrowserWindow;
import com.google.inject.Inject;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

/**
 *
 */
public class TestApplication {

	private final PluginSystemClient pluginSystemClient;

	/**
	 * Constructor.
	 * @param pluginSystemClient the plugin system client
	 */
	@Inject
	public TestApplication(PluginSystemClient pluginSystemClient) {
		this.pluginSystemClient = pluginSystemClient;
	}

	/**
	 *
	 */
	public void run() {
		final Terminal terminal = new MySwingTerminal(100, 30);
		final Screen screen = new Screen(terminal);
		final GUIScreen gui = new GUIScreen(screen);
		gui.getScreen().startScreen();
		gui.showWindow(new FileBrowserWindow(pluginSystemClient), GUIScreen.Position.FULL_SCREEN);
		gui.getScreen().stopScreen();
		
//		System.out.println("--- start ---");
//		for (Extension e : pluginSystemClient.getExtensionsForExtensionPoint("test")) {
//			System.out.println(e.getData().get("name").getAsString());
//		}
	}

}
