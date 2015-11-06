/**
 * Copyright (c) 2015 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.experiment.simple;

import java.util.Set;
import com.google.inject.Inject;

/**
 *
 */
public class MyRunnable {

	private final Set<ValueProvider> valueProviders;

	/**
	 * Constructor.
	 * @param valueProviders the value providers
	 */
	@Inject
	public MyRunnable(final Set<ValueProvider> valueProviders) {
		this.valueProviders = valueProviders;
	}

	/**
	 * 
	 */
	public void run() {
		for (final ValueProvider valueProvider : valueProviders) {
			System.out.println("* " + valueProvider.getValue());
		}
	}

}
