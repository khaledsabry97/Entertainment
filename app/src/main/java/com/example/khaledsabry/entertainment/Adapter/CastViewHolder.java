package com.example.khaledsabry.entertainment.Adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Interfaces.OnImageConvertedSuccess;
import com.example.khaledsabry.entertainment.Items.Character;
import com.example.khaledsabry.entertainment.R;


/**
 * Created by KhALeD SaBrY on 23-Jun-18.
 */

public class CastViewHolder extends RecyclerView.ViewHolder {

    ImageView posterImage;
    TextView characterName;
    TextView actorName;

    public CastViewHolder(View itemView) {
        super(itemView);

        posterImage = itemView.findViewById(R.id.profileImageid);
        characterName = itemView.findViewById(R.id.characterID);
        actorName = itemView.findViewById(R.id.actorNamID);
    }
    public void updateUi(final Character character) {
        actorName.setText("Actor Name : "+character.getArtist().getName());
        characterName.setText(character.getCharacterName());
        ImageController.putImageLowQuality(character.getArtist().getPosterImage(), posterImage);
    }

}
