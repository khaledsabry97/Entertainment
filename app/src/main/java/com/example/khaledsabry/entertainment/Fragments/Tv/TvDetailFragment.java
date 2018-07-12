package com.example.khaledsabry.entertainment.Fragments.Tv;


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
import com.example.khaledsabry.entertainment.Fragments.Artist.ArtistMainFragment;
import com.example.khaledsabry.entertainment.Fragments.Artist.RolesFragment;
import com.example.khaledsabry.entertainment.Fragments.MovieView.PosterFragment;
import com.example.khaledsabry.entertainment.R;

public class TvDetailFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener {

    static int tvId;
    static int id = -200;
    int mainView = R.id.home;
    int images = R.id.images;
    int seasons = R.id.seasons;
    int backButtonid = R.id.backButtonid;

    public static TvDetailFragment newInstance(int tvId, boolean reset) {
        TvDetailFragment fragment = new TvDetailFragment();
        TvDetailFragment.tvId = tvId;
        if (reset)
            TvDetailFragment.id = -200;
        if (TvDetailFragment.id == -200)
            TvDetailFragment.id = R.id.home;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_detail, container, false);
        BottomNavigationView bottomNavigationView = view.findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        bottomNavigationView.setSelectedItemId(id);
        bottomNavigationView.setItemTextColor(ColorStateList.valueOf(Color.WHITE));

        return view;
    }
    void loadFragment(Fragment fragment) {
        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.moviedetailid, fragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        TvDetailFragment.id = item.getItemId();
        if (id==backButtonid)
            getActivity().getSupportFragmentManager().popBackStack();
        else if (id == mainView)
            loadFragment(TvMainFragment.newInstance(tvId));
        else if (id == images)
            loadFragment(PosterFragment.newInstance(tvId, PosterFragment.Type.tv));
        else if (id == seasons)
            loadFragment(RolesFragment.newInstance(tvId));

        return true;
    }


    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {

    }
}
