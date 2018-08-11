package com.example.khaledsabry.entertainment.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Controllers.CategoryController;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;


public class CategoryAddFragment extends Fragment {

    AlertDialog.Builder builder;
    ArrayList<String> playLists;
    ArrayList<Boolean> checks;
    ArrayList<Integer> ids;


    ArrayList<Integer> checked = new ArrayList<>();
    ArrayList<Integer> unChecked = new ArrayList<>();

    CategoryController categoryController = new CategoryController();
    public static CategoryAddFragment newInstance(ArrayList<String> playLists,ArrayList<Integer> ids,ArrayList<Boolean> checks) {
        CategoryAddFragment fragment = new CategoryAddFragment();
fragment.playLists = playLists;
fragment.checks = checks;
fragment.ids = ids;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_add, container, false);

        builder = new AlertDialog.Builder(getContext());

        boolean[] booleans = new boolean[checks.size()];
        CharSequence[] lists = new CharSequence[playLists.size()];
        for(int i = 0 ;i <booleans.length;i++)
        {
            booleans[i] = checks.get(i);
            lists[i] = playLists.get(i);
        }
        builder.setTitle("choose the lists to add");
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setPositiveButton("set", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setMultiChoiceItems(lists, booleans, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked)
checked.add(ids.get(which));

                else
                    unChecked.add(ids.get(which));
            }
        });

        builder.create().show();


        return view;
    }

}
