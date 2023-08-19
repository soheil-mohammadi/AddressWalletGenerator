package utils;


import android.graphics.Typeface;
import org.soheil.walletgenerator.App;
import org.soheil.walletgenerator.R;
import javax.inject.Inject;
import javax.inject.Singleton;
import androidx.core.content.res.ResourcesCompat;
import enums.FontType;

@Singleton
public class FontManager {

   private static final String TAG = "FontManager";

   @Inject
   public FontManager() {

   }


   public Typeface getAppFont(Boolean isBold) {
      return ResourcesCompat.getFont(App.context, isBold ?  R.font.en_bold : R.font.en);
   }

   public Typeface getAppFont(FontType fontType) {

      switch (fontType) {

         case BOLD:
            return ResourcesCompat.getFont(App.context, R.font.en_bold );

         case MEDIUM:
            return ResourcesCompat.getFont(App.context, R.font.en_medium);

         case REGULAR:
            return ResourcesCompat.getFont(App.context, R.font.en);

      }


      return ResourcesCompat.getFont(App.context, R.font.en);

   }

}
