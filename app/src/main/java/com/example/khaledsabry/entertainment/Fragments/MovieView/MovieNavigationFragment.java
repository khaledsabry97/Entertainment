package com.example.khaledsabry.entertainment.Fragments.MovieView;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.Functions;
import com.example.khaledsabry.entertainment.Fragments.ImagesFragment;
import com.example.khaledsabry.entertainment.Fragments.TorrentFragment;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;
import com.example.khaledsabry.entertainment.Fragments.Youtube;


public class MovieNavigationFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener {

    static int movieId;
    static int id = -200;
    public static Movie movie;

    public static MovieNavigationFragment newInstance(int movieId, boolean reset) {
        MovieNavigationFragment fragment = new MovieNavigationFragment();
        MovieNavigationFragment.movieId = movieId;
        if (reset)
            MovieNavigationFragment.id = -200;
        if (MovieNavigationFragment.id == -200)
            MovieNavigationFragment.id = R.id.navigation_home;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        BottomNavigationView bottomNavigationView = v.findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        bottomNavigationView.setSelectedItemId(id);
        bottomNavigationView.setItemTextColor(ColorStateList.valueOf(Color.WHITE));


        return v;
    }

    void loadFragment(Fragment fragment) {
        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.moviedetailid, fragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Functions.stopConnectionsAndStartImageGlide();
        MovieNavigationFragment.id = item.getItemId();
        if (item.getItemId() == R.id.navigation_home) {
            loadFragment(MovieMainFragment.newInstance(movieId));

        } else if (item.getItemId() == R.id.navigation_back)
            getActivity().getSupportFragmentManager().popBackStack();
        else if (item.getItemId() == R.id.navigation_images)
            loadFragment(ImagesFragment.newInstance(movieId, ImagesFragment.Type.movie));
        else if (item.getItemId() == R.id.navigation_download) {
            if (movie != null)
                loadFragment(TorrentFragment.newInstance(movie.getTitle() + " " + movie.getYear()));
        }
        //
        //   loadFragmentWithReturn(ReviewFragment.newInstance(movieId));
        else if (item.getItemId() == R.id.navigation_reco_simi)
            loadFragment(Youtube.newInstance("aJ7BoNG-r2c",null));
        // loadFragmentWithReturn(RecommendedAndSimilarFragment.newInstance(movieId));
        return true;
    }

    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {

    }
}
