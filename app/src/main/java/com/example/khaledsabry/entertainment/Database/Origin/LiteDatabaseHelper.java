package com.example.khaledsabry.entertainment.Database.Origin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.khaledsabry.entertainment.Activities.MainActivity;

import java.security.PublicKey;


public class LiteDatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "Movita.db";
    public static final int databaseVersion = 5;

    @Override
    public void onCreate(SQLiteDatabase db) {

        //pass create the tables
        db.execSQL(LiteDatabaseTables.createDataBaseSql);

    }

    public LiteDatabaseHelper()
    {
        super(MainActivity.getActivity(),databaseName,null, databaseVersion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //pass drop the tables
        db.execSQL(LiteDatabaseTables.deleteDataBaseSql);
        onCreate(db);
    }

    
}
