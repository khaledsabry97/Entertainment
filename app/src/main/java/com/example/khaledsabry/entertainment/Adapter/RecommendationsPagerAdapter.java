package com.example.khaledsabry.entertainment.Adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Fragments.MovieView.MovieNavigationFragment;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by KhALeD SaBrY on 02-Jul-18.
 */

public class RecommendationsPagerAdapter extends PagerAdapter {
    ImageView backdrop;
    TextView title,genres,overview;
    CircleImageView poster;
    ArrayList<Movie> movies;

    public RecommendationsPagerAdapter(ArrayList<Movie> movies) {
        super();
        this.movies = movies;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.recommendations, container, false);
        final Movie movie = movies.get(position);

poster = view.findViewById(R.id.poster_image);
backdrop = view.findViewById(R.id.backdrop_image);
title = view.findViewById(R.id.title);
genres = view.findViewById(R.id.genresid);
overview = view.findViewById(R.id.overview_id);

ImageController.putImageLowQuality(movie.getPosterImage(),poster);
ImageController.putImageMidQuality(movie.getBackDropPoster(),backdrop);

title.setText(movie.getTitle());
genres.setText(movie.getGenreList());
overview.setText(movie.getOverView());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.loadFragmentNoReturn(R.id.mainContainer, MovieNavigationFragment.newInstance(movie.getMovieId(), 0));
            }
        });

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        if (movies.size() > 20)
            return 20;
        return movies.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
}
