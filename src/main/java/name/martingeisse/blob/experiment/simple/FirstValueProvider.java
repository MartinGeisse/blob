/**
 * Copyright (c) 2015 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.experiment.simple;

import name.martingeisse.blob.core.Extension;

/**
 *
 */
@Extension
public class FirstValueProvider implements ValueProvider {

	// override
	@Override
	public String getValue() {
		return "first";
	}
	
}
