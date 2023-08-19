package base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.soheil.walletgenerator.App;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * Created by soheilmohammadi on 9/4/18.
 */

public abstract class BaseActivity <T extends ViewDataBinding> extends HiltActivity implements View.OnClickListener {

    private static final String TAG = "BaseActivity";

    private String currentLang ;

    View view;

    public T viewDataBinding;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        App.setCurrentActivity(this);

        this.viewDataBinding = DataBindingUtil.setContentView(this , getLayout());
        this.view = viewDataBinding.getRoot();
    }

    @Override
    protected void onStart() {
        super.onStart();
        App.setCurrentActivity(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        App.setCurrentActivity(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        App.setCurrentActivity(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        App.setCurrentActivity(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.setCurrentActivity(null);
    }

    public void notifyPropertyChanged (int fieldID) {
        viewDataBinding.notifyPropertyChanged(fieldID);
    }

    public abstract int getLayout();


}
