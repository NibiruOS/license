package org.nibiru.license.core.impl;

import org.nibiru.license.core.api.Function;
import org.nibiru.license.core.api.LicenceException;
import org.nibiru.license.core.api.License;
import org.nibiru.license.core.api.PublicKeyProvider;
import org.nibiru.license.core.api.SignatureProvider;

import static java.util.Objects.requireNonNull;

import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.Signature;
import java.security.SignatureException;

import org.nibiru.license.core.api.LicenseAuthorizationValidator;

/**
 * {@link LicenseAuthorizationValidator} based on digital signatures and
 * {@link Function}.
 *
 * @param <I> The license info type
 */
public class SignatureLicenseAuthorizationValidator<I extends Serializable>
        implements LicenseAuthorizationValidator<I> {
    private final SignatureProvider signatureProvider;
    private final PublicKeyProvider publicKeyProvider;
    private final Function<I, byte[]> converter;

    public SignatureLicenseAuthorizationValidator(
            SignatureProvider signatureProvider,
            PublicKeyProvider publicKeyProvider, Function<I, byte[]> converter) {
        this.signatureProvider = requireNonNull(signatureProvider);
        this.publicKeyProvider = requireNonNull(publicKeyProvider);
        this.converter = requireNonNull(converter);
    }

    @Override
    public boolean validate(License<I> license) {
        try {
            requireNonNull(license);
            Signature signature = this.signatureProvider.getSignature();
            signature.initVerify(this.publicKeyProvider.getPublicKey());
            signature.update(this.converter.apply(license.getInfo()));
            return signature.verify(license.getAuthorization());
        } catch (SignatureException | InvalidKeyException e) {
            throw new LicenceException(e);
        }
    }
}
