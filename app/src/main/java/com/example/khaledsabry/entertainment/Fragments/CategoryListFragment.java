package com.example.khaledsabry.entertainment.Fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Adapter.CategoryAdapter;
import com.example.khaledsabry.entertainment.Adapter.TitleAdapter;
import com.example.khaledsabry.entertainment.Controllers.CategoryController;
import com.example.khaledsabry.entertainment.Interfaces.OnSuccess;
import com.example.khaledsabry.entertainment.R;

import java.nio.InvalidMarkException;
import java.util.ArrayList;

/**
 * class to show all the categories that you have in the database
 */
public class CategoryListFragment extends Fragment {

    //these are for showing the categories names
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    RecyclerView categoriesRecycle;
    TitleAdapter titleAdapter;

    //these for showing the items in categories
    TextView header;
    RecyclerView listRecycle;
    TabLayout tabLayout;
    CategoryAdapter adapter;

    //these for adding,deleting and changing the names of categories and items
    CategoryController categoryController;
    ImageView deleteCategory;
    ImageView addCategory;
    ImageView changeCategoryName;
    Integer currentCategoryId;
    String currentCategoryName;

    public static CategoryListFragment newInstance() {
        CategoryListFragment fragment = new CategoryListFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);

        header = view.findViewById(R.id.header);
        categoriesRecycle = view.findViewById(R.id.categoryrecycle);
        listRecycle = view.findViewById(R.id.listrecycle);
        categoryController = new CategoryController();
        navigationView = view.findViewById(R.id.nav_view);
        drawerLayout = view.findViewById(R.id.drawer_layout);
        tabLayout = view.findViewById(R.id.tablayout);
        deleteCategory = view.findViewById(R.id.deletecategory);
        addCategory = view.findViewById(R.id.addcategory);
        changeCategoryName = view.findViewById(R.id.changename);

        currentCategoryName = "";
        currentCategoryId = null;


        setTitleCategoriesAdapter();
        setItemsAdapter();


        deleteCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCategory();
            }
        });

        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCategory();
            }
        });

        changeCategoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeCategoryName();
            }
        });

        setTabLayout();
        categoryController.setCategoryListFragment(this);
        categoryController.getCategories();


        return view;
    }

    /**
     * set the category contents and show it on the recyclerview
     */
    private void setItemsAdapter() {
        adapter = new CategoryAdapter();
        listRecycle.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        listRecycle.setLayoutManager(linearLayoutManager2);
    }

    /**
     * set the category names and show it on the navigation bar
     * inside recycler view
     */
    private void setTitleCategoriesAdapter() {
        titleAdapter = new TitleAdapter();
        categoriesRecycle.setAdapter(titleAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        categoriesRecycle.setLayoutManager(linearLayoutManager);

    }

    /**
     * set the tablayout click listener
     */
    private void setTabLayout() {
        //  setTabsTitles(tabLayout);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                adapter.setTypeToShow(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }



    /**
     * this function is called from catgoryController
     * to set the navigation view with category names
     *
     * @param categoryNames category names that user has created it
     * @param ids           ids for each of category
     */
    public void setNavigationView(final ArrayList<String> categoryNames, final ArrayList<Integer> ids) {
        //this is to add a category
        titleAdapter.setData(categoryNames, new OnSuccess.Object() {
            @Override
            public void onSuccess(OnSuccess.Object state) {

            }

            @Override
            public void onSuccess(Integer num) {

                drawerLayout.closeDrawer(GravityCompat.END, true);
                getCategoryItems(categoryNames, ids, num);


            }
        });

        //show items of the first category
        getCategoryItems(categoryNames, ids, 0);

    }

    /**
     * get from the database the items of selected category
     *
     * @param categoryNames to set the header name depending on the selected index
     * @param ids           to select index from it used to send to the database to get items for the category
     * @param num           selected index
     */
    private void getCategoryItems(final ArrayList<String> categoryNames, final ArrayList<Integer> ids, Integer num) {
        //if there is no categories so return
        if (ids.size() == 0)
            return;
        //if there are categories select
        int id = Integer.valueOf(String.valueOf(ids.get(num)));
        header.setText(categoryNames.get(num));
        currentCategoryId = id;
        currentCategoryName = categoryNames.get(num);
        categoryController.getItemsByCategoryId(id);
    }

    /**
     * called from categoryController after brings back the items for
     * specific category
     *
     * @param itemsId the ids in the database for each item
     * @param tmdbIds the tmdb id for each movie,tv or artist
     * @param types   the type of the item
     *                1 : movie
     *                2: tv
     *                3: artist
     */
    public void setRecyclerView(ArrayList<Integer> itemsId, final ArrayList<Integer> tmdbIds, ArrayList<Integer> types) {

        //itemsid: id for the categoryItem
        //tmdbIds: tmdb Id
        //types : movie,tv or artist
        //categoryController: to use it later to delete a category item
        adapter.setData(itemsId, tmdbIds, types, currentCategoryId, currentCategoryName, categoryController);


    }



    /**
     * to add new category
     */
    void addCategory() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
// I'm using fragment here so I'm using getView() to provide ViewGroup
// but you can provide here any other instance of ViewGroup from your Fragment / Activity
        View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.add_category, (ViewGroup) getView(), false);
// Set up the input
        final EditText input = (EditText) viewInflated.findViewById(R.id.input);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        builder.setView(viewInflated);

// Set up the buttons
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // dialog.dismiss();
                final String newName = input.getText().toString();
                if (newName.isEmpty())
                    return;
                categoryController.addMovieCategory(newName, new OnSuccess.bool() {
                    @Override
                    public void onSuccess(boolean state) {
                        if (state) {
                            categoryController.toast(newName + " has been added");
                            categoryController.getCategories();
                        } else
                            categoryController.toast("failed to add the category");
                    }
                });
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    /**
     * change currrent category name
     */
    private void changeCategoryName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
// I'm using fragment here so I'm using getView() to provide ViewGroup
// but you can provide here any other instance of ViewGroup from your Fragment / Activity
        View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.add_category, (ViewGroup) getView(), false);
