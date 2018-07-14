package com.example.khaledsabry.entertainment.Controllers;

import android.os.AsyncTask;
import android.util.Log;

import com.example.khaledsabry.entertainment.Interfaces.OnSeasonSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnTorrentSearchSuccess;
import com.example.khaledsabry.entertainment.Items.Torrent;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static java.lang.Math.log;

/**
 * Created by KhALeD SaBrY on 14-Jul-18.
 */

public class TorrentController {


    public void downloadSkyTorrent(final String searchItem, final OnTorrentSearchSuccess listener) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {


                    ArrayList<Torrent> torrents = new ArrayList<>();
                    org.jsoup.nodes.Document
                            doc = Jsoup.connect("https://www.skytorrents.lol/?query=" + searchItem).get();

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
                        if (seeders.equals("0"))
                            continue;
                        torrent.setTitle(title);
                        torrent.setMagnet(magnet);
                        torrent.setLeechers(leechers);
                        torrent.setSeeders(seeders);
                        torrent.setSize(size);


                        torrents.add(torrent);
                    }
                    listener.onSuccess(torrents);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
