package com.example.khaledsabry.entertainment.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Items.SearchItem;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 04-Jul-18.
 */

public class ResultItemRecyclarView extends RecyclerView.Adapter<ResultItemViewHolder> {

    ArrayList<SearchItem> searchItems = new ArrayList<>();

    public ResultItemRecyclarView(ArrayList<SearchItem> movies) {
        this.searchItems = movies;
    }

    @NonNull
    @Override
    public ResultItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_resultitem,parent,false);

        return new ResultItemViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ResultItemViewHolder holder, int position) {
        final SearchItem searchItem = searchItems.get(position);
        holder.updateUi(searchItem);

    }

    @Override
    public int getItemCount() {
        return searchItems.size();
    }
}
