package org.nibiru.license.core.impl;

import org.nibiru.license.core.api.KeyStoreProvider;
import org.nibiru.license.core.api.LicenceException;
import org.nibiru.license.core.api.PublicKeyProvider;

import static java.util.Objects.requireNonNull;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PublicKey;

/**
 * Public key provider that reads the key from a {@link KeyStore}.
 */
public class KeyStorePublicKeyProvider implements PublicKeyProvider {
	private final String alias;
	private final KeyStoreProvider keyStoreProvider;

	public KeyStorePublicKeyProvider(String alias,
			KeyStoreProvider keyStoreProvider) {
		this.alias = requireNonNull(alias);
		this.keyStoreProvider = requireNonNull(keyStoreProvider);
	}

	@Override
	public PublicKey getPublicKey() {
		try {
			return keyStoreProvider.getKeyStore().getCertificate(alias)
					.getPublicKey();
		} catch (KeyStoreException e) {
			throw new LicenceException(e);
		}
	}
}
