package com.example.khaledsabry.entertainment.Database.Origin;

public class LiteDatabaseTables {
    //quoutes that is used to let the query function correctly
    static String quote = "\"";
    public static final String createDataBaseSql = "CREATE TABLE "+Category.tableName+ " (\n" +
            Category.id +"  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
           Category.categoryName+" TEXT NOT NULL,\n" +
            Category.tmdbId+" INTEGER NOT NULL,\n" +
            Category.imdbId+" INTEGER,\n" +
            Category.type+" integer, \n" +
            " CONSTRAINT f UNIQUE ("+Category.categoryName+" ASC, "+Category.tmdbId+" ASC) ON CONFLICT REPLACE \n"+
            ");\n";

    public static final String deleteDataBaseSql = "Drop Table " + Category.tableName + " ; \n";

    public static class Category {
        public static final String tableName = "`category`";
        public static final String id = "`id`";
        public static final String categoryName = "`category_name`";
        public static final String tmdbId = "`tmdb_id`";
        public static final String imdbId = "`imdb_id`";
        public static final String type = "`type`";

        public static final int constantMovie = 1;
        public static final int constantTv = 2;
        public static final int constantArtist = 3;
    }
}
