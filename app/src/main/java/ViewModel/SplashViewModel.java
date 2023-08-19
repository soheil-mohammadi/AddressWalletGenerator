package ViewModel;


import android.os.Handler;

import Navigator.SplashNavigator;
import androidx.hilt.lifecycle.ViewModelInject;
import base.BaseViewModel;
import utils.Utils;

public class SplashViewModel extends BaseViewModel<SplashNavigator>  {

    private static final String TAG = "SplashViewModel";

    private Utils utils ;


    @ViewModelInject
    public SplashViewModel(Utils utils) {
        this.utils = utils;
        showMainScreen();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    private void showMainScreen () {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getNavigator().onOpenMain();

            }
        } , 2500);
    }

}