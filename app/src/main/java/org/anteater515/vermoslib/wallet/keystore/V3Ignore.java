package org.anteater515.vermoslib.wallet.keystore;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.anteater515.vermoslib.foundation.crypto.EncPair;
import org.anteater515.vermoslib.wallet.model.Metadata;

/**
 * Created by xyz on 2018/2/8.
 */
public abstract class V3Ignore {
  @JsonIgnore
  public abstract EncPair getEncMnemonic();

  @JsonIgnore
  @JsonGetter(value = "imTokenMeta")
  public abstract Metadata getMetadata();

  @JsonIgnore
  public abstract String getMnemonicPath();

  @JsonIgnore
  public abstract String getEncXPub();

  @JsonIgnore
  public abstract int getMnemonicIndex();
}
