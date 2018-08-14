package com.example.khaledsabry.entertainment.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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


        categoryController.setCategoryListFragment(this);
        categoryController.getCategories();


        return view;
    }


    public void setNavigationView(final ArrayList<String> categoryNames, final ArrayList<Integer> ids) {
        final TitleAdapter titleAdapter = new TitleAdapter(categoryNames, new OnSuccess.Object() {
            @Override
            public void onSuccess(OnSuccess.Object state) {

            }

            @Override
            public void onSuccess(Integer num) {
                drawerLayout.closeDrawer(GravityCompat.END,true);
               int id = Integer.valueOf(String.valueOf(ids.get(num)));
               header.setText(categoryNames.get(num));
                categoryController.getListToCategory(id);

            }
        });
        categoriesRecycle.setAdapter(titleAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        categoriesRecycle.setLayoutManager(linearLayoutManager);

        int id = Integer.valueOf(String.valueOf(ids.get(0)));
        header.setText(categoryNames.get(0));
        categoryController.getListToCategory(id);

    }

    public void setRecyclerView(ArrayList<Integer> listId, final ArrayList<Integer> contentId, ArrayList<Integer> types) {
        CategoryAdapter adapter = new CategoryAdapter(listId, contentId, types);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        listRecycle.setLayoutManager(linearLayoutManager);
        listRecycle.setAdapter(adapter);
    }

}
