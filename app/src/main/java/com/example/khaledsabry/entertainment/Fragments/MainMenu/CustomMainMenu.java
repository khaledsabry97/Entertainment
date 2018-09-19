package com.example.khaledsabry.entertainment.Fragments.MainMenu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Adapters.ContentAdapter;
import com.example.khaledsabry.entertainment.Adapters.MainContentAdapter;
import com.example.khaledsabry.entertainment.Adapters.MenuAdapter;
import com.example.khaledsabry.entertainment.R;


public class CustomMainMenu extends Fragment {
    ViewPager viewPager;
    MainContentAdapter mainContentAdapter;
    RecyclerView contentRecycler;
    ContentAdapter contentAdapter;
    RecyclerView menuRecycler;
    MenuAdapter menuAdapter;

    public static CustomMainMenu newInstance() {
        CustomMainMenu fragment = new CustomMainMenu();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_custom_main_menu2, container, false);
        viewPager = view.findViewById(R.id.view_pager_id);
        contentRecycler = view.findViewById(R.id.content_recycler_id);
        menuRecycler = view.findViewById(R.id.icons_recycler_id);


        setupContentRecycler();
        setupmenuRecycler();
        setupViewPager();

        return view;
    }

    private void setupViewPager() {
        mainContentAdapter = new MainContentAdapter(getFragmentManager());
        viewPager.setAdapter(mainContentAdapter);
    }

    private void setupmenuRecycler() {
        menuAdapter = new MenuAdapter();
        menuRecycler.setAdapter(menuAdapter);
        contentRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    private void setupContentRecycler() {
        contentAdapter = new ContentAdapter();
        contentRecycler.setAdapter(contentAdapter);
    }

}
