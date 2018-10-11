package com.example.khaledsabry.entertainment.Controllers;

import com.example.khaledsabry.entertainment.Connection.ApiConnections;
import com.example.khaledsabry.entertainment.Connection.YoutubeJson;
import com.example.khaledsabry.entertainment.Interfaces.OnSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnYoutubeSuccess;

import org.json.JSONObject;

/**
 * Created by KhALeD SaBrY on 24-Jul-18.
 */

public class YoutubeController {
    public enum Type {
        movie_review,
        season_review,
        episode_review,
        trailer_review,
        tv_review,
        featurette,
        trailer,
        behind_the_scenes,
        soundtrack,

    }

    private String url = Constants.youtubeConnectionSearchBaseUrl;
    private ApiConnections connection;
    private YoutubeJson youtubeJson;

    public YoutubeController() {
        connection = ApiConnections.getInstance();
        youtubeJson = new YoutubeJson();
    }

    private void addNoResults(Integer num) {
        url += "&maxResults=";
        if (num == null)
            url += String.valueOf(50);
        else
            url += String.valueOf(50);

    }

    private void addType(String type) {
        url += "&type=";
        if (type == null)
            url += "video";
        else
            url += type;
    }

    private void addSafeSearch() {
        url += "&safeSearch=moderate";
    }

    public void search(String name, String year, Type type, final OnYoutubeSuccess listener) {
        String searchQuery = name;
        if (year != null)
            searchQuery += " " +year;
        searchQuery = getAccordingToType(searchQuery, type);
        addNoResults(50);
        addSafeSearch();
        addType(null);
        addQuery(searchQuery);
        execute(new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(youtubeJson.getVideos(jsonObject));

            }
        });


    }

    private void addQuery(String searchQuery) {
        url += "&q=" + searchQuery;
    }

    private void execute(OnSuccess.Json listener) {
        connection.connect(url, listener);
    }

    private String getAccordingToType(String searchQuery, Type type) {

        switch (type)
        {
            case movie_review:
                searchQuery += " Movie Review";

                break;

            case tv_review:
                searchQuery += " Review";

                break;

            case season_review:

                break;

            case episode_review:
                searchQuery += " episode review";

                break;

            case trailer_review:

                searchQuery += " Trailer Review";

                break;

            case trailer:
                searchQuery += " Official Trailer";

                break;

            case featurette:
                searchQuery += " featurette";


                break;

            case soundtrack:

                searchQuery += " SoundTrack";

                break;

            case behind_the_scenes:
                searchQuery += " Behind the Scenes";

                break;
        }

        return searchQuery;
    }




    public void  getLatestTrailers( final OnYoutubeSuccess listener) {

        setBaseUrl(Constants.youtubeConnectionPlaylistBaseUrl);
        addPlaylist(Constants.latestTrailersPlaylist);
        execute(new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(youtubeJson.getVideos(jsonObject));

            }
        });


    }

    private void addPlaylist(String id)
    {
        url += "&playlistId="+id;
    }

    private void setBaseUrl(String baseUrl)
    {
        url = baseUrl;
    }
}
