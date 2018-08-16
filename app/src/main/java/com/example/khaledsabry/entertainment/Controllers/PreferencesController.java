package com.example.khaledsabry.entertainment.Controllers;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Database.UserData;
import com.example.khaledsabry.entertainment.Interfaces.OnDatabaseSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnSuccess;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 15-Aug-18.
 */

public class PreferencesController extends Controller {

    public void uploadProfileImage(String image64, final OnSuccess.bool listener) {
        uploadImage(userTable.profileImage, image64, listener);
      /*  databaseController.insertController().userUploadImage(UserData.getInstance().getUserId(),userTable.profileImage,image64, new OnDatabaseSuccess.bool() {
                    @Override
                    public void onSuccess(boolean state) {
                        listener.onSuccess(state);
                    }
                }
        );*/
    }

    public void uploadBackDropImage(String image64, final OnSuccess.bool listener) {
        uploadImage(userTable.backdropImage, image64, listener);
    }

    //upload an image to the server only using the name and the image in base64
    public void uploadImage(String name, String image64, final OnSuccess.bool listener) {
        databaseController.insertController().userUploadImage(UserData.getInstance().getUserId(), name, image64, new OnDatabaseSuccess.bool() {
                    @Override
                    public void onSuccess(boolean state) {
                        listener.onSuccess(state);
                    }
                }
        );
    }

    //get from the server image by name and set the image view directly
    public void getImageFromServerToImageView(String name, final ImageView imageView, final OnSuccess.bool listener) {
        databaseController.selectController().imageGetImage(UserData.getInstance().getUserId(), name, new OnDatabaseSuccess.array() {
            @Override
            public void onSuccess(ArrayList<JSONObject> jsonObjects) {
                ArrayList<String> paths = (ArrayList<String>) (Object) getArray(imageTable.path, jsonObjects);
                if (paths.size() == 0)
                    return;
                String path = paths.get(0);
                ImageController.putImageToImageView(path, imageView);
                listener.onSuccess(true);

            }
        });
    }


    public void setProfileImage(final ImageView imageView) {
        databaseController.selectController().imageGetImage(UserData.getInstance().getUserId(), userTable.profileImage, new OnDatabaseSuccess.array() {
            @Override
            public void onSuccess(ArrayList<JSONObject> jsonObjects) {
                ArrayList<String> paths = (ArrayList<String>) (Object) getArray(imageTable.path, jsonObjects);
                if (paths.size() == 0)
                    return;
                String path = paths.get(0);
                Glide.with(MainActivity.getActivity().getApplicationContext()).load(path).apply(RequestOptions.circleCropTransform()).into(imageView);


            }
        });

    }


    public void setBackDropImage(final ImageView imageView) {
        getImageFromServerToImageView(userTable.backdropImage, imageView, new OnSuccess.bool() {
            @Override
            public void onSuccess(boolean state) {

            }
        });
    }
}
