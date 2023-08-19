package ViewModel;


import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.soheil.walletgenerator.App;
import org.soheil.walletgenerator.R;

import java.util.ArrayList;

import Models.AddressModel;
import Models.SignMsgModel;
import Navigator.HomeNavigator;
import Navigator.SplashNavigator;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import base.BaseViewModel;
import cryptoj.CryptoJ;
import cryptoj.enums.AddressType;
import cryptoj.enums.Network;
import cryptoj.exceptions.CryptoJException;
import utils.Utils;

public class HomeViewModel extends BaseViewModel<HomeNavigator>  {


    private MutableLiveData<AddressModel> generatedAddress = new MutableLiveData<>(null);
    private MutableLiveData<SignMsgModel> signMessage = new MutableLiveData<>(null);
    private MutableLiveData<String> preDefinedRawMsg = new MutableLiveData<>("Hello world, this is a message !!");

    public enum HomeState {

        START ,
        GENERATED ,
        SIGN_MSG ,
        ON_PROGRESS

    }

    private static final String TAG = "HomeViewModel";

    private Utils utils ;
    private MutableLiveData<HomeState> homeState = new MutableLiveData<>(HomeState.START);


    @ViewModelInject
    public HomeViewModel(Utils utils) {
        this.utils = utils;

    }


    public void onCopyAddressClicked () {
        utils.copyToClipboard(generatedAddress.getValue().getAddress());
        getNavigator().showToastMsg(App.getInstance().getResString(R.string.address_copied_to_clipboard));

    }

    public void onCopyPrivateKeyClicked () {
        utils.copyToClipboard(generatedAddress.getValue().getPrivateKey());
        getNavigator().showToastMsg(App.getInstance().getResString(R.string.private_key_copied_to_clipboard));
    }

    public void onCopySignatureClicked () {
        utils.copyToClipboard(signMessage.getValue().getSignature());
        getNavigator().showToastMsg(App.getInstance().getResString(R.string.signature_copied_to_clipboard));
    }

    public void onRegenerateButtonClicked () {
        onGenerateButtonOfStart();
    }


    public void onSignMessageClicked () {

        homeState.setValue(HomeState.SIGN_MSG);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Network network = Network.ETHEREUM_MAINNET;
                    String signature = CryptoJ.signMessage(
                            network,
                            preDefinedRawMsg.getValue(),
                            generatedAddress.getValue().getPrivateKey()
                    );

                    signMessage.postValue(new SignMsgModel(preDefinedRawMsg.getValue() , signature));

                } catch (CryptoJException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }


    public void onGenerateButtonOfStart () {

        setOnProgressState();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Network network = Network.ETHEREUM_MAINNET;
                    AddressType addressType = AddressType.P2WPKH_NATIVE_SEGWIT;

                    ArrayList<String> mnemonic = CryptoJ.generateMnemonic(12);

                    String xPub = CryptoJ.generateXPub(network, addressType, mnemonic);

                    String address = CryptoJ.generateAddress(network, addressType, xPub, 0);
                    String privateKey = CryptoJ.generatePrivateKey(network, addressType, mnemonic, 0);

                    generatedAddress.postValue(new AddressModel(mnemonic , address , privateKey));
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            homeState.setValue(HomeState.GENERATED);
                        }
                    });

                } catch (CryptoJException e) {
                    e.printStackTrace();
                }

            }
        }).start();


    }

    private void setOnProgressState () {
        homeState.setValue(HomeState.ON_PROGRESS);
    }


    public LiveData<HomeState> getHomeState() {
        return homeState;
    }

    public LiveData<AddressModel> getGeneratedAddress() {
        return generatedAddress;
    }

    public LiveData<SignMsgModel> getSignMessage() {
        return signMessage;
    }

    public LiveData<String> getPreDefinedRawMsg() {
        return preDefinedRawMsg;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }


}