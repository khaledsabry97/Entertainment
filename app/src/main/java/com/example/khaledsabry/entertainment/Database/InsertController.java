package com.example.khaledsabry.entertainment.Database;

import com.example.khaledsabry.entertainment.Database.Origin.DatabaseController;
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

        insert.put(userTable.username, username);
        insert.put(userTable.email, email);
        insert.put(userTable.age, age);
        insert.put(userTable.phone, phone);
        insert.put(userTable.password, password);

        String query = createInsertQuery(userTable.tableName, insert);
        server.insert(query, listener);
    }

    public void itemAdd(int categoryId, String tmdbId, String imdbId, int type, OnDatabaseSuccess.bool listener) {

        insert.put(categoryItemTable.categoryId, String.valueOf(categoryId));
        insert.put(categoryItemTable.imdbId, imdbId);
        insert.put(categoryItemTable.tmdbId, tmdbId);
        insert.put(categoryItemTable.type, String.valueOf(type));


        String query = createInsertQuery(categoryItemTable.tableName, insert);
        server.insert(query, listener);

    }

    //add to the database the favourit/history movies.tvs or artists
    public void itemAdd(String categoryName, int categoryType, int userid, String tmdbId, String imdbId, int contentType, String description, OnDatabaseSuccess.bool listener) {
/*
        DeleteController deleteController = new DeleteController();
        if (categoryType == constants.Favourite) {
            insert.put(DatabaseTables.category.categoryName, "Favourite");
            deleteController.CategoryItemRemove(userid, tmdbId, "Favourite", new OnDatabaseSuccess.bool() {
                @Override
                public void onSuccess(boolean state) {

                }
            });
        } else if (categoryType == constants.history) {
            insert.put(DatabaseTables.category.categoryName, "History");
            deleteController.CategoryItemRemove(userid, tmdbId, "History", new OnDatabaseSuccess.bool() {
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
        insert.put(categoryTable.name, "Favourite Movies");
        insert.put(categoryTable.userId, String.valueOf(UserData.getInstance().getUserId()));
        insert.put(categoryTable.type, String.valueOf(constants.movie));

        server.insert(createInsertQuery(categoryTable.tableName, insert), listener);

        insert.clear();
        insert.put(categoryTable.name, "Favourite Tv Series");
        insert.put(categoryTable.userId, String.valueOf(UserData.getInstance().getUserId()));
        insert.put(categoryTable.type, String.valueOf(constants.tv));

        server.insert(createInsertQuery(categoryTable.tableName, insert), listener);

        insert.clear();
        insert.put(categoryTable.name, "Favourite Artists");
        insert.put(categoryTable.userId, String.valueOf(UserData.getInstance().getUserId()));
        insert.put(categoryTable.type, String.valueOf(constants.artist));

        server.insert(createInsertQuery(categoryTable.tableName, insert), listener);


        insert.clear();
        insert.put(categoryTable.name, "History");
        insert.put(categoryTable.userId, String.valueOf(UserData.getInstance().getUserId()));
        insert.put(categoryTable.type, String.valueOf(constants.other));

        server.insert(createInsertQuery(categoryTable.tableName, insert), listener);
    }

    //add category
    public void categoryAdd(final String name, final int constantType, final OnDatabaseSuccess.bool listener) {
        selectController().categoryCheckIfFound(name, new OnDatabaseSuccess.array() {
            @Override
            public void onSuccess(ArrayList<JSONObject> jsonObjects) {
                if (jsonObjects != null) {

                    if (jsonObjects.size() != 0) {
                        listener.onSuccess(false);
                        return;
                    }
                    insert.put(categoryTable.name, name);
                    insert.put(categoryTable.userId, String.valueOf(UserData.getInstance().getUserId()));
                    insert.put(categoryTable.type, String.valueOf(constantType));

                    server.insert(createInsertQuery(categoryTable.tableName, insert), listener);

                }}
            });

        }

    //[Special type of insert]
    //to update the database with image
    public void userUploadImage(int userId, String imageName, String image64, OnDatabaseSuccess.bool listener) {

        server.uploadImage(userId, addqoutes(imageName), addqoutes(image64), listener);

    }

    }
