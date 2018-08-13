package com.example.khaledsabry.entertainment.Controllers;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Database.UserData;
import com.example.khaledsabry.entertainment.Fragments.CategoryListFragment;
import com.example.khaledsabry.entertainment.Fragments.MovieView.MovieMainFragment;
import com.example.khaledsabry.entertainment.Interfaces.OnDatabaseSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnSuccess;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 03-Aug-18.
 */

public class CategoryController extends Controller {


 private    CategoryListFragment categoryListFragment;

    public void setCategoryListFragment(CategoryListFragment categoryListFragment) {
        this.categoryListFragment = categoryListFragment;
    }

    public void addFavourite(String tmdbId, String imdbId, int type, final OnSuccess.bool listener)
    {
/*

        databaseController.insertController().categoryAdd(null,constants.Favourite, userData.getUserId(), tmdbId, imdbId, type, null, new OnDatabaseSuccess.bool() {
            @Override
            public void onSuccess(boolean state) {
                listener.onSuccess(state);
            }
        });*/
    }


    public void addHistory(String tmdbId, String imdbId, int type, final OnSuccess.bool listener)
    {

/*
        databaseController.insertController().categoryAdd(null,constants.history, userData.getUserId(), tmdbId, imdbId, type, null, new OnDatabaseSuccess.bool() {
            @Override
            public void onSuccess(boolean state) {
                listener.onSuccess(state);
            }
        });*/
    }


public void addListToCategory(Integer categoryId, String tmdbId, String imdbId, int type, final OnSuccess.bool listener)
{

    databaseController.insertController().listAdd(categoryId, tmdbId, imdbId, type, new OnDatabaseSuccess.bool() {
        @Override
        public void onSuccess(boolean state) {
            listener.onSuccess(state);
        }
    });

}

public void removeFromList(int categoryId,String tmdbId,OnSuccess.bool listener)
{
    databaseController.deleteController().categoryRemoveList(categoryId, tmdbId, new OnDatabaseSuccess.bool() {
        @Override
        public void onSuccess(boolean state) {

        }
    });
}

//get categories to select from them to add to the category
    public void getCategories(final int tmdbId)
    {
        MovieMainFragment.categoryNames = null;
        MovieMainFragment.categoryCheacks = null;
        MovieMainFragment.categoryIds = null;
        databaseController.selectController().categoryGet(UserData.getInstance().getUserId(), new OnDatabaseSuccess.array() {
            @Override
            public void onSuccess(ArrayList<JSONObject> jsonObjects) {
                final ArrayList<String> names =(ArrayList<String>) (Object) getArray(categoryTable.name,jsonObjects);
                final ArrayList<Integer> totalId =(ArrayList<Integer>) (Object) getArray(categoryTable.id,jsonObjects);


                databaseController.selectController().listGetByTmdbId(UserData.getInstance().getUserId(), tmdbId, new OnDatabaseSuccess.array() {
                    @Override
                    public void onSuccess(ArrayList<JSONObject> jsonObjects) {
                        ArrayList<Integer> id =(ArrayList<Integer>) (Object) getArray(listTable.categoryId,jsonObjects);
                        ArrayList<Boolean> booleans = new ArrayList<>();
                        for(int i = 0 ; i <totalId.size(); i++)
                        {
                            if(id.contains(totalId.get(i)))
                                booleans.add(true);
                            else
                                booleans.add(false);
                       }

                        MovieMainFragment.categoryNames = names;
                        MovieMainFragment.categoryCheacks = booleans;
                        MovieMainFragment.categoryIds = totalId;

                    }
                });
            }
        });
    }

//get categories to this user
    public void getCategories()
    {

        databaseController.selectController().categoryGet(UserData.getInstance().getUserId(), new OnDatabaseSuccess.array() {
            @Override
            public void onSuccess(ArrayList<JSONObject> jsonObjects) {
                final ArrayList<String> names =(ArrayList<String>) (Object) getArray(categoryTable.name,jsonObjects);
                final ArrayList<Integer> totalId =(ArrayList<Integer>) (Object) getArray(categoryTable.id,jsonObjects);

                categoryListFragment.setNavigationView(names,totalId);

            }
        });
    }

    public void addMovieCategory(String name,OnSuccess.bool listener)
    {
        databaseController.insertController().categoryAdd(name, constants.movie, new OnDatabaseSuccess.bool() {
            @Override
            public void onSuccess(boolean state) {

            }
        });

    }



    public void getListToCategory(final int categoryId)
    {
        databaseController.selectController().listGetByCategory(categoryId, new OnDatabaseSuccess.array() {
            @Override
            public void onSuccess(ArrayList<JSONObject> jsonObjects) {
                ArrayList<Integer> ids =(ArrayList<Integer>) (Object) getArray(listTable.id,jsonObjects);
                ArrayList<Integer> contentIds =(ArrayList<Integer>) (Object) getArray(listTable.tmdbId,jsonObjects);
                ArrayList<Integer> types =(ArrayList<Integer>) (Object) getArray(listTable.type,jsonObjects);


                categoryListFragment.setRecyclerView(ids,contentIds,types);

            }
        });
    }

}
