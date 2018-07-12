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
import com.example.khaledsabry.entertainment.Fragments.MovieMoreDetailsFragment;
import com.example.khaledsabry.entertainment.R;


public class DetailFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener {

    static int movieId;
    static int id = -200;

    public static DetailFragment newInstance(int movieId, boolean reset) {
        DetailFragment fragment = new DetailFragment();
        DetailFragment.movieId = movieId;
        if (reset)
            DetailFragment.id = -200;
        if (DetailFragment.id == -200)
            DetailFragment.id = R.id.navigation_home;
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
        DetailFragment.id = item.getItemId();
        if (item.getItemId() == R.id.navigation_home) {
            loadFragment(MovieDetailFragment.newInstance(movieId));

        } else if (item.getItemId() == R.id.navigation_dashboard)
getActivity().getSupportFragmentManager().popBackStack();
        else if (item.getItemId() == R.id.navigation_notifications)
            loadFragment(PosterFragment.newInstance(movieId, PosterFragment.Type.movie));
      //  else if (item.getItemId() == R.id.navigation_trailers)
            //   loadFragment(YOU.newInstance("aJ7BoNG-r2c"));
         //   loadFragment(ReviewFragment.newInstance(movieId));
        else if (item.getItemId() == R.id.navigation_reco_simi)
            loadFragment(RecommendedAndSimilarFragment.newInstance(movieId));
        return true;
    }

    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {

    }
}
