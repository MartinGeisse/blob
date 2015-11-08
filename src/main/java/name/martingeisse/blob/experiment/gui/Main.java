/**
 * Copyright (c) 2015 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.experiment.gui;

import com.google.inject.Guice;
import com.google.inject.Injector;
import name.martingeisse.blob.core.Plugin;
import name.martingeisse.blob.gui.DefaultUrlMap;
import name.martingeisse.blob.gui.GuiSystem;
import name.martingeisse.blob.gui.UrlMap;
import name.martingeisse.blob.gui.WindowSpecification;
import name.martingeisse.blob.gui.hosted.JavaFxGuiSystem;

/**
 *
 */
public class Main extends Plugin {

	/**
	 * The main method.
	 * @param args command-line arguments
	 */
	public static void main(final String[] args) {
		final Main module = new Main();
		final Injector injector = Guice.createInjector(module);
		final GuiSystem guiSystem = injector.getInstance(GuiSystem.class);
		guiSystem.openWindow(new WindowSpecification("public-gui://foobar/wurst"));
	}

	// override
	@Override
	protected void configure() {
		super.configure();
	    bind(GuiSystem.class).to(JavaFxGuiSystem.class);
	    bind(UrlMap.class).to(DefaultUrlMap.class);
	}

}
