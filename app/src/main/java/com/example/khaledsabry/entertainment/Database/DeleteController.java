package com.example.khaledsabry.entertainment.Database;

import com.example.khaledsabry.entertainment.Database.Origin.DatabaseController;
import com.example.khaledsabry.entertainment.Interfaces.OnDatabaseSuccess;

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

    public void CategoryItemRemove(int categoryId, String tmdbId, OnDatabaseSuccess.bool listener) {

        tmdbId = addqoutes(tmdbId);

        condition += categoryItemTable.categoryId + equal + categoryId;
        condition += and;
        condition += categoryItemTable.tmdbId + equal + tmdbId;

        server.delete(createDeleteQuery(categoryItemTable.tableName, condition), listener);


    }

    public void imageRemoveByNameAndUserId(String path)
    {
    }




}
