/**
 * Copyright (c) 2010 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.core;

import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * Used to provide objects that come from extensions. This is used by the consumer
 * of an extension (i.e. typically the provider of the corresponding extension
 * point) to create/obtain implementation objects without calling the necessary
 * reflection / Guice methods themselves. Specifically, without this class, extension
 * consumers would have to call the {@link Injector} manually to obtain the
 * implementation objects.
 */
public final class ExtensionObjectProvider {

	private final Injector injector;

	/**
	 * Constructor.
	 * @param injector the injector
	 */
	@Inject
	public ExtensionObjectProvider(final Injector injector) {
		this.injector = injector;
	}

	/**
	 * Creates an instance of the specified class.
	 * 
	 * @param className the name of the class to instantiate
	 * @return the instance
	 */
	public Object create(final String className) {
		try {
			return create(Class.forName(className));
		} catch (ProviderException e) {
			throw e;
		} catch (Exception e) {
			throw new ProviderException(e);
		}
	}

	/**
	 * Creates an instance of the specified class.
	 * 
	 * @param theClass the class to instantiate
	 * @return the instance
	 */
	public <T> T create(final Class<? extends T> theClass) {
		try {
			return injector.getInstance(theClass);
		} catch (ProviderException e) {
			throw e;
		} catch (Exception e) {
			throw new ProviderException(e);
		}
	}

	/**
	 * Creates an instance of the specified class.
	 * 
	 * @param className the name of the class to instantiate
	 * @param expectedType the expected type of the returned object
	 * @return the instance
	 */
	public <T> T create(final String className, final Class<T> expectedType) {
		try {
			return expectedType.cast(create(className));
		} catch (ProviderException e) {
			throw e;
		} catch (ClassCastException e) {
			throw new ProviderException("class " + className + " does not implement/extend the expected type " + expectedType, e);
		} catch (Exception e) {
			throw new ProviderException(e);
		}
	}

	/**
	 * Creates an instance of the specified class.
	 * 
	 * @param theClass the class to instantiate
	 * @param expectedType the expected type of the returned object
	 * @return the instance
	 */
	public <T> T create(final Class<?> theClass, final Class<T> expectedType) {
		try {
			return expectedType.cast(create(theClass));
		} catch (ProviderException e) {
			throw e;
		} catch (ClassCastException e) {
			throw new ProviderException("class " + theClass + " does not implement/extend the expected type " + expectedType, e);
		} catch (Exception e) {
			throw new ProviderException(e);
		}
	}

	/**
	 * This exception type gets thrown when something goes wrong while providing an extension object 
	 */
	public static class ProviderException extends RuntimeException {

		/**
		 * Constructor.
		 */
		public ProviderException() {
			super();
		}

		/**
		 * Constructor.
		 * @param message ...
		 * @param cause ...
		 */
		public ProviderException(final String message, final Throwable cause) {
			super(message, cause);
		}

		/**
		 * Constructor.
		 * @param message ...
		 */
		public ProviderException(final String message) {
			super(message);
		}

		/**
		 * Constructor.
		 * @param cause ...
		 */
		public ProviderException(final Throwable cause) {
			super(cause);
		}

	}
}
