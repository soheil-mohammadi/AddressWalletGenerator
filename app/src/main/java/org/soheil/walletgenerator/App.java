package org.soheil.walletgenerator;


import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.util.DisplayMetrics;
import java.io.File;
import java.util.Locale;
import javax.inject.Inject;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import dagger.hilt.android.HiltAndroidApp;
import enums.FontType;
import utils.FontManager;
import utils.Utils;

/**
 * Created by soheilmohammadi on 9/4/18.
 */

@HiltAndroidApp
public class App extends Application {

    private  final String TAG = "App";
    public static Context context;

    public static File WORKPATH ;

    private static App instance ;

    private static AppCompatActivity currentActivity ;

    @Inject
    public Utils utils;

    @Inject
    public FontManager fontManager;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();


        WORKPATH = new File( context.getFilesDir().getAbsolutePath() + "/WalletGenerator");

        if(!WORKPATH.exists())
            WORKPATH.mkdir();

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        setLocale(getCurrentLang());


        instance = this;
    }

    private String getCurrentLang() {
        return  "en";
    }

    public Configuration setLocale(String lang) {

        if(lang.trim().equals(""))
            return null;

        Locale myLocale = new Locale(lang);

        Configuration config ;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            if(currentActivity != null) {
                config = currentActivity.getResources().getConfiguration();
            }else  {
                config = context.getResources().getConfiguration();
            }

            Locale.setDefault(myLocale);
            config.setLocale(myLocale);

            if(currentActivity != null) {
                currentActivity.createConfigurationContext(config);
                currentActivity.getResources().updateConfiguration(config,
                        currentActivity.getResources().getDisplayMetrics());
            }else {
                context.createConfigurationContext(config);
                context.getResources().updateConfiguration(config,
                        context.getResources().getDisplayMetrics());
            }



        }else  {
            Resources res ;

            if(currentActivity != null) {
                res = currentActivity.getResources();
            }else  {
                res = context.getResources();
            }

            DisplayMetrics dm = res.getDisplayMetrics();
            config = res.getConfiguration();
            config.setLocale(myLocale);

            Locale.setDefault(myLocale);
            config.setLocale(myLocale);
            res.updateConfiguration(config, dm);
        }


        return config;

    }


    public  String getResString(int res_id) {
        if(getCurrentActivity() != null)
            return getCurrentActivity().getString(res_id );
        else
            return context.getResources().getString(res_id);
    }


    public  String getResString(int res_id , Object ... formatArgs ) {
        if(getCurrentActivity() != null)
            return getCurrentActivity().getString(res_id  , formatArgs);
        else
            return context.getResources().getString(res_id , formatArgs);
    }


    public static void setCurrentActivity(AppCompatActivity activity) {
        currentActivity = activity;

    }

    public static AppCompatActivity getCurrentActivity() {
        return currentActivity;

    }

    public Context getContext() {
        return context;
    }

    public static App getInstance() {
        return instance;
    }


    public String getVersionName() {
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return  pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "1.0.0.0" ;
        }
    }

    public Utils getUtils() {
        return utils;
    }


    public Typeface getAppFont(FontType fontType) {
        return fontManager.getAppFont(fontType);
    }

}
