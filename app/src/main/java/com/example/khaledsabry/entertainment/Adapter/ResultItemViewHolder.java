package com.example.khaledsabry.entertainment.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Fragments.Search.SearchArtistFragment;
import com.example.khaledsabry.entertainment.Fragments.Search.SearchMovieFragment;
import com.example.khaledsabry.entertainment.Fragments.Search.SearchTvFragment;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.SearchItem;
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

    public void updateUi(final SearchItem searchItem)
    {
        if(searchItem.getType().equals("movie"))
        {
            title.setText(searchItem.getMovie().getTitle());
            ImageController.putImageLowQuality(searchItem.getMovie().getPosterImage(),poster);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.searchresultitemid, SearchMovieFragment.newInstance(searchItem.getMovie())).commit();
                }
            });
        }
        else if(searchItem.getType().equals("tv"))
        {
            title.setText(searchItem.getTv().getTitle());
            ImageController.putImageLowQuality(searchItem.getMovie().getPosterImage(),poster);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.searchresultitemid, SearchTvFragment.newInstance(searchItem.getTv())).commit();
                }
            });
        }

        else if(searchItem.getType().equals("person"))
        {
            title.setText(searchItem.getArtist().getName());
            ImageController.putImageLowQuality(searchItem.getArtist().getPosterImage(),poster);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.searchresultitemid, SearchArtistFragment.newInstance(searchItem.getArtist())).commit();
                }
            });
        }

    }
}
