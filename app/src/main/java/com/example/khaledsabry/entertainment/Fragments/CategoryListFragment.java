package com.example.khaledsabry.entertainment.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.khaledsabry.entertainment.Adapter.CategoryAdapter;
import com.example.khaledsabry.entertainment.Adapter.TitleAdapter;
import com.example.khaledsabry.entertainment.Controllers.CategoryController;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;


public class CategoryListFragment extends Fragment {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    RecyclerView listRecycle;
    RecyclerView categoriesRecycle;
    CategoryController categoryController;
    public static CategoryListFragment newInstance() {
        CategoryListFragment fragment = new CategoryListFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);

        categoryController = new CategoryController();
navigationView  = view.findViewById(R.id.nav_view);
drawerLayout = view.findViewById(R.id.drawer_layout);



categoryController.setCategoryListFragment(this);
        categoryController.getCategories();


        return view;
    }


    public  void setNavigationView(ArrayList<String> categoryNames, final ArrayList<Integer> ids)
    {
        final TitleAdapter titleAdapter = new TitleAdapter(categoryNames);
        categoriesRecycle.setAdapter(titleAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        categoriesRecycle.setLayoutManager(linearLayoutManager);
        titleAdapter.titleViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            int  id =    titleAdapter.titleViewHolder.getAdapterPosition();
            id = ids.get(id);
            categoryController.getListToCategory(id);
            }
        });
    }

    public void setRecyclerView( ArrayList<Integer> listId, final ArrayList<Integer> contentId,ArrayList<Integer> types)
    {
        CategoryAdapter adapter = new CategoryAdapter(listId,contentId,types);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        listRecycle.setLayoutManager(linearLayoutManager);
        listRecycle.setAdapter(adapter);
    }

}
