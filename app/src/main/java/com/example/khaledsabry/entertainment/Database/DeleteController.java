package com.example.khaledsabry.entertainment.Database;

import com.example.khaledsabry.entertainment.Database.Origin.DatabaseController;
import com.example.khaledsabry.entertainment.Interfaces.OnDatabaseSuccess;

/**
 * Created by KhALeD SaBrY on 31-Jul-18.
 */

public class DeleteController extends DatabaseController {

    private String table ="";
    private String condition = "";

    public void CategoryItemRemove(int categoryId, String tmdbId,int type, OnDatabaseSuccess.bool listener) {

        tmdbId = addqoutes(tmdbId);

        condition += categoryItemTable.categoryId + equal + categoryId;
        condition += and;
        condition += categoryItemTable.tmdbId + equal + tmdbId;
        condition +=and;
        condition += categoryItemTable.type +equal + type;

        server.delete(createDeleteQuery(categoryItemTable.tableName, condition), listener);


    }

    public void imageRemoveByNameAndUserId(String path)
    {
    }

public void categoryDelete(int categoryId,OnDatabaseSuccess.bool listener)
{

    table = categoryTable.tableName;
    condition += categoryTable.id + equal + categoryId;
    String query = createDeleteQuery(table,condition);
    server.delete(query,listener);
}

    public void categoryItemDelete(int categoryItemId,OnDatabaseSuccess.bool listener)
    {

        table = categoryItemTable.tableName;
        condition += categoryItemTable.id + equal + categoryItemId;
        String query = createDeleteQuery(table,condition);
        server.delete(query,listener);
    }



}
