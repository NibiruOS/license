package org.nibiru.license.core.api;

import java.security.Signature;

/**
 * Component that provides a signature.
 */
public interface SignatureProvider {
	/**
	 * @return The signature
	 */
	Signature getSignature();
}
