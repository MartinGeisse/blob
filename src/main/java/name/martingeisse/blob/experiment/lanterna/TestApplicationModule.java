/**
 * Copyright (c) 2011 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.experiment.lanterna;

import name.martingeisse.blob.core.ExtensionObjectProvider;
import name.martingeisse.blob.core.PluginSystemClient;
import name.martingeisse.blob.core2.Plugin;

/**
 *
 */
public class TestApplicationModule extends Plugin {

	// override
	@Override
	protected void configure() {
		super.configure();
		bind(ExtensionObjectProvider.class);
		bind(PluginSystemClient.class);
	}

}
