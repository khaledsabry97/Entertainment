package com.example.khaledsabry.entertainment.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Fragments.Search.SearchArtistFragment;
import com.example.khaledsabry.entertainment.Fragments.MovieView.MoviePreviewFragment;
import com.example.khaledsabry.entertainment.Fragments.Tv.TvPreviewFragment;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.SearchItem;
import com.example.khaledsabry.entertainment.Items.Tv;
import com.example.khaledsabry.entertainment.R;

/**
 * Created by KhALeD SaBrY on 05-Jul-18.
 */

public class RoleViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    ImageView poster;

    public RoleViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.titleid);
        poster = itemView.findViewById(R.id.posterid);

    }


    public void updateUi(final SearchItem searchItem) {
        if (searchItem.getType().equals("movie")) {
            if (!searchItem.getMovie().getReleaseDate().equals("")) {
                String date = searchItem.getMovie().getReleaseDate();
                char[] d = new char[4];
                d[0] = date.charAt(0);
                d[1] = date.charAt(1);
                d[2] = date.charAt(2);
                d[3] = date.charAt(3);
                date = String.copyValueOf(d);

                title.setText(searchItem.getMovie().getTitle() + " (" + date + ")");
            } else
                title.setText(searchItem.getMovie().getTitle());
            ImageController.putImageLowQuality(searchItem.getMovie().getPosterImage(), poster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.searchresultitemid, MoviePreviewFragment.newInstance(searchItem.getMovie())).addToBackStack(null).commit();
                }
            });
        } else if (searchItem.getType().equals("tv")) {
           /* title.setText(searchItem.getTv().getTitle());
            ImageController.putImageLowQuality(searchItem.getMovie().getPosterImage(),poster);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.searchresultitemid, SearchTvFragment.newInstance(searchItem.getTv())).commit();
                }
            });*/
        } else if (searchItem.getType().equals("person")) {
            title.setText(searchItem.getArtist().getName());
            ImageController.putImageLowQuality(searchItem.getArtist().getPosterImage(), poster);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.searchresultitemid, SearchArtistFragment.newInstance(searchItem.getArtist())).commit();
                }
            });
        }

    }




    public void updateUi(final Movie movie) {
        if (!movie.getReleaseDate().equals("")) {

            String date = movie.getReleaseDate();
            char[] d = new char[4];
            d[0] = date.charAt(0);
            d[1] = date.charAt(1);
            d[2] = date.charAt(2);
            d[3] = date.charAt(3);
            date = String.copyValueOf(d);

            title.setText(movie.getTitle() + " (" + date + ")");
        } else
            title.setText(movie.getTitle());
        ImageController.putImageLowQuality(movie.getPosterImage(), poster);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.searchresultitemid, MoviePreviewFragment.newInstance(movie)).commit();}
    });
    }

    public void updateUi(final Tv tv) {
        if (!tv.getFirstAirDate().equals("")) {

            String date = tv.getFirstAirDate();
            char[] d = new char[4];
            d[0] = date.charAt(0);
            d[1] = date.charAt(1);
            d[2] = date.charAt(2);
            d[3] = date.charAt(3);
            date = String.copyValueOf(d);

            title.setText(tv.getTitle() + " (" + date + ")");
        } else
            title.setText(tv.getTitle());
        ImageController.putImageLowQuality(tv.getPosterImage(), poster);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.searchresultitemid, TvPreviewFragment.newInstance(tv,null,null,false)).commit();}
        });
    }


}