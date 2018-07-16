package com.example.khaledsabry.entertainment.Controllers;

import android.os.AsyncTask;

import com.example.khaledsabry.entertainment.Connection.ApiConnections;
import com.example.khaledsabry.entertainment.Connection.TorrentApi;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieDataSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnTorrentSearchSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.Torrent;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 14-Jul-18.
 */

public class TorrentController {
    private ApiConnections connections = ApiConnections.getInstance();
private TorrentApi torrentApi = TorrentApi.getInstance();

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

    public void downloadSkyTorrent(final String searchItem, final OnTorrentSearchSuccess listener) {
        TorrentApi.getInstance().skyTorrent(searchItem, new OnTorrentSearchSuccess() {
            @Override
            public void onSuccess(ArrayList<Torrent> torrents) {
                listener.onSuccess(torrents);
            }
        });

    }

public void getTv(final String tvTitle, final OnTorrentSearchSuccess listener)
{
     String url = "https://oneom.tk/search/serial?title="+tvTitle;
    connections.connectOneom(url, new OnSuccess() {
        @Override
        public void onSuccess(JSONObject jsonObject) {
            int id = torrentApi.getSerialId(jsonObject,tvTitle);
            if(id == -1)
                return;
            String Url = "https://oneom.tk/serial/"+id;
            connections.connectOneom(Url, new OnSuccess() {
                @Override
                public void onSuccess(JSONObject jsonObject) {
                    listener.onSuccess(torrentApi.getSerial(jsonObject));

                }
            });
        }
    });
}




}