// Set up the input
        final EditText input = (EditText) viewInflated.findViewById(R.id.input);
        input.setHint("Write the name for this category");
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        builder.setView(viewInflated);

// Set up the buttons
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // dialog.dismiss();
                final String newName = input.getText().toString();
                if (newName.isEmpty())
                    return;
                categoryController.updateName(newName, currentCategoryId, new OnSuccess.bool() {
                    @Override
                    public void onSuccess(boolean state) {
                        if (state) {
                            categoryController.toast(currentCategoryName + " has been changed to " + newName);
                            titleAdapter.addTitle(newName);
                        } else
                            categoryController.toast("failed to change Name");
                    }
                });
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    /**
     * delete current category
     */
    private void deleteCategory() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("Do you want to delete this category ( " + currentCategoryName + " ) ?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (currentCategoryId == null)
                    return;
                categoryController.deleteCategory(currentCategoryId, new OnSuccess.bool() {
                    @Override
                    public void onSuccess(boolean state) {
                        if (state) {
                            categoryController.toast("category " + currentCategoryName + " has been deleted successfully");
                            MainActivity.loadFragmentNoReturn(R.id.mainContainer, CategoryListFragment.newInstance());
                        } else {
                            categoryController.toast("failed to delete the category ( " + currentCategoryName + " )");
                        }
                    }
                });
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });

        builder.show();


    }

    /**
     * delete item from current category
     * called from the adapter
     *
     * @param categoryItemId     the item of the category id
     * @param categoryId         the category id
     * @param categoryName       the category name
     * @param itemName           the item name
     * @param categoryController controller to remove the item because it's a static function
     */
    public static void deleteCategoryItem(final int categoryItemId, final int categoryId, String categoryName, String itemName, final CategoryController categoryController) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.getActivity());

            builder.setTitle("Do you want to remove [ " + itemName + " ] from [ " + categoryName + " ] ?");
            builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    categoryController.deleteItemFromCategory(categoryItemId, new OnSuccess.bool() {
                        @Override
                        public void onSuccess(boolean state) {
                            if (state) {
                                categoryController.toast("Deleted successfully");
                                categoryController.getItemsByCategoryId(categoryId);
                                MainActivity.loadFragmentNoReturn(R.id.mainContainer, CategoryListFragment.newInstance());
                            } else {
                                categoryController.toast("failed to delete");
                            }
                        }
                    });
                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();

                }
            });

            builder.show();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
