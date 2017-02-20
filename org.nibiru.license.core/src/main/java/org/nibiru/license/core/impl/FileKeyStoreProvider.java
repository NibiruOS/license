package org.nibiru.license.core.impl;

import org.nibiru.license.core.api.LicenceException;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * KeyStore provider that reads KeyStore from a File.
 */
public class FileKeyStoreProvider extends AbstractKeyStoreProvider {
	private final File keyStoreFile;

	public FileKeyStoreProvider(File keyStoreFile, String keyStorePassword) {
		super(keyStorePassword);
		this.keyStoreFile = requireNonNull(keyStoreFile);
	}

	@Override
	protected InputStream openKeyStoreInputStream() {
		try {
			return new FileInputStream(keyStoreFile);
		} catch (FileNotFoundException e) {
			throw new LicenceException(e);
		}
	}
}
