package com.example.khaledsabry.entertainment.Database;

import com.example.khaledsabry.entertainment.Database.Origin.DatabaseController;
import com.example.khaledsabry.entertainment.Interfaces.OnDatabaseSuccess;

import org.json.JSONObject;

import java.util.ArrayList;
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

    public void categoryUpdateName(final String name, final Integer id, final OnDatabaseSuccess.bool listener) {
        selectController().categoryCheckIfFound(name, new OnDatabaseSuccess.array() {
            @Override
            public void onSuccess(ArrayList<JSONObject> jsonObjects) {
                if (jsonObjects != null) {

                    if (jsonObjects.size() != 0) {
                        listener.onSuccess(false);
                        return;
                    }
                    table = categoryTable.tableName;

                    set.put(categoryTable.name, name);


                    condition += categoryTable.id + equal + id;


                    String query = createUpdateQuery(table, set, condition);
                    server.update(query, listener);

                }}
        });

    }




}
