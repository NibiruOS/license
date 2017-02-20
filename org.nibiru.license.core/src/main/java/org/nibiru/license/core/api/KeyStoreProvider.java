package org.nibiru.license.core.api;

import java.security.KeyStore;

/**
 * Interface for accessing the key store.
 */
public interface KeyStoreProvider {
	/**
	 * @return The key store
	 */
	KeyStore getKeyStore();
}
