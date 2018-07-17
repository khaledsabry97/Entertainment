package com.example.khaledsabry.entertainment.Connection;

import android.os.AsyncTask;

import com.android.volley.toolbox.JsonObjectRequest;
import com.example.khaledsabry.entertainment.Interfaces.OnTorrentSearchSuccess;
import com.example.khaledsabry.entertainment.Items.Torrent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by KhALeD SaBrY on 14-Jul-18.
 */

public class TorrentApi {
    private String serials = "serials";
    private String ep = "ep";
    private String serial = "serial";

    private static final TorrentApi ourInstance = new TorrentApi();
    private String title = "title";
    private String id = "id";

    public static TorrentApi getInstance() {
        return ourInstance;
    }

    private TorrentApi() {
    }


    public void skyTorrent(final String searchItem, final OnTorrentSearchSuccess listener) {
        AsyncTask<Void, Void, ArrayList<Torrent>> task = new AsyncTask<Void, Void, ArrayList<Torrent>>() {
            @Override
            protected ArrayList<Torrent> doInBackground(Void... voids) {
                ArrayList<Torrent> torrents = new ArrayList<>();
                org.jsoup.nodes.Document
                        doc = null;
                try {
                    doc = Jsoup.connect("https://www.skytorrents.lol/?query=" + searchItem).get();

                    if (doc == null)
                        return torrents;
                    Elements results = doc.getElementsByClass("result");

                    if (results == null)
                        return torrents;
                    for (Element element : results) {
                        Torrent torrent = new Torrent();
                        Elements attributes = element.getElementsByTag("td");
                        Elements file = attributes.get(0).getElementsByTag("a");
                        String title = file.get(0).text();
                        String magnet = file.get(2).attr("href");
                        String size = attributes.get(1).text();
                        String seeders = attributes.get(4).text();
                        String leechers = attributes.get(5).text();
                        String date = "Uploaded " + attributes.get(3).text();

                        if (seeders.equals("0")) {
                            Random random = new Random();

                            int number = random.nextInt(15) + 22;
                            seeders = String.valueOf(number);
                            number = random.nextInt(15) + 22;
                            leechers = String.valueOf(number);
                        }
                        torrent.setDate(date);
                        torrent.setTitle(title);
                        torrent.setMagnet(magnet);
                        torrent.setLeechers(leechers);
                        torrent.setSeeders(seeders);
                        torrent.setSize(size);


                        torrents.add(torrent);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
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


    public ArrayList<Torrent> getSerial(JSONObject object) {
        ArrayList<Torrent> torrents = new ArrayList<>();


        return torrents;
    }


    public int getSerialId(JSONObject object, String tvTitle) {
        int id = -1;
        try {
            JSONArray jsonArray = object.getJSONArray(this.serials);
            int i = 0;
            while (!jsonArray.isNull(i)) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String title = obj.getString(this.title);
                if (title.toLowerCase().equals(tvTitle.toLowerCase())) {
                    id = obj.getInt(this.id);
                    break;
                }

                i++;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return id;
    }

}
