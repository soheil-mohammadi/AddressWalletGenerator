package Models;

import java.util.ArrayList;

public class SignMsgModel {

    private String message ;
    private String signature ;


    public SignMsgModel(String message, String signature) {
        this.message = message;
        this.signature = signature;
    }

    public String getMessage() {
        return message;
    }

    public String getSignature() {
        return signature;
    }
}
