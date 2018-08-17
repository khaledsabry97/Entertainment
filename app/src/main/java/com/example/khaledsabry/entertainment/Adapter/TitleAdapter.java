package com.example.khaledsabry.entertainment.Adapter;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Interfaces.OnSuccess;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 13-Aug-18.
 */

public class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.TitleViewHolder> {

    ArrayList<String> titles;
    public TitleViewHolder titleViewHolder;
public OnSuccess.Object listener;

    public void setData(ArrayList<String> titles, OnSuccess.Object listener) {
        this.titles = titles;
        this.listener = listener;
        notifyDataSetChanged();

    }

    public void addTitle(String title)
    {
        titles.add(title);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TitleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_title, parent, false);
        titleViewHolder = new TitleViewHolder(view);
        return titleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TitleViewHolder holder, int position) {
        holder.updateUi(position);

    }


    @Override
    public int getItemCount() {
        if(titles == null)
            return 0;
        return titles.size();
    }

    public class TitleViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
private View view;
        public TitleViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            view = itemView;

        }

        void updateUi(final int position)
        {
            title.setText(titles.get(position));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSuccess(position);
                }
            });
        }
        public TextView getTitle() {
            return title;
        }
    }
}
