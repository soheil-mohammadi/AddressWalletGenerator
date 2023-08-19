package cryptoj.enums;

import lombok.Getter;
import lombok.experimental.FieldDefaults;
import static lombok.AccessLevel.PRIVATE;

@Getter
@FieldDefaults(level = PRIVATE)
public enum Network {

    BITCOIN_MAINNET(
            "BITCOIN_MAINNET", "Bitcoin (Mainnet)",
            CoinType.BTC, true, 0,
            "bc", 0x00, 0x05, 0x80,
            0x0488B21E,     // xpub
            0x0488ADE4,     // xprv
            0x04b24746,    // zpub
            0x04b2430c     // zprv
    ),
    BITCOIN_TESTNET(
            "BITCOIN_TESTNET", "Bitcoin (Testnet)",
            CoinType.BTC, false, 1,
            "tb", 0x6f, 0xc4, 0xef,
            0x043587cf,     // tpub
            0x04358394,     // tprv
            0x045f1cf6,    // vpub
            0x045f18bc     // vprv
    ),
    BITCOIN_REGTEST(
            "BITCOIN_REGTEST", "Bitcoin (Regtest)",
            CoinType.BTC, false, 1,
            "bcrt", 0x6f, 0xc4, 0xef,
            0x043587cf,     // tpub
            0x04358394,     // tprv
            0x045f1cf6,    // vpub
            0x045f18bc     // vprv
    ),
    ETHEREUM_MAINNET(
            "ETHEREUM_MAINNET", "Ethereum (Mainnet)",
            CoinType.ETH, true, 60,
            "bc", 0x00, 0x05, 0x80,
            0x0488B21E,     // xpub
            0x0488ADE4,     // xprv
            0x04b24746,    // zpub
            0x04b2430c     // zprv
    ),
    ETHEREUM_TESTNET_ROPSTEN(
            "ETHEREUM_TESTNET_ROPSTEN", "Ethereum (Testnet Ropsten)",
            CoinType.ETH, false, 1,
            "bc", 0x00, 0x05, 0x80,
            0x0488B21E,     // xpub
            0x0488ADE4,     // xprv
            0x04b24746,    // zpub
            0x04b2430c     // zprv
    ),
    LITECOIN_MAINNET(
            "LITECOIN_MAINNET", "Litecoin (Mainnet)",
            CoinType.LTC, true, 2,
            "ltc", 0x30, 0x32, 0xb0,
            0x019da462,     // Ltub
            0x019d9cfe,     // Ltpv
            0x04b24746,    // zpub
            0x04b2430c     // zprv
    ),
    LITECOIN_TESTNET(
            "LITECOIN_TESTNET", "Litecoin (Testnet)",
            CoinType.LTC, false, 1,
            "tltc", 0x6f, 0xc4, 0xef,
            0x043587cf,     // tpub
            0x04358394,     // tprv
            0x043587cf,    // tpub
            0x04358394     // tprv
    );

    final String code;
    final String name;

    final CoinType coinType; // network code - matched with Network.code
    final boolean isMainNet;
    final int coinId; // BIP44 coin_id - https://github.com/satoshilabs/slips/blob/master/slip-0044.md

    // Base58 encoding version numbers
    final String bech32;
    final int pubKeyHash;
    final int scriptHash;
    final int wif;
    final int p2pkhPub;
    final int p2pkhPriv;
    final int p2wpkhPub;
    final int p2wpkhPriv;

    Network(
            final String code,
            final String name,
            final CoinType coinType,
            final boolean isMainNet,
            final int coinId,
            final String bech32,
            final int pubKeyHash,
            final int scriptHash,
            final int wif,
            final int p2pkhPub,
            final int p2pkhPriv,
            final int p2wpkhPub,
            final int p2wpkhPriv
    ) {
        this.code = code;
        this.name = name;
        this.coinType = coinType;
        this.isMainNet = isMainNet;
        this.coinId = coinId;
        this.bech32 = bech32;
        this.pubKeyHash = pubKeyHash;
        this.scriptHash = scriptHash;
        this.wif = wif;
        this.p2pkhPub = p2pkhPub;
        this.p2pkhPriv = p2pkhPriv;
        this.p2wpkhPub = p2wpkhPub;
        this.p2wpkhPriv = p2wpkhPriv;
    }


    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public CoinType getCoinType() {
        return coinType;
    }

    public boolean isMainNet() {
        return isMainNet;
    }

    public int getCoinId() {
        return coinId;
    }

    public String getBech32() {
        return bech32;
    }

    public int getPubKeyHash() {
        return pubKeyHash;
    }

    public int getScriptHash() {
        return scriptHash;
    }

    public int getWif() {
        return wif;
    }

    public int getP2pkhPub() {
        return p2pkhPub;
    }

    public int getP2pkhPriv() {
        return p2pkhPriv;
    }

    public int getP2wpkhPub() {
        return p2wpkhPub;
    }

    public int getP2wpkhPriv() {
        return p2wpkhPriv;
    }
}
