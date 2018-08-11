package com.example.khaledsabry.entertainment.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContentSideBarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContentSideBarFragment extends Fragment {

    public static ContentSideBarFragment newInstance() {
        ContentSideBarFragment fragment = new ContentSideBarFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_content_side_bar, container, false);



        return view;
    }


    public static void openCategoryAdd(ArrayList<String> names,ArrayList<Integer> ids,ArrayList<Boolean> booleans)
    {
        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().add(R.id.mainContainer,CategoryAddFragment.newInstance(names,ids,booleans)).commit();
    }

}
