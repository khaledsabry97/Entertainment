package com.example.khaledsabry.entertainment.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Controllers.CategoryController;
import com.example.khaledsabry.entertainment.Interfaces.OnSuccess;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;
import java.util.HashMap;


public class CategoryAddFragment extends Fragment {

    AlertDialog.Builder builder;
    ArrayList<String> playLists;
    ArrayList<Boolean> checks;
    ArrayList<Integer> ids;


    ArrayList<Integer> checked = new ArrayList<>();
    ArrayList<Integer> unChecked = new ArrayList<>();
String tmdbId;
int type;
HashMap<Integer,Boolean> map = new HashMap<>();
    CategoryController categoryController;
    public static CategoryAddFragment newInstance(ArrayList<String> playLists,ArrayList<Integer> ids,ArrayList<Boolean> checks,int type,String tmdbId) {
        CategoryAddFragment fragment = new CategoryAddFragment();
fragment.playLists = playLists;
fragment.checks = checks;
fragment.ids = ids;
fragment.tmdbId = tmdbId;
fragment.type = type;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_add, container, false);
        categoryController = new CategoryController();
        builder = new AlertDialog.Builder(getContext());

        final boolean[] booleans = new boolean[checks.size()];
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

                ArrayList<Integer> keys = new ArrayList<Integer>(map.keySet());
                ArrayList<Boolean> values = new ArrayList<Boolean>(map.values());
                map.clear();

                for (int i = 0; i < keys.size(); i++) {
                    if(values.get(i) == true) {
                        String s = String.valueOf(keys.get(i));
                        Integer id = Integer.valueOf(s);
                        categoryController.addListToCategory(id, tmdbId, null, type, new OnSuccess.bool() {
                            @Override
                            public void onSuccess(boolean state) {

                            }
                        });
                    }
                    else {
                        String s = String.valueOf(keys.get(i));
                        Integer id = Integer.valueOf(s);
                        categoryController.removeFromList(id, tmdbId, new OnSuccess.bool() {
                            @Override
                            public void onSuccess(boolean state) {

                            }
                        });
                    }
                }

}
        });

        builder.setMultiChoiceItems(lists, booleans, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(map.get(ids.get(which)) != null)
                    map.remove(ids.get(which));

                if(checks.get(which) != isChecked)
                    map.put(ids.get(which),isChecked);

            }
        });

        builder.create().show();


        return view;
    }

}
