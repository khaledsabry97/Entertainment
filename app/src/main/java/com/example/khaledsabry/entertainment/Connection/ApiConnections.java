package com.example.khaledsabry.entertainment.Connection;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.khaledsabry.entertainment.Controllers.Settings;
import com.example.khaledsabry.entertainment.Interfaces.OnSuccess;
import com.example.khaledsabry.entertainment.Items.Season;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by KhALeD SaBrY on 26-Jun-18.
 */

public class ApiConnections {
    private String tmdbBaseUrl = "https://api.themoviedb.org/3/";
    private String oneOmBaseUrl = "https://oneom.tk/";
    private Context context;
    private String url;
    private static final ApiConnections ourInstance = new ApiConnections();
    private ArrayList<JsonObjectRequest> jsonObjectRequestArrayList = new ArrayList<>();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getTmdbBaseUrl() {
        return tmdbBaseUrl;
    }

    public String getApiKey() {
        return "?api_key=" + Settings.TmdbApiKey;
    }

    public String getOneOmBaseUrl() {
        return oneOmBaseUrl;
    }

    public static ApiConnections getInstance() {
        return ourInstance;
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
        ){
             @Override
             public Map<String, String> getHeaders() throws AuthFailureError {
                 Map<String, String> header = new LinkedHashMap<String, String>();
                 header.put("Content-Type", "application/json");
                 return super.getHeaders();
             }
         };
         jsonObjectRequestArrayList.clear();
        jsonObjectRequestArrayList.add(jsonObjectRequest);
        Volley.newRequestQueue(context).add(jsonObjectRequest);

    }

    public void connectOneom(String url, final OnSuccess listener) {

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
        ) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Accept", "application/json");
                return params;
            }
        };

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Volley.newRequestQueue(context).add(jsonObjectRequest);

    }


    public void stopConnection()
    {
        for (JsonObjectRequest jsonObjectRequest:jsonObjectRequestArrayList
             ) {

            jsonObjectRequest.cancel();



        }
        jsonObjectRequestArrayList.clear();
    }

}
