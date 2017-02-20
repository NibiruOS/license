package org.nibiru.license.core.impl;

import org.nibiru.license.core.api.Function;
import org.nibiru.license.core.api.License;
import org.nibiru.license.core.api.LicenseSerializer;
import org.nibiru.license.core.impl.function.Base64ToBytesFunction;
import org.nibiru.license.core.impl.function.BytesToBase64Function;
import org.nibiru.license.core.impl.function.BytesToSerializableFunction;
import org.nibiru.license.core.impl.function.SaltFunction;
import org.nibiru.license.core.impl.function.SerializableToBytesFunction;
import org.nibiru.license.core.impl.function.UnsaltFunction;

import java.io.Serializable;

import javax.inject.Provider;

/**
 * JSR330 provider for creating a default {@link LicenseSerializer}
 * implementation, using converters.
 * 
 * @param <I>
 *            The license info type
 */
public class DefaultLicenseSerializerProvider<I extends Serializable>
		implements Provider<LicenseSerializer<I>> {
	@Override
	public LicenseSerializer<I> get() {
		Function<I, String> infoToStringFunction = Function.Builder.create(new SerializableToBytesFunction<I>())
				.andThen(new SaltFunction())
				.andThen(new BytesToBase64Function())
				.build();
		Function<License<I>, String> licenseToStringFunction = Function.Builder.create(new SerializableToBytesFunction<License<I>>())
				.andThen(new SaltFunction())
				.andThen(new BytesToBase64Function())
				.build();
		Function<String, I> stringToInfoFunction = Function.Builder.create(new Base64ToBytesFunction())
				.andThen(new UnsaltFunction())
				.andThen(new BytesToSerializableFunction<I>())
				.build();
		Function<String, License<I>> stringToLicenseFunction = Function.Builder.create(new Base64ToBytesFunction())
				.andThen(new UnsaltFunction())
				.andThen(new BytesToSerializableFunction<License<I>>())
				.build();

		return new FunctionLicenseSerializer<I>(infoToStringFunction,
				licenseToStringFunction, stringToInfoFunction,
				stringToLicenseFunction);
	}
}
