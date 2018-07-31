package com.example.khaledsabry.entertainment.Database;

/**
 * Created by KhALeD SaBrY on 31-Jul-18.
 */

public class DatabaseTables {

   public static class file
   {
       private final static String baseUrl = "https://water-gas-oil.000webhostapp.com/";
        private final static String insertion = "insertion.php";

        public static String getSelection() {
            return baseUrl+selection;
        }

        private final static String selection = "selection.php";

        public static String getInsertion() {
            return baseUrl +insertion;
        }
    }
  public   class user
    {
        public final static String className = "user";
        public final static String username = "username";
        public final static String email = "email";
        public final static String password ="password";
        public final static String age = "age";
        public final static String phone = "phone";
    }
}
