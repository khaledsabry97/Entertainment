package com.example.khaledsabry.entertainment.Controllers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.Gravity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Interfaces.OnImageConvertedSuccess;

import java.io.InputStream;
import java.net.URL;

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

        if(image == null)
            return;
        String url =  "https://image.tmdb.org/t/p/"+highQuality+posterImage;
      //  Picasso.get().load(url).into(image);
        Glide.with(MainActivity.getActivity()).load(url).into(image);


    }

    public static void putImageMidQuality(String posterImage, ImageView image) {
        if(image == null)
            return;
        String url =  "https://image.tmdb.org/t/p/"+midQuality+posterImage;
       // Picasso.get().load(url).into(image);
        Glide.with(MainActivity.getActivity()).load(url).into(image);

    }

    public static void putImageLowQuality(String posterImage, ImageView image) {
        if(image == null)
            return;
        String url =  "https://image.tmdb.org/t/p/"+lowQuality+posterImage;
       // Picasso.get().load(url).into(image);
        Glide.with(MainActivity.getActivity()).load(url).into(image);

    }



}
