package org.nibiru.license.core.impl.function;

import org.nibiru.license.core.api.Function;

import static java.util.Objects.requireNonNull;

import java.security.SecureRandom;

/**
 * Byte array to byte array converter that applies a random salt in order to
 * obfuscate the message.
 */
public class SaltFunction implements Function<byte[], byte[]> {
	@Override
	public byte[] apply(byte[] source) {
		requireNonNull(source);
		byte salt = new SecureRandom().generateSeed(1)[0];
		byte[] salted = new byte[source.length + 1];
		salted[0] = salt;
		for (int n = 0; n < source.length; n++) {
			salted[n + 1] = (byte) (source[n] ^ salt);
		}
		return salted;
	}
}
