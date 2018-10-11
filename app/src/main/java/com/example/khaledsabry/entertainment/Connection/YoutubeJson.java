package com.example.khaledsabry.entertainment.Connection;

import com.example.khaledsabry.entertainment.Items.Youtube;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 24-Jul-18.
 */

public class YoutubeJson {
    private String items = "items";
    private String id = "id";
    private String snippet = "snippet";
    private String title = "title";
    private String description = "description";
    private String thumbnails = "thumbnails";
    private String maxres = "maxres";
    private String url = "url";
    private String localized = "localized";
    private String name = "name";
    private String videoId = "videoId";
    private String high = "high";
    private String mid = "mid";
    private String status = "status";
    private String tagline = "tagline";


    /**
     * gets a videos from json object fromthe youtube controller
     *
     * @param jsonObject a json object that sent by youtubeController
     * @return returns a list of youtube videos
     */
    public ArrayList<Youtube> getVideos(JSONObject jsonObject) {
        ArrayList<Youtube> youtubes = new ArrayList<>();
        int i = 0;
        try {
            JSONArray jsonArray = jsonObject.getJSONArray(items);

            while (!jsonArray.isNull(i)) {
                Youtube youtube = new Youtube();
                JSONObject object = jsonArray.getJSONObject(i);
                JSONObject id = object.getJSONObject(this.id);
                String videoId = id.getString(this.videoId);
                JSONObject snippet = object.getJSONObject(this.snippet);
                String title = snippet.getString(this.title);
                String description = snippet.getString(this.description);
                JSONObject thumbnail = snippet.getJSONObject(this.thumbnails);
                JSONObject img;
                if (thumbnail.isNull(this.maxres))
                    img = thumbnail.getJSONObject(this.high);
                else
                    img = thumbnail.getJSONObject(this.maxres);
                String url = img.getString(this.url);


                youtube.setTitle(title);
                youtube.setDescription(description);
                youtube.setId(videoId);
                youtube.setPosterUrl(url);

                if (!description.equals("This video is private."))
                    youtubes.add(youtube);
                i++;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return youtubes;
    }


}
