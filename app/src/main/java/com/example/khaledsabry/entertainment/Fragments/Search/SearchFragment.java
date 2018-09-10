package com.example.khaledsabry.entertainment.Fragments.Search;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Connection.ApiConnections;
import com.example.khaledsabry.entertainment.Controllers.Functions;
import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Interfaces.OnSearchSuccess;
import com.example.khaledsabry.entertainment.Items.SearchItem;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    TmdbController tmdbController;
    EditText searchView;

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        searchView = v.findViewById(R.id.searchid);

        tmdbController = new TmdbController();
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ApiConnections.getInstance().stopConnection();
                Functions.stopConnectionsAndStartImageGlide();
                tmdbController.search(String.valueOf(s), new OnSearchSuccess() {
                    @Override
                    public void onSuccess(ArrayList<SearchItem> searchItems) {
                        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.resultsid, SearchResult.newInstance(searchItems)).commit();
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return v;
    }


}
