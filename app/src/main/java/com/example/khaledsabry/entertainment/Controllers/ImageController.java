package com.example.khaledsabry.entertainment.Controllers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestFutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Database.Origin.DatabaseServer;
import com.example.khaledsabry.entertainment.Interfaces.OnImageConvertedSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnImagesDownloaded;

import java.io.InputStream;
import java.net.URL;
import java.nio.InvalidMarkException;
import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 27-Jun-18.
 */

public class ImageController {
    private static final String highQuality = "original";
    private static final String midQuality  = "w500";
    private static final String lowQuality = "w185";
    private static final ImageController ourInstance = new ImageController();

    public static ImageController getInstance() {
        return ourInstance;
    }



    public static void putImageHighQuality(String posterImage, ImageView image) {

        if(image == null || posterImage == null)
            return;
        if(posterImage.equals("null"))
            return;
        String url =  "https://image.tmdb.org/t/p/"+highQuality+posterImage;
      //  Picasso.get().load(url).into(image);
        Glide.with(MainActivity.getActivity()).load(url).into(image);


    }

    public static void putImageMidQuality(String posterImage, ImageView image) {
        if(image == null || posterImage == null)
            return;
        if(posterImage.equals("null"))
            return;
        String url =  "https://image.tmdb.org/t/p/"+midQuality+posterImage;
       // Picasso.get().load(url).into(image);
        Glide.with(MainActivity.getActivity()).load(url).into(image);

    }

    public static void putImageLowQuality(String posterImage, ImageView image) {
        if(image == null || posterImage == null)
            return;
        if(posterImage.equals("null"))
            return;
        String url =  "https://image.tmdb.org/t/p/"+lowQuality+posterImage;
       // Picasso.get().load(url).into(image);
        Glide.with(MainActivity.getActivity()).load(url).into(image);

    }




    public static void putImageToImageView(String posterImage, ImageView image) {
       // if(image == null || posterImage == null)
    //        return;

        try {
            String url = posterImage;
            // Picasso.get().load(url).into(image);
            Glide.with(MainActivity.getActivity()).load(url).into(image);
        }      catch (Exception e)
        {

        }

    }

    public static void putDrawableToImageView(int posterImage, ImageView image) {
        try {

            Glide.with(MainActivity.getActivity()).load(posterImage).into(image);
        }      catch (Exception e)
        {

        }

    }


}
