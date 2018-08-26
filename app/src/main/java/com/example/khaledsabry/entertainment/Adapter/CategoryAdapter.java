package com.example.khaledsabry.entertainment.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.CategoryController;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Fragments.Artist.ArtistNavigationFragment;
import com.example.khaledsabry.entertainment.Fragments.CategoryListFragment;
import com.example.khaledsabry.entertainment.Fragments.MovieView.MovieNavigationFragment;
import com.example.khaledsabry.entertainment.Fragments.Tv.TvNavigationFragment;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieDataSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 13-Aug-18.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    ArrayList<Integer> itemsId = new ArrayList<>();
    ArrayList<Integer> tmdbIds = new ArrayList<>();
    ArrayList<Integer> types = new ArrayList<>();
    CategoryController categoryController = new CategoryController();
    String categoryName;
    int categoryId;

    private int typeToShow;

    /**
     * set the data in the adapter to show items of categories
     *
     * @param itemsId            the ids in the database for each item
     * @param tmdbIds            tmdb id for each movie,tv or artist
     * @param types              1:movie 2:tv 3:artist
     * @param categoryId         the category id that this item in it
     * @param categoryName       to send it the delete item function
     * @param categoryController to call the delete item function
     */
    public void setData(ArrayList<Integer> itemsId, ArrayList<Integer> tmdbIds, ArrayList<Integer> types, int categoryId, String categoryName, CategoryController categoryController) {
        this.itemsId = itemsId;
        this.tmdbIds = tmdbIds;
        this.types = types;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryController = categoryController;

        notifyDataSetChanged();
    }

    /**
     * to show three types of content
     * 0: all
     * 1: movie
     * 2: tv
     * 3: artist
     *
     * @param typeToShow call it from the tab layout
     */
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
        if (tmdbIds == null)
            return 0;
        return tmdbIds.size();
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

        void updateUi(final int postion) {
            view.setVisibility(View.VISIBLE);

            Integer type = Integer.valueOf(types.get(postion) + "");
            final Integer categoryItemId = Integer.valueOf(itemsId.get(postion) + "");
//here we determine the type we show if the type to show is 0 then show all else show its type
            if (typeToShow != 0 && type != typeToShow) {
                view.setVisibility(View.GONE);
                return;

            }

            switch (type) {
                //movie
                case 1:
                    controller.getMovieGetDetails(Integer.valueOf(tmdbIds.get(postion) + ""), new OnMovieDataSuccess() {
                        @Override
                        public void onSuccess(final Movie movie) {

                            setObjects(movie.getTitle(), movie.getTmdbRate(), movie.getReleaseDate(), movie.getPosterImage(), categoryItemId, 1, movie.getMovieId());

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


        private void setObjects(final String title, float rate, String date, String posterUrl, final int removeItemId, final int type, final int contentId) {
            this.title.setText(title);
            this.rate.setText(String.valueOf(rate));
            this.date.setText(date);
            ImageController.putImageMidQuality(posterUrl, poster);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (type == 1)
                        MainActivity.loadFragmentNoReturn(R.id.mainContainer, MovieNavigationFragment.newInstance(contentId, true));
                    else if (type == 2)
                        MainActivity.loadFragmentNoReturn(R.id.mainContainer, TvNavigationFragment.newInstance(contentId, true));
                    else if (type == 3)
                        MainActivity.loadFragmentNoReturn(R.id.mainContainer, ArtistNavigationFragment.newInstance(contentId, true));
                }
            });


            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //remove the movie,tv or artist from the categoryItem
                    CategoryListFragment.deleteCategoryItem(removeItemId, categoryId, categoryName, title, categoryController);
                }
            });

        }


    }
}
