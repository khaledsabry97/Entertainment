package com.example.khaledsabry.entertainment.Adapter;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieDataSuccess;
import com.example.khaledsabry.entertainment.Items.Classification;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.SearchItem;
import com.example.khaledsabry.entertainment.R;

/**
 * Created by KhALeD SaBrY on 23-Jul-18.
 */

public class BoxOfficeAdapter extends RecyclerView.Adapter<BoxOfficeAdapter.BoxOfficeViewHolder> {

    Classification classification = new Classification();

    public BoxOfficeAdapter(Classification classification) {
        this.classification = classification;
    }

    @NonNull
    @Override
    public BoxOfficeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_contentdetail, parent, false);
        return new BoxOfficeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BoxOfficeViewHolder holder, int position) {
        holder.updateUi(classification, position);


    }

    @Override
    public int getItemCount() {
        return classification.getSearchItems().size();
    }

    class BoxOfficeViewHolder extends RecyclerView.ViewHolder {
        TextView position;
        TextView rate;
        TextView title;
        TextView genres;
        TextView revenue;
        TextView date;
        ImageView poster;
        ConstraintLayout layout;

        public BoxOfficeViewHolder(View itemView) {
            super(itemView);

            position = itemView.findViewById(R.id.position);
            rate = itemView.findViewById(R.id.rate);
            title = itemView.findViewById(R.id.title);
            genres = itemView.findViewById(R.id.genre);
            revenue = itemView.findViewById(R.id.revenue);
            date = itemView.findViewById(R.id.date);
            poster = itemView.findViewById(R.id.poster);
            layout = itemView.findViewById(R.id.layout);
        }

        private void updateUi(final Classification classification, final int position) {
            layout.setVisibility(View.GONE);

            final SearchItem searchItem = classification.getSearchItems().get(position);

            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    if (classification.getType() == Classification.type.boxoffice) {

                        final Movie movie = searchItem.getMovie();
                        TmdbController tmdbController = new TmdbController();
                        if (searchItem.getMovie().getPosterImage() != null) {
                            MainActivity.getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    setBoxOffice(movie, position + 1);
                                }
                            });
                        } else {
                            MainActivity.getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    poster.setImageBitmap(null);
                                }
                            });
                            tmdbController.getSearchOneMovie(searchItem.getMovie().getTitle(), searchItem.getMovie().getYear(), new OnMovieDataSuccess() {
                                @Override
                                public void onSuccess(Movie movie) {
                                    searchItem.getMovie().setPosterImage(movie.getPosterImage());
                                    searchItem.getMovie().setMovieId(movie.getMovieId());
                                    searchItem.getMovie().setTmdbRate(movie.getTmdbRate());
                                    searchItem.getMovie().setReleaseDate(movie.getReleaseDate());
                                   // searchItem.getMovie().setGenres(movie.getGenres());

                                    movie = searchItem.getMovie();
                                    final Movie finalMovie = movie;
                                    MainActivity.getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {

                                            setBoxOffice(finalMovie, position + 1);
                                        }
                                    });
                                }
                            });
                        }
                    }





                    //else if


                }
            });


        }


        void setBoxOffice(Movie movie, int position) {
            ImageController.putImageLowQuality(movie.getPosterImage(), poster);

            this.position.setText(String.valueOf(position));
            rate.setText(String.valueOf(movie.getTmdbRate()));
            title.setText(movie.getTitle());
            revenue.setText(movie.getDomesticBudget());
            date.setText(movie.getYear());

            layout.setVisibility(View.VISIBLE);
        }
    }

}

