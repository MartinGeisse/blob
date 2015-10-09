/**
 * Copyright (c) 2011 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.experiment.lanterna;

import name.martingeisse.blob.core.ExtensionObjectProvider;
import name.martingeisse.blob.core.PluginSystemClient;
import com.google.inject.AbstractModule;

/**
 *
 */
public class TestApplicationModule extends AbstractModule {

	// override
	@Override
	protected void configure() {
		bind(ExtensionObjectProvider.class);
		bind(PluginSystemClient.class);
	}

}
