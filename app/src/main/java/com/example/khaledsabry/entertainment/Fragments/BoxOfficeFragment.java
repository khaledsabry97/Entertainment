package com.example.khaledsabry.entertainment.Fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Adapter.BoxOfficeAdapter;
import com.example.khaledsabry.entertainment.Connection.WebApi;
import com.example.khaledsabry.entertainment.Controllers.Functions;
import com.example.khaledsabry.entertainment.Interfaces.OnWebSuccess;
import com.example.khaledsabry.entertainment.Items.Classification;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;
import java.util.List;


public class BoxOfficeFragment extends Fragment {

    RecyclerView recyclerView;
    TextView header;
    BoxOfficeAdapter adapter;
    public static BoxOfficeFragment newInstance() {
        BoxOfficeFragment fragment = new BoxOfficeFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_box_office, container, false);
header = view.findViewById(R.id.header);
recyclerView = view.findViewById(R.id.recyclerid);

header.setText("Box Office");
        setBoxOffice();

        return view;
    }


    private void setBoxOffice() {


        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                WebApi.getInstance().mojoBoxOffice(new OnWebSuccess.OnMovieList() {
                    @Override
                    public void onSuccess(ArrayList<Movie> movies) {
                        final Classification classification = new Classification();
                        classification.setImage(R.drawable.arrowleft);
                        classification.setTitle("movieboxoffice");
                        classification.setType(Classification.type.boxoffice);
                        classification.setSearchItems(Functions.movies(movies,null,10));

                        MainActivity.getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter = new BoxOfficeAdapter(classification);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setAdapter(adapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));

                            }
                        });
                    }
                });
            }
        });
    }

}
