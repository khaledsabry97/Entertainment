package com.example.khaledsabry.entertainment.Database;

import com.example.khaledsabry.entertainment.Database.Origin.DatabaseController;
import com.example.khaledsabry.entertainment.Interfaces.OnDatabaseSuccess;

import java.util.HashMap;

/**
 * Created by KhALeD SaBrY on 31-Jul-18.
 */

public class UpdateController extends DatabaseController {
    HashMap<String, String> set;
    String condition = "";
    String table;

    public UpdateController() {
        this.set = new HashMap<>();
    }

    public void categoryUpdateName(String name, Integer id, OnDatabaseSuccess.bool listener) {
        table = categoryTable.tableName;

        set.put(categoryTable.name, name);


        condition += categoryTable.id + and + id;


        String query = createUpdateQuery(table, set, condition);
        server.update(query, listener);
    }




}
