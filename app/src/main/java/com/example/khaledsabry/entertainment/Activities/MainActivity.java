package com.example.khaledsabry.entertainment.Activities;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.khaledsabry.entertainment.Fragments.BlankFragment;
import com.example.khaledsabry.entertainment.Fragments.DetailFragment;
import com.example.khaledsabry.entertainment.Fragments.FullPoster;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;

import static java.nio.file.Files.copy;

public class MainActivity extends AppCompatActivity {

    static Movie movie;
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

    public  void loadMovieDetailFragment(Movie movie)
    {
        DetailFragment movieDetailFragment = DetailFragment.newInstance(movie);
        getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer,movieDetailFragment).addToBackStack(null).commit();

    }


    public  void loadFullPosterFragment(String poster)
    {
        FullPoster fullPoster = FullPoster.newInstance(poster);
        getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer,fullPoster).addToBackStack(null).commit();
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus)
            hideSystemUI();

    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);


    }



    public void loadFragment(android.support.v4.app.Fragment fragment)
    {

       getSupportFragmentManager().beginTransaction().add(R.id.mainContainer,fragment).addToBackStack(null).commit();
    }
}
