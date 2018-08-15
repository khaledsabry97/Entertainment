package com.example.khaledsabry.entertainment.Database;

import com.android.volley.toolbox.StringRequest;
import com.example.khaledsabry.entertainment.Database.Origin.DatabaseController;
import com.example.khaledsabry.entertainment.Interfaces.OnDatabaseSuccess;

import java.util.HashMap;

/**
 * Created by KhALeD SaBrY on 31-Jul-18.
 */

public class UpdateController extends DatabaseController{
    HashMap<String, String> set;
    String condition = "";
    String table;



    public void categoryUpdateName(String name, Integer id, OnDatabaseSuccess.bool listener)
    {
        table = tableCategory.tableName;

        set.put(tableCategory.name,name);


        condition += tableCategory.id + and + id;



        String query = createUpdateQuery(table,set,condition);
        server.update(query,listener);
    }





}
