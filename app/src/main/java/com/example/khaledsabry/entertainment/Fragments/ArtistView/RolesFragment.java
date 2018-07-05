package com.example.khaledsabry.entertainment.Fragments.ArtistView;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.R;


public class RolesFragment extends Fragment {

    public static RolesFragment newInstance(String param1, String param2) {
        RolesFragment fragment = new RolesFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_roles, container, false);


        return v;
    }

}
