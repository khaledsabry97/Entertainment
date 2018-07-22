package com.example.khaledsabry.entertainment.Connection;

import android.os.AsyncTask;

import com.example.khaledsabry.entertainment.Interfaces.OnWebSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.Torrent;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by KhALeD SaBrY on 14-Jul-18.
 */

public class WebApi {
    private String serials = "serials";
    private String ep = "ep";
    private String serial = "serial";

    private static final WebApi ourInstance = new WebApi();
    private String title = "title";
    private String id = "id";

    public static WebApi getInstance() {
        return ourInstance;
    }

    private WebApi() {
    }


    public void skyTorrent(final String searchItem, final OnWebSuccess.OnTorrentSearch listener) {
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


    public void mojoWorldWideGross(final int year, final OnWebSuccess.OnMovieList listener) {
        AsyncTask<Void, Void, ArrayList<Movie>> task = new AsyncTask<Void, Void, ArrayList<Movie>>() {
            @Override
            protected ArrayList<Movie> doInBackground(Void... voids) {
                ArrayList<Movie> movies = new ArrayList<>();
                org.jsoup.nodes.Document
                        doc = null;
                try {
                    doc = Jsoup.connect("http://www.boxofficemojo.com/yearly/chart/?view2=worldwide&yr=" + String.valueOf(year)).get();

                    if (doc == null)
                        return movies;
                    Elements results = doc.getElementsByTag("tbody");
                    results = results.get(2).getElementsByTag("tr");
                    results.remove(0);
                    results.remove(0);
                    results.remove(0);


                    if (results == null)
                        return movies;
                    for (Element element : results) {
                        Elements attributes = element.getElementsByTag("td");
                        int position = Integer.valueOf(attributes.get(0).text());
                        String title = attributes.get(1).text();
                        String worldWideBudget = attributes.get(3).text();
                        String domesticBudget = attributes.get(4).text();
                        String overSeas = attributes.get(6).text();
                        Movie movie = new Movie();
                        movie.setTitle(title);
                        movie.setOverseasBudget(overSeas);
                        movie.setWorldWideBudget(worldWideBudget);
                        movie.setDomesticBudget(domesticBudget);
                        movie.setReleaseDate(String.valueOf(year));
                        movies.add(movie);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return movies;
            }

            @Override
            protected void onPostExecute(ArrayList<Movie> movies) {
                listener.onSuccess(movies);

            }
        };
        task.execute();

    }

    public void mojoAllTheTime(final OnWebSuccess.OnMovieList listener) {
        AsyncTask<Void, Void, ArrayList<Movie>> task = new AsyncTask<Void, Void, ArrayList<Movie>>() {
            @Override
            protected ArrayList<Movie> doInBackground(Void... voids) {
                ArrayList<Movie> movies = new ArrayList<>();
                org.jsoup.nodes.Document
                        doc = null;
                try {
                    doc = Jsoup.connect("http://www.boxofficemojo.com/alltime/world/").get();

                    if (doc == null)
                        return movies;
                    Elements results = doc.getElementsByTag("tbody");
                    results = results.get(2).getElementsByTag("tr");
                    results.remove(0);


                    if (results == null)
                        return movies;
                    for (Element element : results) {
                        Elements attributes = element.getElementsByTag("td");
                        String title = attributes.get(1).text();
                        String worldWideBudget = attributes.get(3).text();
                        String domesticBudget = attributes.get(4).text();
                        String overSeas = attributes.get(6).text();
                        String year = attributes.get(8).text();
                        if (year.contains("^"))
                            year = year.replace("^", "");


                        Movie movie = new Movie();
                        movie.setTitle(title);
                        movie.setOverseasBudget(overSeas);
                        movie.setWorldWideBudget(worldWideBudget);
                        movie.setDomesticBudget(domesticBudget);
                        movie.setReleaseDate(year);
                        movies.add(movie);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return movies;
            }

            @Override
            protected void onPostExecute(ArrayList<Movie> movies) {
                listener.onSuccess(movies);

            }
        };
        task.execute();

    }


    public void mojoYearlyBoxOffice(final OnWebSuccess.OnMovieList listener) {
        AsyncTask<Void, Void, ArrayList<Movie>> task = new AsyncTask<Void, Void, ArrayList<Movie>>() {
            @Override
            protected ArrayList<Movie> doInBackground(Void... voids) {
                ArrayList<Movie> movies = new ArrayList<>();
                org.jsoup.nodes.Document
                        doc = null;
                try {
                    doc = Jsoup.connect("http://www.boxofficemojo.com/yearly/?view2=worldwide&view=releasedate").get();

                    if (doc == null)
                        return movies;
                    Elements results = doc.getElementsByTag("tbody");
                    results = results.get(2).getElementsByTag("tr");
                    results.remove(0);


                    if (results == null)
                        return movies;
                    for (Element element : results) {
                        Elements attributes = element.getElementsByTag("td");
                        String year = attributes.get(0).text();
                        String title = attributes.get(1).text();
                        String worldWideBudget = attributes.get(2).text();

                        if (year.contains("^"))
                            year = year.replace("^", "");


                        Movie movie = new Movie();
                        movie.setTitle(title);
                        movie.setWorldWideBudget(worldWideBudget);
                        movie.setReleaseDate(year);
                        movies.add(movie);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return movies;
            }

            @Override
            protected void onPostExecute(ArrayList<Movie> movies) {
                listener.onSuccess(movies);

            }
        };
        task.execute();

    }

}
