package org.anteater515.vermoslib.foundation.crypto;

import com.lambdaworks.crypto.SCrypt;

import org.anteater515.vermoslib.wallet.model.Messages;
import org.anteater515.vermoslib.wallet.model.TokenException;
import org.anteater515.vermoslib.foundation.utils.NumericUtil;

import java.security.GeneralSecurityException;

/**
 * Created by xyz on 2018/2/3.
 */

public class SCryptCrypto extends Crypto<SCryptParams> {
  static final String SCRYPT = "scrypt";


  public SCryptCrypto() {
    super();
    this.kdf = SCRYPT;
  }

  public static SCryptCrypto createSCryptCrypto() {
    SCryptCrypto crypto = new SCryptCrypto();
    byte[] salt = NumericUtil.generateRandomBytes(SALT_LENGTH);
    SCryptParams params = SCryptParams.createSCryptParams();
    params.setSalt(NumericUtil.bytesToHex(salt));
    crypto.kdfparams = params;
    return crypto;
  }

  @Override
  public byte[] generateDerivedKey(byte[] password) {
    int dkLen = this.kdfparams.getDklen();
    int n = this.kdfparams.getN();
    int p = this.kdfparams.getP();
    int r = this.kdfparams.getR();
    byte[] salt = NumericUtil.hexToBytes(this.kdfparams.getSalt());
    try {
      return SCrypt.scrypt(password, salt, n, r, p, dkLen);
    } catch (GeneralSecurityException e) {
      throw new TokenException(Messages.SCRYPT_PARAMS_INVALID, e);
    }
  }

}
