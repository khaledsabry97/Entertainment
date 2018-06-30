package com.example.khaledsabry.entertainment.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.khaledsabry.entertainment.Fragments.DetailFragment;
import com.example.khaledsabry.entertainment.Fragments.BlankFragment;
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




}
