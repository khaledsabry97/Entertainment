package com.example.khaledsabry.entertainment.Controllers;

import com.example.khaledsabry.entertainment.Database.Origin.DatabaseTables;
import com.example.khaledsabry.entertainment.Database.UserData;
import com.example.khaledsabry.entertainment.Interfaces.OnDatabaseSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnSuccess;

import org.json.JSONObject;

import java.util.ArrayList;

import javax.xml.parsers.FactoryConfigurationError;

/**
 * Created by KhALeD SaBrY on 30-Jul-18.
 */

public class SignInUpController extends Controller {

    public void signUp(String username, String email, String password, String age, String phone) {

        databaseController.insertController().userSignIn(username, email, password, age, phone, new OnDatabaseSuccess.bool() {
            @Override
            public void onSuccess(boolean listener) {
                if (listener)
                    toast("True");
                else
                    toast("false");
            }
        });


    }

    public  void signIn(String username, String password, final OnSuccess.bool listener)
    {
        databaseController.selectController().userSignIn(username, password, new OnDatabaseSuccess.array() {
            @Override
            public void onSuccess(ArrayList<JSONObject> jsonObjects) {
                if(jsonObjects.size() == 0)
                {
                    listener.onSuccess(false);
                    return;
                }

                String username = (String) getObject(DatabaseTables.user.username,jsonObjects);
                int id = Integer.valueOf((String) getObject(DatabaseTables.user.id,jsonObjects));
                UserData.getInstance().setUsername(username);
                UserData.getInstance().setUserId(id);
                listener.onSuccess(true);
            }
        });
    }

    public void checkUserNameAvailability(String username,final OnSuccess.bool listener) {
        databaseController.selectController().userCheckUsername(username, new OnDatabaseSuccess.array() {
            @Override
            public void onSuccess(ArrayList<JSONObject> jsonObjects) {
                if (jsonObjects.size() == 0)
                    listener.onSuccess(true);
                else
                    listener.onSuccess(false);
            }
        });
    }


    public void checkEmailAvailability(String email, final OnSuccess.bool listener) {
        databaseController.selectController().userCheckEmail(email, new OnDatabaseSuccess.array() {
            @Override
            public void onSuccess(ArrayList<JSONObject> jsonObjects) {
                if (jsonObjects.size() == 0)
                    listener.onSuccess(true);
                else
                    listener.onSuccess(false);

            }
        });
    }

}
