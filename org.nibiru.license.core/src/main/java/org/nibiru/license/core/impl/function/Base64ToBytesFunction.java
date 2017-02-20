package org.nibiru.license.core.impl.function;

import org.nibiru.license.core.api.Function;

import static java.util.Objects.requireNonNull;

/**
 * Base 64 to byte array converter.
 */
public class Base64ToBytesFunction implements Function<String, byte[]> {
	@Override
	public byte[] apply(String source) {
		requireNonNull(source);
		return Base64Coder.decode(source);
	}
}
