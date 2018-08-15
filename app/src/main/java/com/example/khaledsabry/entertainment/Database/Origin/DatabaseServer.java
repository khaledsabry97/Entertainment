package com.example.khaledsabry.entertainment.Database.Origin;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Interfaces.OnDatabaseSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnSuccess;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by KhALeD SaBrY on 01-Aug-18.
 */

public class DatabaseServer {

    //base url to the server
    private final String baseUrl = "https://water-gas-oil.000webhostapp.com/";
    //and these are the files on the server
    private final String inserting = "inserting.php";
    private final String selecting = "selecting.php";
    private final String deleting = "deleting.php";
    private final String updating = "updating.php";

    //this is a commen string i use it in every call to every file
    private final String serverResponse = "server_response";

    //this is a main function that i use it from only the functions of this class
    private void connect(int request, String url, final String query, final OnSuccess.Json listener) {
        StringRequest stringRequest = new StringRequest(request, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        response.toString();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            listener.onSuccess(jsonObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.toString();
            }
        }) {
            //adding parameters to the request
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("query", query);
                return params;
            }
        };


        Volley.newRequestQueue(MainActivity.getActivity().getApplicationContext()).add(stringRequest);
    }

    //insert function to send it to the database
    public void insert(final String query, final OnDatabaseSuccess.bool listener) {
        String url = baseUrl + inserting;
        connect(Request.Method.POST, url, query, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                boolean state = objectJson(jsonObject);
                listener.onSuccess(state);
            }
        });
    }

    //select function to get data from the database
    public void select(final String query, final OnDatabaseSuccess.array listener) {
        String url = baseUrl + selecting;
        connect(Request.Method.POST, url, query, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                ArrayList<JSONObject> jsonObjects = arrayJson(jsonObject);
                listener.onSuccess(jsonObjects);
            }
        });
    }

    //update function to update data from the database
    public void update(final String query, final OnDatabaseSuccess.bool listener) {
        String url = baseUrl + inserting;
        connect(Request.Method.POST, url, query, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                boolean state = objectJson(jsonObject);
                listener.onSuccess(state);
            }
        });
    }

    //delete data from the database
    public void delete(final String query, final OnDatabaseSuccess.bool listener) {
        String url = baseUrl + inserting;
        connect(Request.Method.POST, url, query, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                boolean state = objectJson(jsonObject);
                listener.onSuccess(state);
            }
        });
    }

    //after i receive an jsonobject from the database i send the object here to handle different types of queries
    //object json for the : insert,update and delete
    public boolean objectJson(JSONObject object) {
        boolean state;
        try {
            state = object.getBoolean(serverResponse);


        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        return state;

    }

    //arrayjson for the : select
    public ArrayList<JSONObject> arrayJson(JSONObject object) {
        ArrayList<JSONObject> arrayList = new ArrayList<>();

        try {
            JSONArray array = object.getJSONArray(serverResponse);
            int i = 0;
            while (!array.isNull(i)) {
                JSONObject jsonObject = array.getJSONObject(i);
                arrayList.add(jsonObject);
                i++;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return arrayList;

    }
}
