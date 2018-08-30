package com.example.khaledsabry.entertainment.Database;

import android.content.ContentValues;

import com.example.khaledsabry.entertainment.Database.Origin.DatabaseController;
import com.example.khaledsabry.entertainment.Database.Origin.LiteDatabaseTables;
import com.example.khaledsabry.entertainment.Database.Origin.LiteDatabaseTables.Category;
import com.example.khaledsabry.entertainment.Interfaces.OnDatabaseSuccess;

public class LocalInsertController extends DatabaseController {

    public void categoryAddHistory(String tmdbId, String imdbId, int type, OnDatabaseSuccess.number listener) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Category.categoryName, "History");
        contentValues.put(Category.tmdbId, tmdbId);
        contentValues.put(Category.imdbId, imdbId);
        contentValues.put(Category.type, type);

        long result = getWritableDatabase().insert(Category.tableName, null, contentValues);
        listener.onSuccess((int) result);


    }


}
