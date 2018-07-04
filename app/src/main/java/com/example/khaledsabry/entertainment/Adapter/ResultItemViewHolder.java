package com.example.khaledsabry.entertainment.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;

/**
 * Created by KhALeD SaBrY on 04-Jul-18.
 */

public class ResultItemViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    ImageView poster;

    public ResultItemViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.titleId);
        poster = itemView.findViewById(R.id.posterid);
    }

    public void updateUi(Movie movie)
    {
        title.setText(movie.getTitle());
        ImageController.putImageLowQuality(movie.getPosterImage(),poster);
    }
}
