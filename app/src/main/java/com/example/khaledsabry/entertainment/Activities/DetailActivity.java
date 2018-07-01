package com.example.khaledsabry.entertainment.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MenuItem;

import com.example.khaledsabry.entertainment.Fragments.CastAndCrewFragment;
import com.example.khaledsabry.entertainment.Fragments.MovieDetailFragment;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;

/**
 * Created by KhALeD SaBrY on 30-Jun-18.
 */

public class DetailActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener {

    static Movie movie;

    private static DetailActivity detailActivity;
    public DetailActivity() {
        detailActivity = this;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detail);

        movie = MainActivity.movie;
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setOnNavigationItemReselectedListener(this);

    }


    void loadFragment(Fragment fragment)
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.moviedetailid, fragment).commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.navigation_home)
            loadFragment(MovieDetailFragment.newInstance(movie));
        else if(item.getItemId() == R.id.navigation_dashboard)
            loadFragment(CastAndCrewFragment.newInstance(movie));
        return true;
    }

    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {

    }


    int getwidth()
    {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
    return height;}
}
