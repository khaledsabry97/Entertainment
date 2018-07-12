package com.example.khaledsabry.entertainment.Fragments.MovieView;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Adapter.ProductionCompanyAdapter;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.ProductionCompany;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

public class ProductionCompanyFragment extends Fragment {
    RecyclerView recyclerView;
    ProductionCompanyAdapter productionCompanyAdapter;
    static Movie movie;
    private static ArrayList<ProductionCompany> productionCompanies;

    public static ProductionCompanyFragment newInstance(ArrayList<ProductionCompany> productionCompanies) {
        ProductionCompanyFragment fragment = new ProductionCompanyFragment();
        ProductionCompanyFragment.productionCompanies = productionCompanies;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_production_company, container, false);
        recyclerView = v.findViewById(R.id.recyclerid);
        productionCompanyAdapter = new ProductionCompanyAdapter(productionCompanies);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(productionCompanyAdapter);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        return v;
    }

}
