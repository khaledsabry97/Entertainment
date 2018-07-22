package com.example.khaledsabry.entertainment.Fragments.MainMenu;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Adapter.MainRecyclersAdapter;
import com.example.khaledsabry.entertainment.Connection.Tmdb;
import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Interfaces.OnArtistDataSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieList;
import com.example.khaledsabry.entertainment.Interfaces.OnTvList;
import com.example.khaledsabry.entertainment.Items.Artist;
import com.example.khaledsabry.entertainment.Items.Classification;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.SearchItem;
import com.example.khaledsabry.entertainment.Items.Tv;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;


public class MainMenuFragment extends Fragment {

    RecyclerView recyclerView;
    TmdbController tmdbController;
    MainRecyclersAdapter adapter;
    public static MainMenuFragment newInstance() {
        MainMenuFragment fragment = new MainMenuFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view =  inflater.inflate(R.layout.fragment_main_menu, container, false);

      recyclerView = view.findViewById(R.id.recyclerid);
tmdbController = new TmdbController();
         adapter = new MainRecyclersAdapter(new ArrayList<Classification>());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        setMoviesNowPlaying();
        setMoviesPopular();
        setMoviesTopRated();
        setMoviesUpComing();
        setTvPopular();
        setTvTopRated();
        setTvOnAir();
        setTvShowToday();
        setArtistPopular();
        return view;
    }

    private void setArtistPopular() {
        tmdbController.getArtistPopular(new OnArtistDataSuccess.List() {
            @Override
            public void onSuccess(ArrayList<Artist> artists) {
                Classification classification = new Classification();
                classification.setImage(R.drawable.arrowleft);
                classification.setTitle("Popular Artists");
                classification.setSearchItems(artists(artists));
                adapter.putClassification(classification);
            }
        });
    }

    private void setTvShowToday() {
        tmdbController.getTvAirToday(new OnTvList() {
            @Override
            public void onSuccess(ArrayList<Tv> tvs) {
                Classification classification = new Classification();
                classification.setImage(R.drawable.arrowleft);
                classification.setTitle("On Tv Today");
                classification.setSearchItems(tvs(tvs));
                adapter.putClassification(classification);
            }
        });
    }

    private void setTvOnAir() {
        tmdbController.getTvOnAir(new OnTvList() {
            @Override
            public void onSuccess(ArrayList<Tv> tvs) {
                Classification classification = new Classification();
                classification.setImage(R.drawable.arrowleft);
                classification.setTitle("Tv Series On Air");
                classification.setSearchItems(tvs(tvs));
                adapter.putClassification(classification);
            }
        });
    }

    private void setTvTopRated() {
        tmdbController.getTvTopRated(new OnTvList() {
            @Override
            public void onSuccess(ArrayList<Tv> tvs) {
                Classification classification = new Classification();
                classification.setImage(R.drawable.arrowleft);
                classification.setTitle("Top Rated Tv Series");
                classification.setSearchItems(tvs(tvs));
                adapter.putClassification(classification);
            }
        });
    }

    private void setTvPopular() {
        tmdbController.getTvPopular(new OnTvList() {
            @Override
            public void onSuccess(ArrayList<Tv> tvs) {
                Classification classification = new Classification();
                classification.setImage(R.drawable.arrowleft);
                classification.setTitle("Popular Tv Series");
                classification.setSearchItems(tvs(tvs));
                adapter.putClassification(classification);
            }
        });
    }

    private void setMoviesUpComing() {
        tmdbController.getMoviesUpComing(new OnMovieList() {
            @Override
            public void onMovieList(ArrayList<Movie> movies) {
                Classification classification = new Classification();
                classification.setImage(R.drawable.arrowleft);
                classification.setTitle("UpComing Movies");
                classification.setSearchItems(movies(movies));
                adapter.putClassification(classification);

            }
        });
    }

    private void setMoviesTopRated() {
        tmdbController.getMoviesTopRated(new OnMovieList() {
            @Override
            public void onMovieList(ArrayList<Movie> movies) {
                Classification classification = new Classification();
                classification.setImage(R.drawable.arrowleft);
                classification.setTitle("Top Movies");
                classification.setSearchItems(movies(movies));
                adapter.putClassification(classification);

            }
        });
    }

    private void setMoviesPopular() {

        tmdbController.getMoviesPopular(new OnMovieList() {
            @Override
            public void onMovieList(ArrayList<Movie> movies) {
                Classification classification = new Classification();
                classification.setImage(R.drawable.arrowleft);
                classification.setTitle("Popular Movies");
                classification.setSearchItems(movies(movies));
                adapter.putClassification(classification);

            }
        });
    }

    private void setMoviesNowPlaying() {
        tmdbController.getMoviesNowPlaying(new OnMovieList() {
            @Override
            public void onMovieList(ArrayList<Movie> movies) {

               Classification classification = new Classification();
               classification.setImage(R.drawable.arrowleft);
               classification.setTitle("Now Playing");
               classification.setSearchItems(movies(movies));

                adapter.putClassification(classification);

            }
        });
    }



    ArrayList<SearchItem> movies(ArrayList<Movie> movies)
    {
        ArrayList<SearchItem> items = new ArrayList<>();
        for (int i = 0 ; i < movies.size();i++)
        {
            SearchItem searchItem = new SearchItem();
            searchItem.setType("movie");
            searchItem.setMovie(movies.get(i));
            items.add(searchItem);
        }
        return items;
    }

    ArrayList<SearchItem> tvs(ArrayList<Tv> tvs)
    {
        ArrayList<SearchItem> items = new ArrayList<>();
        for (int i = 0 ; i < tvs.size();i++)
        {
            SearchItem searchItem = new SearchItem();
            searchItem.setType("tv");
            searchItem.setTv(tvs.get(i));
            items.add(searchItem);
        }
        return items;
    }


    ArrayList<SearchItem> artists(ArrayList<Artist> artists)
    {
        ArrayList<SearchItem> items = new ArrayList<>();
        for (int i = 0 ; i < artists.size();i++)
        {
            SearchItem searchItem = new SearchItem();
            searchItem.setType("artist");
            searchItem.setArtist(artists.get(i));
            items.add(searchItem);
        }
        return items;
    }
}
