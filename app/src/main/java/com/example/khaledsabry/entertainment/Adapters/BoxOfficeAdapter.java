package com.example.khaledsabry.entertainment.Adapters;

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
import com.example.khaledsabry.entertainment.Fragments.MovieViews.MovieNavigationFragment;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieDataSuccess;
import com.example.khaledsabry.entertainment.Items.Classification;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.SearchItem;
import com.example.khaledsabry.entertainment.R;

/**
 * Created by KhALeD SaBrY on 23-Jul-18.
 */

public class BoxOfficeAdapter extends RecyclerView.Adapter<BoxOfficeAdapter.BoxOfficeViewHolder> {

    public Classification classification = new Classification();
    int size = 0;

    public void setData(Classification classification) {
        this.classification = classification;
        notifyDataSetChanged();
    }

    public void addData(Classification classification) {
        this.classification.getSearchItems().add(classification.getSearchItems().get(0));
        this.classification.setType(classification.getType());
        // notifyDataSetChanged();
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
        TextView noweeks;
        TextView totalrevenue;
        ImageView poster;
        ConstraintLayout layout;

        public BoxOfficeViewHolder(View itemView) {
            super(itemView);

            position = itemView.findViewById(R.id.position);
            rate = itemView.findViewById(R.id.rate);
            title = itemView.findViewById(R.id.title);
            genres = itemView.findViewById(R.id.genres_id);
            revenue = itemView.findViewById(R.id.revenue);
            date = itemView.findViewById(R.id.date);
            poster = itemView.findViewById(R.id.poster);
            layout = itemView.findViewById(R.id.layout);
            noweeks = itemView.findViewById(R.id.weeks);
            totalrevenue = itemView.findViewById(R.id.totalrevenue);
            //        layout.setVisibility(View.GONE);

        }

        private void updateUi(final Classification classification, final int position) {
            // layout.setVisibility(View.GONE);

            final SearchItem searchItem = classification.getSearchItems().get(position);
            switch (classification.getType()) {
                case boxoffice: {
                    //get the movie
                    final Movie movie = searchItem.getMovie();
                    TmdbController tmdbController = new TmdbController();
                    //then check if this movie has a poster image or not
                    //null : mean that you didn't get any link to the tmdb database
                    // not null: means that you already have the link so don't enter again and search for the item on the tmdb database
                    if (searchItem.getMovie().getPosterImage() != null) {
                        MainActivity.getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //position + 1 : because the postion starts from 0
                                setBoxOffice(movie, position + 1, 2);
                            }
                        });
                    } else {
                        MainActivity.getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//phase 1: means that you got the data from boxofficemijo but you didn't get the data from tmdb
//phase 2: means that you get the data from both boxofficemijo and tmdb
                                setBoxOffice(movie, position + 1, 1);

                            }
                        });
                        //search in the tmdb on that movie by name
                        tmdbController.getSearchOneMovie(searchItem.getMovie().getTitle(), searchItem.getMovie().getYear(), new OnMovieDataSuccess() {
                            @Override
                            public void onSuccess(Movie movie) {
                                searchItem.getMovie().setPosterImage(movie.getPosterImage());
                                searchItem.getMovie().setMovieId(movie.getId());
                                searchItem.getMovie().setTmdbRate(movie.getTmdbRate());
                                searchItem.getMovie().setReleaseDate(movie.getReleaseDate());
                                // searchItem.getMovie().setGenres(movie.getGenres());

                                movie = searchItem.getMovie();
                                final Movie finalMovie = movie;
                                MainActivity.getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        setDailyBoxOffice(finalMovie, position + 1, 2);
                                    }
                                });
                            }
                        });
                    }
                    break;
                }

                case dailyboxoffice: {
                    //get the movie
                    final Movie movie = searchItem.getMovie();
                    TmdbController tmdbController = new TmdbController();
                    //then check if this movie has a poster image or not
                    //null : mean that you didn't get any link to the tmdb database
                    // not null: means that you already have the link so don't enter again and search for the item on the tmdb database
                    if (searchItem.getMovie().getPosterImage() != null) {
                        MainActivity.getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //position + 1 : because the postion starts from 0
                                setDailyBoxOffice(movie, position + 1, 2);
                            }
                        });
                    } else {
                        MainActivity.getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //phase 1: means that you got the data from boxofficemijo but you didn't get the data from tmdb
                                //phase 2: means that you get the data from both boxofficemijo and tmdb
                                setDailyBoxOffice(movie, position + 1, 1);

                            }
                        });
                        //search in the tmdb on that movie by name
                        tmdbController.getSearchOneMovie(searchItem.getMovie().getTitle(), searchItem.getMovie().getYear(), new OnMovieDataSuccess() {
                            @Override
                            public void onSuccess(Movie movie) {
                                searchItem.getMovie().setPosterImage(movie.getPosterImage());
                                searchItem.getMovie().setMovieId(movie.getId());
                                searchItem.getMovie().setTmdbRate(movie.getTmdbRate());
                                searchItem.getMovie().setReleaseDate(movie.getReleaseDate());
                                // searchItem.getMovie().setGenres(movie.getGenres());

                                movie = searchItem.getMovie();
                                final Movie finalMovie = movie;
                                MainActivity.getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        setDailyBoxOffice(finalMovie, position + 1, 2);
                                    }
                                });
                            }
                        });
                    }

                    break;
                }
                case imdbTop250: {
                    MainActivity.getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setTop250(searchItem.getMovie(), position + 1);

                        }
                    });


                    break;
                }
                default:


            }
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {


                }

            });
        }

        private void setTop250(Movie movie, int postion) {
            layout.setVisibility(View.VISIBLE);
            title.setText(movie.getTitle());
            rate.setText(movie.getImdbRate() + "");
            position.setText(postion + "");
            ImageController.putImageMidQuality(movie.getPosterImage(), poster);


        }


        void setBoxOffice(final Movie movie, int position, int phase) {
            layout.setVisibility(View.VISIBLE);
            poster.setImageBitmap(null);

            if (phase == 1) {
                title.setText(movie.getTitle());
                noweeks.setText("No Of Weeks In Box Office : " + movie.getNoWeeksInBoxOffice());
                totalrevenue.setText("Total Revenue : " + movie.getTotalRevenue());
                noweeks.setVisibility(View.VISIBLE);
                this.position.setText(String.valueOf(position));
                revenue.setText("Weekend Revenue : " + movie.getRevneue(false));

            } else if (phase == 2) {
                title.setText(movie.getTitle());
                noweeks.setText("No Of Weeks In Box Office : " + movie.getNoWeeksInBoxOffice());
                totalrevenue.setText("Total Revenue : " + movie.getTotalRevenue());
                noweeks.setVisibility(View.VISIBLE);
                this.position.setText(String.valueOf(position));
                revenue.setText("Weekend Revenue : " + movie.getRevneue(false));
                ImageController.putImageLowQuality(movie.getPosterImage(), poster);

                rate.setText(String.valueOf(movie.getTmdbRate()));
                date.setText(movie.getReleaseDate());

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainActivity.loadFragmentNoReturn(R.id.mainContainer, MovieNavigationFragment.newInstance(movie.getId(), 0));
                    }
                });

            }
        }


        void setDailyBoxOffice(final Movie movie, int position, int phase) {
            layout.setVisibility(View.VISIBLE);
            poster.setImageBitmap(null);

            if (phase == 1) {
                title.setText(movie.getTitle());
                noweeks.setVisibility(View.INVISIBLE);
                totalrevenue.setVisibility(View.INVISIBLE);
                noweeks.setVisibility(View.INVISIBLE);
                this.position.setText(String.valueOf(position));
                revenue.setText("Domestic Revenue : " + movie.getDomesticBudget());

            } else if (phase == 2) {

                ImageController.putImageLowQuality(movie.getPosterImage(), poster);
                title.setText(movie.getTitle());
                this.position.setText(String.valueOf(position));
                rate.setText(String.valueOf(movie.getTmdbRate()));
                date.setText(movie.getReleaseDate());

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainActivity.loadFragmentNoReturn(R.id.mainContainer, MovieNavigationFragment.newInstance(movie.getId(), 0));
                    }
                });

            }
        }

    }
}

