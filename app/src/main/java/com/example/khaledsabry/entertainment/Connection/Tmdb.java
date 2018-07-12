package com.example.khaledsabry.entertainment.Connection;

import com.example.khaledsabry.entertainment.Items.Artist;
import com.example.khaledsabry.entertainment.Items.Character;
import com.example.khaledsabry.entertainment.Items.Collection;
import com.example.khaledsabry.entertainment.Items.Crew;
import com.example.khaledsabry.entertainment.Items.Genre;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.ProductionCompany;
import com.example.khaledsabry.entertainment.Items.Review;
import com.example.khaledsabry.entertainment.Items.SearchItem;
import com.example.khaledsabry.entertainment.Items.Season;
import com.example.khaledsabry.entertainment.Items.Tv;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by KhALeD SaBrY on 27-Jun-18.
 */

public class Tmdb {


    private String original_title = "original_title";
    private String id = "id";
    private String imdb_id = "imdb_id";
    private String original_language = "original_language";
    private String overview = "overview";
    private String popularity = "popularity";
    private String poster_path = "poster_path";
    private String production_companies = "production_companies";
    private String logo_path = "logo_path";
    private String name = "name";
    private String origin_country = "origin_country";
    private String revenue = "revenue";
    private String runtime = "runtime";
    private String status = "status";
    private String tagline = "tagline";
    private String title = "title";
    private String vote_average = "vote_average";
    private String adult = "adult";
    private String budget = "budget";
    private String genres = "genres";
    private String cast = "cast";
    private String gender = "gender";
    private String order = "order";
    private String profile_path = "profile_path";
    private String character = "character";
    private String credits = "credits";
    private String crew = "crew";
    private String job = "job";
    private String release_date = "release_date";
    private String images = "images";
    private String posters = "posters";
    private String backdrops = "backdrops";
    private String file_path = "file_path";
    private String videos = "videos";
    private String results = "results";
    private String key = "key";
    private String genre_ids = "genre_ids";
    private String reviews = "reviews";
    private String author = "author";
    private String content = "content";
    private String belongs_to_collection = "belongs_to_collection";
    private String backdrop_path = "backdrop_path";
    private String media_type = "media_type";
    private String person = "person";
    private String movie = "movie";
    private String tv = "tv";
    private String known_for = "known_for";
    private String birthday = "birthday";
    private String deathday = "deathday";
    private String place_of_birth = "place_of_birth";
    private String biography = "biography";
    private String profiles = "profiles";
    private String episode_run_time = "episode_run_time";
    private String first_air_date = "first_air_date";
    private String last_air_date = "last_air_date";
    private String number_of_episodes = "number_of_episodes";
    private String number_of_seasons = "number_of_seasons";
    private String in_production = "in_production";
    private String seasons = "seasons";
    private String season_number = "season_number";
    private String air_date  = "air_date";
    private String episode_count  = "episode_count";
    private String networks ="networks";




    private static final Tmdb ourInstance = new Tmdb();

    public static Tmdb getInstance() {
        return ourInstance;
    }

    private Tmdb() {
    }


