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
import com.example.khaledsabry.entertainment.Controllers.Functions;
import com.example.khaledsabry.entertainment.Fragments.ImagesFragment;
import com.example.khaledsabry.entertainment.R;

public class TvNavigationFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener {

    static int tvId;
    static int id = -200;
    int mainView = R.id.home;
    int images = R.id.images;
    int seasons = R.id.seasons;
    int backButtonid = R.id.backButtonid;
    public static TvNavigationFragment newInstance(int tvId, boolean reset) {
        TvNavigationFragment fragment = new TvNavigationFragment();
        TvNavigationFragment.tvId = tvId;
        if (reset)
            TvNavigationFragment.id = -200;
        if (TvNavigationFragment.id == -200)
            TvNavigationFragment.id = R.id.home;
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
        Functions.stopConnectionsAndStartImageGlide();

        TvNavigationFragment.id = item.getItemId();
        if (id==backButtonid)
            getActivity().getSupportFragmentManager().popBackStack();
        else if (id == mainView)
            loadFragment(TvMainFragment.newInstance(tvId));
        else if (id == images)
            loadFragment(ImagesFragment.newInstance(tvId, ImagesFragment.Type.tv));
        else if (id == seasons)
            loadFragment(TvContentFragment.newInstance(tvId));

        return true;
    }


    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {

    }
}
