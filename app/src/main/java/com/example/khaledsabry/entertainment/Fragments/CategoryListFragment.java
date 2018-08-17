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


public class CategoryListFragment extends Fragment {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    RecyclerView listRecycle;
    RecyclerView categoriesRecycle;
    CategoryController categoryController;
    TextView header;
    TabLayout tabLayout;
    CategoryAdapter adapter;
    TitleAdapter titleAdapter;
    ImageView deleteCategory;
    ImageView addCategory;
    ImageView changeCategoryName;
    Integer currentCategoryId;
    String currentCategoryName;
    static Context context;

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

        //context is for AlertBuilder In a static function to remove item from category;
        context = getContext();
        currentCategoryName = "";
        currentCategoryId = null;


        titleAdapter = new TitleAdapter();
        adapter = new CategoryAdapter();
        categoriesRecycle.setAdapter(titleAdapter);
        listRecycle.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        categoriesRecycle.setLayoutManager(linearLayoutManager);
        listRecycle.setLayoutManager(linearLayoutManager);

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

    //get the categories and set it in the navigation view
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

    //from setNavigationView function call this to get items to specific category
    public void setRecyclerView(ArrayList<Integer> itemsId, final ArrayList<Integer> contentId, ArrayList<Integer> types) {
        //itemsid: id for the categoryItem
        //contentId: tmdb Id
        //types : movie,tv or artist
        //categoryController: to use it later to delete a category item

        adapter.setData(itemsId, contentId, types, currentCategoryId, currentCategoryName, categoryController);


    }


    //set an tab click listener
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

    public static void deleteCategoryItem(final int categoryItemId, final int categoryId, String categoryName, final CategoryController categoryController) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            builder.setTitle("Do you want to remove this from ( " + categoryName + " ) ?");
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
