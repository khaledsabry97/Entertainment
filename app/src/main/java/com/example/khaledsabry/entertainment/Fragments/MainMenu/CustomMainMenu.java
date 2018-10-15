package com.example.khaledsabry.entertainment.Fragments.MainMenu;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Adapters.ContentAdapter;
import com.example.khaledsabry.entertainment.Adapters.MainContentAdapter;
import com.example.khaledsabry.entertainment.Adapters.MenuAdapter;
import com.example.khaledsabry.entertainment.Connection.WebApi;
import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Controllers.YoutubeController;
import com.example.khaledsabry.entertainment.Enums.Type;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieList;
import com.example.khaledsabry.entertainment.Interfaces.OnWebSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnYoutubeSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.News;
import com.example.khaledsabry.entertainment.Items.Youtube;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.khaledsabry.entertainment.Enums.Type.movies_now_playing;
import static com.example.khaledsabry.entertainment.Enums.Type.watch_so_much;
import static com.example.khaledsabry.entertainment.Enums.Type.youtube;


public class CustomMainMenu extends Fragment {
    //upper
    ViewPager viewPager;
    MainContentAdapter mainContentAdapter;

    //bottom
    RecyclerView contentRecycler;
    ContentAdapter contentAdapter;
    //right
    RecyclerView menuRecycler;
    MenuAdapter menuAdapter;

    HashMap<Object, Type> objectTypeHashMap = new HashMap<>();
    TmdbController tmdbController;
    YoutubeController youtubeController;

    public static CustomMainMenu newInstance() {
        CustomMainMenu fragment = new CustomMainMenu();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_custom_main_menu2, container, false);
        viewPager = view.findViewById(R.id.view_pager_id);
        contentRecycler = view.findViewById(R.id.content_recycler_id);
        menuRecycler = view.findViewById(R.id.icons_recycler_id);

        tmdbController = new TmdbController();
        youtubeController = new YoutubeController();

        setupContentRecycler();
        setupmenuRecycler();
        setupViewPager();


        getWatchSoMuchMovies();
        getNews();
        getMoviesNowPlaying();
        getNewestTrailers();


        return view;
    }

    private void getNewestTrailers() {

youtubeController.getLatestTrailers(new OnYoutubeSuccess() {
    @Override
    public void onSuccess(ArrayList<Youtube> youtubes) {
        addViewPager(youtubes.get(0).getId(),youtube);
    }
});

    }

    private void getMoviesNowPlaying() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                tmdbController.getMoviesNowPlaying(new OnMovieList() {
                    @Override
                    public void onMovieList(final ArrayList<Movie> movies) {


                        MainActivity.getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                addViewPager(movies.get(0), movies_now_playing);

                            }
                        });
                    }
                });


            }
        });

    }

    private void getNews() {
        WebApi.getInstance().imdbMovieNews(new OnWebSuccess.OnNews() {
            @Override
            public void onSuccess(ArrayList<News> news) {
                addViewPager(news.get(0), Type.news);

            }
        });
    }

    private void setupViewPager() {
        mainContentAdapter = new MainContentAdapter();
        viewPager.setAdapter(mainContentAdapter);
  //      mainContentAdapter.moveView(viewPager,mainContentAdapter,500,2000);
    }

    private void setupmenuRecycler() {
        menuAdapter = new MenuAdapter();
        menuRecycler.setAdapter(menuAdapter);
        contentRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    private void setupContentRecycler() {
        contentAdapter = new ContentAdapter();
        contentRecycler.setAdapter(contentAdapter);
    }

    public void getWatchSoMuchMovies() {
        WebApi.getInstance().watchSoMuchBluRay(new OnWebSuccess.OnMovie() {
            @Override
            public void onSuccess(Movie movie) {
                addViewPager(movie, watch_so_much);
            }
        });
    }


    void addViewPager(Object object,Type type)
    {
        objectTypeHashMap.clear();
        objectTypeHashMap.put(object,type);
        mainContentAdapter.addFragment(objectTypeHashMap);
    }
}
