package com.example.khaledsabry.entertainment.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.khaledsabry.entertainment.Activities.CharacterCard;
import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Items.Character;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 23-Jun-18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<CardContent> {
    ArrayList<Character> list = new ArrayList<>();
    @Override
    public CardContent onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardContent = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_cardview,parent,false);

        return new CardContent(cardContent);
    }

    public RecyclerAdapter(ArrayList<Character> list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(final CardContent holder, int position) {
        final Character character = list.get(position);
        holder.updateUi(character);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getActivity().loadFullPosterFragment(character.getArtist().getPosterImage());

            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
