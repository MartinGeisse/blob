/**
 * Copyright (c) 2010 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.experiment.lanterna.filebrowser;

import java.io.File;
import name.martingeisse.blob.core.ExtensionObjectProvider;
import name.martingeisse.common.util.ParameterUtil;

/**
 * Contains the data from an extension to display file properties.
 */
public final class FilePropertyExtension {

	private final String displayName;

	private final String rendererClassName;
	
	private final ExtensionObjectProvider extensionObjectProvider;

	/**
	 * Constructor.
	 * @param displayName the displayed name
	 * @param rendererClassName the name of the class for the renderer
	 * @param extensionObjectProvider the extension object provider
	 */
	public FilePropertyExtension(final String displayName, final String rendererClassName, ExtensionObjectProvider extensionObjectProvider) {
		this.displayName = ParameterUtil.ensureNotNull(displayName, "displayName");
		this.rendererClassName = ParameterUtil.ensureNotNull(rendererClassName, "rendererClassName");
		this.extensionObjectProvider = ParameterUtil.ensureNotNull(extensionObjectProvider, "extensionObjectProvider");
	}

	/**
	 * Getter method for the displayName.
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Getter method for the rendererClassName.
	 * @return the rendererClassName
	 */
	public String getRendererClassName() {
		return rendererClassName;
	}
	
	/**
	 * Renders the label text for the specified file.
	 * 
	 * @param file the file
	 * @return the label text
	 */
	public String renderLabel(File file) {
		Renderer renderer = extensionObjectProvider.create(rendererClassName, Renderer.class);
		return displayName + ": " + (file == null ? "" : renderer.render(file));
	}

	/**
	 * The renderer for a file property.
	 */
	public static interface Renderer {

		/**
		 * Renders the property value for the specified file.
		 * 
		 * @param file the file
		 * @return the rendered property value
		 */
		public String render(File file);

	}

}
