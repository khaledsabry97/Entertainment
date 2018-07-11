package com.example.khaledsabry.entertainment.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.Tv;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 05-Jul-18.
 */

public class RoleRecyclarAdapter extends RecyclerView.Adapter<RoleViewHolder> {
    int type; //type = 0 --> movies          type = 1 --> tv
    ArrayList<Movie> movies;
    ArrayList<Tv> tvs;

    public RoleRecyclarAdapter(int type, ArrayList<Movie> movies, ArrayList<Tv> tvs) {
        this.type = type;
        this.movies = movies;
        this.tvs = tvs;
    }

    @NonNull
    @Override
    public RoleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_role,parent,false);
        return new RoleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoleViewHolder holder, int position) {
        if(type==0)
            holder.updateUi(movies.get(position));
        else         if(type==1)
            holder.updateUi(tvs.get(position));


    }

    @Override
    public int getItemCount() {
        if(type == 0)
            return movies.size();
        else if(type == 1)
            return tvs.size();
        return 0;
    }
}
