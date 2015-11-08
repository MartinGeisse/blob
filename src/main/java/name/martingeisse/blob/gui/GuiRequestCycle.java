/**
 * Copyright (c) 2015 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.gui;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * An object that contains all information regarding a request from the GUI system
 * to application code.
 */
public interface GuiRequestCycle {

	/**
	 * Returns the URL.
	 * @return the URL
	 */
	public URL getUrl();
	
	/**
	 * Returns the request method. The request methods defined for HTTP are used for GUI
	 * requests. This method always returns an all-uppercase string.
	 * 
	 * @return the request method
	 */
	public String getRequestMethod();
	
	/**
	 * @return the input stream for the request body
	 */
	public InputStream getRequestBodyStream();
	
	/**
	 * @return the output stream for the response body
	 */
	public OutputStream getResponseBodyStream();
	
}
