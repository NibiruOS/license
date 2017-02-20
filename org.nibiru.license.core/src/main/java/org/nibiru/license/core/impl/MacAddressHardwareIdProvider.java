package org.nibiru.license.core.impl;

import org.nibiru.license.core.api.HardwareIdProvider;
import org.nibiru.license.core.api.LicenceException;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * {@link HardwareIdProvider} implementation that reads localhost network
 * interface MAC address.
 */
public class MacAddressHardwareIdProvider implements HardwareIdProvider {
    @Override
    public byte[] getHardwareId() {
        try {
            return NetworkInterface
                    .getByInetAddress(InetAddress.getLocalHost())
                    .getHardwareAddress();
        } catch (SocketException | UnknownHostException e) {
            throw new LicenceException(e);
        }
    }
}
