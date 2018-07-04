package com.example.khaledsabry.entertainment.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;

/**
 * Created by KhALeD SaBrY on 01-Jul-18.
 */

public class PosterViewHolder extends RecyclerView.ViewHolder {
    ImageView posterImg;

    public PosterViewHolder(View itemView) {
        super(itemView);
        posterImg = itemView.findViewById(R.id.posterimgid);
    }


    public void updateUi(String posterUrl) {
        ImageController.putImageMidQuality(posterUrl, posterImg);
    }
}
