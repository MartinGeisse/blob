/**
 * Copyright (c) 2015 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.gui.hosted;

import com.google.inject.Inject;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import name.martingeisse.blob.gui.GuiSystem;
import name.martingeisse.blob.gui.UrlMap;
import name.martingeisse.blob.gui.WindowSpecification;

/**
 * A JavaFX based implementation of the GUI system.
 */
public class JavaFxGuiSystem implements GuiSystem {

	@SuppressWarnings("unused")
	private final MyFxApplication fxApplication;

	/**
	 * Constructor.
	 * @param urlMap the URL map
	 */
	@Inject
	public JavaFxGuiSystem(UrlMap urlMap) {
		MyFxApplication.initializeUrlMap(urlMap);
		MyFxApplication.launchInNewThread();
		fxApplication = MyFxApplication.instance;
	}

	// override
	@Override
	public void openWindow(final WindowSpecification specification) {
		if (specification == null) {
			throw new IllegalArgumentException("window specification cannot be null");
		}
		Platform.runLater(() -> {
			final Stage stage = new Stage();
			final WebView webView = new WebView();
			// webView.getEngine().loadContent("<html><body style='background-color: #f8f8f8; margin: 0px; '><div style='background-color: #fff; height: 30px; '>Title Bar</div>Hello World!<br /><a href=\"http://www.google.com\">link</a><br /><a href=\"window://foo\">link2</a></body></html>");
			// webView.getEngine().load("http://www.stackoverflow.com/");
			webView.getEngine().load(specification.getUrl());
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(new Scene(webView));
			stage.setResizable(false);
			stage.show();
		});
	}

}
