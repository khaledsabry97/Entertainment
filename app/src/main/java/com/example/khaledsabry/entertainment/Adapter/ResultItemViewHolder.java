package com.example.khaledsabry.entertainment.Adapter;

import android.app.Fragment;
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
    TextView type;
    TextView date;
    ImageView poster;


    public ResultItemViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        poster = itemView.findViewById(R.id.posterrelativelayout);
        type = itemView.findViewById(R.id.type);
        date = itemView.findViewById(R.id.date);
    }

    public void updateUi(final SearchItem searchItem) {
        if (searchItem.getType().equals("movie")) {
            setDate(searchItem.getMovie().getReleaseDate());

            setTitle(searchItem.getMovie().getTitle());

            ImageController.putImageLowQuality(searchItem.getMovie().getPosterImage(), poster);

            type.setText("Movie");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadFragment(MoviePreviewFragment.newInstance(searchItem.getMovie()));
                }
            });
        } else if (searchItem.getType().equals("tv")) {
            type.setText("Tv");
            setDate(searchItem.getTv().getFirstAirDate());

            setTitle(searchItem.getTv().getTitle());

            ImageController.putImageLowQuality(searchItem.getTv().getPosterImage(), poster);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadFragment(TvPreviewFragment.newInstance(searchItem.getTv()));
                }
            });
        } else if (searchItem.getType().equals("person")) {

            setTitle(searchItem.getArtist().getName());
            ImageController.putImageLowQuality(searchItem.getArtist().getPosterImage(), poster);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    type.setText("Artist");
                    TmdbController tmdbController = new TmdbController();
                    tmdbController.getPersonDetails(searchItem.getArtist().getId(), new OnArtistDataSuccess() {
                        @Override
                        public void onSuccess(Artist artist) {
                            loadFragment(ArtistPreviewFragment.newInstance(artist));
                        }
                    });
                }
            });
        }

    }


    private void setTitle(String title) {
        this.title.setText(title);

    }


    private void setDate(String date) {
        if (!date.equals("")) {
            date = date.substring(0, 4);
            this.date.setText(date);
        }
    }


    private void loadFragment(android.support.v4.app.Fragment fragment) {
        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.searchresultitemid, fragment).commit();

    }
}
