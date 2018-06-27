package com.example.khaledsabry.entertainment.Controllers;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.example.khaledsabry.entertainment.Items.Genre;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.ProductionCompany;
import com.example.khaledsabry.entertainment.MainActivity;
import com.example.khaledsabry.entertainment.OnImageConvertedSuccess;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by KhALeD SaBrY on 27-Jun-18.
 */

public class Controller {
    private static Controller instance = null;

    private Controller() {

    }

    public static Controller getInstance() {
        if (instance == null)
            instance = new Controller();
        return instance;
    }




}
