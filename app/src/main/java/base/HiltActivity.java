package base;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import dagger.hilt.android.AndroidEntryPoint;
import utils.FrgManager;
import utils.Utils;

@AndroidEntryPoint
public abstract class HiltActivity extends AppCompatActivity {

    @Inject
    public FrgManager frgManager;

    @Inject
    public Utils utils;
}
