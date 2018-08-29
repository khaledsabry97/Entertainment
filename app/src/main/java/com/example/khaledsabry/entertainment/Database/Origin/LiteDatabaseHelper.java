package com.example.khaledsabry.entertainment.Database.Origin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.PublicKey;


public class LiteDatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "Movita.db";
    public static final int databaseVersion = 1;

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL();

    }

    public LiteDatabaseHelper(Context context)
    {
        super(context,databaseName,null, databaseVersion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
