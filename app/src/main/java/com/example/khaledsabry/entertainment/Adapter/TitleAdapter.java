package com.example.khaledsabry.entertainment.Adapter;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 13-Aug-18.
 */

public class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.TitleViewHolder> {

ArrayList<String> titles;

public TitleViewHolder titleViewHolder;
    public TitleAdapter(ArrayList<String> titles) {
        this.titles = titles;
    }

    @NonNull
    @Override
    public TitleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_title,parent,false);
        titleViewHolder = new TitleViewHolder(view);
        return titleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TitleViewHolder holder, int position) {
holder.title.setText(titles.get(position));

    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

   public class TitleViewHolder extends RecyclerView.ViewHolder
    {
public TextView title;
View view;
        public TitleViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            view = itemView;
        }


    }
}
