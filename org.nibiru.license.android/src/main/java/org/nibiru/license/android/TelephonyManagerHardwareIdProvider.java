package org.nibiru.license.android;

import android.telephony.TelephonyManager;

import org.nibiru.license.core.api.HardwareIdProvider;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.requireNonNull;

/**
 * @{TelephonyManager} based @{link HardwareIdProvider}.
 */
public class TelephonyManagerHardwareIdProvider implements HardwareIdProvider {
    private final TelephonyManager telephonyManager;

    @Inject
    public TelephonyManagerHardwareIdProvider(TelephonyManager telephonyManager) {
        this.telephonyManager = requireNonNull(telephonyManager);
    }

    @Override
    public byte[] getHardwareId() {
        return telephonyManager.getDeviceId().getBytes();
    }
}
