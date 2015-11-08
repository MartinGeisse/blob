/**
 * Copyright (c) 2015 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.gui.hosted;

import java.net.URL;
import java.net.URLStreamHandlerFactory;
import java.util.concurrent.CountDownLatch;
import javafx.application.Application;
import javafx.stage.Stage;
import name.martingeisse.blob.gui.GuiProvider;
import name.martingeisse.blob.gui.UrlMap;

/**
 * <p>
 * THIS CLASS IS NOT PUBLIC API! DO NOT USE IT!
 * </p>
 * 
 * <p>
 * Sadly, some JavaFX nonsense requires this class to be public, otherwise the launcher refuses
 * to launch it.
 * </p>
 */
public class MyFxApplication extends Application {

	private static final CountDownLatch startupCountDownLatch = new CountDownLatch(1);
	volatile static MyFxApplication instance;

	/**
	 * Constructor.
	 */
	public MyFxApplication() {
	}
	
	/**
	 * Initializes a data structure that finds the appropriate {@link GuiProvider} for public
	 * and private GUI URLs, and also sets up an {@link URLStreamHandlerFactory} for it.
	 */
	static void initializeUrlMap(UrlMap urlMap) {
		URL.setURLStreamHandlerFactory(protocol -> {
			if ("public-gui".equals(protocol)) {
				return new MyUrlStreamHandler(urlMap, PublicGuiConnection::new);
			}
			if ("private-gui".equals(protocol)) {
				return new MyUrlStreamHandler(urlMap, PrivateGuiConnection::new);
			}
			// TODO provide common resources through an extension point (e.g. plugin:// URLs)
			return null;
		});
	}
	
	/**
	 * Creates a new thread that launches the JavaFX application. This method will only
	 * return once the application is fully initialized.
	 */
	static void launchInNewThread() {
		new Thread(() -> {
			Application.launch(MyFxApplication.class);
		}).start();
		try {
			startupCountDownLatch.await();
		} catch (InterruptedException e) {
			throw new RuntimeException("interrupted while starting JavaFX");
		}
		if (MyFxApplication.instance == null) {
			throw new RuntimeException("could not start JavaFX");
		}
	
	}

	// override
	@Override
	public void start(final Stage primaryStage) {
		instance = this;
		startupCountDownLatch.countDown();
	}

}
