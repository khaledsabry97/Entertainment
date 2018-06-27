package com.example.khaledsabry.entertainment.Controllers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.khaledsabry.entertainment.Interfaces.OnImageConvertedSuccess;
import com.squareup.picasso.Picasso;

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

    private ImageController() {
    }


/*
    private static void show(final String posterUrl, final OnImageConvertedSuccess listener) {

        AsyncTask<String,Bitmap,Bitmap> R = new AsyncTask<String, Bitmap, Bitmap>() {

            @Override
            protected Bitmap doInBackground(String... strings) {
                String urlOfImage = posterUrl;
                Bitmap logo = null;
                try {
                    InputStream is = new URL(urlOfImage).openStream();
                    logo = BitmapFactory.decodeStream(is);
                } catch (Exception e) {
                    // Catch the download exception
                    e.printStackTrace();
                }

                final Bitmap finalLogo = logo;
                return finalLogo;
            }
/*

            @Override
            protected void onPostExecute(Bitmap s) {
                super.onPostExecute(s);
                listener.onImageConvertedSuccess(s);

            }
        };
        R.execute();


    }
*/
/*

    public static void getImageHighQuality(String posterImage,OnImageConvertedSuccess listener) {
       String url =  "https://image.tmdb.org/t/p/"+highQuality+posterImage;
       show(url,listener);
    }
    public static void getPostorImageMidQuality(String posterImage,OnImageConvertedSuccess listener) {
        String url =  "https://image.tmdb.org/t/p/"+midQuality+posterImage;
        show(url,listener);
    }
    public static void getPostorImageLowQuality(String posterImage,OnImageConvertedSuccess listener) {
        String url =  "https://image.tmdb.org/t/p/"+lowQuality+posterImage;
        show(url,listener);
    }
*/

    public static void putImageHighQuality(String posterImage, ImageView image) {
        String url =  "https://image.tmdb.org/t/p/"+highQuality+posterImage;
        Picasso.get().load(url).into(image);
    }

    public static void putImageMidQuality(String posterImage, ImageView image) {
        String url =  "https://image.tmdb.org/t/p/"+midQuality+posterImage;
        Picasso.get().load(url).into(image);
    }

    public static void putImageLowQuality(String posterImage, ImageView image) {
        String url =  "https://image.tmdb.org/t/p/"+lowQuality+posterImage;
        Picasso.get().load(url).into(image);
    }



}
