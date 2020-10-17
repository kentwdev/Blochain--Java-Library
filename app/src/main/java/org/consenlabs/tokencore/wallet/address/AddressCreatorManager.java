package org.anteater515.vermoslib.wallet.address;

import com.google.common.base.Strings;

import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.params.TestNet3Params;
import org.anteater515.vermoslib.wallet.model.Messages;
import org.anteater515.vermoslib.wallet.model.Metadata;
import org.anteater515.vermoslib.wallet.model.Network;
import org.anteater515.vermoslib.wallet.model.TokenException;
import org.anteater515.vermoslib.wallet.model.ChainId;
import org.anteater515.vermoslib.wallet.model.ChainType;

public class AddressCreatorManager {

  public static AddressCreator getInstance(String type, boolean isMainnet, String segWit) {
    if (ChainType.ETHEREUM.equals(type)) {
      return new EthereumAddressCreator();
    } else if (ChainType.BITCOIN.equals(type)) {

      NetworkParameters network = isMainnet ? MainNetParams.get() : TestNet3Params.get();
      if (Metadata.P2WPKH.equals(segWit)) {
        return new SegWitBitcoinAddressCreator(network);
      }
      return new BitcoinAddressCreator(network);
    } else {
      throw new TokenException(Messages.WALLET_INVALID_TYPE);
    }
  }

}
