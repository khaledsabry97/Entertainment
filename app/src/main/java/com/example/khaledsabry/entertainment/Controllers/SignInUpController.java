package com.example.khaledsabry.entertainment.Controllers;

import com.example.khaledsabry.entertainment.Interfaces.OnDatabaseSuccess;

/**
 * Created by KhALeD SaBrY on 30-Jul-18.
 */

public class SignInUpController extends Controller {

    public void signIn(String username,String email,String password,String age,String phone)
    {

        databaseController.insertController().user(username, email, password, age, phone, new OnDatabaseSuccess.bool() {
            @Override
            public void onSuccess(boolean listener) {

            }
        });
    }

}
