package com.example.khaledsabry.entertainment.Adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.SearchItem;
import com.example.khaledsabry.entertainment.Items.Tv;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 04-Jul-18.
 */

public class ResultSearchItemsAdapter extends RecyclerView.Adapter<ResultSearchItemsAdapter.ResultItemViewHolder> {

    ArrayList<SearchItem> searchItems = new ArrayList<>();
    ;

    public ResultSearchItemsAdapter(ArrayList<SearchItem> movies) {
        this.searchItems = movies;
    }

    @NonNull
    @Override
    public ResultItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_resultitem, parent, false);
        return new ResultItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ResultItemViewHolder holder, int position) {
        final SearchItem searchItem = searchItems.get(position);
        holder.updateUi(searchItem);


    }

    @Override
    public int getItemCount() {
        if (searchItems == null)
            return 0;
        return searchItems.size();
    }

    /**
     * show the first result on the preview screen
     */
  /*  public void selectFirstItem() {
        if (searchItems.size() <= 0)
            return;
        Fragment fragment = null;
        switch (searchItems.get(0).getType()) {
            case tv:
                break;
            case movie:
                fragment = MoviePreviewFragment.newInstance(searchItems.get(0).getMovie());
                break;
            case artist:
                fragment = ArtistPreviewFragment.newInstance(searchItems.get(0).getArtist());
                break;
            default:
        }

        MainActivity.loadFragmentNoReturn(R.id.search_result_frame_id, fragment);

    }

*/


    class ResultItemViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView type;
        TextView date;
        ImageView poster;
        View view;

        public ResultItemViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            poster = itemView.findViewById(R.id.backdrop_id);
            type = itemView.findViewById(R.id.type);
            date = itemView.findViewById(R.id.date);
            view = itemView;
        }

        public void updateUi(final SearchItem searchItem) {
            if (itemView == null)
                return;
            switch (searchItem.getType()) {
                case movie:
                    setMovie(searchItem.getMovie());
                    break;
                case tv:
                    setTv(searchItem.getTv());
                    break;
                case artist:
                    setArtist(searchItem.getArtist());
                    break;
                default:
            }


        }

        void setMovie(final Movie movie) {
            //to select the first shown movie
            if(movie.equals(searchItems.get(0).getMovie()))
                MainActivity.loadFragmentNoReturn(R.id.search_result_frame_id, MoviePreviewFragment.newInstance(movie));

            type.setText("Movie");
            setDate(movie.getReleaseDate());
            setTitle(movie.getTitle());
            ImageController.putImageLowQuality(movie.getPosterImage(), poster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.loadFragmentNoReturn(R.id.search_result_frame_id, MoviePreviewFragment.newInstance(movie));
                }
            });
        }

        void setTv(final Tv tv) {
            //to select the first shown tv
          if(tv.equals(searchItems.get(0).getTv()))
                MainActivity.loadFragmentNoReturn(R.id.search_result_frame_id, TvPreviewFragment.newInstance(tv));

            type.setText("Tv");
            setDate(tv.getFirstAirDate());
            setTitle(tv.getTitle());
            ImageController.putImageLowQuality(tv.getPosterImage(), poster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.loadFragmentNoReturn(R.id.search_result_frame_id, TvPreviewFragment.newInstance(tv));
                }
            });
        }


        void setArtist(final Artist artist) {
            TmdbController tmdbController = new TmdbController();

            //to select the first artist
            if(artist.equals(searchItems.get(0).getArtist()))
            {
                // the problem here that the search api doesn't return the required info that i need
                // so i call the api again to get the full details about the artist
                MainActivity.loadFragmentNoReturn(R.id.search_result_frame_id, ArtistPreviewFragment.newInstance(artist));

                final View view = itemView;

                tmdbController.getPersonDetails(artist.getId(), new OnArtistDataSuccess() {
                    @Override
                    public void onSuccess(final Artist artist) {
                        MainActivity.loadFragmentNoReturn(R.id.search_result_frame_id, ArtistPreviewFragment.newInstance(artist));
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                MainActivity.loadFragmentNoReturn(R.id.search_result_frame_id, ArtistPreviewFragment.newInstance(artist));

                            }
                        });

                    }
                });
            }
            else
            {
                final View view = itemView;
                tmdbController.getPersonDetails(artist.getId(), new OnArtistDataSuccess() {
                    @Override
                    public void onSuccess(final Artist artist) {
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                MainActivity.loadFragmentNoReturn(R.id.search_result_frame_id, ArtistPreviewFragment.newInstance(artist));

                            }
                        });
                    }
                });

            }
            type.setText("Artist");

            setTitle(artist.getName());
            ImageController.putImageLowQuality(artist.getPosterImage(), poster);


        }

        /**
         * set the title of the search result item
         *
         * @param title
         */
        private void setTitle(String title) {
            this.title.setText(title);

        }


        /**
         * set the date for movies/tv
         *
         * @param date the airdate or release date
         */
        private void setDate(String date) {
            if (date == null)
                return;
            if (!date.equals("")) {
                date = date.substring(0, 4);
                this.date.setText(date);
            }
        }


    }

}
