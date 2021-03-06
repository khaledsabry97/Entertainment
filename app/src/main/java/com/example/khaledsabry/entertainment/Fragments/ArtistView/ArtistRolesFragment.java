package com.example.khaledsabry.entertainment.Fragments.ArtistView;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.khaledsabry.entertainment.Adapters.RoleRecyclarAdapter;
import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Interfaces.OnArtistDataSuccess;
import com.example.khaledsabry.entertainment.Items.Artist;
import com.example.khaledsabry.entertainment.R;


public class ArtistRolesFragment extends Fragment {
    RecyclerView recyclerView;
    Button movie;
    Button tv;
    Artist artist;
    RoleRecyclarAdapter roleRecyclarAdapter;
    TmdbController tmdbController = new TmdbController();

    public static ArtistRolesFragment newInstance(Artist artist) {
        ArtistRolesFragment fragment = new ArtistRolesFragment();
        fragment.artist = artist;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_roles, container, false);
        movie = v.findViewById(R.id.button_Movie_id);
        tv = v.findViewById(R.id.button_Tv_id);
        recyclerView = v.findViewById(R.id.items_id);



        setObjects();



        return v;
    }

    private void setObjects() {
        setupRecyclerView();
        setButtons();
        movie.performClick();
    }

    private void setButtons() {
        movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setType(0);
            }
        });


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setType(1);
            }
        });


    }

    private void setupRecyclerView() {
        roleRecyclarAdapter = new RoleRecyclarAdapter();
        recyclerView.setAdapter(roleRecyclarAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        artist.sort();
    }

    /**
     * show the content in the recycler view
     *
     * @param type 0-> movie 1->tv
     */
    private void setType(int type) {
        roleRecyclarAdapter.setdata(type, artist.getMovies(), artist.getTvs());

    }

}
