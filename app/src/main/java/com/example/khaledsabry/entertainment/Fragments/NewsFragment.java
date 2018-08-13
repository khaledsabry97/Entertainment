package com.example.khaledsabry.entertainment.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Adapter.NewsAdapter;
import com.example.khaledsabry.entertainment.Connection.WebApi;
import com.example.khaledsabry.entertainment.Controllers.Controller;
import com.example.khaledsabry.entertainment.Controllers.Functions;
import com.example.khaledsabry.entertainment.Fragments.MainMenu.MainMenuFragment;
import com.example.khaledsabry.entertainment.Fragments.Search.SearchFragment;
import com.example.khaledsabry.entertainment.Interfaces.OnWebSuccess;
import com.example.khaledsabry.entertainment.Items.News;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

import static com.example.khaledsabry.entertainment.Activities.MainActivity.loadFragmentNoReturn;
import static com.example.khaledsabry.entertainment.Fragments.YoutubeFragment.drawerLayout;


public class NewsFragment extends Fragment {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    RecyclerView recyclerView;
    TextView header;
    Controller controller;
    NewsAdapter newsAdapter;
    FrameLayout frameLayout;
    public static NewsFragment fragment = null;

    public static NewsFragment newInstance() {
        fragment = new NewsFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        recyclerView = view.findViewById(R.id.recyclerid);
        header = view.findViewById(R.id.header);
        navigationView = view.findViewById(R.id.nav_view);
        drawerLayout = view.findViewById(R.id.drawer_layout);
        frameLayout = view.findViewById(R.id.web);

        controller = new Controller();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Functions.stopConnectionsAndStartImageGlide();
                if (id == R.id.topnews)
                    loadTopNews();
                else if (id == R.id.movienews)
                    loadMovieNews();
                else if (id == R.id.actorsnews)
                    loadActorsNews();
                else if (id == R.id.tvnews)
                    loadTvNews();
                else if (id == R.id.indienews)
                    loadIndieNews();
                navigationView.setCheckedItem(id);
                drawerLayout.closeDrawer(GravityCompat.END, true);
                return true;

            }
        });


        newsAdapter = new NewsAdapter(null);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(newsAdapter);
        navigationView.setCheckedItem(R.id.topnews);
        loadTopNews();
        return view;
    }

    private void loadTopNews() {

        header.setText("Top News");
        header.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        WebApi.getInstance().imdbTopNews(new OnWebSuccess.OnNews() {
            @Override
            public void onSuccess(ArrayList<News> news) {
                newsAdapter.setNews(news);
                newsAdapter.notifyDataSetChanged();

            }
        });
    }

    private void loadMovieNews() {
        header.setText("Movies News");
        header.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        WebApi.getInstance().imdbMovieNews(new OnWebSuccess.OnNews() {
            @Override
            public void onSuccess(ArrayList<News> news) {
                newsAdapter.setNews(news);
                newsAdapter.notifyDataSetChanged();

            }
        });
    }

    private void loadTvNews() {
        header.setText("Tv News");
        header.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        WebApi.getInstance().imdbTvNews(new OnWebSuccess.OnNews() {
            @Override
            public void onSuccess(ArrayList<News> news) {
                newsAdapter.setNews(news);
                newsAdapter.notifyDataSetChanged();

            }
        });
    }

    private void loadActorsNews() {
        header.setText("Actors News");
        header.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        WebApi.getInstance().imdbCelebrityNews(new OnWebSuccess.OnNews() {
            @Override
            public void onSuccess(ArrayList<News> news) {
                newsAdapter.setNews(news);
                newsAdapter.notifyDataSetChanged();

            }
        });
    }

    private void loadIndieNews() {
        header.setText("Indie News");
        header.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        WebApi.getInstance().imdbIndieNews(new OnWebSuccess.OnNews() {
            @Override
            public void onSuccess(ArrayList<News> news) {
                newsAdapter.setNews(news);
                newsAdapter.notifyDataSetChanged();

            }
        });
    }


    public void loadWebView(String url) {
        MainActivity.loadFragmentWithReturn(R.id.web, WebFragment.newInstance(url));
    }

}
