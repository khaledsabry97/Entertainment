package com.example.khaledsabry.entertainment.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Fragments.MovieView.CastFragment;
import com.example.khaledsabry.entertainment.Fragments.MovieView.CrewFragment;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieDataSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieMoreDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieMoreDetailsFragment extends Fragment {
    static int movieId;
    static int currentId = -1;
    static Movie movie;
    Button actorButton;
    Button crewButton;

    TmdbController tmdbController = new TmdbController();

    public static MovieMoreDetailsFragment newInstance(int movieId) {
        MovieMoreDetailsFragment fragment = new MovieMoreDetailsFragment();
        MovieMoreDetailsFragment.movieId = movieId;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_movie_more_details, container, false);

        actorButton = v.findViewById(R.id.button_actors_id);
        crewButton = v.findViewById(R.id.button_crew_id);


        getMovieDetails();


        actorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadActorFragment();

            }
        });

        crewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCrewFragment();
            }
        });


        return v;
    }

    private void getMovieDetails() {
        if (movieId != currentId)
            tmdbController.getMovieVideosCreditsCategories(
                    movieId, new OnMovieDataSuccess() {
                        @Override
                        public void onSuccess(Movie movie) {
                            currentId = movieId;
                            MovieMoreDetailsFragment.movie = movie;
                            setObjects();
                        }
                    }
            );
        else
            setObjects();
    }

    private void setObjects() {
        loadTrailersFragment();
        loadCategoriesFragment();
        loadActorFragment();
    }

    private void loadActorFragment() {
        CastFragment castFragment = CastFragment.newInstance(movie.getCharacters());
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.actors_crews_id, castFragment).commit();
    }

    private void loadCrewFragment() {
        CrewFragment crewFragment = CrewFragment.newInstance(movie.getCrews());
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.actors_crews_id, crewFragment).commit();
    }


    private void loadCategoriesFragment() {

    }

    private void loadTrailersFragment() {
        //       getActivity().getSupportFragmentManager().beginTransaction().add(R.id.trailerid, YoutubeFragment.newInstance(movie)).commit();

    }

}
