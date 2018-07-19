package com.example.khaledsabry.entertainment.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Fragments.Artist.ArtistPreviewFragment;
import com.example.khaledsabry.entertainment.Fragments.MovieView.MoviePreviewFragment;
import com.example.khaledsabry.entertainment.Fragments.Tv.TvPreviewFragment;
import com.example.khaledsabry.entertainment.Interfaces.OnArtistDataSuccess;
import com.example.khaledsabry.entertainment.Items.Artist;
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
        title = itemView.findViewById(R.id.titleid);
        poster = itemView.findViewById(R.id.posterid);
    }

    public void updateUi(final SearchItem searchItem)
    {
        if(searchItem.getType().equals("movie"))
        {
            if(!searchItem.getMovie().getReleaseDate().equals("")) {
                String date = searchItem.getMovie().getReleaseDate();
                char[] d = new char[4];
                d[0] = date.charAt(0);
                d[1] = date.charAt(1);
                d[2] = date.charAt(2);
                d[3] = date.charAt(3);
                date = String.copyValueOf(d);

                title.setText(searchItem.getMovie().getTitle() + " (" + date + ")");
            }
            else
                title.setText(searchItem.getMovie().getTitle());
            ImageController.putImageLowQuality(searchItem.getMovie().getPosterImage(),poster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.searchresultitemid, MoviePreviewFragment.newInstance(searchItem.getMovie())).addToBackStack(null).commit();
                }
            });
        }
        else if(searchItem.getType().equals("tv"))
        {
           title.setText(searchItem.getTv().getTitle());
            ImageController.putImageLowQuality(searchItem.getTv().getPosterImage(),poster);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.searchresultitemid, TvPreviewFragment.newInstance(searchItem.getTv())).commit();
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


                    TmdbController tmdbController = new TmdbController();
                    tmdbController.getPersonDetails(searchItem.getArtist().getId(), new OnArtistDataSuccess() {
                        @Override
                        public void onSuccess(Artist artist) {
                            MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.searchresultitemid, ArtistPreviewFragment.newInstance(artist)).commit();
                        }
                    });
                }
            });
        }

    }
}
