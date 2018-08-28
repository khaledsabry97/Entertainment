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
    public void selectFirstItem() {
        if (searchItems.size() <= 0)
            return;
        Fragment fragment = null;
        switch (searchItems.get(0).getType()) {
            case tv:
                fragment = TvPreviewFragment.newInstance(searchItems.get(0).getTv());
                break;
            case movie:
                fragment = MoviePreviewFragment.newInstance(searchItems.get(0).getMovie());
                break;
            case artist:
                fragment = ArtistPreviewFragment.newInstance(searchItems.get(0).getArtist());
                break;
            default:
        }

        MainActivity.loadFragmentNoReturn(R.id.searchresultitemid, fragment);

    }


    class ResultItemViewHolder extends RecyclerView.ViewHolder {
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
            setDate(movie.getReleaseDate());

            setTitle(movie.getTitle());

            ImageController.putImageLowQuality(movie.getPosterImage(), poster);

            type.setText("Movie");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.loadFragmentNoReturn(R.id.searchresultitemid, MoviePreviewFragment.newInstance(movie));
                }
            });
        }

        void setTv(final Tv tv) {
            type.setText("Tv");
            setDate(tv.getFirstAirDate());

            setTitle(tv.getTitle());

            ImageController.putImageLowQuality(tv.getPosterImage(), poster);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.loadFragmentNoReturn(R.id.searchresultitemid, TvPreviewFragment.newInstance(tv));
                }
            });
        }


        void setArtist(final Artist artist) {
            setTitle(artist.getName());
            ImageController.putImageLowQuality(artist.getPosterImage(), poster);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    type.setText("Artist");
                    TmdbController tmdbController = new TmdbController();
                    tmdbController.getPersonDetails(artist.getId(), new OnArtistDataSuccess() {
                        @Override
                        public void onSuccess(Artist artist) {
                            MainActivity.loadFragmentNoReturn(R.id.searchresultitemid, ArtistPreviewFragment.newInstance(artist));
                        }
                    });
                }
            });
        }


        private void setTitle(String title) {
            this.title.setText(title);

        }


        private void setDate(String date) {
            if(date== null)
                return;
            if (!date.equals("")) {
                date = date.substring(0, 4);
                this.date.setText(date);
            }
        }


    }

}
