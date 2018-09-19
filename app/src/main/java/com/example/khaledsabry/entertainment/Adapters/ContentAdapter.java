package com.example.khaledsabry.entertainment.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Items.Classification;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentHolder>  {
    Classification classification;



    public void setData(Classification classification)
    {
this.classification = classification;
notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_content,parent,false);
        return new ContentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentHolder holder, int position) {
        holder.updateUi(position);
    }

    @Override
    public int getItemCount() {
        if(classification == null)
        return 0;
        return classification.getSearchItems().size();
    }

    class ContentHolder extends RecyclerView.ViewHolder {
        public ContentHolder(View itemView) {
            super(itemView);
        }

        void updateUi(int position)
        {

        }
    }
}
