package com.example.khaledsabry.entertainment.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Fragments.ArtistView.ArtistNavigationFragment;
import com.example.khaledsabry.entertainment.Items.Character;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 23-Jun-18.
 */

//the recycler adapter for the actors
public class CastRecyclerAdapter extends RecyclerView.Adapter<CastRecyclerAdapter.CastViewHolder> {
    //character is an artist but with the character name and the movie/tv he showed up in
    ArrayList<Character> list;

    public CastRecyclerAdapter(ArrayList<Character> list) {
        this.list = list;
    }

    @Override
    public CastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardContent = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_character, parent, false);
        return new CastViewHolder(cardContent);
    }


    @Override
    public void onBindViewHolder(final CastViewHolder holder, final int position) {
        final Character character = list.get(position);
        holder.updateUi(character);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.loadFragmentNoReturn(R.id.mainContainer, ArtistNavigationFragment.newInstance(list.get(position).getArtist().getId(),true));


            }
        });


    }

    @Override
    public int getItemCount() {
        if(list == null)
            return 0;
        return list.size();
    }

    class CastViewHolder extends RecyclerView.ViewHolder {

        ImageView posterImage;
        TextView characterName;
        TextView actorName;

        public CastViewHolder(View itemView) {
            super(itemView);

            posterImage = itemView.findViewById(R.id.profile_image);
            characterName = itemView.findViewById(R.id.character_name);
            actorName = itemView.findViewById(R.id.artist_name);
        }

        public void updateUi(final Character character) {
            actorName.setText(character.getArtist().getName());
            characterName.setText(character.getCharacterName());
            ImageController.putImageLowQuality(character.getArtist().getPosterImage(), posterImage);
        }

    }


}
