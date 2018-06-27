package com.example.khaledsabry.entertainment.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.googleplay.Activity.MainActivity;
import com.example.khaledsabry.googleplay.R;
import com.example.khaledsabry.googleplay.Station;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 23-Jun-18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<CardContent> {
    ArrayList<Station> list = new ArrayList<>();
    @Override
    public CardContent onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardContent = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);

        return new CardContent(cardContent);
    }

    public RecyclerAdapter(ArrayList<Station> list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(final CardContent holder, int position) {
        final Station station = list.get(position);
        holder.updateUi(station);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getMainActivity().loadImageFullscreen(station);
            }
        });





    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
