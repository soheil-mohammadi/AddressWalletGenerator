package utils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Base64;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import org.soheil.walletgenerator.App;
import org.soheil.walletgenerator.R;

import javax.inject.Inject;
import javax.inject.Singleton;
import androidx.annotation.DrawableRes;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.core.content.ContextCompat;

@Singleton
public class GlideManager {

    private static final String TAG = "GlideManager";
    private RequestManager glide ;

    @Inject
    public GlideManager(RequestManager glide) {
        this.glide = glide;
    }

    public void loadRes(int res , ImageView imageView) {
        glide.load(Build.VERSION.SDK_INT < Build.VERSION_CODES.N ? res : loadDrawable(res)).
                apply(new RequestOptions()
                        .fitCenter()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                ).into(imageView);
    }

    public void loadRoundRes(int res , ImageView imageView) {

        glide.load(Build.VERSION.SDK_INT < Build.VERSION_CODES.N ? res : loadDrawable(res)).
                apply(new RequestOptions()
                        .fitCenter()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE))
                .circleCrop().into(imageView);
    }

    public void loadBytes(String bytes , ImageView imageView) {
        byte[] imageByteArray = Base64.decode(bytes, Base64.DEFAULT);
        glide.load(imageByteArray).into(imageView);
    }

    public void loadUrl(String imgUrl , ImageView imageView) {

        if (imgUrl.equals("")) {
            loadRes(R.drawable.ic_logo , imageView);
        }else {
            glide.load(imgUrl).into(imageView);
        }

    }

    public void loadUrl(String imgUrl , int placeHolder , ImageView imageView) {

        if (imgUrl.equals("")) {
            loadRes(placeHolder , imageView);
        }else {
            glide.load(imgUrl).into(imageView);
        }

    }

    private Drawable loadDrawable (@DrawableRes int resourceID) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            return AppCompatDrawableManager.get().getDrawable( App.getCurrentActivity() != null ?
                    App.getCurrentActivity() : App.context , resourceID);
        }else {
            return ContextCompat.getDrawable( App.getCurrentActivity() != null ?
                    App.getCurrentActivity() : App.context , resourceID);
        }

    }


    public void loadDrawable (Drawable drawable , ImageView imageView) {
        glide.load(drawable).
                apply(new RequestOptions()
                        .fitCenter()).into(imageView);
    }


    public void loadBitmap(Bitmap bitmap , ImageView imageView) {
        glide.load(bitmap).into(imageView);
    }

    public void loadBitmapWithoutPlaceHolder(Bitmap bitmap , ImageView imageView) {
        glide.load(bitmap).into(imageView);
    }

    public void loadRes(int res  , ImageView imageView , int placeHolder) {
        glide.load(loadDrawable(res)).
                placeholder(placeHolder).into(imageView);
    }


    public void loadRes(String resName , ImageView imageView) {

        glide.load(App.context.getResources().getIdentifier(resName , "drawable" ,
                        App.context.getPackageName()))
                .apply(new RequestOptions()
                        .fitCenter()
                        .skipMemoryCache(true)
                ).into(imageView);
    }


}
