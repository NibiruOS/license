package org.nibiru.license.core.api;

import java.io.Serializable;

/**
 * Component that authorizes a license.
 * 
 * @param <I>
 *            The license info type
 */
public interface LicenseAuthorizer<I extends Serializable> {
	/**
	 * Creates an authorized license from license information.
	 * 
	 * @param licenseInfo
	 *            The license information
	 * @return The authorized license
	 */
	License<I> authorize(I licenseInfo);
}
