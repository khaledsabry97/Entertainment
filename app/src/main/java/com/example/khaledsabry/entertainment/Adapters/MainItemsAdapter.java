package com.example.khaledsabry.entertainment.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Items.Classification;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

public class MainItemsAdapter extends RecyclerView.Adapter<MainItemsAdapter.MainItemsViewHolder> {
ArrayList<Classification> classifications;

    public MainItemsAdapter() {
    }

    public void setClassifications(ArrayList<Classification> classifications)
    {
this.classifications = classifications;
notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MainItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_main_items,parent,false);
        return new MainItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainItemsViewHolder holder, int position) {
        holder.updateUi(classifications.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        if(classifications == null)
            return 0;
        return classifications.size();
    }

    class MainItemsViewHolder extends RecyclerView.ViewHolder{

TextView title;
        public MainItemsViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_id);
        }

        void updateUi(String title)
        {
            this.title.setText(title);
        }
    }
}
