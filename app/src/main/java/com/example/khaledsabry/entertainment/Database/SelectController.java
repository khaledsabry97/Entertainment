package com.example.khaledsabry.entertainment.Database;

import com.example.khaledsabry.entertainment.Database.Origin.DatabaseController;
import com.example.khaledsabry.entertainment.Interfaces.OnDatabaseSuccess;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by KhALeD SaBrY on 31-Jul-18.
 */


//i used a typical pattern in designing the function
//first : add qoutes to the String vaiables
//second : selects array you put there the attributes from the tables you want to get using the databasetabels
//third : tables it's a hashmap that is used to put in the tables you want the attributes from and the value you put a sign for the table
//fourth : condition is a way to make the condition you want
//fifth : call create select query from the database controller and then send it to the server

public class SelectController extends DatabaseController {



    private ArrayList<String> selects;
    private HashMap<String, String> tables;
    private String condition = "";

    public SelectController() {
        selects = new ArrayList<>();
        tables = new HashMap<String, String>();
    }

    public void userSignIn(String username, String password, OnDatabaseSuccess.array listener) {

       username = addqoutes(username);
     password =   addqoutes(password);


        tables.put(userTable.tableName, "s");

        condition += userTable.username + equal + username;
        condition += and;
        condition += userTable.password + equal + password;

        String query = createSelectQuery(selects, tables, condition);
        server.select(query, listener);


    }


    public void userCheckUsername(String username,OnDatabaseSuccess.array listener)
    {
        username = addqoutes(username);

        selects.add(userTable.username);

        tables.put(userTable.tableName,null);

        condition += userTable.username + equal + username;
        String query = createSelectQuery(selects,tables,condition);
        server.select(query,listener);

    }

    public void userCheckEmail(String email, OnDatabaseSuccess.array listener)
    {
        email = addqoutes(email);

        selects.add(userTable.username);

        tables.put(userTable.tableName,null);

        condition += userTable.email + equal + email;
        String query = createSelectQuery(selects,tables,condition);
        server.select(query,listener);

    }


    public void userSearchForusers(String usernameOrEmail,OnDatabaseSuccess.array listener)
    {
        usernameOrEmail = addqoutes(usernameOrEmail);

        selects.add(userTable.username);
        selects.add(userTable.email);
        selects.add(userTable.id);

        tables.put(userTable.tableName,null);


        condition += userTable.username + equal + usernameOrEmail;
        condition += or;
        condition += userTable.email +equal +usernameOrEmail;

        String query = createSelectQuery(selects,tables,condition);
        server.select(query,listener);
    }



    public void categoryGet(int userId,OnDatabaseSuccess.array listener)
    {

tables.put(categoryTable.tableName,null);
condition += userId +equal + categoryTable.userId;

server.select(createSelectQuery(selects,tables,condition),listener);

    }


    public void CategoryItemGetByTmdb(int userId, int tmdbId, OnDatabaseSuccess.array listener)
    {

        selects.add(categoryItemTable.categoryId);


        tables.put(categoryItemTable.tableName,null);
        tables.put(categoryTable.tableName,null);

        condition += userId +equal + categoryTable.userId;
        condition += and;
        condition += tmdbId +equal + categoryItemTable.tmdbId;

        server.select(createSelectQuery(selects,tables,condition),listener);

    }


    public void CategoryItemGetByCategory(int categoryId, OnDatabaseSuccess.array listener)
    {

        tables.put(categoryItemTable.tableName,null);

        condition += categoryId +equal + categoryItemTable.categoryId;

        server.select(createSelectQuery(selects,tables,condition),listener);

    }

    public void categoryCheckIfFound(String categoryName,OnDatabaseSuccess.array listener)
    {
        categoryName = addqoutes(categoryName);
        selects.add(categoryTable.name);

        tables.put(categoryTable.tableName,null);

        condition += UserData.getInstance().getUserId() + equal + categoryTable.userId;
        condition += and;
        condition += categoryName + equal + categoryTable.name ;
        server.select(createSelectQuery(selects,tables,condition),listener);
    }


    public void imageGetImage(int userId,String name, OnDatabaseSuccess.array listener)
    {
        name = addqoutes(name);


        tables.put(imageTable.tableName,null);

       condition += imageTable.userId + equal + userId;
       condition += and;
       condition += imageTable.name + equal + name;
        String query = createSelectQuery(selects,tables,condition);
        server.select(query,listener);

    }


    public void categoryGet(int userId,String name,OnDatabaseSuccess.array listener)
    {


        name = addqoutes(name);
        tables.put(categoryTable.tableName,null);
        condition += userId +equal + categoryTable.userId;
        condition += and;
        condition += categoryTable.name + equal + name;

        server.select(createSelectQuery(selects,tables,condition),listener);

    }
}
