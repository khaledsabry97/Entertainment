package com.example.khaledsabry.entertainment.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Fragments.Artist.ArtistNavigationFragment;
import com.example.khaledsabry.entertainment.Fragments.MovieView.MovieNavigationFragment;
import com.example.khaledsabry.entertainment.Fragments.Tv.TvNavigationFragment;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieDataSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.SearchItem;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 22-Jul-18.
 */

public class ClassificationAdapter extends RecyclerView.Adapter<ClassificationAdapter.ClassificationViewHolder> {

    ArrayList<SearchItem> searchItems = new ArrayList<>();

    public ClassificationAdapter(ArrayList<SearchItem> searchItems) {
        this.searchItems = searchItems;
    }

    @NonNull
    @Override
    public ClassificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(MainActivity.getActivity()).inflate(R.layout.cardview_classificationitem, parent, false);
        return new ClassificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassificationViewHolder holder, int position) {
        SearchItem searchItem = searchItems.get(position);
        holder.updateUi(searchItem);


    }

    @Override
    public int getItemCount() {
        return searchItems.size();
    }

    class ClassificationViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;

        View view;

        public ClassificationViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.posterrelativelayout);
            title = itemView.findViewById(R.id.title);
            view = itemView;
        }


        void updateUi(final SearchItem searchItem) {
            if (searchItem.getTypeString().equals("movie")) {
                ImageController.putImageLowQuality(searchItem.getMovie().getPosterImage(), image);
                this.title.setText(searchItem.getMovie().getTitle());
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, MovieNavigationFragment.newInstance(searchItem.getMovie().getMovieId(), true)).addToBackStack(null).commit();
                    }
                });
            } else if (searchItem.getTypeString().equals("tv")) {
                ImageController.putImageLowQuality(searchItem.getTv().getPosterImage(), image);
                this.title.setText(searchItem.getTv().getTitle());
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, TvNavigationFragment.newInstance(searchItem.getTv().getId(), true)).addToBackStack(null).commit();
                    }
                });

            } else if (searchItem.getTypeString().equals("artist")) {
                ImageController.putImageLowQuality(searchItem.getArtist().getPosterImage(), image);
                this.title.setText(searchItem.getArtist().getName());

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, ArtistNavigationFragment.newInstance(searchItem.getArtist().getId(), true)).addToBackStack(null).commit();
                    }
                });
            } else if (searchItem.getTypeString().equals("mojomovie")) {
                TmdbController tmdbController = new TmdbController();
                if (searchItem.getMovie().getPosterImage() != null)
                    ImageController.putImageLowQuality(searchItem.getMovie().getPosterImage(), image);
                else {
                    image.setImageBitmap(null);
                    tmdbController.getSearchOneMovie(searchItem.getMovie().getTitle(), searchItem.getMovie().getYear(), new OnMovieDataSuccess() {
                        @Override
                        public void onSuccess(Movie movie) {
                            image.setImageBitmap(null);
                            searchItem.getMovie().setPosterImage(movie.getPosterImage());
                            ImageController.putImageLowQuality(movie.getPosterImage(), image);
                            searchItem.getMovie().setMovieId(movie.getMovieId());
                        }
                    });
                }


                this.title.setText(searchItem.getMovie().getTitle());
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, MovieNavigationFragment.newInstance(searchItem.getMovie().getMovieId(), true)).addToBackStack(null).commit();
                    }

                });

            }
        }
    }
}
