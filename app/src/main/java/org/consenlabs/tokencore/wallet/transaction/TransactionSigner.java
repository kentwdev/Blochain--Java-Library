package org.anteater515.vermoslib.wallet.transaction;

import org.anteater515.vermoslib.wallet.Wallet;

public interface TransactionSigner {
  TxSignResult signTransaction(String chainId, String password, Wallet wallet);
}
