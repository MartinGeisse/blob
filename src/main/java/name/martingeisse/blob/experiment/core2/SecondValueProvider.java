/**
 * Copyright (c) 2015 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.experiment.core2;

import name.martingeisse.blob.core2.Extension;

/**
 *
 */
@Extension
public class SecondValueProvider implements ValueProvider {

	// override
	@Override
	public String getValue() {
		return "second";
	}
	
}