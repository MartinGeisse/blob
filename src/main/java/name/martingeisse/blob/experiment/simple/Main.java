/**
 * Copyright (c) 2011 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.experiment.simple;

import com.google.inject.Guice;
import com.google.inject.Injector;


/**
 *
 */
public class Main {

	/**
	 * The main method.
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		TestApplicationModule module = new TestApplicationModule();
	    Injector injector = Guice.createInjector(module);
	    TestApplication application = injector.getInstance(TestApplication.class);
	    application.run();
	}
	
}
