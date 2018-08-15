package com.example.khaledsabry.entertainment.Adapter;

import android.media.Image;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Fragments.MovieView.MovieNavigationFragment;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieDataSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;
import com.google.android.youtube.player.YouTubePlayer;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 13-Aug-18.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    ArrayList<Integer> itemsId = new ArrayList<>();
    ArrayList<Integer> items = new ArrayList<>();
    ArrayList<Integer> types = new ArrayList<>();

    private int typeToShow;

    public void setData(ArrayList<Integer> itemsId, ArrayList<Integer> items, ArrayList<Integer> types) {
        this.itemsId = itemsId;
        this.items = items;
        this.types = types;
        notifyDataSetChanged();
    }

    public void setTypeToShow(int typeToShow) {
        this.typeToShow = typeToShow;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_contentdetail, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.updateUi(position);
    }

    @Override
    public int getItemCount() {
        if (items == null)
            return 0;
        return items.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        TmdbController controller;

        TextView title, rate, date;
        ImageView poster;
        ImageView remove;
        View view;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            controller = new TmdbController();
            view = itemView;
            title = itemView.findViewById(R.id.title);
            rate = itemView.findViewById(R.id.rate);
            date = itemView.findViewById(R.id.date);
            poster = itemView.findViewById(R.id.poster);
            remove = itemView.findViewById(R.id.delete);

            remove.setVisibility(View.VISIBLE);


        }

        void load(final int postion, int typeShown) {


        }

        void updateUi(final int postion) {
            view.setVisibility(View.VISIBLE);

            Integer type = Integer.valueOf(types.get(postion) + "");
//here we determine the type we show if the type to show is 0 then show all else show its type
            if (typeToShow != 0 && type != typeToShow) {
                view.setVisibility(View.GONE);
                return;

            }

            switch (type) {
                //movie
                case 1:
                    controller.getMovieGetDetails(Integer.valueOf(items.get(postion) + ""), new OnMovieDataSuccess() {
                        @Override
                        public void onSuccess(final Movie movie) {

                            setObjects(movie.getTitle(), movie.getTmdbRate(), movie.getReleaseDate(), movie.getPosterImage(), movie.getMovieId(), 1, movie.getMovieId());

                        }
                    });
                    break;
//tv
                case 2:

                    break;

//artist
                case 3:

                    break;
                default:

            }


        }


        private void setObjects(String title, float rate, String date, String posterUrl, int removeItemId, final int type, final int movieId) {
            this.title.setText(title);
            this.rate.setText(String.valueOf(rate));
            this.date.setText(date);
            ImageController.putImageMidQuality(posterUrl, poster);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (type == 1)
                        MainActivity.loadFragmentNoReturn(R.id.mainContainer, MovieNavigationFragment.newInstance(movieId, true));
                }
            });


            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //remove the movie,tv or artist from the list

                }
            });

        }


    }
}
