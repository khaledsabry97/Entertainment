package com.example.khaledsabry.entertainment.Controllers;

import android.os.AsyncTask;

import com.example.khaledsabry.entertainment.Connection.ApiConnections;
import com.example.khaledsabry.entertainment.Connection.WebApi;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieDataSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnWebSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.Torrent;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 14-Jul-18.
 */

public class TorrentController {
    private ApiConnections connections = ApiConnections.getInstance();
private WebApi webApi = WebApi.getInstance();

    public void getRottenTomatoesRate(final String movieName, final OnMovieDataSuccess listener) {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Movie movie = new Movie();
                    String item = movieName;
                    item = item.replace(" ", "_");
                    item = item.toLowerCase();
                    ArrayList<Torrent> torrents = new ArrayList<>();
                    org.jsoup.nodes.Document
                            doc = Jsoup.connect("https://www.rottentomatoes.com/m/" + item).get();

                    Elements results = doc.getElementsByClass("meter-value superPageFontColor");
                    String rate = results.get(0).text();
                    listener.onSuccess(movie);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void downloadSkyTorrent(final String searchItem, final OnWebSuccess.OnTorrentSearch listener) {
        WebApi.getInstance().skyTorrent(searchItem, new OnWebSuccess.OnTorrentSearch() {
            @Override
            public void onSuccess(ArrayList<Torrent> torrents) {
                listener.onSuccess(torrents);
            }
        });

    }







}
