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


    public void userSignIn(String username, String email, String password, String age, String phone, OnDatabaseSuccess.bool listener) {

        HashMap<String, String> insert = new HashMap<>();
        insert.put(DatabaseTables.user.username, username);
        insert.put(DatabaseTables.user.email, email);
        insert.put(DatabaseTables.user.age, age);
        insert.put(DatabaseTables.user.phone, phone);
        insert.put(DatabaseTables.user.password, password);
        addqoutes(insert);
        String query = createInsertQuery(DatabaseTables.user.tableName, insert);
        server.insert(query, listener);
    }
}
