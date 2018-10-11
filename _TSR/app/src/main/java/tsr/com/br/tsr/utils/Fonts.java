
package tsr.com.br.tsr.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;

import tsr.com.br.tsr.R;

/**
 * @author vivianlo
 * Class responsible for the font definition used in the application
 */
public final class Fonts {

    private static Typeface robotoRegular;
    private static Typeface robotoBold;

    public static void initFonts(final Context context) {
        robotoRegular		= ResourcesCompat.getFont(context, R.font.roboto_regular);
        robotoBold			= ResourcesCompat.getFont(context, R.font.roboto_bold);
    }

    public static Typeface getRobotoBold() {
        return robotoBold;
    }

    public static Typeface getRobotoRegular() {
        return robotoRegular;
    }

}
