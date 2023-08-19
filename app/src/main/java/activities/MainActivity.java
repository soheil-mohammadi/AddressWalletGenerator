package activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import org.soheil.walletgenerator.R;
import org.soheil.walletgenerator.databinding.ActivityMainBinding;

import javax.inject.Inject;

import Fragments.SplashFrg;
import base.BaseActivity;
import base.BaseFragment;
import dagger.hilt.android.AndroidEntryPoint;
import utils.Utils;


@AndroidEntryPoint
public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Inject
    public Utils utils ;

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSplash();
    }


    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }


    private void setSplash() {
        if(frgManager.getCurrentFrg(R.id.frame_activity_main) != null)
            return;

        frgManager.showFrgWithoutAnim(R.id.frame_activity_main , false
                , SplashFrg.newInstance());
    }



    @Override
    public void onBackPressed() {
        Object object = frgManager.getCurrentFrg(R.id.frame_activity_main);

        if(object instanceof BaseFragment)
            ((BaseFragment) object).onBackPressed();
        else
            super.onBackPressed();
    }


    @Override
    public void onClick(View v) {
        Object object = frgManager.getCurrentFrg(R.id.frame_activity_main);
        if(object instanceof BaseFragment)
            ((BaseFragment) object) .onClick(v);

    }

}
