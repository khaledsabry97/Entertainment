package com.example.khaledsabry.entertainment.Fragments;


import android.content.DialogInterface;
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
import com.example.khaledsabry.entertainment.Adapters.CategoryAdapter;
import com.example.khaledsabry.entertainment.Adapters.TitleAdapter;
import com.example.khaledsabry.entertainment.Controllers.CategoryController;
import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Controllers.Toasts;
import com.example.khaledsabry.entertainment.Interfaces.OnArtistDataSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieDataSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnTvSuccess;
import com.example.khaledsabry.entertainment.Items.Artist;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.Tv;
import com.example.khaledsabry.entertainment.R;
import com.felix.bottomnavygation.BottomNav;
import com.felix.bottomnavygation.ItemNav;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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
    Integer currentCategoryId;
    String currentCategoryName;

    //to get details for the items
    TmdbController controller;
    ArrayList<Integer> itemsId;
    ArrayList<Integer> tmdbIds;
    ArrayList<Integer> types;
    ArrayList<Object> objects;

    BottomNav bottomNav;
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
        bottomNav = view.findViewById(R.id.bottom_navigation_id);

        currentCategoryName = "";
        currentCategoryId = null;
        controller = new TmdbController();

        setTitleCategoriesAdapter();
        setItemsAdapter();


        setUpBottomNav();

        setTabLayout();
        categoryController.setCategoryListFragment(this);
        categoryController.getCategories();


        return view;
    }

    /**
     * setup the bottom navigation for add and change and delete the categories
     */
    private void setUpBottomNav()
    {
        bottomNav.addItemNav(new ItemNav(getContext(), R.drawable.ic_playlist_add_black_24dp, "Add").addColorAtive(R.color.white));
        bottomNav.addItemNav(new ItemNav(getContext(), R.drawable.ic_edit_black_24dp, "Edit").addColorAtive(R.color.white));
        bottomNav.addItemNav(new ItemNav(getContext(), R.drawable.ic_delete_forever_black_24dp,"Delete").addColorAtive(R.color.white));


        bottomNav.setTabSelectedListener(new BottomNav.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int i) {
                switch (i)
                {
                    case 0:
                        addCategory();
                        break;
                    case 1:
                        changeCategoryName();
                        break;
                    case 2:
                        deleteCategory();
                        break;
                }
            }

            @Override
            public void onTabLongSelected(int i) {

            }
        });
        bottomNav.build();
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
     * @param itemsId the ids in the database for each object
     * @param tmdbIds the tmdb id for each movie,tv or artist
     * @param types   the type of the object
     *                1 : movie
     *                2: tv
     *                3: artist
     */
    public void setContentsInRecyclerView(ArrayList<Integer> itemsId, final ArrayList<Integer> tmdbIds, ArrayList<Integer> types) {

        //itemsid: id for the categoryItem
        //tmdbIds: tmdb Id
        //types : movie,tv or artist
        //categoryController: to use it later to delete a category object
        adapter.clearData();
        this.itemsId = itemsId;
        this.tmdbIds = tmdbIds;
        this.types = types;
        adapter.setBase(currentCategoryId, currentCategoryName, categoryController);
        getDetails();


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
                            Toasts.success(newName + " has been added");
                            categoryController.getCategories();
                        } else
                            Toasts.error("failed to add the category");
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
                            Toasts.success(currentCategoryName + " has been changed to " + newName);
                            titleAdapter.addTitle(newName);
                        } else
                            Toasts.error("failed to change Name");
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
        if(currentCategoryName.toLowerCase().equals("favourite"))
            return;
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
                            Toasts.success("category " + currentCategoryName + " has been deleted successfully");
                            MainActivity.loadFragmentNoReturn(R.id.mainContainer, CategoryListFragment.newInstance());
                        } else {
                            Toasts.error("failed to delete the category ( " + currentCategoryName + " )");
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
     * delete object from current category
     * called from the adapter
     *
     * @param categoryItemId     the object of the category id
     * @param categoryId         the category id
     * @param categoryName       the category name
     * @param itemName           the object name
     * @param categoryController controller to remove the object because it's a static function
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
                                Toasts.success("Deleted successfully");
                                categoryController.getItemsByCategoryId(categoryId);
                                MainActivity.loadFragmentNoReturn(R.id.mainContainer, CategoryListFragment.newInstance());
                            } else {
                                Toasts.error("failed to delete");
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

    /**
     * get the details for the tmdbs and aad it to the adapter
     */
    public void getDetails() {
   final Timer timer =     new Timer();
           timer.schedule(new TimerTask() {
            int i = 0;
            @Override
            public void run() {
                if(tmdbIds == null) {
                    timer.cancel();
                    return;
                }
                if(i== tmdbIds.size()) {
                    timer.cancel();
                    return;
                }

                final Integer type = Integer.parseInt(String.valueOf(types.get(i)));
                final Integer itemId = Integer.parseInt(String.valueOf(itemsId.get(i)));
                Integer tmdbId = Integer.parseInt(String.valueOf(tmdbIds.get(i)));
                final Integer position = i;
                switch (type)
                {
                    case 1:
                        controller.getMovie(tmdbId, new OnMovieDataSuccess() {
                            @Override
                            public void onSuccess(Movie movie) {
                                addToAdapter(movie,type,itemId,position);
                            }
                        });
                        break;
                    case 2:
                        controller.getTv(tmdbId, new OnTvSuccess() {
                            @Override
                            public void onSuccess(Tv tv) {
                                addToAdapter(tv,type,itemId,position);
                            }
                        });
                        break;
                    case 3:
                        controller.getPersonDetails(tmdbId, new OnArtistDataSuccess() {
                            @Override
                            public void onSuccess(Artist artist) {
                                addToAdapter(artist,type,itemId,position);
                            }
                        });

                }


                i++;
            }
        },0,250);
    }


    /**
     * after you get the info from database then from tmdb id
     * @param object movie,tv or artist
     * @param type 1 for movie 2 for tv 3 for artist
     * @param itemId the id for the object in the database
     * @param position position you got it from the database
     */
    private void addToAdapter(Object object, Integer type,Integer itemId,Integer position)
    {
        adapter.setData(object,type,itemId,position);
    }
}
