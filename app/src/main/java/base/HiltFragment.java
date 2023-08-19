package base;

import javax.inject.Inject;

import Custom.Toolbar;
import androidx.fragment.app.Fragment;
import dagger.hilt.android.AndroidEntryPoint;
import utils.FrgManager;

@AndroidEntryPoint
public abstract class HiltFragment extends Fragment {

    @Inject
    public Toolbar toolbar ;

    @Inject
    public FrgManager frgManager ;

}
