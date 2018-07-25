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
        trailer_review,
        tv_review,
        featurette,
        trailer,
        behind_the_scenes,
        soundtrack,

    }

    private String url = Settings.youtubeConnectionBaseUrl;
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
            searchQuery += year;
        searchQuery = getAccordingToType(searchQuery, type);
        addNoResults(50);
        addSafeSearch();
        addType(null);
        addQuery(searchQuery);
        execute(new OnSuccess() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(youtubeJson.getVideos(jsonObject));
            }
        });
    }

    private void addQuery(String searchQuery) {
        url += "&q=" + searchQuery;
    }

    private void execute(OnSuccess listener) {
        connection.connect(url, listener);
    }

    private String getAccordingToType(String searchQuery, Type type) {
        if (type == Type.movie_review)
            searchQuery += " Review";
        else if (type == Type.trailer_review)
            searchQuery += " Trailer Review";
        else if (type == Type.trailer)
            searchQuery += " Official Trailer";
        else if (type == Type.featurette)
            searchQuery += " featurette";
        else if (type == Type.behind_the_scenes)
            searchQuery += " Behind the Scenes";
        else if (type == Type.soundtrack)
            searchQuery += " SoundTrack";
        else if (type == Type.tv_review)
            searchQuery += " Review";


        return searchQuery;
    }
}
