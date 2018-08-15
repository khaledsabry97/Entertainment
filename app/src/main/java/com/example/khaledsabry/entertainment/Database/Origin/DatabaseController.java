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
    protected final String equal = "=";
    protected final String less = "<";
    protected final String bigger = ">";
    protected final String lessOrEqual = "<=";
    protected final String biggerOrEqual = ">=";
    protected String and = " and ";
    protected String or = " or ";

    protected DatabaseTables.constants constants = new DatabaseTables.constants();
    protected DatabaseTables.user tableUser = new DatabaseTables.user();
    protected DatabaseTables.category tableCategory = new DatabaseTables.category();
    protected DatabaseTables.list listTable = new DatabaseTables.list();
    protected DatabaseTables databaseTables = new DatabaseTables();
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
        addqoutes(map);

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
        if (selects.size() == 0)
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

        if (!condition.equals(""))
            query += "where " + condition;

        return query;

    }


    protected String createDeleteQuery(String table, String condition) {
        String query;
        query = "Delete From " + table;
        query += "where " + condition;

        return query;

    }


    //this is to create an insert query
    //important: in this project i used this in all the queries
    protected String createUpdateQuery(String tableName, HashMap<String, String> map,String condition) {
        addqoutes(map);

        String query = "update " + tableName;
        query += " set ";

        ArrayList<String> keys = new ArrayList<String>(map.keySet());
        ArrayList<String> values = new ArrayList<String>(map.values());

        for (int i = 0; i < keys.size(); i++) {
            query += keys.get(i);
            query += "= ";
            String qoutes =addqoutes(values.get(i));
            query += qoutes;

            if (i == keys.size() - 1)
                break;
            else
                query += ",";

        }

        if (!condition.equals(""))
            query += " where " + condition;


        return query;
    }


    protected DatabaseTables databaseTables() {
        return new DatabaseTables();
    }

}
