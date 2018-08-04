package com.example.khaledsabry.entertainment.Database.Origin;

/**
 * Created by KhALeD SaBrY on 31-Jul-18.
 */

//this class is for organizing the structure of the database
    //every class inside the databasetables class is a table in the database
        //every String in the classes inside the databasetables is an attribute in the database
public class DatabaseTables {

    //this is not a table but it's a constants that is used to easy the database
    public static class constants
    {
        //for category table "category type"
        public final  int favourite = 1;
        public final  int history = 2;
        public final  int other = 3;


        //for category table "content type"
        public final  int movie = 1;
        public final  int tv = 2;
        public final  int artist = 2;

        //for friend table  "type"
        public final  int friend = 1;
        public final  int movieLover = 2;
        public final  int contentMaker = 3;
        public final  int brother = 4;
        public final  int sister = 5;


    }
  public class user
    {
        public final static String tableName = "`user`";
        public final static String username = "`username`";
        public final static String email = "`email`";
        public final static String password ="`password`";
        public final static String age = "`age`";
        public final static String phone = "`phone`";
        public final static String id = "id";
    }

    public class category
    {
        public final static String tableName = "`category`";
        public final static String userId = "`userId`";
        public final static String contentType = "`contentType`";
        public final static String categoryType ="`categoryType`";
        public final static String categoryName ="`categoryName`";

        public final static String tmdbId = "`tmdbId`";
        public final static String imdbId = "`imdbId`";
        public final static String description = "`description`";
    }

    public class follower
    {
        public final static String tableName = "`follower`";
        public final static String userId = "`userId`";
        public final static String followerId = "`followerId`";
        public final static String type ="`type`";

    }

}
