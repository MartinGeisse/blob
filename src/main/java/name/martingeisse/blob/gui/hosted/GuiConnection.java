/**
 * Copyright (c) 2015 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.gui.hosted;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import name.martingeisse.blob.gui.GuiProvider;
import name.martingeisse.blob.gui.GuiRequestCycle;

/**
 *
 */
public abstract class GuiConnection extends HttpURLConnection {

	private final GuiProvider guiProvider;
	private final PipedInputStream requestPipedInputStream;
	private final PipedOutputStream requestPipedOutputStream;
	private final PipedInputStream responsePipedInputStream;
	private final PipedOutputStream responsePipedOutputStream;

	GuiConnection(final GuiProvider guiProvider, final URL url) {
		super(url);
		this.guiProvider = guiProvider;
		this.requestPipedInputStream = new PipedInputStream();
		this.requestPipedOutputStream = new PipedOutputStream();
		this.responsePipedInputStream = new PipedInputStream();
		this.responsePipedOutputStream = new PipedOutputStream();
	}

	// override
	@Override
	public OutputStream getOutputStream() throws IOException {
		return requestPipedOutputStream;
	}

	// override
	@Override
	public InputStream getInputStream() throws IOException {
		return responsePipedInputStream;
	}

	// override
	@Override
	public void connect() throws IOException {
		if (connected) {
			return;
		}
		requestPipedOutputStream.connect(requestPipedInputStream);
		responsePipedOutputStream.connect(responsePipedInputStream);
		guiProvider.handle(new RequestCycle());
		connected = true;
	}
	
	// override
	@Override
	public String getHeaderField(int n) {
		return (n == 0 ? "HTTP/1.0 200 OK" : null);
	}
	
	// override
	@Override
	public boolean getDoInput() {
		return true;
	}
	
	// override
	@Override
	public boolean getDoOutput() {
		return true;
	}

	// override
	@Override
	public void disconnect() {
		try {
			requestPipedInputStream.close();
		} catch (final IOException e) {
		}
		try {
			requestPipedOutputStream.close();
		} catch (final IOException e) {
		}
		try {
			requestPipedInputStream.close();
		} catch (final IOException e) {
		}
		try {
			responsePipedOutputStream.close();
		} catch (final IOException e) {
		}
		connected = false;
	}

	// override
	@Override
	public boolean usingProxy() {
		return false;
	}

	private class RequestCycle implements GuiRequestCycle {

		// override
		@Override
		public URL getUrl() {
			return GuiConnection.this.getURL();
		}

		// override
		@Override
		public String getRequestMethod() {
			return GuiConnection.this.getRequestMethod().toUpperCase();
		}

		// override
		@Override
		public InputStream getRequestBodyStream() {
			return requestPipedInputStream;
		}

		// override
		@Override
		public OutputStream getResponseBodyStream() {
			return responsePipedOutputStream;
		}

	}

}
