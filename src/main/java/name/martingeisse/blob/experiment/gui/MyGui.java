/**
 * Copyright (c) 2015 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.experiment.gui;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import name.martingeisse.blob.core.Extension;
import name.martingeisse.blob.gui.GuiProvider;
import name.martingeisse.blob.gui.GuiRequestCycle;

/**
 *
 */
@Extension
public class MyGui implements GuiProvider {

	// override
	@Override
	public String getHostname() {
		return "foobar";
	}

	// override
	@Override
	public void handle(final GuiRequestCycle requestCycle) throws IOException {
		OutputStreamWriter writer = new OutputStreamWriter(requestCycle.getResponseBodyStream(), StandardCharsets.UTF_8);
		writer.write("Hello world!");
		writer.flush();
		requestCycle.getResponseBodyStream().flush();
	}

}
