package com.example.khaledsabry.entertainment.Fragments.Search;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Adapters.ResultSearchItemsAdapter;
import com.example.khaledsabry.entertainment.Items.SearchItem;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchResult#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchResult extends Fragment {

    RecyclerView recyclerView;
    ResultSearchItemsAdapter resultSearchItemsAdapter;
    ArrayList<SearchItem> searchItems = new ArrayList<>();

    public static SearchResult newInstance(ArrayList<SearchItem> searchItems) {
        SearchResult fragment = new SearchResult();
        fragment.searchItems = searchItems;

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);
        recyclerView = view.findViewById(R.id.items_id);



        setupRecyclerView();
        return view;
    }

    private void setupRecyclerView() {

        resultSearchItemsAdapter = new ResultSearchItemsAdapter(searchItems);
        recyclerView.setAdapter(resultSearchItemsAdapter);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
       // resultSearchItemsAdapter.selectFirstItem();
    }

}
