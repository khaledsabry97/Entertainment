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
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Adapter.CategoryAdapter;
import com.example.khaledsabry.entertainment.Adapter.TitleAdapter;
import com.example.khaledsabry.entertainment.Controllers.CategoryController;
import com.example.khaledsabry.entertainment.Interfaces.OnSuccess;
import com.example.khaledsabry.entertainment.R;

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

        setTabLayout();
        adapter = new CategoryAdapter();
        categoryController.setCategoryListFragment(this);
        categoryController.getCategories();


        return view;
    }


    public void setNavigationView(final ArrayList<String> categoryNames, final ArrayList<Integer> ids) {
        //this is to add a category
        categoryNames.add("Add New Category");

        titleAdapter = new TitleAdapter(categoryNames, new OnSuccess.Object() {
            @Override
            public void onSuccess(OnSuccess.Object state) {

            }

            @Override
            public void onSuccess(Integer num) {
                if (num == categoryNames.size() - 1) {
                    updateText();
                    return;
                }
                drawerLayout.closeDrawer(GravityCompat.END, true);
                int id = Integer.valueOf(String.valueOf(ids.get(num)));
                header.setText(categoryNames.get(num));
                categoryController.getItemsByCategoryId(id);

            }
        });
        categoriesRecycle.setAdapter(titleAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        categoriesRecycle.setLayoutManager(linearLayoutManager);

        int id = Integer.valueOf(String.valueOf(ids.get(0)));
        header.setText(categoryNames.get(0));
        categoryController.getItemsByCategoryId(id);

    }

    public void setRecyclerView(ArrayList<Integer> listId, final ArrayList<Integer> contentId, ArrayList<Integer> types) {
        adapter.setData(listId, contentId, types);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        listRecycle.setLayoutManager(linearLayoutManager);
        listRecycle.setAdapter(adapter);
    }


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


    void setTabsTitles(TabLayout tabLayout) {
        tabLayout.getTabAt(0).setText("All");
        tabLayout.getTabAt(1).setText("Movies");
        tabLayout.getTabAt(2).setText("Tv Series");
        tabLayout.getTabAt(3).setText("Artists");
    }


    void updateText() {
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
                final String m_Text = input.getText().toString();
                categoryController.addMovieCategory(m_Text, new OnSuccess.bool() {
                    @Override
                    public void onSuccess(boolean state) {
                        if (state) {
                            categoryController.toast(m_Text + " has been added");
                            titleAdapter.addTitle(m_Text);
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


}
