package Custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import org.soheil.walletgenerator.App;
import org.soheil.walletgenerator.R;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import enums.FontType;

public class CustomTextView extends AppCompatTextView {

    private Boolean isBold  = false ;
    private Boolean isMedium  = false ;

    public CustomTextView(Context context) {
        super(context);
        init(null);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {

        if(attrs != null) {
            TypedArray typedArray = App.context.obtainStyledAttributes(attrs , R.styleable.CustomTextView);
            isBold = typedArray.getBoolean(R.styleable.CustomTextView_isBold , false);
            isMedium = typedArray.getBoolean(R.styleable.CustomTextView_isMedium , false);
        }

        if(isMedium) {
            setTypeface(App.getInstance().getAppFont(FontType.MEDIUM));
        }else if (isBold) {
            setTypeface(App.getInstance().getAppFont(FontType.BOLD));
        }else {
            setTypeface(App.getInstance().getAppFont(FontType.REGULAR));
        }

    }


    public void setBold(boolean bold) {
        isBold = bold;
        init(null);
    }

    public void setMedium(boolean medium) {
        isMedium = medium;
        init(null);
    }

    public void setRegular() {
        isBold = false;
        isMedium = false;
        init(null);
    }
}
