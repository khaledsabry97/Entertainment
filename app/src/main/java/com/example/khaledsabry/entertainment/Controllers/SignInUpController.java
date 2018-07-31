package com.example.khaledsabry.entertainment.Controllers;

import com.example.khaledsabry.entertainment.Database.DatabaseController;

import java.util.HashMap;

import com.example.khaledsabry.entertainment.Database.DatabaseTables.*;

/**
 * Created by KhALeD SaBrY on 30-Jul-18.
 */

public class SignInUpController extends DatabaseController {
private HashMap<String,String> list;

    public void signIn(String username,String email,String password,String age,String phone)
    {
        list = new HashMap<>();
        list.put(user.username,username);
        list.put(user.email,email);
        list.put(user.password,password);
        list.put(user.age,age);
        list.put(user.phone,phone);
        insertion().user(list);
    }

}
