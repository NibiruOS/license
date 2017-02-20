package org.nibiru.license.core.impl;

import org.nibiru.license.core.api.LicenceException;
import org.nibiru.license.core.api.SignatureProvider;

import static java.util.Objects.requireNonNull;

import java.security.NoSuchAlgorithmException;
import java.security.Signature;

/**
 * Signature provider which uses standard Java security classes.
 */
public class DefaultSignatureProvider implements SignatureProvider {
	private final String algorithm;

	public DefaultSignatureProvider(String algorithm) {
		this.algorithm = requireNonNull(algorithm);
	}

	@Override
	public Signature getSignature() {
		try {
			return Signature.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new LicenceException(e);
		}
	}
}
