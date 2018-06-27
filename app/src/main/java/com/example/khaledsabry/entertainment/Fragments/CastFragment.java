package com.example.khaledsabry.entertainment.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.khaledsabry.entertainment.R;

import com.example.khaledsabry.entertainment.Adapter.RecyclerAdapter;


public class CastFragment extends Fragment {
    RecyclerView recyclerView;

    private static final String ARG_PARAM1 = "param1";
    public static final int stationType1 = 1;
    public static final int stationType2 = 2;
    public static final int stationType3 = 3;
    private  int stationType;



    public static CastFragment newInstance(int i) {
        CastFragment fragment = new CastFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, i);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cast, container, false);
        recyclerView = v.findViewById(R.id.contentPanel);
        recyclerView.setHasFixedSize(true);

        RecyclerAdapter adapter;/*
        if (stationType == stationType1) {
            adapter = new RecyclerAdapter(DataService.getInstance().getStationType1());
        } else if (stationType == stationType2) {
            adapter = new RecyclerAdapter(DataService.getInstance().getStationType2());

        } else {
            adapter = new RecyclerAdapter(DataService.getInstance().getStationType3());

        }
*/
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           if (getArguments() != null) {
              stationType = getArguments().getInt(ARG_PARAM1);
         }
    }
}
