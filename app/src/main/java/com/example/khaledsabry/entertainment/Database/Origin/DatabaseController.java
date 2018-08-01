package com.example.khaledsabry.entertainment.Database.Origin;

import com.example.khaledsabry.entertainment.Controllers.Controller;
import com.example.khaledsabry.entertainment.Database.DeleteController;
import com.example.khaledsabry.entertainment.Database.InsertController;
import com.example.khaledsabry.entertainment.Database.SelectController;
import com.example.khaledsabry.entertainment.Database.UpdateController;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by KhALeD SaBrY on 31-Jul-18.
 */

public class DatabaseController {
    //quoutes that is used to let the query function correctly
    String quoute = "\"";
    //this is a refrence to the database server to access to the web
    protected DatabaseServer server = new DatabaseServer();

    //get a insert controller variable
    public InsertController insertController() {
        return new InsertController();
    }

    //get a select controller variable
    public SelectController selectController() {
        return new SelectController();
    }

    //get a delete controller variable
    public DeleteController deleteController() {
        return new DeleteController();
    }

    //get a update controller variable
    public UpdateController updateController() {
        return new UpdateController();
    }

    //add qoutes to a map
    protected void addqoutes(HashMap<String, String> map) {
        ArrayList<String> keys = new ArrayList<String>(map.keySet());
        ArrayList<String> values = new ArrayList<String>(map.values());
        map.clear();

        for (int i = 0; i < keys.size(); i++) {
            map.put(keys.get(i), quoute + values.get(i) + quoute);
        }


    }

    //add qoutes to a name/string
    protected String addqoutes(String name) {
        return quoute + name + quoute;
    }

    //this is to create an insert query
    //important: in this project i used this in all the queries
    protected String createInsertQuery(String tableName, HashMap<String, String> map) {
        String query = "insert into " + tableName + "(";
        ArrayList<String> keys = new ArrayList<String>(map.keySet());
        ArrayList<String> values = new ArrayList<String>(map.values());

        for (int i = 0; i < keys.size(); i++) {
            query += keys.get(i);
            if (i == keys.size() - 1)
                query += ")";
            else
                query += ",";

        }


        query += " values(";

        for (int i = 0; i < values.size(); i++) {
            query += values.get(i);
            if (i == values.size() - 1)
                query += ")";
            else
                query += ",";

        }

        return query;
    }


    //if the you don't want to add a sign to the table put null
    //if there is no condition then send null
    protected String createSelectQuery(ArrayList<String> selects, HashMap<String, String> tablenames, String condition) {
        String query;
        query = "select ";
        if (selects == null)
            query += " * ";
        else
            for (int i = 0; i < selects.size(); i++) {
                query += selects.get(i);
                if (i == selects.size() - 1)
                    query += " ";
                else
                    query += ",";
            }

        query += "from ";


        ArrayList<String> keys = new ArrayList<String>(tablenames.keySet());
        ArrayList<String> values = new ArrayList<String>(tablenames.values());

        for (int i = 0; i < tablenames.size(); i++) {
            query += keys.get(i);
            if (values.get(i) != null)
            query += values.get(i);
            if (i == tablenames.size() - 1)
                query += " ";
            else
                query += ",";
        }

        if (condition != null)
            query += "where " + condition;

       return query;

    }
}
