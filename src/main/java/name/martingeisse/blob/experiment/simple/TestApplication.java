/**
 * Copyright (c) 2011 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.experiment.simple;

import com.google.inject.Inject;
import name.martingeisse.blob.core.Extension;
import name.martingeisse.blob.core.PluginSystemClient;

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
		System.out.println("--- start ---");
		for (Extension e : pluginSystemClient.getExtensionsForExtensionPoint("test")) {
			System.out.println(e.getData().get("name").getAsString());
		}
	}

}
