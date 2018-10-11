package tsr.com.br.tsr.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

/**
 * @author vivianlo
 * Class responsible for displaying and load the image
 */
public final class ImageUtils {

    /**
     * This method will display a placeholder to indicate the loading of the image.
     * If you can successfully load the image, the image will be displayed.
     * If you cannot load the photo, the placeholder will continue to be displayed and the background color will change to white.
     * @param context - To load image with glide
     * @param url - Image URL
     * @param relImage - To change background color
     * @param imageView - Where will be loaded the photo
     * @param imageViewPlaceholder - Placeholder
     */
    public static void loadImage (final Context context, final String url, final RelativeLayout relImage, final ImageView imageView, final ImageView imageViewPlaceholder) {

        Glide.with(context)
                .load(url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        new android.os.Handler().postDelayed(
                                new Runnable() {
                                    public void run() {
                                        loadFail(context, relImage, imageViewPlaceholder);
                                    }
                                },
                                100);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        new android.os.Handler().postDelayed(
                                new Runnable() {
                                    public void run() {
                                        loadSucess(context, relImage, imageViewPlaceholder);
                                    }
                                },
                                100);

                        return false;
                    }
                })
                .apply( new RequestOptions().fitCenter())
                .into(imageView);
    }

    private static void loadFail (final Context context, RelativeLayout relImage, final ImageView imageViewPlaceholder) {
        imageViewPlaceholder.setAlpha(0.4f);
        imageViewPlaceholder.setVisibility(View.VISIBLE);
        relImage.setBackgroundColor(context.getResources().getColor(android.R.color.white));
    }

    private static void loadSucess (final Context context, RelativeLayout relImage, final ImageView imageViewPlaceholder) {
        imageViewPlaceholder.setVisibility(View.GONE);
        relImage.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
    }
}
