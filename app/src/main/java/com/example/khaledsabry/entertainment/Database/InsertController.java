package com.example.khaledsabry.entertainment.Database;

import com.example.khaledsabry.entertainment.Database.Origin.DatabaseController;
import com.example.khaledsabry.entertainment.Database.Origin.DatabaseTables;
import com.example.khaledsabry.entertainment.Interfaces.OnDatabaseSuccess;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by KhALeD SaBrY on 30-Jul-18.
 */

//i used a typical pattern in designing the function
//first : put the variables you get in a Hashmap so that every variable have a specific attribute from the database
//second : use the addqoutes function to add the qoutes to the variables
//third : get a query from a function called createinsertquery by passing the name of the table you want to insert and the data you want to insert it
//finally : use server variable and choose the insert function and send the query and the listener
public class InsertController extends DatabaseController {

    HashMap<String, String> insert;

    public InsertController() {

        insert = new HashMap<>();
    }

    public void userSignIn(String username, String email, String password, String age, String phone, OnDatabaseSuccess.bool listener) {

        insert.put(tableUser.username, username);
        insert.put(tableUser.email, email);
        insert.put(tableUser.age, age);
        insert.put(tableUser.phone, phone);
        insert.put(tableUser.password, password);

        String query = createInsertQuery(tableUser.tableName, insert);
        server.insert(query, listener);
    }

    public void listAdd(int categoryId, String tmdbId, String imdbId, int type, OnDatabaseSuccess.bool listener) {
    /*deleteController().categoryRemoveList(categoryId, tmdbId, imdbId, new OnDatabaseSuccess.bool() {
        @Override
        public void onSuccess(boolean state) {

        }
    });

    */

        insert.put(listTable.categoryId, String.valueOf(categoryId));
        insert.put(listTable.imdbId, imdbId);
        insert.put(listTable.tmdbId, tmdbId);
        insert.put(listTable.type, String.valueOf(type));


        String query = createInsertQuery(listTable.tableName, insert);
        server.insert(query, listener);

    }

    //add to the database the favourit/history movies.tvs or artists
    public void listAdd(String categoryName, int categoryType, int userid, String tmdbId, String imdbId, int contentType, String description, OnDatabaseSuccess.bool listener) {
/*
        DeleteController deleteController = new DeleteController();
        if (categoryType == constants.Favourite) {
            insert.put(DatabaseTables.category.categoryName, "Favourite");
            deleteController.categoryRemoveList(userid, tmdbId, "Favourite", new OnDatabaseSuccess.bool() {
                @Override
                public void onSuccess(boolean state) {

                }
            });
        } else if (categoryType == constants.history) {
            insert.put(DatabaseTables.category.categoryName, "History");
            deleteController.categoryRemoveList(userid, tmdbId, "History", new OnDatabaseSuccess.bool() {
                @Override
                public void onSuccess(boolean state) {

                }
            });
        } else
        {


            String query = createInsertQuery(DatabaseTables.category.tableName, insert);
            server.insert(query, new OnDatabaseSuccess.bool() {
                @Override
                public void onSuccess(boolean state) {
                    if(state)
                    {


                    }
                }
            });

        }
            insert.put(DatabaseTables.category.categoryName, categoryName);
        insert.put(DatabaseTables.category.categoryType, String.valueOf(categoryType));
        insert.put(DatabaseTables.category.contentType, String.valueOf(contentType));
        insert.put(DatabaseTables.category.userId, String.valueOf(userid));
        insert.put(DatabaseTables.category.tmdbId, String.valueOf(tmdbId));
        insert.put(DatabaseTables.category.imdbId, String.valueOf(imdbId));
        insert.put(DatabaseTables.category.description, String.valueOf(description));

        String query = createInsertQuery(DatabaseTables.category.tableName, insert);
        server.insert(query, listener);
    }

    //add somebody to follow
    public void userAddfollowing(int id, int followerId, int type, OnDatabaseSuccess.bool listener) {
        insert.put(DatabaseTables.follower.userId, String.valueOf(id));
        insert.put(DatabaseTables.follower.followerId, String.valueOf(followerId));
        insert.put(DatabaseTables.follower.type, String.valueOf(type));

        String query = createInsertQuery(DatabaseTables.follower.tableName, insert);
        server.insert(query, listener);
*/

    }


    //categories will be added when sign up
    public void categorySignUpDefault(OnDatabaseSuccess.bool listener) {
        insert.put(tableCategory.name, "Favourite Movies");
        insert.put(tableCategory.userId, String.valueOf(UserData.getInstance().getUserId()));
        insert.put(tableCategory.type, String.valueOf(constants.movie));

        server.insert(createInsertQuery(tableCategory.tableName, insert), listener);

        insert.clear();
        insert.put(tableCategory.name, "Favourite Tv Series");
        insert.put(tableCategory.userId, String.valueOf(UserData.getInstance().getUserId()));
        insert.put(tableCategory.type, String.valueOf(constants.tv));

        server.insert(createInsertQuery(tableCategory.tableName, insert), listener);

        insert.clear();
        insert.put(tableCategory.name, "Favourite Artists");
        insert.put(tableCategory.userId, String.valueOf(UserData.getInstance().getUserId()));
        insert.put(tableCategory.type, String.valueOf(constants.artist));

        server.insert(createInsertQuery(tableCategory.tableName, insert), listener);


        insert.clear();
        insert.put(tableCategory.name, "History");
        insert.put(tableCategory.userId, String.valueOf(UserData.getInstance().getUserId()));
        insert.put(tableCategory.type, String.valueOf(constants.other));

        server.insert(createInsertQuery(tableCategory.tableName, insert), listener);
    }

    //add category
    public void categoryAdd(final String name, final int constantType, final OnDatabaseSuccess.bool listener) {
        selectController().categoryCheckIfFound(name, new OnDatabaseSuccess.array() {
            @Override
            public void onSuccess(ArrayList<JSONObject> jsonObjects) {
                if (jsonObjects != null) {

                    if (jsonObjects.size() == 0) {
                        listener.onSuccess(false);
                        return;
                    }
                    insert.put(tableCategory.name, name);
                    insert.put(tableCategory.userId, String.valueOf(UserData.getInstance().getUserId()));
                    insert.put(tableCategory.type, String.valueOf(constantType));

                    server.insert(createInsertQuery(tableCategory.tableName, insert), listener);

                }}
            });

        }



    }
