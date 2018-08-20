package com.example.khaledsabry.entertainment.Connection;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieDataSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnWebSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.News;
import com.example.khaledsabry.entertainment.Items.Torrent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by KhALeD SaBrY on 14-Jul-18.
 */

public class WebApi {

    private static final WebApi ourInstance = new WebApi();


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
                } catch (Exception e) {

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

    public void mojoBoxOffice(final OnWebSuccess.OnMovieList listener) {
        AsyncTask<Void, Void, ArrayList<Movie>> task = new AsyncTask<Void, Void, ArrayList<Movie>>() {
            @Override
            protected ArrayList<Movie> doInBackground(Void... voids) {
                ArrayList<Movie> movies = new ArrayList<>();
                org.jsoup.nodes.Document
                        doc = null;
                try {
                    doc = Jsoup.connect("http://www.boxofficemojo.com/weekend/chart/").get();

                    if (doc == null)
                        return movies;
                    Elements results = doc.getElementsByTag("tbody");
                    results = results.get(3).getElementsByTag("tr");
                    results.remove(0);
                    results.remove(0);

                    results.remove(0);


                    if (results == null)
                        return movies;
                    for (Element element : results) {
                        Elements attributes = element.getElementsByTag("td");

                        String title = attributes.get(2).text();
                        String weekendGross = attributes.get(4).text();
                        String totalGross = attributes.get(9).text();
                        String budget = attributes.get(10).text();
                        String daysInBoxOffice = attributes.get(11).text();


                        budget += "M";


                        Movie movie = new Movie();
                        movie.setTitle(title);
                        movie.setRevneue(weekendGross);
                        movie.setTotalRevenue(totalGross);
                        movie.setBudget(budget);
                        movie.setNoWeeksInBoxOffice(daysInBoxOffice);
                        movies.add(movie);
                        if (movies.size() == 10)
                            break;

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {

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

    public void mojoDaily(final Integer dayNumber, final OnWebSuccess.OnMovieList listener) {

        @SuppressLint("StaticFieldLeak") AsyncTask<Void, Void, ArrayList<Movie>> task = new AsyncTask<Void, Void, ArrayList<Movie>>() {
            @Override
            protected ArrayList<Movie> doInBackground(Void... voids) {
                ArrayList<Movie> movies = new ArrayList<>();
                org.jsoup.nodes.Document
                        doc = null;


                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, dayNumber);
                Date date = calendar.getTime();

                String today = formatter.format(date);
                try {
                    doc = Jsoup.connect("http://www.boxofficemojo.com/daily/chart/?sortdate=" + today).get();

                    if (doc == null)
                        return null;
                    Elements results = doc.getElementsByTag("tbody");
                    results = results.get(8).getElementsByTag("tr");
                    results.remove(0);


                    if (results == null)
                        return movies;
                    for (Element element : results) {
                        Elements attributes = element.getElementsByTag("td");

                        String title = attributes.get(1).getElementsByTag("b").text();
                        Elements attribute = element.getElementsByTag("b");
                        String revenue = attribute.get(1).text();
                        if (revenue.equals("N/A"))
                            break;

                        revenue = attribute.get(1).childNode(0).toString();


                        Movie movie = new Movie();
                        movie.setTitle(title);
                        movie.setDomesticBudget(revenue);
                        movies.add(movie);
                        if (movies.size() == 10)
                            break;

                    }
                } catch (Exception e) {
                    return null;
                }
                return movies;
            }

            @Override
            protected void onPostExecute(ArrayList<Movie> movies) {
                if (movies == null)
                    return;
                if (movies.size() == 0)
                    mojoDaily(dayNumber - 1, listener);
                else
                    listener.onSuccess(movies);

            }
        };
        task.execute();

    }

    public void imdbCelebrityNews(final OnWebSuccess.OnNews listener) {
        imdbNews("celebrity", listener);
    }

    public void imdbTvNews(final OnWebSuccess.OnNews listener) {
        imdbNews("tv", listener);
    }

    public void imdbIndieNews(final OnWebSuccess.OnNews listener) {
        imdbNews("indie", listener);
    }

    public void imdbMovieNews(final OnWebSuccess.OnNews listener) {
        imdbNews("movie", listener);
    }

    public void imdbTopNews(final OnWebSuccess.OnNews listener) {
        imdbNews("top", listener);
    }

    private void imdbNews(final String type, final OnWebSuccess.OnNews listener) {
        AsyncTask<Void, Void, ArrayList<News>> task = new AsyncTask<Void, Void, ArrayList<News>>() {
            @Override
            protected ArrayList<News> doInBackground(Void... voids) {
                ArrayList<News> news = new ArrayList<>();
                org.jsoup.nodes.Document
                        doc = null;
                try {
                    doc = Jsoup.connect("https://www.imdb.com/news/" + type).get();

                    if (doc == null)
                        return news;
                    Elements results = doc.getElementsByClass("ipl-zebra-list__item news-article");


                    if (results == null)
                        return news;
                    for (Element element : results) {
                        Elements attributes = element.getElementsByClass("news-article__title");
                        String newsTitle = attributes.get(0).text();
                        Elements sdf = attributes.get(0).getElementsByAttribute("href");
                        String url = "https://www.imdb.com";
                        url += sdf.get(0).attr("href");

                        attributes = element.getElementsByClass("ipl-inline-list__item news-article__date");
                        String time = attributes.get(0).text();
                        String writtenBy = "";
                        try {
                            attributes = element.getElementsByClass("ipl-inline-list__item news-article__author");
                            writtenBy = attributes.get(0).text();
                        } catch (Exception e) {

                        }
                        attributes = element.getElementsByClass("ipl-inline-list__item news-article__source");
                        String source = attributes.get(0).text();


                        attributes = element.getElementsByClass("news-article__content");
                        String content = attributes.get(0).text();
                        String image = null;
                        try {
                            attributes = element.getElementsByClass("news-article__image");
                            Attributes attributes1 = attributes.get(0).attributes();
                            image = attributes1.get("src");
                            image = image.substring(0, image.indexOf("._") + 1);
                            image += "V1_SY600_SX400_AL_.jpg";
                        } catch (Exception e) {

                        }


                        News news1 = new News();
                        news1.setTitle(newsTitle);
                        news1.setContent(content);
                        news1.setImage(image);
                        news1.setSource(source);
                        news1.setTime(time);
                        news1.setUrl(url);
                        news1.setWrittenBy(writtenBy);

                        news.add(news1);


                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return news;
            }

            @Override
            protected void onPostExecute(ArrayList<News> news) {
                listener.onSuccess(news);

            }
        };
        task.execute();

    }


    public void imdbMovieDetails(final String imdbId, final OnWebSuccess.OnMovie listener) {

        AsyncTask<Void, Void, Movie> task = new AsyncTask<Void, Void, Movie>() {
            @Override
            protected Movie doInBackground(Void... voids) {
                Movie movie = new Movie();
                org.jsoup.nodes.Document
                        doc = null;
                try {
                    doc = Jsoup.connect("https://www.imdb.com/title/" + imdbId).get();

                    if (doc == null)
                        return movie;
                    Elements results = doc.getElementsByTag("script");
                    results = doc.getElementsByAttributeValue("type", "application/ld+json");
                    Element d = results.get(0);
                    String stringJsonObject = d.childNode(0).toString();
                    JSONObject jsonObject = new JSONObject(stringJsonObject);
                    String mpaa = jsonObject.getString("contentRating");
                    JSONObject ratings = jsonObject.getJSONObject("aggregateRating");
                    String imdbRate = ratings.getString("ratingValue");

                    movie.setImdbRate(Float.parseFloat(imdbRate));
                    movie.setMpaa(mpaa);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {

                }
                return movie;
            }

            @Override
            protected void onPostExecute(Movie movie) {
                listener.onSuccess(movie);

            }
        };
        task.execute();

    }

    public void rottenTomatoesMoviePreview(final String movie, final String year, final OnWebSuccess.OnMovie listener) {

        ApiConnections.getInstance().connect("https://www.rottentomatoes.com/api/private/v1.0/movies?q=" + movie + " " + year, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                Movie movie1 = new Movie();
                try {
                    JSONArray array = jsonObject.getJSONArray("movies");
                    int i = 0;
                    while (!array.isNull(0)) {
                        JSONObject object = array.getJSONObject(i);
                        String ryear = String.valueOf(object.getInt("year"));
                        if (year.equals(ryear))
                            break;
                        else
                            i++;


                    }
                    if (array.isNull(i))
                        return;
                    JSONObject object = array.getJSONObject(i);

                    JSONObject ratings = object.getJSONObject("ratings");
                    float score = ratings.getInt("critics_score");
                    String criticsRatingType = ratings.getString("critics_rating");
                    String tomatoesId = object.getString("id");
                    movie1.setRottentTomatoesRatingType(criticsRatingType);
                    movie1.setMovieRottenTomatoesId(tomatoesId);
                    movie1.setRottenTomatoesRate(score / 10);
                    listener.onSuccess(movie1);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    public void imdbTop250Movies(final String imdbId, final OnWebSuccess.OnMovie listener) {
        AsyncTask<Void,Movie,Void> task = new AsyncTask<Void, Movie, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Movie movie = new Movie();
                TmdbController tmdbController = new TmdbController();

                org.jsoup.nodes.Document
                        doc = null;
                try {
                    doc = Jsoup.connect("https://www.imdb.com/chart/top").get();

                    if (doc == null)
                        return null;
                    Elements results = doc.getElementsByAttributeValue("class", "lister-list");
                    Elements titleElement = doc.getElementsByAttributeValue("class", "titleColumn");
                    Elements ratingElement = doc.getElementsByAttributeValue("class","ratingColumn imdbRating");
                    Elements imdbIdElement  = doc.getElementsByAttributeValue("class","ratingColumn");
                    for (int i =0 ; i < titleElement.size();i++) {

                        Attributes s = imdbIdElement.get(0).attributes();
                        String imdbId = imdbIdElement.get(0).attr("data-titleid");
tmdbController.getMovieByImdb("", new OnMovieDataSuccess() {
    @Override
    public void onSuccess(Movie movie) {
publishProgress(movie);
    }
});




                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {

                }
                return null;

            };


            @Override
            protected void onProgressUpdate(Movie... values) {
                listener.onSuccess(values[0]);
            }
        };


task.execute();
    }

}
