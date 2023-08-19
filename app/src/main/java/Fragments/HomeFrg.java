package Fragments;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import org.soheil.walletgenerator.App;
import org.soheil.walletgenerator.R;
import org.soheil.walletgenerator.databinding.FrgHomeBinding;

import javax.inject.Inject;

import Adapter.WordsAdapter;
import Custom.Toolbar;
import Listeners.OnFrgDestroy;
import Models.AddressModel;
import Models.SignMsgModel;
import Navigator.HomeNavigator;
import ViewModel.HomeViewModel;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import base.BaseFragment;
import dagger.hilt.android.AndroidEntryPoint;
import utils.Utils;


@AndroidEntryPoint
public class HomeFrg extends BaseFragment<FrgHomeBinding, HomeViewModel> implements HomeNavigator {

    private static final String TAG = "HomeFrg";

    @Inject
    public Utils utils;

    @Inject
    public WordsAdapter wordsAdapter;


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
        observeGenerationAddress();
        observeSignMessage();
    }


    private void observeGenerationAddress () {

        viewDataBinding.containerGeneratedHomeState.recyclerMnemonicGeneratedHomeState
                .setAdapter(wordsAdapter);

        viewModel.getGeneratedAddress().observe(this, new Observer<AddressModel>() {
            @Override
            public void onChanged(AddressModel addressModel) {
                if(addressModel == null)
                    return;

                wordsAdapter.submitList(addressModel.getMnemonic());

                viewDataBinding.containerGeneratedHomeState.txtAddressGeneratedHomeState
                        .setText(addressModel.getAddress());

                viewDataBinding.containerGeneratedHomeState.txtPrivateKeyGeneratedHomeState
                        .setText(addressModel.getPrivateKey());
            }
        });
    }


    private void observeSignMessage () {

        viewModel.getSignMessage().observe(this, new Observer<SignMsgModel>() {
            @Override
            public void onChanged(SignMsgModel signMsgModel) {
                if(signMsgModel == null)
                    return;

                viewDataBinding.containerSignedMsgHomeState.txtSignatureSignedHomeState
                        .setText(signMsgModel.getSignature());

            }
        });
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


    @Override
    public void showToastMsg(String msg) {
        utils.showSuccessToast(msg);
    }
}
