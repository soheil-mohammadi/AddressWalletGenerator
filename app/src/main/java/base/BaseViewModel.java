package base;

import java.lang.ref.WeakReference;

import androidx.lifecycle.ViewModel;

public abstract class BaseViewModel<N> extends ViewModel {

    private WeakReference<N> mNavigator;

    public N getNavigator() {
        return mNavigator.get();
    }

    public void setNavigator(N navigator) {
        this.mNavigator = new WeakReference<>(navigator);
    }

}