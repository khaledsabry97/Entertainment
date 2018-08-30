package com.example.khaledsabry.entertainment.Interfaces;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by KhALeD SaBrY on 01-Aug-18.
 */

public interface OnDatabaseSuccess {


    interface array {
        void onSuccess(ArrayList<JSONObject> jsonObjects);

    }

    interface bool {
        void onSuccess(boolean state);

    }
    interface number {
        void onSuccess(int state);

    }
}
