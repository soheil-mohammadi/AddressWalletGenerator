package utils;

import org.soheil.walletgenerator.App;
import org.soheil.walletgenerator.R;

import javax.inject.Inject;
import javax.inject.Singleton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import base.BaseFragment;

@Singleton
public class FrgManager {

    private static final String TAG = "FrgManager";

    @Inject
    public FrgManager() {

    }

    public void showFrgWithoutAnim(int idResFrame , Boolean isReplace , Fragment fragment) {
        if(App.getCurrentActivity() != null) {
            try {
                FragmentManager fragmentManager = App.getCurrentActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                if(isReplace) {
                    fragmentTransaction.replace(idResFrame , fragment , fragment.getClass().getName());
                }else {
                    Fragment currentFrg = fragmentManager.findFragmentById(idResFrame);
                    if(currentFrg != null)
                        fragmentTransaction.hide(currentFrg);
                    fragmentTransaction.add(idResFrame , fragment , fragment.getClass().getName());
                }


                if(isReplace)
                    fragmentTransaction.addToBackStack(fragment.getClass().getName());
                fragmentTransaction.commit();

            }catch (Exception e) {

            }

        }

    }



    public void showFrg(int idResFrame , Boolean isReplace , Fragment fragment) {
        if(App.getCurrentActivity() != null) {
            try {
                FragmentManager fragmentManager = App.getCurrentActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right,
                        R.anim.enter_from_right, R.anim.exit_to_left);

                if(isReplace) {
                    fragmentTransaction.replace(idResFrame , fragment , fragment.getClass().getName());
                }else {
                    Fragment currentFrg = fragmentManager.findFragmentById(idResFrame);
                    if(currentFrg != null)
                        fragmentTransaction.hide(currentFrg);
                    fragmentTransaction.add(idResFrame , fragment , fragment.getClass().getName());
                }


                fragmentTransaction.addToBackStack(fragment.getClass().getName());
                fragmentTransaction.commit();

            }catch (Exception e) {

            }

        }

    }

    public Object getCurrentFrg(int idResFrame) {
        if(App.getCurrentActivity() != null) {
            FragmentManager fragmentManager = App.getCurrentActivity().getSupportFragmentManager();
            if(fragmentManager.findFragmentById(idResFrame) instanceof BaseFragment)
                return (BaseFragment) fragmentManager.findFragmentById(idResFrame);

        }

        return  null;
    }



    public boolean isFragmentExistInBackStack (Fragment fragment) {

        if(App.getCurrentActivity() != null) {
            FragmentManager fm = App.getCurrentActivity().getSupportFragmentManager();
            return  fm.findFragmentByTag(fragment.getClass().getName()) != null;
        }

        return false;

    }
    public void removeAllFrgs() {

        if(App.getCurrentActivity() != null) {

            FragmentManager fm = App.getCurrentActivity().getSupportFragmentManager();
            for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                try {
                    fm.popBackStack();
                }catch (IllegalStateException e) {

                }
            }
        }

    }

    public <T extends Fragment>  void removeSpecificFrg(Class<T> targetFrg) {

        if(App.getCurrentActivity() != null) {

            FragmentManager fm = App.getCurrentActivity().getSupportFragmentManager();
            for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                try {
                    if(targetFrg.getName().equals(fm.getBackStackEntryAt(i).getName())) {
                        fm.popBackStack();
                        break;
                    }
                }catch (IllegalStateException e) {

                }
            }
        }

    }

    public void removeFrg(Fragment fragment) {

        if(App.getCurrentActivity() != null) {
            try {
                FragmentManager fm = App.getCurrentActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.remove(fragment).commit();
            }catch (IllegalStateException e) {

            }
        }

    }

    public <T extends Fragment> void removeUntilSpecificFragment(Class<T> targetFrg) {

        if(App.getCurrentActivity() != null) {
            FragmentManager fm = App.getCurrentActivity().getSupportFragmentManager();
            fm.popBackStack(targetFrg.getName(), 0);
        }
    }


    public void destroyFrg(Fragment fragment) {

        if(isFragmentExistInBackStack(fragment)) {
            if(App.getCurrentActivity() != null) {
                FragmentManager fm = App.getCurrentActivity().getSupportFragmentManager();
                fm.popBackStackImmediate();
            }
        }else {
            removeFrg(fragment);
        }

    }
}
