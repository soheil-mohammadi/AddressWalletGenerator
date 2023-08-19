package base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.soheil.walletgenerator.App;
import org.soheil.walletgenerator.BR;

import Listeners.OnCustomToolbarListener;
import Listeners.OnFrgDestroy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by soheilmohammadi on 9/22/18.
 */

public abstract class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel> extends HiltFragment  {

    private static final String TAG = "BaseFragment";

    private View view;
    private boolean isStopped = false;

    public T viewDataBinding;
    public V viewModel ;

    private AppCompatActivity frgActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        frgActivity = (AppCompatActivity) context;
    }

    private void initBinding () {
        viewDataBinding.setVariable(BR.viewModel , viewModel);
        viewDataBinding.setLifecycleOwner(this);
    }

    @Nullable
    public AppCompatActivity getFrgActivity() {
        return frgActivity;
    }

    public void notifyPropertyChanged (int fieldID) {
        viewDataBinding.notifyPropertyChanged(fieldID);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        if (view != null) {
            return view ;
        }


        viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()) , getLayout() , container , false);
        viewModel = new ViewModelProvider(this).get(getViewModelClass());
        view = viewDataBinding.getRoot();

        viewModel.setNavigator(this);

        initBinding();
        initToolbar();
        onCreatedView(savedInstanceState);

        return view;
    }


    private void initToolbar () {
        if(showToolbar()) {
            toolbar.view(view , getToolbarNavIcon())
                    .title(getToolbarTitle())
                    .image(getToolbarIcon())
                    .imageSecond(getToolbarSecondIcon())
                    .setTitleGravity(getToolbarTitleGravity());

            toolbar.build(new OnCustomToolbarListener() {
                @Override
                public void on_navBar_clicked() {
                    onNavBarBackClicked();
                }

                @Override
                public void handle_toolbar_component_(TextView title, ImageView icon , ImageView secondIcon) {
                    handleToolbarComponent(title , icon , secondIcon);
                }
            });
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        if(isStopped) {
            onResumedAfterStop();
            isStopped = false;
        }
    }


    @Override
    public void onStop() {
        super.onStop();
        isStopped = true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        view = null;
        frgActivity = null;
    }

    public void onBackPressed() {
        onBackAction();
    }

    public void defaultNavBackPressedToolbar() {
        onBackAction();
    }

    private void onBackAction() {
        OnFrgDestroy onFrgDestroy = destroy();

        if(onFrgDestroy == null) {

            int stackCounts = getFrgActivity().getSupportFragmentManager().getBackStackEntryCount();
            if(stackCounts <= 1) {
                App.getCurrentActivity().finish();
            }else  {
                destroyFrg();
            }
        }else {
            onFrgDestroy.onDestroy();
        }

    }

    public void onResumedAfterStop () {

    }

    //Toolbar Management :
    public abstract boolean showToolbar();
    public abstract String getToolbarTitle();
    public abstract int getToolbarIcon();
    public abstract int getToolbarSecondIcon();
    protected abstract int getToolbarTitleGravity();
    public abstract int getToolbarNavIcon();
    public abstract void onNavBarBackClicked();
    public abstract void handleToolbarComponent(TextView title , ImageView icon , ImageView secondIcon);


    public abstract void onCreatedView(@Nullable Bundle savedInstanceState);
    public abstract int getLayout();
    public abstract Class<V> getViewModelClass();
    public abstract void onClick(View view);
    public abstract OnFrgDestroy destroy();

    public void destroyFrg() {
        frgManager.destroyFrg(this);
    }

}
