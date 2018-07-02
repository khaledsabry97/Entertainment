package com.example.khaledsabry.entertainment.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Connection.TmdbType;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Controllers.MovieController;
import com.example.khaledsabry.entertainment.Fragments.DetailFragment;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieDataSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 02-Jul-18.
 */

public class RecommendationsPagerAdapter extends PagerAdapter {
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
        ImageView poster = view.findViewById(R.id.posterid);
        TextView rate = view.findViewById(R.id.rateid);
        TextView title = view.findViewById(R.id.titleid);

        ImageController.putImageHighQuality(movie.getPosterImage(), poster);
        rate.setText(movie.getTmdbRate() + "");
        title.setText(movie.getTitle());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TmdbType tmdbType = new TmdbType();
                tmdbType.getMovieGetDetails(movie.getMovieId(), new OnMovieDataSuccess() {
                            @Override
                            public void onSuccess(Movie movie) {
                                MainActivity.getActivity().loadFragment(R.id.mainContainer, DetailFragment.newInstance(movie, true));

                            }
                        });
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
