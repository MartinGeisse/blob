/**
 * Copyright (c) 2011 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.inject.Inject;

/**
 * The client that gives access to the plugin system.
 */
public final class PluginSystemClient {

	private static final Gson gson = new GsonBuilder().create();

	private final PluginReport pluginReport;

	private final ExtensionObjectProvider extensionObjectProvider;

	/**
	 * Constructor.
	 * @param extensionObjectProvider the provider for extension objects
	 */
	@Inject
	public PluginSystemClient(final ExtensionObjectProvider extensionObjectProvider) {
		final Set<String> extensionPointIds = new HashSet<>();
		final List<Extension> extensions = new ArrayList<>();
		for (final File pluginFolder : new File("resource/plugins").listFiles()) {
			if (!pluginFolder.isDirectory()) {
				throw new RuntimeException("'plugins' folder contains non-folder resource: " + pluginFolder);
			}
			final String pluginId = pluginFolder.getName();
			final File pluginJsonFile = new File(pluginFolder, "plugin.json");
			if (!pluginJsonFile.isFile()) {
				throw new RuntimeException("'plugins' folder contains folder without plugin.json: " + pluginJsonFile);
			}
			JsonObject pluginJsonData;
			try (FileInputStream fileInputStream = new FileInputStream(pluginJsonFile)) {
				try (InputStreamReader reader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8)) {
					pluginJsonData = gson.fromJson(reader, JsonObject.class);
				}
			} catch (final IOException e) {
				throw new RuntimeException("could not read plugin.json file: " + pluginJsonFile, e);
			}
			final JsonArray extensionPointsArray = pluginJsonData.getAsJsonArray("extensionPoints");
			if (extensionPointsArray != null) {
				for (final JsonElement extensionPointsElement : extensionPointsArray) {
					final JsonObject extensionPoint = extensionPointsElement.getAsJsonObject();
					final String localExtensionPointId = extensionPoint.get("id").getAsString();
					final String qualifiedExtensionPointId = pluginId + ':' + localExtensionPointId;
					extensionPointIds.add(qualifiedExtensionPointId);
				}
			}
			final JsonArray extensionsArray = pluginJsonData.getAsJsonArray("extensions");
			if (extensionsArray != null) {
				for (final JsonElement extensionsElement : pluginJsonData.getAsJsonArray("extensions")) {
					final JsonObject extension = extensionsElement.getAsJsonObject();
					final String qualifiedExtensionPointId = extension.get("extensionPointId").getAsString();
					final JsonObject extensionData = extension.getAsJsonObject("data");
					extensions.add(new Extension(qualifiedExtensionPointId, extensionData));
				}
			}
		}
		this.pluginReport = new PluginReport(extensionPointIds, extensions);
		this.extensionObjectProvider = extensionObjectProvider;
	}

	/**
	 * Getter method for the pluginReport.
	 * @return the pluginReport
	 */
	public PluginReport getPluginReport() {
		return pluginReport;
	}

	/**
	 * Gets all extensions for a specific extension point ID.
	 * 
	 * @param extensionPointId the extension point ID
	 * @return the extensions
	 */
	public Extension[] getExtensionsForExtensionPoint(final String extensionPointId) {
		if (!pluginReport.getExtensionPointIds().contains(extensionPointId)) {
			throw new NoSuchExtensionPointException(extensionPointId);
		}
		final List<Extension> result = new ArrayList<>();
		for (final Extension extension : pluginReport.getExtensions()) {
			if (extension.getExtensionPointId().equals(extensionPointId)) {
				result.add(extension);
			}
		}
		return result.toArray(new Extension[result.size()]);
	}

	/**
	 * Getter method for the extensionObjectProvider.
	 * @return the extensionObjectProvider
	 */
	public ExtensionObjectProvider getExtensionObjectProvider() {
		return extensionObjectProvider;
	}

}