    public Movie showMovie(JSONObject movieDetails) {

        Movie movie = new Movie();
        try {

            JSONObject cast = movieDetails.getJSONObject(credits);
            JSONObject videos = movieDetails.getJSONObject(this.videos);
            JSONObject reviews = movieDetails.getJSONObject(this.reviews);
            JSONObject collection;
            if (!movieDetails.isNull(this.belongs_to_collection)) {
                collection = movieDetails.getJSONObject(this.belongs_to_collection);


                int id = collection.getInt(this.id);
                String name = collection.getString(this.name);
                String poster = collection.getString(this.poster_path);
                String background = collection.getString(this.backdrop_path);
                Collection collection1 = new Collection(id, name, poster, background);
                movie.setCollection(collection1);

            }
            JSONArray jsonArray;
            int i;
            ArrayList<ProductionCompany> productionCompanies = new ArrayList<>();
            ArrayList<Genre> genre = new ArrayList<>();
            ArrayList<Character> characters = new ArrayList<>();
            ArrayList<Crew> crews = new ArrayList<>();
            ArrayList<String> trailers = new ArrayList<>();
            ArrayList<Review> reviews1 = new ArrayList<>();


            jsonArray = movieDetails.getJSONArray(production_companies);
            i = 0;
            while (!jsonArray.isNull(i)) {
                JSONObject object = jsonArray.getJSONObject(i);
                int ids = object.getInt(id);
                String logo = object.getString(logo_path);
                String nam = object.getString(name);
                String countr = object.getString(origin_country);
                ProductionCompany productionCompany = new ProductionCompany(ids, logo, nam, countr);
                productionCompanies.add(productionCompany);
                i++;
            }
            jsonArray = movieDetails.getJSONArray(genres);
            i = 0;

            while (!jsonArray.isNull(i)) {
                JSONObject object = jsonArray.getJSONObject(i);
                int ids = object.getInt(id);
                String nam = object.getString(name);

                Genre gen = new Genre(ids, nam);
                genre.add(gen);
                i++;
            }

/*
            jsonArray = cast.getJSONArray(this.cast);
            i = 0;

            while (!jsonArray.isNull(i)) {
                JSONObject object = jsonArray.getJSONObject(i);
                int ids = object.getInt(id);
                String nam = object.getString(name);
                String profilePath = object.getString(profile_path);
                int gend = object.getInt(gender);
                String characterName = object.getString(this.character);
                Artist artist = new Artist(nam, ids, gend, profilePath);
                Character character = new Character(artist, characterName);


                characters.add(character);
                i++;
            }*/
            i = 0;

/*
            jsonArray = cast.getJSONArray(crew);
            i = 0;
            while (!jsonArray.isNull(i)) {
                JSONObject object = jsonArray.getJSONObject(i);
                int ids = object.getInt(id);
                String nam = object.getString(name);
                String jobtitle = object.getString(job);
                String profilePath = object.getString(profile_path);
                int gend = object.getInt(gender);

                Artist artist = new Artist(nam, ids, gend, profilePath);
                Crew crew = new Crew(artist, jobtitle);
                crews.add(crew);


                i++;
            }
*/

            jsonArray = videos.getJSONArray(this.results);
            i = 0;
            while (!jsonArray.isNull(i)) {
                JSONObject object = jsonArray.getJSONObject(i);
                String filepath = object.getString(this.key);
                trailers.add(filepath);
                i++;
            }

            jsonArray = reviews.getJSONArray(this.results);
            i = 0;
            while (!jsonArray.isNull(i)) {
                JSONObject object = jsonArray.getJSONObject(i);
                String name = object.getString(this.author);
                String content = object.getString(this.content);
                Review review = new Review(content, name);
                reviews1.add(review);
                i++;
            }
            movie.setTitle(movieDetails.getString(title));
            movie.setBudget(movieDetails.getInt(budget));
            movie.setLanguage(movieDetails.getString(original_language));
            movie.setOverView(movieDetails.getString(overview));
            movie.setMovieImdbId(movieDetails.getString(imdb_id));
            movie.setMovieId(movieDetails.getInt(id));
            movie.setRevneue(movieDetails.getInt(revenue));
            movie.setPopularity(movieDetails.getDouble(popularity));
            movie.setPosterImage(movieDetails.getString(poster_path));
            movie.setTmdbRate(movieDetails.getInt(vote_average));
            movie.setStatus(movieDetails.getString(status));
            movie.setRunTime(movieDetails.getInt(runtime));
            movie.setAdult(movieDetails.getBoolean(adult));
            movie.setReleaseDate(movieDetails.getString(release_date));
            movie.setTrailers(trailers);
        //    movie.setReviews(reviews1);
            movie.setReviews(getReviews(movieDetails));
           // movie.setCharacters(characters);
            movie.setCharacters(getCharacters(movieDetails));
            movie.setGenres(genre);
            movie.setProductionCompanies(productionCompanies);
           // movie.setCrews(crews);
            movie.setCrews(getCrew(movieDetails));

            getPosters(movieDetails, movie);
        } catch (JSONException e) {
            String s = e.toString();

        }
        return movie;

    }


