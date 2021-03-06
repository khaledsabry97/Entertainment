package com.example.khaledsabry.entertainment.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Fragments.MovieViews.MoviePreviewFragment;
import com.example.khaledsabry.entertainment.Fragments.TvViews.TvPreviewFragment;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.Tv;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 05-Jul-18.
 */

public class RoleRecyclarAdapter extends RecyclerView.Adapter<RoleRecyclarAdapter.RoleViewHolder> {
    int type; //type = 0 --> movies          type = 1 --> tv
    ArrayList<Movie> movies;
    ArrayList<Tv> tvs;

    /**
     * set the data to recycler view and update info
     * @param type /type = 0 --> movies          type = 1 --> tv
     * @param movies movies list
     * @param tvs tvs list
     */
    public void setdata(int type, ArrayList<Movie> movies, ArrayList<Tv> tvs) {
        this.type = type;
        this.movies = movies;
        this.tvs = tvs;
        setFirstIndex();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RoleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_resultitem, parent, false);
        return new RoleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoleViewHolder holder, int position) {
        if (type == 0)
            holder.updateUi(movies.get(position));
        else if (type == 1)
            holder.updateUi(tvs.get(position));


    }

    /**
     * select the first index
     */
    public void setFirstIndex() {
        if (type == 0)
            MainActivity.loadFragmentNoReturn(R.id.search_result_frame_id, MoviePreviewFragment.newInstance(movies.get(0)));
        else
            MainActivity.loadFragmentNoReturn(R.id.search_result_frame_id, TvPreviewFragment.newInstance(tvs.get(1)));

    }
    @Override
    public int getItemCount() {
        if (type == 0)
            return movies.size();
        else if (type == 1)
            return tvs.size();
        return 0;
    }


    public class RoleViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView type;
        TextView date;
        ImageView poster;

        public RoleViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            poster = itemView.findViewById(R.id.backdrop_id);
            type = itemView.findViewById(R.id.type);
            date = itemView.findViewById(R.id.date);
            type.setVisibility(View.GONE);

        }

        /**
         * update for movies ui
         * @param movie movie object
         */
        public void updateUi(final Movie movie) {
            if (movie.getReleaseDate() != null)
                if (!movie.getReleaseDate().equals("")) {
                    String date = movie.getReleaseDate();
                    date = date.substring(0, 4);
                    this.date.setText(date);
                }
            title.setText(movie.getTitle());
            ImageController.putImageLowQuality(movie.getPosterImage(), poster);

            type.setText("Movie");


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.loadFragmentNoReturn(R.id.search_result_frame_id, MoviePreviewFragment.newInstance(movie));
                }
            });
        }

        /**
         * update for tv ui
         * @param tv tv object
         */
        public void updateUi(final Tv tv) {
            if (tv.getFirstAirDate() != null)
                if (!tv.getFirstAirDate().equals("")) {
                    String date = tv.getFirstAirDate();
                    date = date.substring(0, 4);
                    this.date.setText(date);
                }
            title.setText(tv.getTitle());

            type.setText("Tv");
            ImageController.putImageLowQuality(tv.getPosterImage(), poster);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.loadFragmentNoReturn(R.id.search_result_frame_id, TvPreviewFragment.newInstance(tv));
                }
            });
        }




    }

}
