package com.example.khaledsabry.entertainment.Database;

import com.example.khaledsabry.entertainment.Database.Origin.DatabaseController;
import com.example.khaledsabry.entertainment.Database.Origin.DatabaseTables;
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
    private final String equal = "=";
    private final String less = "<";
    private final String bigger = ">";
    private final String lessOrEqual = "<=";
    private final String biggerOrEqual = ">=";
    private String and = " and ";
    private String or = " or ";


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


        tables.put(DatabaseTables.user.tableName, "s");

        condition += DatabaseTables.user.username + equal + username;
        condition += and;
        condition += DatabaseTables.user.password + equal + password;

        String query = createSelectQuery(selects, tables, condition);
        server.select(query, listener);


    }


    public void userCheckUsername(String username,OnDatabaseSuccess.array listener)
    {
        username = addqoutes(username);

        selects.add(DatabaseTables.user.username);

        tables.put(DatabaseTables.user.tableName,null);

        condition += DatabaseTables.user.username + equal + username;
        String query = createSelectQuery(selects,tables,condition);
        server.select(query,listener);

    }

    public void userCheckEmail(String email, OnDatabaseSuccess.array listener)
    {
        email = addqoutes(email);

        selects.add(DatabaseTables.user.username);

        tables.put(DatabaseTables.user.tableName,null);

        condition += DatabaseTables.user.email + equal + email;
        String query = createSelectQuery(selects,tables,condition);
        server.select(query,listener);

    }


    public void userSearchForusers(String usernameOrEmail,OnDatabaseSuccess.array listener)
    {
        usernameOrEmail = addqoutes(usernameOrEmail);

        selects.add(DatabaseTables.user.username);
        selects.add(DatabaseTables.user.email);
        selects.add(DatabaseTables.user.id);

        tables.put(DatabaseTables.user.tableName,null);


        condition += DatabaseTables.user.username + equal + usernameOrEmail;
        condition += or;
        condition += DatabaseTables.user.email +equal +usernameOrEmail;

        String query = createSelectQuery(selects,tables,condition);
        server.select(query,listener);
    }


}
