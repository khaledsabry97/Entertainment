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
        public final  int history = 1;
        public final  int Favourite = 2;
        public final  int other = 3;


        //for category table "content type"
        public final  int movie = 1;
        public final  int tv = 2;
        public final  int artist = 3;

        //for friend table  "type"
        public final  int friend = 1;
        public final  int movieLover = 2;
        public final  int contentMaker = 3;
        public final  int brother = 4;
        public final  int sister = 5;


    }
  public static class user
    {
        public final  String tableName = "`user`";
        public final  String username = "`user_name`";
        public final  String email = "`email`";
        public final  String password ="`password`";
        public final  String age = "`age`";
        public final  String phone = "`phone`";
        public final  String id = "id";
        public final  String profileImage = "`profile_image`";
        public final  String backdropImage = "`backdrop_image`";
    }

    public static class category
    {
        public final  String tableName = "`category`";
        public final  String id = "`id`";
        public final  String userId = "`user_id`";
        public final  String name = "`name`";
        public final  String description = "`description`";
        public final  String type = "`type`";
        public final  String createdDate = "`created_date`";





    }

    public static class categoryItem
    {
        public final  String tableName = "`category_item`";
        public final  String id = "`id`";
        public final  String categoryId = "`category_id`";
        public final  String tmdbId = "`tmdb_id`";
        public final  String imdbId = "`imdb_id`";
        public final  String type = "`type`";

        public final  String addedDate = "`added_date`";
    }

    public static class image
    {
        public final  String tableName = "`image`";
        public final  String id = "`id`";
        public final  String userId = "`user_id`";
        public final  String name = "`name`";
        public final  String path = "`path`";
        public final  String createdDate = "`created_date`";

    }
    public class follower
    {
        public final static String tableName = "`follower`";
        public final static String userId = "`userId`";
        public final static String followerId = "`followerId`";
        public final static String type ="`type`";

    }

}
