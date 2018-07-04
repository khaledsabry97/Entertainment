package com.example.khaledsabry.entertainment.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.khaledsabry.entertainment.Connection.TmdbConnection;
import com.example.khaledsabry.entertainment.Fragments.MovieView.DetailFragment;
import com.example.khaledsabry.entertainment.Fragments.SearchFragment;
import com.example.khaledsabry.entertainment.NavigationDrawer;
import com.example.khaledsabry.entertainment.R;
import com.example.khaledsabry.entertainment.Controllers.Settings;

import java.util.Timer;
import java.util.TimerTask;

import static java.nio.file.Files.copy;

public class MainActivity extends AppCompatActivity {

    private static MainActivity mainActivity;

    public MainActivity() {
        mainActivity = this;
    }

    public static MainActivity getActivity() {
        return mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Settings.getInstance().setWidthAndHeight(getWindowManager());
        TmdbConnection.getInstance().setContext(getApplicationContext());


        periodicHideNavigation();
  //      loadMovieDetailFragment(68735);

        //startActivity(new Intent(this,ANOTHER.class));
       loadFragment(R.id.mainContainer, SearchFragment.newInstance());

    }

    public void loadMovieDetailFragment(int movieId) {
        DetailFragment detailFragment = DetailFragment.newInstance(movieId, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, detailFragment).addToBackStack(null).commit();

    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus)
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


    public void loadFragment(int idContainer, android.support.v4.app.Fragment fragment) {

        getSupportFragmentManager().beginTransaction().replace(idContainer, fragment).addToBackStack(null).commit();
    }


    private void periodicHideNavigation()
    {
        final Handler handler = new Handler();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        hideSystemUI();

                    }
                });

            }
        }, 1000, 2000);
    }

}
