package com.example.khaledsabry.entertainment.Controllers;

import android.os.AsyncTask;

import com.example.khaledsabry.entertainment.Interfaces.OnMovieDataSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnTorrentSearchSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.Torrent;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 14-Jul-18.
 */

public class TorrentController {


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
        AsyncTask<Void,Void,ArrayList<Torrent>> task = new AsyncTask<Void, Void, ArrayList<Torrent>>() {
            @Override
            protected ArrayList<Torrent> doInBackground(Void... voids) {
                ArrayList<Torrent> torrents = new ArrayList<>();
                org.jsoup.nodes.Document
                        doc = null;
                try {
                    doc = Jsoup.connect("https://www.skytorrents.lol/?query=" + searchItem).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Elements results = doc.getElementsByClass("result");

                for (Element element : results) {
                    Torrent torrent = new Torrent();
                    Elements attributes = element.getElementsByTag("td");
                    Elements file = attributes.get(0).getElementsByTag("a");
                    String title = file.get(0).text();
                    String magnet = file.get(2).attr("href");
                    String size = attributes.get(1).text();
                    String seeders = attributes.get(4).text();
                    String leechers = attributes.get(5).text();
                    String date = "Uploaded "+attributes.get(3).text();
                    //    if (seeders.equals("0"))
                    //    continue;
                    torrent.setDate(date);
                    torrent.setTitle(title);
                    torrent.setMagnet(magnet);
                    torrent.setLeechers(leechers);
                    torrent.setSeeders(seeders);
                    torrent.setSize(size);


                    torrents.add(torrent);
                }
                return torrents;
            }

            @Override
            protected void onPostExecute(ArrayList<Torrent> torrents) {
                listener.onSuccess(torrents);

            }
        };
        task.execute();

    }




}
