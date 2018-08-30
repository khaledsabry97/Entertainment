package com.example.khaledsabry.entertainment.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Items.Character;
import com.example.khaledsabry.entertainment.R;

import com.example.khaledsabry.entertainment.Adapter.CastRecyclerAdapter;

import java.util.ArrayList;


public class CastFragment extends Fragment {

    RecyclerView recyclerView;
    private ArrayList<Character> characters;

    public static CastFragment newInstance(ArrayList<Character> characters) {
        CastFragment fragment = new CastFragment();
        fragment.characters = characters;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cast_crew, container, false);
        recyclerView = v.findViewById(R.id.recycler_id);

        setupTheRecyclerView();
        return v;
    }

    /**
     * setup the contents of the recycler view
     */
    private void setupTheRecyclerView() {

        CastRecyclerAdapter adapter = new CastRecyclerAdapter(characters);
        recyclerView.setAdapter(adapter);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);

    }
}
