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
import com.example.khaledsabry.entertainment.Fragments.BlankFragment;
import com.example.khaledsabry.entertainment.Fragments.FullPoster;
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

    private static MainActivity mainActivity;
    public MainActivity() {
        mainActivity = this;
    }

    public static MainActivity getActivity()
    {
        return mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        BlankFragment blankFragment = BlankFragment.newInstance();
        if(blankFragment == null)
        {blankFragment = BlankFragment.newInstance();
        }
        fm.beginTransaction().add(R.id.mainContainer,blankFragment).commit();
    }

    public void loadMovieDetailFragment(Movie movie)
    {
        MovieDetailFragment movieDetailFragment = MovieDetailFragment.newInstance(movie);
        getSupportFragmentManager().beginTransaction().add(R.id.mainContainer,movieDetailFragment).addToBackStack(null).commit();
    }


    public  void loadFullPosterFragment(Movie movie)
    {
        FullPoster fullPoster = FullPoster.newInstance(movie);
        getSupportFragmentManager().beginTransaction().add(R.id.mainContainer,fullPoster).addToBackStack(null).commit();
    }




}
