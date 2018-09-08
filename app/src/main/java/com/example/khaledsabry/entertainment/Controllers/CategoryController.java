package com.example.khaledsabry.entertainment.Controllers;

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


    private CategoryListFragment categoryListFragment;

    public void setCategoryListFragment(CategoryListFragment categoryListFragment) {
        this.categoryListFragment = categoryListFragment;
    }

    public void addFavourite(final String tmdbId, final String imdbId, final int type, final OnSuccess.bool listener) {

databaseController.selectController().categoryGet(UserData.getInstance().getUserId(), "Favourite", new OnDatabaseSuccess.array() {
    @Override
    public void onSuccess(ArrayList<JSONObject> jsonObjects) {

        String id = ((String) getObject(categoryTable.id, jsonObjects));
        if(id == null) {
            listener.onSuccess(false);
            return;
        }

        databaseController.insertController().itemAdd(Integer.parseInt(id), tmdbId, imdbId, type, new OnDatabaseSuccess.bool() {
            @Override
            public void onSuccess(boolean state) {
                listener.onSuccess(true);
            }
        });

    }
});
    }


    public void addHistory(String tmdbId, String imdbId, int type) {

        databaseController.localInsertController().categoryAddHistory(tmdbId, imdbId, type, new OnDatabaseSuccess.number() {
            @Override
            public void onSuccess(int state) {
                if(state == -1)
                    toast("there went a problem in saving the history");
            }
        });
    }


    public void addItemToCategory(Integer categoryId, String tmdbId, String imdbId, int type, final OnSuccess.bool listener) {

        databaseController.insertController().itemAdd(categoryId, tmdbId, imdbId, type, new OnDatabaseSuccess.bool() {
            @Override
            public void onSuccess(boolean state) {
                listener.onSuccess(state);
            }
        });

    }

    public void removeItem(int categoryId, String tmdbId,int constantType, OnSuccess.bool listener) {
        databaseController.deleteController().CategoryItemRemove(categoryId, tmdbId,constantType, new OnDatabaseSuccess.bool() {
            @Override
            public void onSuccess(boolean state) {

            }
        });
    }

    //get categories to select from them to add to the category
    public void getCategories(final int tmdbId, final int constantContent, final OnSuccess.objects listener) {
        databaseController.selectController().categoryGet(UserData.getInstance().getUserId(), new OnDatabaseSuccess.array() {
            @Override
            public void onSuccess(ArrayList<JSONObject> jsonObjects) {
                final ArrayList<String> names = (ArrayList<String>) (Object) getArray(categoryTable.name, jsonObjects);
                final ArrayList<Integer> totalId = (ArrayList<Integer>) (Object) getArray(categoryTable.id, jsonObjects);


                databaseController.selectController().CategoryItemGetByTmdb(UserData.getInstance().getUserId(), tmdbId,constantContent, new OnDatabaseSuccess.array() {
                    @Override
                    public void onSuccess(ArrayList<JSONObject> jsonObjects) {
                        ArrayList<Integer> id = (ArrayList<Integer>) (Object) getArray(categoryItemTable.categoryId, jsonObjects);
                        ArrayList<Boolean> booleans = new ArrayList<>();
                        for (int i = 0; i < totalId.size(); i++) {
                            if (id.contains(totalId.get(i)))
                                booleans.add(true);
                            else
                                booleans.add(false);
                        }

                        ArrayList<Object> objects = new ArrayList<Object>();
                        objects.add(totalId);
                        objects.add(names);
                        objects.add(booleans);
                        listener.onSuccess(objects);



                    }
                });
            }
        });
    }

    /**
     * get the categories from the database and send it back to
     * show it on the navigation bar in categoryListFragment
      */
    public void getCategories() {

        databaseController.selectController().categoryGet(UserData.getInstance().getUserId(), new OnDatabaseSuccess.array() {
            @Override
            public void onSuccess(ArrayList<JSONObject> jsonObjects) {
                final ArrayList<String> names = (ArrayList<String>) (Object) getArray(categoryTable.name, jsonObjects);
                final ArrayList<Integer> totalId = (ArrayList<Integer>) (Object) getArray(categoryTable.id, jsonObjects);

                categoryListFragment.setNavigationView(names, totalId);

            }
        });
    }

    public void addMovieCategory(String name, final OnSuccess.bool listener) {
        databaseController.insertController().categoryAdd(name, constants.movie, new OnDatabaseSuccess.bool() {
            @Override
            public void onSuccess(boolean state) {
                listener.onSuccess(state);
            }
        });

    }

    /**
     * get items from the database and send it back to categorylistFragment
     * @param categoryId to select the items that has this category id
     */
    public void getItemsByCategoryId(final int categoryId) {
        databaseController.selectController().CategoryItemGetByCategory(categoryId, new OnDatabaseSuccess.array() {
            @Override
            public void onSuccess(ArrayList<JSONObject> jsonObjects) {
                ArrayList<Integer> ids = (ArrayList<Integer>) (Object) getArray(categoryItemTable.id, jsonObjects);
                ArrayList<Integer> contentIds = (ArrayList<Integer>) (Object) getArray(categoryItemTable.tmdbId, jsonObjects);
                ArrayList<Integer> types = (ArrayList<Integer>) (Object) getArray(categoryItemTable.type, jsonObjects);


                categoryListFragment.setRecyclerView(ids, contentIds, types);

            }
        });
    }


    //update category name given the new name you want to change to and the id of the category
    public void updateName(String name, Integer id, final OnSuccess.bool listener) {
        databaseController.updateController().categoryUpdateName(name, id, new OnDatabaseSuccess.bool() {
            @Override
            public void onSuccess(boolean state) {
                listener.onSuccess(state);


            }
        });
    }

    //remove the category using its id
    public void deleteCategory(Integer id, final OnSuccess.bool listener) {

        databaseController.deleteController().categoryDelete(id, new OnDatabaseSuccess.bool() {
            @Override
            public void onSuccess(boolean state) {
                listener.onSuccess(state);
            }
        });
    }

    //remove an item from the category
    public void deleteItemFromCategory(Integer categoryItemId, final OnSuccess.bool listener) {

        databaseController.deleteController().categoryItemDelete(categoryItemId, new OnDatabaseSuccess.bool() {
            @Override
            public void onSuccess(boolean state) {
                listener.onSuccess(state);
            }
        });
    }

}
