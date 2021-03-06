package com.example.khaledsabry.entertainment.Database.Origin;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.khaledsabry.entertainment.Database.DeleteController;
import com.example.khaledsabry.entertainment.Database.InsertController;
import com.example.khaledsabry.entertainment.Database.LocalDeleteController;
import com.example.khaledsabry.entertainment.Database.LocalInsertController;
import com.example.khaledsabry.entertainment.Database.SelectController;
import com.example.khaledsabry.entertainment.Database.UpdateController;

import org.json.JSONException;
import org.json.JSONObject;

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
    protected String and = " AND ";
    protected String or = " or ";

    //variable to the database controller to use it latter in the childeren of the parent controller class
    protected DatabaseTables.constants constants = new DatabaseTables.constants();
    protected DatabaseTables.user userTable = new DatabaseTables.user();
    protected DatabaseTables.category categoryTable = new DatabaseTables.category();
    protected DatabaseTables.categoryItem categoryItemTable = new DatabaseTables.categoryItem();
    protected DatabaseTables.image imageTable = new DatabaseTables.image();

   private LiteDatabaseHelper databaseHelper = new LiteDatabaseHelper();
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

    //get insert controller for local database
    public LocalInsertController localInsertController()
    {
        return new LocalInsertController();
    }

    //get delete controller for local database
    public LocalDeleteController localDeleteController()
    {
        return new LocalDeleteController();
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
        query = "DELETE FROM " + table;
        query += " WHERE " + condition;

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
            query += values.get(i);

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



    protected SQLiteDatabase getWritableDatabase()
    {
        databaseHelper = new LiteDatabaseHelper();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        return db;
    }

    protected SQLiteDatabase getReadableDatabase()
    {
        databaseHelper = new LiteDatabaseHelper();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        return db;
    }

    protected ArrayList<JSONObject> toJson(Cursor cursor)
    {
        ArrayList<JSONObject> jsonObjects = new ArrayList<>();
        if(cursor == null)
            return jsonObjects;
        int columnNo = cursor.getColumnCount();
        cursor.moveToFirst();
        cursor.moveToPosition(-1);
        while(cursor.moveToNext())
        {
            JSONObject jsonObject = new JSONObject();
            try {
           for(int i = 0; i < columnNo ;i++)
           {
if(cursor.getColumnName(i) != "")
{
    if(cursor.getString(i) != null)
        jsonObject.put(cursor.getColumnName(i),cursor.getString(i));
    else
        jsonObject.put(cursor.getColumnName(i),"");


}


           }
            } catch (JSONException e) {
                e.printStackTrace();
            }
jsonObjects.add(jsonObject);


        }



        return jsonObjects;
    }
}
