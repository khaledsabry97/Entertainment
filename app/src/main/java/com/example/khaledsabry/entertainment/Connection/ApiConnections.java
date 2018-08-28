package com.example.khaledsabry.entertainment.Connection;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.Constants;
import com.example.khaledsabry.entertainment.Interfaces.OnSuccess;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by KhALeD SaBrY on 26-Jun-18.
 */

public class ApiConnections {
//this is the main and the only instance object of this class
    private  static ApiConnections ourInstance = new ApiConnections();
    private ArrayList<JsonObjectRequest> jsonObjectRequestArrayList = new ArrayList<>();


    //Singleton pattern
    private ApiConnections()
    {

    }
    public static  ApiConnections getInstance() {
        if(ourInstance == null)
            ourInstance = new ApiConnections();
        return ourInstance;
    }

    //listener: return the json object after gets it
    //url: the http website to get the required json
    public void connect(String url, final OnSuccess.Json listener) {

        url = url.replace(" ","%20");


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
        if(url.equals("search/multi"))
jsonObjectRequestArrayList.add(jsonObjectRequest);
        Volley.newRequestQueue(MainActivity.getActivity().getApplicationContext()).add(jsonObjectRequest);

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
