package com.example.khaledsabry.entertainment.Interfaces;

import android.support.v4.util.ObjectsCompat;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 27-Jun-18.
 */

public interface OnSuccess {

    interface Object
    {
        void onSuccess(Object state);
        void onSuccess(Integer num);
    }
    interface Json {
        void onSuccess(JSONObject jsonObject);
    }
     interface string
    {
        void onSuccess(String word);
    }

    interface bool
    {
        void onSuccess(boolean state);
    }

    interface arrayMap
    {
        void onSuccess(String key, ArrayList<Object> values);
    }
}
