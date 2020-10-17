package org.anteater515.vermoslib.wallet.address;

public interface AddressCreator {
    String fromPrivateKey(String prvKeyHex);
    String fromPrivateKey(byte[] prvKeyBytes);
}
