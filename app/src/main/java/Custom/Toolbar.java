package Custom;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Bitmap;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.soheil.walletgenerator.App;
import org.soheil.walletgenerator.R;

import javax.inject.Inject;
import Listeners.OnCustomToolbarListener;
import androidx.appcompat.app.ActionBar;
import utils.GlideManager;


/**
 * Created by soheilmohammadi on 6/2/18.
 */


public class Toolbar {

    private static final String TAG = "Toolbar";

    private androidx.appcompat.widget.Toolbar toolbar ;
    private View view ;

    private LinearLayout container_toolbar , container_main_content_toolbar  ;
    private View spaceViewToolbar ;
    private ImageView img_main_toolbar  ,  img_second_toolbar  ;
    private TextView txt_title_toolbar ;
    private Boolean hasNavBar = false;

    public static final  int NO_IMG_TOOL = -1;
    public static final  int DEFAULT_IMG_NAV_BACK = -2;

    private GlideManager glideManager;

    @Inject
    public Toolbar(GlideManager glideManager) {
        this.glideManager = glideManager;
    }

    public Toolbar view(View view) {
        this.view = view  ;
        initViews();
        return this ;
    }

    public Toolbar view(View view , Boolean hasNavBar) {
        this.view = view  ;
        initViews();
        App.getInstance().getCurrentActivity().setSupportActionBar(toolbar);
        ActionBar actionBar = App.getInstance().getCurrentActivity().getSupportActionBar();
        this.hasNavBar = hasNavBar;
        actionBar.setDisplayHomeAsUpEnabled(hasNavBar);
        actionBar.setDisplayShowHomeEnabled(hasNavBar);

        return this ;
    }

    public Toolbar view(View view , int navBarIcon) {
        this.view = view  ;
        initViews();
        App.getInstance().getCurrentActivity().setSupportActionBar(toolbar);
        ActionBar actionBar = App.getInstance().getCurrentActivity().getSupportActionBar();
        if(navBarIcon == NO_IMG_TOOL)  {
            this.hasNavBar = false;
        }else {
            this.hasNavBar = true;
            if(navBarIcon != DEFAULT_IMG_NAV_BACK)
                actionBar.setHomeAsUpIndicator(navBarIcon);
        }

        actionBar.setDisplayHomeAsUpEnabled(hasNavBar);
        actionBar.setDisplayShowHomeEnabled(hasNavBar);

        return this ;
    }

    public void hide() {
        container_toolbar.setVisibility(View.GONE);
    }


    private void initViews() {
        toolbar = view.findViewById(R.id.toolbar);
        container_toolbar = view.findViewById(R.id.container_toolbar);
        container_main_content_toolbar = view.findViewById(R.id.container_main_content_toolbar);
        img_main_toolbar = view.findViewById(R.id.img_main_toolbar);
        img_second_toolbar = view.findViewById(R.id.img_second_toolbar);
        txt_title_toolbar = view.findViewById(R.id.txt_title_toolbar);
        spaceViewToolbar = view.findViewById(R.id.spaceViewToolbar);
    }


    public Toolbar backColor(int color) {
        container_toolbar.setBackgroundColor(color);
        return this ;
    }

    public Toolbar backDrawable(int drawableRes) {
        container_toolbar.setBackgroundResource(drawableRes);
        return this ;
    }


    public Toolbar title(String title) {
        if (title != null && !title.trim().equals("")){
            txt_title_toolbar.setVisibility(View.VISIBLE);
            txt_title_toolbar.setText(title);
        }
        else
            txt_title_toolbar.setVisibility(View.GONE);

        return this ;
    }



    public Toolbar image (Bitmap toolbarImage) {
        if (toolbarImage != null){
            glideManager.loadBitmap(toolbarImage , img_main_toolbar);
        }else {
            img_main_toolbar.setVisibility(View.INVISIBLE);
        }

        return this ;
    }

    public Toolbar image (int resToolbarImage) {
        if (resToolbarImage != NO_IMG_TOOL){
            img_main_toolbar.setVisibility(View.VISIBLE);
            glideManager.loadRes(resToolbarImage , img_main_toolbar);
        }else {
            img_main_toolbar.setVisibility(View.INVISIBLE);
        }

        return this ;
    }

    public Toolbar imageSecond (int resToolbarImage) {
        if (resToolbarImage != NO_IMG_TOOL) {
            img_second_toolbar.setVisibility(View.VISIBLE);
            spaceViewToolbar.setVisibility(View.VISIBLE);
            img_second_toolbar.setImageResource(resToolbarImage);
        } else {
            spaceViewToolbar.setVisibility(View.GONE);
            img_second_toolbar.setVisibility(View.GONE);
        }

        return this ;
    }

    public Toolbar setTitleGravity (int gravity) {
        txt_title_toolbar.setGravity(gravity);
        return this ;
    }


    public void build (final OnCustomToolbarListener listener) {

        listener.handle_toolbar_component_(txt_title_toolbar , img_main_toolbar , img_second_toolbar);
        if(hasNavBar) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.on_navBar_clicked();
                }
            });
        }
    }

}
