package com.example.khaledsabry.entertainment.Interfaces;

import org.json.JSONObject;

/**
 * Created by KhALeD SaBrY on 27-Jun-18.
 */

public interface OnSuccess {

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
}
