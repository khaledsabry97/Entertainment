package com.example.khaledsabry.entertainment.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Fragments.FullPoster;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 01-Jul-18.
 */

public class PosterAdapter extends RecyclerView.Adapter<PosterViewHolder> {

    public ArrayList<String> imgs = new ArrayList<>();

    public PosterAdapter(ArrayList<String> imgs) {
        this.imgs = imgs;
    }

    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.poster_cardview, parent, false);

        return new PosterViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        final String posterUrl = imgs.get(position);
        holder.updateUi(posterUrl);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getActivity().loadFragment(R.id.mainContainer, FullPoster.newInstance(posterUrl));
            }
        });

    }

    @Override
    public int getItemCount() {
        return imgs.size();
    }
}
