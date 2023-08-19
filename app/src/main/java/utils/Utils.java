package utils;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.service.notification.StatusBarNotification;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.soheil.walletgenerator.App;
import org.soheil.walletgenerator.R;
import org.soheil.walletgenerator.BuildConfig;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import javax.inject.Singleton;

import Custom.MDToast;
import activities.MainActivity;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import androidx.databinding.BindingAdapter;
import androidx.databinding.adapters.Converters;
import dagger.hilt.android.qualifiers.ApplicationContext;
import timber.log.Timber;

import static android.content.Context.ACTIVITY_SERVICE;
import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.content.Context.NOTIFICATION_SERVICE;


@Singleton
public class Utils {

    private static final String TAG = "Utils";

    private Context context;

    private Handler handler;

    private static GlideManager glideManager;


    @Inject
    public Utils(@ApplicationContext Context context , GlideManager glideManager) {
        this.handler = new Handler(Looper.getMainLooper());
        this.context = context;
        this.glideManager = glideManager;
    }



    public boolean isAppInForeground()
    {

        ActivityManager am = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        ActivityManager.RunningTaskInfo foregroundTaskInfo = am.getRunningTasks(1).get(0);
        String foregroundTaskPackageName = foregroundTaskInfo.topActivity.getPackageName();

        return foregroundTaskPackageName.toLowerCase().equals(context.getPackageName().toLowerCase());

    }

    public  void copyFromAsset(String dir , String assetPath ,  String fileName) {
        AssetManager assetManager = context.getAssets();
        try {
            InputStream in = assetManager.open(assetPath);
            File outFile = new File(dir, fileName);
            if(outFile.exists()) {
                return;
            }
            OutputStream out = new FileOutputStream(outFile);
            copyFile(in, out);
            in.close();
            out.flush();
            out.close();
        } catch(IOException e) {
        }
    }

    public void copyToClipboard(String text) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(App.getInstance().getResString(R.string.app_name), text);
        if (clipboard != null) {
            clipboard.setPrimaryClip(clip);
        }
    }



    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }


    @BindingAdapter("resSrc")
    public static void setImageResWithGlide(ImageView imageView, int resource) {
        glideManager.loadRes(resource, imageView);
    }

    @BindingAdapter("android:layout_width")
    public static void setLayoutWidth(View view, float width) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = width == -1 ? ViewGroup.LayoutParams.WRAP_CONTENT :  (int) width;
        view.setLayoutParams(layoutParams);
    }


    @BindingAdapter("android:layout_height")
    public static void setLayoutHeight(View view, float height) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = height == -1 ? ViewGroup.LayoutParams.WRAP_CONTENT :  (int) height;
        view.setLayoutParams(layoutParams);
    }


    public void showErrorToast(String error) {
        MDToast.makeText(App.context, error, MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();
    }

    public void showInfoToast(String msg) {
        MDToast.makeText(App.context, msg, MDToast.LENGTH_SHORT, MDToast.TYPE_INFO).show();
    }

    public void showWarningToast(String msg) {
        MDToast.makeText(App.context, msg, MDToast.LENGTH_SHORT, MDToast.TYPE_WARNING).show();
    }

    public void showWarningLongToast(String msg) {
        MDToast.makeText(App.context, msg, MDToast.LENGTH_LONG, MDToast.TYPE_WARNING).show();
    }

    public void showSuccessToast(String msg) {
        MDToast.makeText(App.context, msg, MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();
    }

    public float dpFromPx(float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public int getRealHeightScreenSize () {
        int mRealSizeHeight  ;

        WindowManager windowManager =
                (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        final Display display = windowManager.getDefaultDisplay();
        Point outPoint = new Point();
        display.getRealSize(outPoint);

        if (outPoint.y > outPoint.x) {
            mRealSizeHeight = outPoint.y;
        } else {
            mRealSizeHeight = outPoint.x;
        }

        return mRealSizeHeight;
    }

    public boolean isDeviceNormalOrLarge() {


        //Detecting Tablet Devices :
        if(getScreenInches() >= 7) {
            return true;
        }

        // Based On This :: https://developer.android.com/guide/topics/large-screens/support-different-screen-sizes

        float screenHeightDp = dpFromPx(getRealHeightScreenSize());

      //  Utils.showErrorLog(TAG , "Screen Size In Inches :: " + getScreenInches() + " ... " + screenHeightDp  );

        return screenHeightDp >= 760 ;

    }


    public void removeFile(String filePath) {
        File file = new File(filePath);
        file.deleteOnExit();
    }

    public String readFile(String filePath) throws Exception {
        BufferedReader b = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
        String ret = "";
        try {
            for (; ; ) {
                String s = b.readLine();
                if (s == null) break;
                ret += s;
            }
        } catch (EOFException e) {
        }
        return ret;
    }

    public String readFileFromAssets(String name) throws Exception {
        BufferedReader b = new BufferedReader(new InputStreamReader(App.context.getAssets().open(name)));
        String ret = "";
        try {
            for (; ; ) {
                String s = b.readLine();
                if (s == null) break;
                ret += s;
            }
        } catch (EOFException e) {
        }
        return ret;
    }




    public double getScreenInches() {

        DisplayMetrics dm = new DisplayMetrics();
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        display.getMetrics(dm);
        int widthPixels = dm.widthPixels;
        int heightPixels = dm.heightPixels;


        try {
            Point realSize = new Point();
            Display.class.getMethod("getRealSize", Point.class).invoke(display, realSize);
            widthPixels = realSize.x;
            heightPixels = realSize.y;
        } catch (Exception ignored) {
        }

        return Math.sqrt(Math.pow(widthPixels / dm.xdpi, 2) + Math.pow(heightPixels / dm.ydpi, 2));

    }

    public void openUrl(String url) {
        if(url == null)
            return;

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        try {
            App.getCurrentActivity().startActivity(intent);
        } catch (ActivityNotFoundException e) {
            showWarningToast(App.getInstance().getResString(R.string.not_found_act_for_handle));
        } catch (Exception c) {

        }
    }

    public int getColor(@ColorRes int color) {
        return ContextCompat.getColor(context, color);
    }


    public static void showErrorLog(String tag, String content) {
        if (BuildConfig.DEBUG)
            Timber.tag(tag).e(content);
    }

    public void showLongErrorLog (String tag , String log) {
        if (BuildConfig.DEBUG) {
            int maxLogSize = 1000;
            for(int i = 0; i <= log.length() / maxLogSize; i++) {
                int start = i * maxLogSize;
                int end = (i+1) * maxLogSize;
                end = Math.min(end, log.length());
                Timber.tag(tag).e(log.substring(start, end));
            }
        }

    }

    public int loadDrawableIntByName(String name) {
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }

}
