package com.example.khaledsabry.entertainment.Fragments.MainMenu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Adapter.ClassificationAdapter;
import com.example.khaledsabry.entertainment.Items.Classification;
import com.example.khaledsabry.entertainment.Items.SearchItem;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;


public class ClassificationRecyclerFragment extends Fragment {

    RecyclerView recyclerView;
    TextView title;
    ImageView poster;

    static Classification classifications = new Classification();

    public static ClassificationRecyclerFragment newInstance(Classification classifications) {
        ClassificationRecyclerFragment fragment = new ClassificationRecyclerFragment();
        ClassificationRecyclerFragment.classifications = classifications;

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_classification_recyclar, container, false);

        recyclerView = view.findViewById(R.id.recyclerid);
        title = view.findViewById(R.id.title);
        poster = view.findViewById(R.id.poster);

title.setText(classifications.getTitle());
poster.setImageResource(classifications.getImage());
        ClassificationAdapter adapter = new ClassificationAdapter(classifications.getSearchItems());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


        return view;
    }

}
