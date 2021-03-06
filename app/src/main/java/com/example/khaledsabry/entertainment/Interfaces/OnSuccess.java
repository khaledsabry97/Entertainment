package com.example.khaledsabry.entertainment.Interfaces;


import org.json.JSONObject;

import java.util.ArrayList;
import java.lang.Object;

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

    interface objects
    {
        void onSuccess(ArrayList<java.lang.Object> objects);
    }
}
