package Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.soheil.walletgenerator.App;
import org.soheil.walletgenerator.R;
import org.soheil.walletgenerator.databinding.FrgSplashBinding;
import javax.inject.Inject;
import Listeners.OnFrgDestroy;
import Navigator.SplashNavigator;
import ViewModel.SplashViewModel;
import androidx.annotation.Nullable;
import base.BaseFragment;
import dagger.hilt.android.AndroidEntryPoint;
import utils.Utils;


@AndroidEntryPoint
public class SplashFrg extends BaseFragment<FrgSplashBinding, SplashViewModel> implements SplashNavigator {

    private static final String TAG = "SplashFrg";

    public static SplashFrg newInstance() {
        Bundle args = new Bundle();
        SplashFrg fragment = new SplashFrg();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public boolean showToolbar() {
        return false;
    }

    @Override
    public String getToolbarTitle() {
        return null;
    }

    @Override
    public int getToolbarIcon() {
        return 0;
    }

    @Override
    public int getToolbarSecondIcon() {
        return 0;
    }

    @Override
    protected int getToolbarTitleGravity() {
        return 0;
    }

    @Override
    public int getToolbarNavIcon() {
        return 0;
    }

    @Override
    public void onNavBarBackClicked() {

    }

    @Override
    public void handleToolbarComponent(TextView title, ImageView icon, ImageView secondIcon) {

    }

    @Override
    public void onCreatedView(@Nullable Bundle savedInstanceState) {
        setVersionInfo();
    }


    private void setVersionInfo() {
        viewDataBinding.setAppVersion(App.getInstance()
                .getResString(R.string.version_info , App.getInstance().getVersionName()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public int getLayout() {
        return R.layout.frg_splash;
    }

    @Override
    public Class<SplashViewModel> getViewModelClass() {
        return SplashViewModel.class;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public OnFrgDestroy destroy() {
        return null;
    }

    @Override
    public void onOpenMain() {
        destroyFrg();
        frgManager.showFrg(R.id.frame_activity_main ,
                true , HomeFrg.newInstance());
    }

}
