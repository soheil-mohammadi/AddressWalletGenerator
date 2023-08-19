package di.Modules;


import android.content.Context;
import android.os.Handler;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;

@Module
@InstallIn(ApplicationComponent.class)
public class AppModule {

    @Provides
    RequestManager providesGlide (@ApplicationContext Context context) {
        return Glide.with(context);
    }

    @Provides
    Handler providesHandler () {
        return new Handler();
    }


}