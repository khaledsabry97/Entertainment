package com.example.khaledsabry.entertainment.Database;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.khaledsabry.entertainment.Activities.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by KhALeD SaBrY on 30-Jul-18.
 */

public class Insertion {



    public void user(HashMap<String,String> list)
    {
       String username = list.get(DatabaseTables.user.username);
        String email = list.get(DatabaseTables.user.email);
        String password = list.get(DatabaseTables.user.password);
        String age = list.get(DatabaseTables.user.age);
        String phone = list.get(DatabaseTables.user.phone);


        final String query = "insert into "+ DatabaseTables.user.className+"("+ DatabaseTables.user.username +","+ DatabaseTables.user.email+","+ DatabaseTables.user.password
                +","+ DatabaseTables.user.age+","+ DatabaseTables.user.phone+") values('"+username+"','"+email+"','"+password+"',"+age+","+phone+")";



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                JsonObjectRequest.Method.POST, DatabaseTables.file.getInsertion(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
response.toString();            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("Error", error.toString());
            }
        })
        {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
               params.put("Cookie", "__test=31680452f9f0bce024fab29d26a45ec7; expires=Friday, January 1, 2038 at 1:55:55 AM; path=/");
                params.put("query", query);

                return params;
            }
        };


        Volley.newRequestQueue(MainActivity.getActivity().getApplicationContext()).add(jsonObjectRequest);
/*
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(DatabaseTables.file.getInsertion());
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream os = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));

                    String data = URLEncoder.encode("query","UTF-8")+"="+URLEncoder.encode(query,"UTF-8");
                    InputStream inputStream = httpURLConnection.getInputStream();
bufferedWriter.write(data);
bufferedWriter.flush();
//bufferedWriter.close();
//inputStream.close();
//os.close();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        */
    }
}
