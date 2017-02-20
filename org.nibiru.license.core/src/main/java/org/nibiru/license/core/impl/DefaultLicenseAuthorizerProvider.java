package org.nibiru.license.core.impl;

import static java.util.Objects.requireNonNull;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Provider;

import org.nibiru.license.core.api.LicenseAuthorizer;
import org.nibiru.license.core.api.PrivateKeyProvider;
import org.nibiru.license.core.api.SignatureProvider;
import org.nibiru.license.core.impl.function.SerializableToBytesFunction;

/**
 * JSR330 provider for creating a default {@link LicenseAuthorizer}
 * implementation, using digital signatures.
 * 
 * @param <I>
 *            The license info type
 */
public class DefaultLicenseAuthorizerProvider<I extends Serializable>
		implements Provider<LicenseAuthorizer<I>> {
	private final SignatureProvider signatureProvider;
	private final PrivateKeyProvider privateKeyProvider;

	@Inject
	public DefaultLicenseAuthorizerProvider(
			SignatureProvider signatureProvider,
			PrivateKeyProvider privateKeyProvider) {
		this.signatureProvider = requireNonNull(signatureProvider);
		this.privateKeyProvider = requireNonNull(privateKeyProvider);
	}

	@Override
	public LicenseAuthorizer<I> get() {
		return new SignatureLicenseAuthorizer<I>(signatureProvider,
				privateKeyProvider, new SerializableToBytesFunction<I>());
	}
}
