package com.example.khaledsabry.entertainment.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Items.Artist;
import com.example.khaledsabry.entertainment.Items.Character;
import com.example.khaledsabry.entertainment.Items.Crew;
import com.example.khaledsabry.entertainment.R;

/**
 * Created by KhALeD SaBrY on 29-Jun-18.
 */

//view holder card for the crew
public class CrewViewHolder extends RecyclerView.ViewHolder {
    ImageView posterImage;
    TextView jobName;
    TextView crewName;

    public CrewViewHolder(View itemView) {
        super(itemView);
        posterImage = itemView.findViewById(R.id.profileImageid);
        jobName = itemView.findViewById(R.id.characterID);
        crewName = itemView.findViewById(R.id.actorNamID);
    }

    public void updateUi(final Artist crew) {
        crewName.setText(crew.getName());
        jobName.setText(crew.getJob());
        ImageController.putImageLowQuality(crew.getPosterImage(), posterImage);
    }
}
