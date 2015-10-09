/**
 * Copyright (c) 2011 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.experiment.lanterna;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 *
 */
public class Main {

	/**
	 * The main method.
	 * 
	 * @param args command-line arguments
	 *            
	 * @throws Exception ...
	 */
	public static void main(final String[] args) throws Exception {
		final TestApplicationModule module = new TestApplicationModule();
		final Injector injector = Guice.createInjector(module);
		final TestApplication application = injector.getInstance(TestApplication.class);
		application.run();
	}

}
