package com.example.khaledsabry.entertainment.Fragments.MovieViews;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;


public class MoviePreviewFragment extends Fragment {
    static Movie movie;
    ImageView backdrop;
    TextView title;
    TextView rate;
    TextView overView;
    TextView releaseDate;
    TextView genres;
View view;
    public static MoviePreviewFragment newInstance(Movie movie) {
        MoviePreviewFragment fragment = new MoviePreviewFragment();
        MoviePreviewFragment.movie = movie;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_movie_preview, container, false);

        backdrop = view.findViewById(R.id.backdrop_id);
        title = view.findViewById(R.id.title_id);
        rate = view.findViewById(R.id.rate_id);
        overView = view.findViewById(R.id.overview_id);
        releaseDate = view.findViewById(R.id.release_data_id);
        genres = view.findViewById(R.id.genres_id);

        setObjects();


        return view;
    }

    /**
     * set the objects on the ui fragment
     */
    private void setObjects() {
        if (movie == null)
            return;
        title.setText(movie.getTitle());
        rate.setText(movie.getTmdbRate() + "/10");
        overView.setText(movie.getOverView());
        releaseDate.setText("Release Date : " + movie.getReleaseDate());
        genres.setText(movie.getGenreList());

        ImageController.putImageMidQuality(movie.getBackDropPoster(), backdrop);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.loadFragmentNoReturn(R.id.mainContainer,MovieNavigationFragment.newInstance(movie.getId(),0));
            }
        });


        adjustView(title);
        adjustView(rate);
        adjustView(releaseDate);
        adjustView(overView);
        adjustView(genres);
    }

    /**
     * invisible the ui view if there is no text in it
     * @param textView the ui element
     */
    void adjustView(TextView textView)
    {
        if(textView.getText().toString().equals(""))
            textView.setVisibility(View.INVISIBLE);
    }
}
