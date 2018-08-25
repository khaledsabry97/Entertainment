package com.example.khaledsabry.entertainment.Controllers;

import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Connection.ApiConnections;
import com.example.khaledsabry.entertainment.Items.Artist;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.SearchItem;
import com.example.khaledsabry.entertainment.Items.Tv;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by KhALeD SaBrY on 04-Jul-18.
 */

public class Functions {
    /*  private static final Functions ourInstance = new Functions();

      public static Functions getInstance() {
          return ourInstance;
      }

      private Functions() {
      }
  */
    public static void stopConnectionsAndStartImageGlide() {
        Volley.newRequestQueue(MainActivity.getActivity().getApplicationContext()).start();
        Glide.with(MainActivity.getActivity()).onStop();
        Glide.with(MainActivity.getActivity()).onStart();
    }

    public void movePoster(ViewPager viewPager, PagerAdapter viewPagerAdapter, int delayToStart, int repeatTime) {
        final ViewPager f_viewPager = viewPager;
        final PagerAdapter f_viewPagerAdapter = viewPagerAdapter;
        final int f_delayToStart = delayToStart;
        final int f_repeatTime = repeatTime;
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (f_viewPagerAdapter == null || f_viewPager == null)
                    return;
                if (f_viewPagerAdapter.getCount() == f_viewPager.getCurrentItem() + 1)
                    f_viewPager.setCurrentItem(0, true);
                else
                    f_viewPager.setCurrentItem(f_viewPager.getCurrentItem() + 1, true);
            }
        };
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.post(runnable);

            }
        }, f_delayToStart, f_repeatTime);
    }

//this to get 10 items for movies as a seach item
    public static ArrayList<SearchItem> movies(ArrayList<Movie> movies, String type, Integer limit) {

        ArrayList<SearchItem> items = new ArrayList<>();
        int size;
        if (limit == null)
            size = movies.size();
        else {
            if (limit > movies.size())
                size = movies.size();
            else
                size = limit;
        }

        for (int i = 0; i < size; i++) {
            SearchItem searchItem = new SearchItem();
            if (type == null)
                searchItem.setType("movie");
            else
                searchItem.setType(type);
            searchItem.setMovie(movies.get(i));
            items.add(searchItem);
        }
        return items;
    }

    //this to get tvs as a search item
    public static ArrayList<SearchItem> tvs(ArrayList<Tv> tvs) {
        ArrayList<SearchItem> items = new ArrayList<>();
        for (int i = 0; i < tvs.size(); i++) {
            SearchItem searchItem = new SearchItem();
            searchItem.setType("tv");
            searchItem.setTv(tvs.get(i));
            items.add(searchItem);
        }
        return items;
    }

    public static ArrayList<SearchItem> artists(ArrayList<Artist> artists) {
        ArrayList<SearchItem> items = new ArrayList<>();
        for (int i = 0; i < artists.size(); i++) {
            SearchItem searchItem = new SearchItem();
            searchItem.setType("artist");
            searchItem.setArtist(artists.get(i));
            items.add(searchItem);
        }
        return items;
    }




}
