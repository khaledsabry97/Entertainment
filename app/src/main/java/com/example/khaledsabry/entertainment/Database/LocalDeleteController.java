package com.example.khaledsabry.entertainment.Database;

import android.database.Cursor;

import com.example.khaledsabry.entertainment.Database.Origin.DatabaseController;
import com.example.khaledsabry.entertainment.Database.Origin.LiteDatabaseTables.Category;

public class LocalDeleteController extends DatabaseController {
    private String table = "";
    private String condition = "";


    public void categoryDeleteHistory(String tmdbId) {
        table = Category.tableName;
        condition += Category.categoryName + " LIKE " + addqoutes("History");
        condition += and;
        condition += Category.tmdbId + " LIKE " + addqoutes(tmdbId) + ";";


      String query = createDeleteQuery(table, condition);
      query = "delete from "+Category.tableName + " where "+Category.categoryName + " LIKE " + addqoutes("History");
        getReadableDatabase().execSQL(query);

//      Cursor t =   getWritableDatabase().rawQuery(query,null);
    //  t.toString();
    //    Cursor q2 = getReadableDatabase().rawQuery("select * from "+Category.tableName +"where "+condition ,null);
      //  toJson(q);
    }
}