    public ArrayList<Movie> showRecommendationsAndSimilar(JSONObject movieDetails) {
        ArrayList<Movie> movies = new ArrayList<>();
        JSONArray jsonArray = null;
        try {
            int i;
            jsonArray = movieDetails.getJSONArray(this.results);
            i = 0;
            while (!jsonArray.isNull(i)) {
                JSONObject object = jsonArray.getJSONObject(i);
                getMovies(object, movies);


                i++;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movies;
    }


    private void getMovies(JSONObject movieDetails, ArrayList<Movie> movies) {
        try {

            Movie movie = new Movie();
            JSONArray jsonArray;
            int i;

            ArrayList<Genre> genre = new ArrayList<>();


            jsonArray = movieDetails.getJSONArray(genre_ids);
            i = 0;

            while (!jsonArray.isNull(i)) {
                int ids = jsonArray.getInt(i);

                Genre gen = new Genre(ids);
                genre.add(gen);
                i++;
            }


            movie.setTitle(movieDetails.getString(title));
            movie.setLanguage(movieDetails.getString(original_language));
            movie.setOverView(movieDetails.getString(overview));
            movie.setMovieId(movieDetails.getInt(id));
            movie.setPosterImage(movieDetails.getString(poster_path));
            movie.setTmdbRate(movieDetails.getInt(vote_average));
            movie.setAdult(movieDetails.getBoolean(adult));
            movie.setReleaseDate(movieDetails.getString(release_date));

            movie.setGenres(genre);
            movies.add(movie);

        } catch (JSONException e) {
            String s = e.toString();

        }
    }


    public Movie getMovieVideosCreditsCategories(JSONObject movieDetails) {
        Movie movie = new Movie();
        try {

            JSONObject cast = movieDetails.getJSONObject(credits);
            JSONObject videos = movieDetails.getJSONObject(this.videos);


            JSONArray jsonArray;
            int i;
            ArrayList<ProductionCompany> productionCompanies = new ArrayList<>();
            ArrayList<Character> characters = new ArrayList<>();
            ArrayList<Crew> crews = new ArrayList<>();
            ArrayList<String> trailers = new ArrayList<>();


            jsonArray = movieDetails.getJSONArray(production_companies);
            i = 0;
            while (!jsonArray.isNull(i)) {
                JSONObject object = jsonArray.getJSONObject(i);
                int ids = object.getInt(id);
                String logo = object.getString(logo_path);
                String nam = object.getString(name);
                String countr = object.getString(origin_country);
                ProductionCompany productionCompany = new ProductionCompany(ids, logo, nam, countr);
                productionCompanies.add(productionCompany);
                i++;
            }


            jsonArray = cast.getJSONArray(this.cast);
            i = 0;

            while (!jsonArray.isNull(i)) {
                JSONObject object = jsonArray.getJSONObject(i);
                int ids = object.getInt(id);
                String nam = object.getString(name);
                String profilePath = object.getString(profile_path);
                int gend = object.getInt(gender);
                String characterName = object.getString(this.character);
                Artist artist = new Artist(nam, ids, gend, profilePath);
                Character character = new Character(artist, characterName);


                characters.add(character);
                i++;
            }


            jsonArray = cast.getJSONArray(crew);
            i = 0;
            while (!jsonArray.isNull(i)) {
                JSONObject object = jsonArray.getJSONObject(i);
                int ids = object.getInt(id);
                String nam = object.getString(name);
                String jobtitle = object.getString(job);
                String profilePath = object.getString(profile_path);
                int gend = object.getInt(gender);

                Artist artist = new Artist(nam, ids, gend, profilePath);
                Crew crew = new Crew(artist, jobtitle);
                crews.add(crew);


                i++;
            }


            jsonArray = videos.getJSONArray(this.results);
            i = 0;
            while (!jsonArray.isNull(i)) {
                JSONObject object = jsonArray.getJSONObject(i);
                String filepath = object.getString(this.key);
                trailers.add(filepath);
                i++;
            }


            movie.setMovieId(movieDetails.getInt(id));
            movie.setPopularity(movieDetails.getDouble(popularity));
            movie.setTrailers(trailers);

            movie.setCharacters(characters);
            movie.setProductionCompanies(productionCompanies);
            movie.setCrews(getCrew(movieDetails));

        } catch (JSONException e) {
            String s = e.toString();

        }
        return movie;
    }


    public Movie getPostersBackdrops(JSONObject images, Movie movie) {

        try {


            JSONArray jsonArray;
            int i;


            ArrayList<String> posters = new ArrayList<>();
            ArrayList<String> backdrops = new ArrayList<>();


            jsonArray = images.getJSONArray(this.posters);
            i = 0;
            while (!jsonArray.isNull(i)) {
                JSONObject object = jsonArray.getJSONObject(i);
                String filepath = object.getString(this.file_path);
                posters.add(filepath);
                i++;
            }


            jsonArray = images.getJSONArray(this.backdrops);
            i = 0;
            while (!jsonArray.isNull(i)) {
                JSONObject object = jsonArray.getJSONObject(i);
                String filepath = object.getString(this.file_path);
                backdrops.add(filepath);
                i++;
            }


            movie.setPosters(posters);
            movie.setBackdrops(backdrops);


        } catch (JSONException e) {
            String s = e.toString();

        }
        return movie;

    }

    public ArrayList<String> getPosters(JSONObject movieDetails, Movie movie) {
        ArrayList<String> posters = new ArrayList<>();

        try {

            JSONObject images = movieDetails.getJSONObject(this.images);


            JSONArray jsonArray;
            int i;




            jsonArray = images.getJSONArray(this.posters);
            i = 0;
            while (!jsonArray.isNull(i)) {
                JSONObject object = jsonArray.getJSONObject(i);
                String filepath = object.getString(this.file_path);
                posters.add(filepath);
                i++;
            }


            movie.setPosters(posters);


        } catch (JSONException e) {
            String s = e.toString();

        }
        return posters;

    }

    public ArrayList<Review>  getReviews(JSONObject movieDetails) {
        ArrayList<Review> reviews = new ArrayList<>();

        try {


            JSONArray jsonArray;
            int i;




            jsonArray = movieDetails.getJSONArray(this.results);
            i = 0;
            while (!jsonArray.isNull(i)) {
                JSONObject object = jsonArray.getJSONObject(i);
                String author = object.getString(this.author);
                String content = object.getString(this.content);
                Review review = new Review(content, author);

                reviews.add(review);
                i++;
            }



        } catch (JSONException e) {
            String s = e.toString();

        }
        return reviews;

    }


    public ArrayList<SearchItem> getSearchDone(JSONObject jsonObject) {
        ArrayList<SearchItem> searchItems = new ArrayList<>();

        try {
            JSONArray result = jsonObject.getJSONArray(this.results);
            int i = 0;

            while (!result.isNull(i)) {
                JSONObject object = result.getJSONObject(i);
                SearchItem searchItem = new SearchItem();
                String type = object.getString(this.media_type);
                searchItem.setType(type);
                if (type.equals(this.movie))
                    searchItem.setMovie(getSearchMovie(object));
                else if (type.equals(this.tv))
                    searchItem.setTv(getSearchTv(object));
                else if (type.equals(this.person))
                    searchItem.setArtist(getSearchArtist(object));

                searchItems.add(searchItem);
                i++;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return searchItems;
    }

    private Tv getSearchTv(JSONObject object) {
        Tv tv = new Tv();

        return tv;
    }

    private Movie getSearchMovie(JSONObject object) {
        Movie movie = new Movie();

        try {

            JSONObject movieDetails = object;

            JSONArray jsonArray;
            int i;

            ArrayList<Genre> genre = new ArrayList<>();


            jsonArray = movieDetails.getJSONArray(this.genre_ids);
            i = 0;

            while (!jsonArray.isNull(i)) {
                int ids = jsonArray.getInt(i);

                Genre gen = new Genre();
                gen.setId(ids);
                genre.add(gen);
                i++;
            }


            movie.setTitle(movieDetails.getString(title));
            movie.setLanguage(movieDetails.getString(original_language));
            movie.setOverView(movieDetails.getString(overview));
            movie.setMovieId(movieDetails.getInt(id));
            movie.setPopularity(movieDetails.getDouble(popularity));
            movie.setPosterImage(movieDetails.getString(poster_path));
            movie.setTmdbRate(movieDetails.getInt(vote_average));
            movie.setAdult(movieDetails.getBoolean(adult));
            movie.setReleaseDate(movieDetails.getString(release_date));
            movie.setGenres(genre);
            movie.setBackDropPoster(movieDetails.getString(this.backdrop_path));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movie;
    }

    private Artist getSearchArtist(JSONObject object) {
        Artist artist = new Artist();
        ArrayList<Movie> movies = new ArrayList<>();
        ArrayList<Tv> tvs = new ArrayList<>();
        try {

            JSONArray jsonArray = object.getJSONArray(this.known_for);
            int i = 0;
            while (!jsonArray.isNull(i)) {
                JSONObject object1 = jsonArray.getJSONObject(i);
                String type = object1.getString(this.media_type);

                if (type.equals(this.movie))
                    movies.add(getSearchMovie(object));
                else if (type.equals(this.tv))
                    tvs.add(getSearchTv(object));
                i++;
            }

            artist.setId(object.getInt(this.id));
            artist.setName(object.getString(this.name));
            artist.setPosterImage(object.getString(this.profile_path));
            artist.setPopularity(object.getDouble(this.popularity));

            artist.setMovies(movies);
            artist.setTvs(tvs);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return artist;
    }

    public Movie getGenres(JSONObject jsonObject) {
        Movie movie = new Movie();
        try {
            JSONArray jsonArray = jsonObject.getJSONArray(this.genres);
            int i = 0;
            ArrayList<Genre> genres = new ArrayList<>();
            while (!jsonArray.isNull(i)) {
                JSONObject object = jsonArray.getJSONObject(i);
                int id = object.getInt(this.id);
                String name = object.getString(this.name);

                Genre genre = new Genre(id, name);

                genres.add(genre);
                i++;

            }


            movie.setGenres(genres);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movie;
    }


    public Artist getPersonDetails(JSONObject object) {
        Artist artist = new Artist();
        try {
            artist.setId(object.getInt(this.id));
            artist.setPopularity(object.getDouble(this.popularity));
            artist.setPosterImage(object.getString(this.profile_path));
            artist.setBirthDay(object
                    .getString(this.birthday));
            artist.setDeathDay(object.getString(this.deathday));
            artist.setName(object.getString(this.name));
            artist.setGender(object.getInt(this.gender));
            artist.setBiography(object.getString(this.biography));
            artist.setPlaceOfBirth(object.getString(this.place_of_birth));
            artist.setImdbId(object.getString(this.imdb_id));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return artist;
    }


    public Artist getPersonRoles(JSONObject obj) {
        Artist artist = new Artist();

        try {
            ArrayList<Character> characters = new ArrayList<>();
            ArrayList<Movie> movies = new ArrayList<>();
            ArrayList<Tv> tvs = new ArrayList<>();
            JSONArray result = obj.getJSONArray(this.cast);
            int i = 0;

            while (!result.isNull(i)) {
                JSONObject object = result.getJSONObject(i);
                Movie movie = new Movie();
                Tv tv = new Tv();
                Character character = new Character();
                character.setCharacterName(object.getString(this.character));
                String type = object.getString(this.media_type);
                if (type.equals(this.movie)) {
                    movie = getSearchMovie(object);
                    movies.add(movie);
                } else if (type.equals(this.tv)) {
                    tv = getSearchTv(object);
                    tvs.add(tv);
                }

                i++;
            }
            artist.setMovies(movies);
            artist.setTvs(tvs);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return artist;
    }

    public Artist getPersonImages(JSONObject obj) {
        Artist artist = new Artist();

        try {
            ArrayList<String> images = new ArrayList<>();
            JSONArray result = obj.getJSONArray(this.profiles);
            int i = 0;

            while (!result.isNull(i)) {
                JSONObject object = result.getJSONObject(i);
                images.add(object.getString(this.file_path));
                i++;
            }
            artist.setImages(images);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return artist;
    }


    public Tv getTv(JSONObject obj,Tv tv) {

        try {


            //get the credits
            tv.setCrew(getCrew(obj));
            tv.setActors(getCharacters(obj));

            tv.setReviews(getReviews(obj));
            tv.setPosters(getPosters(obj,new Movie()));
tv.setSeasons(getSeasons(obj));
tv.setProductionCompanies(getProductionCompanies(obj));
tv.setNetworks(getNetworks(obj));

JSONArray jsonArray = obj.getJSONArray(this.episode_run_time);

tv.setRunTime(jsonArray.getInt(0));
            tv.setBackDrop(obj.getString(this.backdrop_path));

            tv.setFirstAirDate(obj.getString(this.first_air_date));

tv.setGenres(getGenre(obj));
tv.setId(obj.getInt(this.id));
tv.setInProduction(obj.getBoolean(this.in_production));
tv.setFirstAirDate(obj.getString(this.first_air_date));
tv.setLastAirDate(obj.getString(this.last_air_date));
tv.setTitle(obj.getString(this.name));


tv.setNumberOfEpisodes(obj.getInt(this.number_of_episodes));
tv.setNumberOfSeasons(obj.getInt(this.number_of_seasons));
tv.setOverView(obj.getString(this.overview));
tv.setPopularity(obj.getDouble(this.popularity));
tv.setPosterImage(obj.getString(this.poster_path));
tv.setStatus(obj.getString(this.status));
tv.setRateTmdb(obj.getDouble(this.vote_average));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tv;
    }



    private ArrayList<Character> getCharacters(JSONObject obj)
    {
        int i = 0;
        JSONArray jsonArray;
        ArrayList<Character> actors = new ArrayList<>();
        try {
            JSONObject credit = obj.getJSONObject(credits);

            jsonArray = credit.getJSONArray(this.cast);
            i =0;

            while (!jsonArray.isNull(i))
            {
                JSONObject object = jsonArray.getJSONObject(i);
                String charcter = object.getString(this.character);
                String nam = object.getString(this.name);
                String poster = object.getString(this.profile_path);
                int gend = object.getInt(this.gender);
                int id = object.getInt(this.id);
                Artist artist = new Artist(nam,id,gend,poster);
                Character character = new Character(artist,charcter);

                actors.add(character);

                i++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return actors;
    }


    private ArrayList<Artist> getCrew(JSONObject obj)
    {
        int i = 0;
        JSONArray jsonArray;
        ArrayList<Artist> crew = new ArrayList<>();
        try {
            JSONObject credit = obj.getJSONObject(credits);


            jsonArray = credit.getJSONArray(this.crew);
            i =0;

            while (!jsonArray.isNull(i))
            {
                JSONObject object = jsonArray.getJSONObject(i);
                String job = object.getString(this.job);
                String nam = object.getString(this.name);
                String poster = object.getString(this.profile_path);
                int gend = object.getInt(this.gender);
                int id = object.getInt(this.id);
                Artist artist = new Artist(nam,id,gend,poster);
                artist.setJob(job);


                crew.add(artist);

                i++;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return crew;
    }

    private ArrayList<Genre> getGenre(JSONObject obj)
    {
        JSONArray jsonArray;
        int i =0;
        ArrayList<Genre> genre = new ArrayList<>();
        try {
        jsonArray = obj.getJSONArray(genres);
        i = 0;

        while (!jsonArray.isNull(i)) {
            JSONObject object = null;

                object = jsonArray.getJSONObject(i);

            int ids = object.getInt(id);
            String nam = object.getString(name);

            Genre gen = new Genre(ids, nam);
            genre.add(gen);
            i++;
        }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return genre;
    }


    private ArrayList<Season> getSeasons(JSONObject obj)
    {
        ArrayList<Season> seasons = new ArrayList<>();
        try {
            JSONArray jsonArray = obj.getJSONArray(this.seasons);
            int i =0;

            while(!jsonArray.isNull(i))
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String air = jsonObject.getString(this.air_date);
                int count = jsonObject.getInt(this.episode_count);
                int id= jsonObject.getInt(this.id);
                int no = jsonObject.getInt(this.season_number);
                String name = jsonObject.getString(this.name);
                String overview = jsonObject.getString(this.overview);
                String poster = jsonObject.getString(this.poster_path);

                Season season = new Season();
                season.setAirDate(air);
                season.setId(id);
                season.setName(name);
                season.setSeasonNumber(no);
                season.setOverView(overview);
                season.setPoster(poster);
                season.setEpisodeCount(count);

                seasons.add(season);


                i++;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
return seasons;
    }

    private ArrayList<ProductionCompany> getProductionCompanies(JSONObject obj)
    {
        ArrayList<ProductionCompany> productionCompanies = new ArrayList<>();
        try {
            JSONArray jsonArray = obj.getJSONArray(this.production_companies);
            int i =0;

            while(!jsonArray.isNull(i))
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
             int id = jsonObject.getInt(this.id);
             String image = jsonObject.getString(this.logo_path);
             String name = jsonObject.getString(this.name);
             String country = jsonObject.getString(this.origin_country);


                ProductionCompany productionCompany = new ProductionCompany(id,image,name,country);
                productionCompanies.add(productionCompany);


                i++;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return productionCompanies;
    }


    private ArrayList<ProductionCompany> getNetworks(JSONObject obj)
    {
        ArrayList<ProductionCompany> networks = new ArrayList<>();
        try {
            JSONArray jsonArray = obj.getJSONArray(this.networks);
            int i =0;

            while(!jsonArray.isNull(i))
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt(this.id);
                String image = jsonObject.getString(this.logo_path);
                String name = jsonObject.getString(this.name);
                String country = jsonObject.getString(this.origin_country);


                ProductionCompany productionCompany = new ProductionCompany(id,image,name,country);
                networks.add(productionCompany);


                i++;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return networks;
    }
}
