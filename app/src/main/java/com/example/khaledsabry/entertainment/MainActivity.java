package com.example.khaledsabry.entertainment;

import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.khaledsabry.entertainment.Connection.TmdbConnection;
import com.example.khaledsabry.entertainment.Connection.TmdbType;
import com.example.khaledsabry.entertainment.Controllers.Controller;
import com.example.khaledsabry.entertainment.Fragments.MovieDetailFragment;
import com.example.khaledsabry.entertainment.Items.Movie;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;

import static java.nio.file.Files.copy;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    private static final int IO_BUFFER_SIZE = 4 * 1024;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        MovieDetailFragment movieDetailFragment = MovieDetailFragment.newInstance();
        if(movieDetailFragment == null)
        {movieDetailFragment = MovieDetailFragment.newInstance();
        }
        fm.beginTransaction().add(R.id.mainContainer,movieDetailFragment).commit();


        /*
        TmdbConnection.getInstance().setContext(this);
        TmdbType tmdbType = new TmdbType();
        tmdbType.getMovieGetDetails(351286);
        Controller.getInstance().setMainActivity(this);
        imageView = findViewById(R.id.imageView);*/
    }




}
