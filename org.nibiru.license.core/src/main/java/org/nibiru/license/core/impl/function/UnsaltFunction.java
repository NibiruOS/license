package org.nibiru.license.core.impl.function;

import org.nibiru.license.core.api.Function;

import static java.util.Objects.requireNonNull;

/**
 * Byte array to byte array converter that removes a salt form an obfuscated
 * message.
 */
public class UnsaltFunction implements Function<byte[], byte[]> {
	@Override
	public byte[] apply(byte[] source) {
		requireNonNull(source);
		byte salt = source[0];
		byte[] unsalted = new byte[source.length - 1];
		for (int n = 0; n < unsalted.length; n++) {
			unsalted[n] = (byte) (source[n + 1] ^ salt);
		}
		return unsalted;
	}
}
