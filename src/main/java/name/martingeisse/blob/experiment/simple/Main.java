/**
 * Copyright (c) 2015 Martin Geisse
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
		TestApplication application = new TestApplication();
	    Injector injector = Guice.createInjector(application);
	    MyRunnable myRunnable = injector.getInstance(MyRunnable.class);
	    myRunnable.run();
	}

}
