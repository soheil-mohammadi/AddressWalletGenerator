package Models;

import java.util.ArrayList;

public class AddressModel {

    private ArrayList<String> mnemonic ;
    private String address ;
    private String privateKey ;


    public AddressModel(ArrayList<String> mnemonic, String address, String privateKey) {
        this.mnemonic = mnemonic;
        this.address = address;
        this.privateKey = privateKey;
    }

    public ArrayList<String> getMnemonic() {
        return mnemonic;
    }

    public String getAddress() {
        return address;
    }

    public String getPrivateKey() {
        return privateKey;
    }
}
