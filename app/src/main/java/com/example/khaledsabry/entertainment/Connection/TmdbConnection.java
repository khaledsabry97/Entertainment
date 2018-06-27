package com.example.khaledsabry.entertainment.Connection;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.khaledsabry.entertainment.OnSuccess;

import org.json.JSONObject;

/**
 * Created by KhALeD SaBrY on 26-Jun-18.
 */

public class TmdbConnection {
    private String baseUrl = "https://api.themoviedb.org/3/";
    private String apiKey = "?api_key=3628f9974c19710b3a434cf958458799";
    private Context context;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

    private static final TmdbConnection ourInstance = new TmdbConnection();

    public static TmdbConnection getInstance() {
        return ourInstance;
    }

    private TmdbConnection() {
    }

    public void connect(String url, final OnSuccess listener) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                JsonObjectRequest.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("Error", error.toString());
            }
        }
        );

        Volley.newRequestQueue(context).add(jsonObjectRequest);

    }
}
