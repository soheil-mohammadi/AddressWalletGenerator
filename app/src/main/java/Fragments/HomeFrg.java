package Fragments;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.soheil.walletgenerator.App;
import org.soheil.walletgenerator.R;
import org.soheil.walletgenerator.databinding.FrgHomeBinding;
import org.soheil.walletgenerator.databinding.FrgSplashBinding;

import Custom.Toolbar;
import Listeners.OnFrgDestroy;
import Navigator.HomeNavigator;
import Navigator.SplashNavigator;
import ViewModel.HomeViewModel;
import ViewModel.SplashViewModel;
import androidx.annotation.Nullable;
import base.BaseFragment;
import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class HomeFrg extends BaseFragment<FrgHomeBinding, HomeViewModel> implements HomeNavigator {

    private static final String TAG = "HomeFrg";

    public static HomeFrg newInstance() {
        Bundle args = new Bundle();
        HomeFrg fragment = new HomeFrg();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public boolean showToolbar() {
        return true;
    }

    @Override
    public String getToolbarTitle() {
        return App.getInstance().getResString(R.string.app_name);
    }

    @Override
    public int getToolbarIcon() {
        return Toolbar.NO_IMG_TOOL;
    }

    @Override
    public int getToolbarSecondIcon() {
        return Toolbar.NO_IMG_TOOL;
    }

    @Override
    protected int getToolbarTitleGravity() {
        return Gravity.CENTER;
    }

    @Override
    public int getToolbarNavIcon() {
        return 0 ;
    }

    @Override
    public void onNavBarBackClicked() {
        defaultNavBackPressedToolbar();
    }

    @Override
    public void handleToolbarComponent(TextView title, ImageView icon, ImageView secondIcon) {

    }

    @Override
    public void onCreatedView(@Nullable Bundle savedInstanceState) {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public int getLayout() {
        return R.layout.frg_home;
    }

    @Override
    public Class<HomeViewModel> getViewModelClass() {
        return HomeViewModel.class;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public OnFrgDestroy destroy() {
        return null;
    }


}
