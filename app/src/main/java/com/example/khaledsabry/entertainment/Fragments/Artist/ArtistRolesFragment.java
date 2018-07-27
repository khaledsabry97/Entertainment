package com.example.khaledsabry.entertainment.Fragments.Artist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.khaledsabry.entertainment.Adapter.RoleRecyclarAdapter;
import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Interfaces.OnArtistDataSuccess;
import com.example.khaledsabry.entertainment.Items.Artist;
import com.example.khaledsabry.entertainment.R;


public class ArtistRolesFragment extends Fragment {
    RecyclerView recyclerView;
    Button movie;
    Button tv;
    static int id;
    static boolean newStuff;
    static Artist artist;
    TmdbController tmdbController = new TmdbController();

    public static ArtistRolesFragment newInstance(int id) {
        ArtistRolesFragment fragment = new ArtistRolesFragment();
        ArtistRolesFragment.id = id;
        newStuff = true;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_roles, container, false);
        movie = v.findViewById(R.id.button_Movie_id);
        tv = v.findViewById(R.id.button_Tv_id);
        recyclerView = v.findViewById(R.id.itemsid);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        if (newStuff)
            tmdbController.getPersonRoles(id, new OnArtistDataSuccess() {
                @Override
                public void onSuccess(Artist artist) {
                    ArtistRolesFragment.artist = artist;
                    newStuff = false;
                    movie.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            movie.setPressed(true);
                            tv.setPressed(false);

                            setAdapter(0);
                        }
                    });
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            movie.setPressed(false);
                            tv.setPressed(true);

                            setAdapter(1);
                        }
                    });
                    movie.setPressed(true);
                    tv.setPressed(false);
                    setAdapter(0);
                }
            });


        return v;
    }

    private void setAdapter(int type) {
artist.sort();
        RoleRecyclarAdapter recyclarAdapter = new RoleRecyclarAdapter(type, artist.getMovies(), artist.getTvs());
        recyclerView.setAdapter(recyclarAdapter);

    }

}
