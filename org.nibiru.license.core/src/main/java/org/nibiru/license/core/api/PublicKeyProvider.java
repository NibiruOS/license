package org.nibiru.license.core.api;

import java.security.PublicKey;

/**
 * Component that provides a public key.
 */
public interface PublicKeyProvider {
	/**
	 * @return The public key
	 */
	PublicKey getPublicKey();
}
