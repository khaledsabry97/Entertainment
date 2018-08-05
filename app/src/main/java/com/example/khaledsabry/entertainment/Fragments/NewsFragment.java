package com.example.khaledsabry.entertainment.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Adapter.NewsAdapter;
import com.example.khaledsabry.entertainment.Connection.WebApi;
import com.example.khaledsabry.entertainment.Interfaces.OnWebSuccess;
import com.example.khaledsabry.entertainment.Items.News;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;


public class NewsFragment extends Fragment {

    RecyclerView recyclerView;
    TextView header;
    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();

        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_news, container, false);
recyclerView = view.findViewById(R.id.recyclerid);
header = view.findViewById(R.id.header);

loadTopNews();
        return view;
    }

    private void loadTopNews() {
        header.setText("Top News");
        header.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        WebApi.getInstance().imdbTopNews(new OnWebSuccess.OnNews() {
            @Override
            public void onSuccess(ArrayList<News> news) {
                NewsAdapter newsAdapter = new NewsAdapter(news);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
                recyclerView.setAdapter(newsAdapter);


            }
        });
    }

}
