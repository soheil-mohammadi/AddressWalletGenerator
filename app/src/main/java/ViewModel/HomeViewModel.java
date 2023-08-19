package ViewModel;


import android.os.Handler;

import Navigator.HomeNavigator;
import Navigator.SplashNavigator;
import androidx.hilt.lifecycle.ViewModelInject;
import base.BaseViewModel;
import utils.Utils;

public class HomeViewModel extends BaseViewModel<HomeNavigator>  {

    private static final String TAG = "HomeViewModel";

    private Utils utils ;


    @ViewModelInject
    public HomeViewModel(Utils utils) {
        this.utils = utils;

    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }


}