package com.example.khaledsabry.entertainment.Database;

import com.example.khaledsabry.entertainment.Database.Origin.DatabaseController;
import com.example.khaledsabry.entertainment.Database.Origin.DatabaseTables;
import com.example.khaledsabry.entertainment.Interfaces.OnDatabaseSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnSuccess;

/**
 * Created by KhALeD SaBrY on 31-Jul-18.
 */

public class DeleteController extends DatabaseController {

    private final String equal = "=";
    private final String less = "<";
    private final String bigger = ">";
    private final String lessOrEqual = "<=";
    private final String biggerOrEqual = ">=";
    private String and = " and ";
    private String or = " or ";

    private String condition = "";

    public void categoryRemoveDuplicates(int userId, String tmdbId, String categoryName, OnDatabaseSuccess.bool listener)
    {
/*
        tmdbId = addqoutes(tmdbId);
        categoryName = addqoutes(categoryName);
        condition += DatabaseTables.category.userId +equal + userId;
        condition += and;
        condition += DatabaseTables.category.tmdbId +equal +tmdbId;
        condition +=and;
        condition += DatabaseTables.category.categoryName + equal +categoryName;

server.delete(createDeleteQuery(DatabaseTables.category.tableName,condition),listener);



*/

    }



}
