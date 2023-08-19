package Listeners;

import android.widget.ImageView;
import android.widget.TextView;


public interface OnCustomToolbarListener {
    void  on_navBar_clicked();
    void handle_toolbar_component_(TextView title, ImageView icon , ImageView secondIcon);
}
