## Vermos Lib
Vermos Lib is a blockchain library providing a relatively consistent API that allows you to manage your wallets and sign transactions in BTC, ETH and EOS chains simultaneously.
Vermos Lib introduces the concept of 'identity', you can use the same mnemonic to manage wallets on the three chains.

## Credits and more info
Thanks bitcoinj, CoreBitcoin and others library.


## Installation

Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```groovy
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Step 2. Add the dependency
```
dependencies {
	implementation 'com.github.AnTeater515:Vermos_Blockhain_Library:Tag'
}
```

Step 3. Add the JAVA8 support to your build.gradle
```
android {
    ……
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
   ……
}
```

Step 4. (Optional) When you wants to debug your app in Android Oreo you shoud add the blow code to your build.gradle. Pls ref to https://issuetracker.google.com/issues/65941637 for more info.
```
android {
    ……
    packagingOptions {
        exclude 'lib/x86_64/darwin/libscrypt.dylib'
    }
   ……
}
```
## Try the API
### Init the storage to store the keystore file
```
public class MainActivity extends AppCompatActivity implements KeystoreStorage {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WalletManager.storage = this;
        WalletManager.scanWallets();
    }

    public File getKeystoreDir() {
        return this.getFilesDir();
    }
}
```

### Create new Identity and derive the eth, btc wallets
```java
// You should create or recover Identity first before you create other wallets
// The last param, Metadata.P2WPKH means that the derived btc wallet is a SegWit wallet
Identity identity = Identity.createIdentity("MyFirstIdentity", SampleKey.PASSWORD, SampleKey.PASSWORD_HINT, Network.MAINNET, Metadata.P2WPKH);


Wallet ethereumWallet = identity.getWallets().get(0);
Wallet bitcoinWallet = identity.getWallets().get(1);
```
### Export Wallet
```java

String prvKey = WalletManager.exportPrivateKey(ethereumWallet.getId(), SampleKey.PASSWORD);
System.out.println(String.format("PrivateKey: %s", prvKey));
String mnemonic = WalletManager.exportMnemonic(ethereumWallet.getId(), SampleKey.PASSWORD).getMnemonic();
System.out.println(String.format("Mnemonic: %s", mnemonic));
String json = WalletManager.exportKeystore(ethereumWallet.getId(), SampleKey.PASSWORD);
System.out.println(String.format("Keystore: %s", json));

// output:
// PrivateKey: f653be3f639f45ea1ed3eb152829b6d881ce62257aa873891e06fa9569a8d9aa
// Mnemonic: tide inmate cloud around wise bargain celery cement jungle melody galaxy grocery
// Keystore: {"id":"c7575eba-3ae3-4cc3-86ba-2eb9c6839cad","version":3,"crypto":{"ciphertext":"7083ba3dd5470ba4be4237604625e05fa6b668954d270beb848365cbf6933ec5","mac":"f4f9ea8d42ff348b11fc146c396da446cc975309b3538e08a58c0b218bddd15d","cipher":"aes-128-ctr","cipherparams":{"iv":"db3f523faf4da4f1c6edcd7bc1386879"},"kdf":"pbkdf2","kdfparams":{"dklen":32,"c":10240,"prf":"hmac-sha256","salt":"0ce830e9f888dfe33c31e6cfc444d6f588161c9d4128d4066ee5dfdcbc5d0079"}},"address":"4a1c2072ac67b616e5c578fd9e2a4d30e0158471"}
```

### SignTransaction
```java
EthereumTransaction tran = new EthereumTransaction(BigInteger nonce, BigInteger gasPrice, BigInteger gasLimit, String to, BigInteger value, String data)
TxSignResult result = tran.signTransaction(chainId, SampleKey.PASSWORD, ethereumWallet);
String signedTx = result.getSignedTx(); // This is the signature result which you need to broadcast.
String txHash = result.getTxHash(); // This is txHash which you can use for locating your transaction record
```
