package com.example.khaledsabry.entertainment.Database;

import com.example.khaledsabry.entertainment.Database.Origin.DatabaseController;
import com.example.khaledsabry.entertainment.Database.Origin.DatabaseTables;
import com.example.khaledsabry.entertainment.Interfaces.OnDatabaseSuccess;

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

        insert.put(DatabaseTables.user.username, username);
        insert.put(DatabaseTables.user.email, email);
        insert.put(DatabaseTables.user.age, age);
        insert.put(DatabaseTables.user.phone, phone);
        insert.put(DatabaseTables.user.password, password);

        String query = createInsertQuery(DatabaseTables.user.tableName, insert);
        server.insert(query, listener);
    }


    //add to the database the favourit/history movies.tvs or artists
    public void categoryAdd(int categoryType, int userid, String tmdbId, String imdbId, int contentType, String description, OnDatabaseSuccess.bool listener) {
        if(categoryType == DatabaseTables.constants.favourite)
            insert.put(DatabaseTables.category.categoryName,"Favourite");
      else  if(categoryType == DatabaseTables.constants.history)
            insert.put(DatabaseTables.category.categoryName,"History");
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


    }
}
