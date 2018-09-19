package com.example.khaledsabry.entertainment.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuHolder> {


    @NonNull
    @Override
    public MenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MenuHolder extends ViewHolder {
        public MenuHolder(View itemView) {
            super(itemView);
        }


        void updateUi()
        {

        }
    }
}
