package com.example.khaledsabry.entertainment.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Adapters.CrewRecyclerAdapter;
import com.example.khaledsabry.entertainment.Items.Artist;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 29-Jun-18.
 */

public class CrewFragment extends Fragment {
    RecyclerView recyclerView;
    private   ArrayList<Artist> crew;

    public static CrewFragment newInstance(ArrayList<Artist> crew) {
        CrewFragment fragment = new CrewFragment();
        fragment.crew = crew;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cast_crew, container, false);

        recyclerView = v.findViewById(R.id.recycler_id);


setupRecyclerView();
        return v;
    }

    private void setupRecyclerView() {



        CrewRecyclerAdapter adapter = new CrewRecyclerAdapter(crew);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
    }


}
