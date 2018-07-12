package com.example.khaledsabry.entertainment.Fragments.Artist;


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
import com.example.khaledsabry.entertainment.Fragments.MovieView.PosterFragment;
import com.example.khaledsabry.entertainment.R;


public class ArtistDetailFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener
{

    static int artistId;
    static int id = -200;
    int mainView = R.id.home;
    int images = R.id.images;
    int roles = R.id.role;
    int backButtonid = R.id.backButtonid;

    public static ArtistDetailFragment newInstance(int artistId, boolean reset) {
        ArtistDetailFragment fragment = new ArtistDetailFragment();
        ArtistDetailFragment.artistId = artistId;
        if (reset)
            ArtistDetailFragment.id = -200;
        if (ArtistDetailFragment.id == -200)
            ArtistDetailFragment.id = R.id.home;
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
    ArtistDetailFragment.id = item.getItemId();
        if (id==backButtonid)
            getActivity().getSupportFragmentManager().popBackStack();
         else if (id == mainView)
            loadFragment(ArtistMainFragment.newInstance(artistId));
        else if (id == images)
        loadFragment(PosterFragment.newInstance(artistId));
        else if (id == roles)
           loadFragment(RolesFragment.newInstance(artistId));

        return true;
        }


    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {

    }
}
