package org.nibiru.license.core.impl;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Date;

import javax.inject.Inject;

import org.nibiru.license.core.api.HardwareIdProvider;
import org.nibiru.license.core.api.LicenseInfoValidator;

/**
 * A {@link LicenseInfoValidator} implementation for {@link DefaultLicenseInfo}.<br>
 * It checks the expiration date and the hardware identification.<br>
 * Any of these values can be null. In such case, the value is ignored.
 */
public class DefaultLicenseInfoValidator implements
		LicenseInfoValidator<DefaultLicenseInfo> {
	private final HardwareIdProvider hardwareIdProvider;

	@Inject
	public DefaultLicenseInfoValidator(HardwareIdProvider hardwareIdProvider) {
		this.hardwareIdProvider = requireNonNull(hardwareIdProvider);
	}

	@Override
	public boolean validate(DefaultLicenseInfo licenseInfo) {
		requireNonNull(licenseInfo);
		boolean dateOk = licenseInfo.getExpirationDate() == null
				|| licenseInfo.getExpirationDate().after(new Date());

		boolean hardwareOk = licenseInfo.getHardwareId() == null
				|| Arrays.equals(licenseInfo.getHardwareId(),
						hardwareIdProvider.getHardwareId());

		return dateOk && hardwareOk;
	}
}
