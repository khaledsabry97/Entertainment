package com.example.khaledsabry.entertainment.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Fragments.SearchFragment;
import com.example.khaledsabry.entertainment.Fragments.SearchResultItemFragment;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 04-Jul-18.
 */

public class ResultItemRecyclarView extends RecyclerView.Adapter<ResultItemViewHolder> {

    ArrayList<Movie> movies = new ArrayList<>();

    public ResultItemRecyclarView(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public ResultItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_resultitem,parent,false);

        return new ResultItemViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ResultItemViewHolder holder, int position) {
        final Movie movie = movies.get(position);
        holder.updateUi(movie);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.searchresultitemid, SearchResultItemFragment.newInstance(movie)).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
