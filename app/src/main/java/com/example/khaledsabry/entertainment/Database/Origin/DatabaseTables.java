package com.example.khaledsabry.entertainment.Database.Origin;

/**
 * Created by KhALeD SaBrY on 31-Jul-18.
 */

//this class is for organizing the structure of the database
    //every class inside the databasetables class is a table in the database
        //every String in the classes inside the databasetables is an attribute in the database
public class DatabaseTables {

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
}
