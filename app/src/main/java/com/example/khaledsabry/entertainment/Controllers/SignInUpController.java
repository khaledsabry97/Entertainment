package com.example.khaledsabry.entertainment.Controllers;

import com.example.khaledsabry.entertainment.Database.Origin.DatabaseTables;
import com.example.khaledsabry.entertainment.Interfaces.OnDatabaseSuccess;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 30-Jul-18.
 */

public class SignInUpController extends Controller {

    public void signIn(String username,String email,String password,String age,String phone)
    {

        databaseController.insertController().userSignIn(username, email, password, age, phone, new OnDatabaseSuccess.bool() {
            @Override
            public void onSuccess(boolean listener) {
                if(listener)
                    toast("True");
                else
                    toast("false");
            }
        });


    }

    public void checkUserNameAvailability(String username)
    {
        databaseController.selectController().userCheckUsername(username, new OnDatabaseSuccess.array() {
            @Override
            public void onSuccess(ArrayList<JSONObject> jsonObjects) {
                ArrayList<String> usernames = (ArrayList<String>)(Object) getArray(DatabaseTables.user.username,jsonObjects);
                if(jsonObjects.size() == 0)
                    toast("false");
                else
                    toast("true");
            }
        });
    }

}
