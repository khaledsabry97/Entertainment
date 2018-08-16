package com.example.khaledsabry.entertainment.Controllers;

import android.widget.Toast;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Database.Origin.DatabaseController;
import com.example.khaledsabry.entertainment.Database.Origin.DatabaseTables;
import com.example.khaledsabry.entertainment.Database.UserData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 27-Jun-18.
 */

public class Controller {
    //variable to the database controller to use it latter in the childeren of the parent controller class
    protected DatabaseController databaseController = new DatabaseController();
    public DatabaseTables.constants constants = new DatabaseTables.constants();
    protected DatabaseTables.user userTable = new DatabaseTables.user();
    protected DatabaseTables.category categoryTable = new DatabaseTables.category();
    protected DatabaseTables.categoryItem categoryItemTable = new DatabaseTables.categoryItem();
    protected DatabaseTables.image imageTable = new DatabaseTables.image();

    protected UserData userData = UserData.getInstance();
    //to show a msg on screen
    public void toast(String msg) {
        Toast.makeText(MainActivity.getActivity().getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    //to convert an array of json objects to an array of values that has the same name in the database
    protected ArrayList<Object> getArray(String key, ArrayList<JSONObject> jsonObjects) {
        ArrayList<Object> arrayList = new ArrayList<>();
       key = key.replace("`","");
        try {
            for (int i = 0; i < jsonObjects.size(); i++) {

                Object object = jsonObjects.get(i).get(key);
arrayList.add(object);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return arrayList;
    }



    protected Object getObject(String key,ArrayList<JSONObject> jsonObjects)
    {
        Object object = null;
        key = key.replace("`","");
        if(jsonObjects.size() == 0)
            return null;
        try {
            object = jsonObjects.get(0).get(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
String name =object.toString();
        return object;

    }




}
