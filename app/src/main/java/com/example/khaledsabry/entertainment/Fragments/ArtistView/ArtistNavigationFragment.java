package com.example.khaledsabry.entertainment.Fragments.ArtistView;


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


public class ArtistNavigationFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener
{

    static int artistId;
    static int id = -200;
    int mainView = R.id.home;
    int images = R.id.images;
    int roles = R.id.role;
    int backButtonid = R.id.backButtonid;

    public static ArtistNavigationFragment newInstance(int artistId, boolean reset) {
        ArtistNavigationFragment fragment = new ArtistNavigationFragment();
        ArtistNavigationFragment.artistId = artistId;
        if (reset)
            ArtistNavigationFragment.id = -200;
        if (ArtistNavigationFragment.id == -200)
            ArtistNavigationFragment.id = R.id.home;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_artist_detail, container, false);

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

    ArtistNavigationFragment.id = item.getItemId();
        if (id==backButtonid)
            getActivity().getSupportFragmentManager().popBackStack();
         else if (id == mainView)
            loadFragment(ArtistMainFragment.newInstance(artistId));
        else if (id == images)
        loadFragment(ImagesFragment.newInstance(null, null));
        else if (id == roles)
           loadFragment(ArtistRolesFragment.newInstance(artistId));

        return true;
        }


    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {

    }
}
