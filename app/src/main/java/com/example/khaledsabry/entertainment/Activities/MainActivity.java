package com.example.khaledsabry.entertainment.Activities;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.khaledsabry.entertainment.Connection.ApiConnections;
import com.example.khaledsabry.entertainment.Fragments.MainMenu.MainMenuFragment;
import com.example.khaledsabry.entertainment.R;
import com.example.khaledsabry.entertainment.Controllers.Settings;

import java.util.Timer;
import java.util.TimerTask;

import static java.nio.file.Files.copy;

public class MainActivity extends AppCompatActivity {

    //to get the main activity in the app
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
        //get width and height for the mobile and tablet
        Settings.getInstance().setWidthAndHeight(getWindowManager());
        //set the context for the volley library
        ApiConnections.getInstance().setContext(getApplicationContext());

// hide the navigation bar and the status bar
        periodicHideNavigation();
//loadFragmentNoReturn(R.id.mainContainer, SearchFragment.newInstance());
       loadFragmentWithReturn(R.id.mainContainer, MainMenuFragment.newInstance());
      //  loadFragmentNoReturn(R.id.mainContainer, NavigationDrawer.newInstance());

    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus)
            hideSystemUI();

    }

    public void hideSystemUI() {
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


    public static void loadFragmentWithReturn(int idContainer, android.support.v4.app.Fragment fragment) {

        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(idContainer, fragment).addToBackStack(null).commit();
    }

    public static void loadFragmentNoReturn(int idContainer, android.support.v4.app.Fragment fragment) {

        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(idContainer, fragment).commit();
    }
    private void periodicHideNavigation() {
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
