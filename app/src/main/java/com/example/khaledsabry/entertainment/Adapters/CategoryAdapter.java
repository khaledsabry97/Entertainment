package com.example.khaledsabry.entertainment.Adapters;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
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
import com.example.khaledsabry.entertainment.Fragments.ArtistView.ArtistNavigationFragment;
import com.example.khaledsabry.entertainment.Fragments.CategoryListFragment;
import com.example.khaledsabry.entertainment.Fragments.MovieView.MovieNavigationFragment;
import com.example.khaledsabry.entertainment.Fragments.TvView.TvNavigationFragment;
import com.example.khaledsabry.entertainment.Items.Artist;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.Tv;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 13-Aug-18.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    ArrayList<Integer> itemsId = new ArrayList<>();
    ArrayList<Integer> types = new ArrayList<>();
    ArrayList<Object> objects = new ArrayList<>();
    ArrayList<Movie> movies = new ArrayList<>();
    ArrayList<Tv> tvs = new ArrayList<>();
    ArrayList<Artist> artists = new ArrayList<>();
    CategoryController categoryController = new CategoryController();
    String categoryName;
    int categoryId;

    private int typeToShow = 0;

    /**
     * set the data in the adapter to show items of categories
     *
     * @param categoryId         the category id that this item in it
     * @param categoryName       to send it the delete item function
     * @param categoryController to call the delete item function
     */
    public void setBase(int categoryId, String categoryName, CategoryController categoryController) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryController = categoryController;
    }

    /**
     * set the data in the adapter to show items of categories
     *
     * @param itemId   the ids in the database for each item
     * @param type     1:movie 2:tv 3:artist
     * @param object   movie,tv or artist
     * @param itemId   the ids in the database for each item
     * @param position the position it will displayed
     */
    public void setData(Object object, Integer type, Integer itemId, Integer position) {
        objects.add(object);
        itemsId.add(itemId);
        types.add(type);
        if(object instanceof Movie)
            movies.add((Movie) object);
        else if(object instanceof Tv)
            tvs.add((Tv) object);
        else if(object instanceof Artist)
            artists.add((Artist) object);

        notifyItemChanged(position);
    }


    public void clearData()
    {
        objects.clear();
        itemsId.clear();
        types.clear();
        movies.clear();
        tvs.clear();
        artists.clear();
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
        if (itemsId == null)
            return 0;
        int size = 0;
        switch (typeToShow) {
            case 0:
                size = objects.size();
                break;
            case 1:
                size = movies.size();
                break;
            case 2:
                size = tvs.size();
                break;
            case 3:
                size = artists.size();
                break;
            default:
                size = 0;
        }

        return size;
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        TmdbController controller;

        TextView title, rate, date,genres,overview;
        ImageView poster;
        ImageView remove;


        public CategoryViewHolder(View itemView) {
            super(itemView);
            controller = new TmdbController();
            title = itemView.findViewById(R.id.title);
            rate = itemView.findViewById(R.id.rate);
            date = itemView.findViewById(R.id.date);
            poster = itemView.findViewById(R.id.poster);
            remove = itemView.findViewById(R.id.delete);
            genres = itemView.findViewById(R.id.genres_id);
overview =itemView.findViewById(R.id.overview_id);
itemView.findViewById(R.id.overview_layout_id).setVisibility(View.VISIBLE);
            itemView.findViewById(R.id.weeks).setVisibility(View.GONE);
            itemView.findViewById(R.id.revenue).setVisibility(View.GONE);
            itemView.findViewById(R.id.totalrevenue).setVisibility(View.GONE);

            remove.setVisibility(View.VISIBLE);


        }

        void updateUi(final int postion) {
            final Integer categoryItemId = Integer.valueOf(itemsId.get(postion) + "");


            switch (typeToShow) {
                case 0:
                    if (objects.get(postion) instanceof Movie) {
                        Movie movie = (Movie) objects.get(postion);

                        setObjects(movie.getTitle(),movie.getOverView(), movie.getTmdbRate(), movie.getReleaseDate(), movie.getPosterImage(),movie.getGenreList(), categoryItemId, 1, movie.getId());
                    } else if (objects.get(postion) instanceof Tv) {
                        Tv tv = (Tv) objects.get(postion);
                        setObjects(tv.getTitle(),tv.getOverView(), (float) tv.getRateTmdb(), tv.getFirstAirDate(), tv.getPosterImage(),tv.getGenreList(), categoryItemId, 2, tv.getId());
                    } else if (objects.get(postion) instanceof Artist) {
                        //TODO set artist
                    }
                    break;
                case 1:
                    Movie movie = movies.get(postion);
                    setObjects(movie.getTitle(),movie.getOverView(), movie.getTmdbRate(), movie.getReleaseDate(), movie.getPosterImage(),movie.getGenreList(), categoryItemId, 1, movie.getId());
                    break;
                case 2:
                    Tv tv = tvs.get(postion);
                    setObjects(tv.getTitle(),tv.getOverView(), (float) tv.getRateTmdb(), tv.getFirstAirDate(), tv.getPosterImage(),tv.getGenreList(), categoryItemId, 2, tv.getId());
                    break;
                case 3:
                    break;
                default:
            }


        }


        private void setObjects(final String title,String overview, float rate, String date, String posterUrl,String genres, final int removeItemId, final int type, final int contentId) {
            this.title.setText(title);
            this.rate.setText(String.valueOf(rate));
            this.date.setText(date);
            this.genres.setText(genres);
            this.overview.setText(overview);
            ImageController.putImageMidQuality(posterUrl, poster);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (type == 1)
                        MainActivity.loadFragmentNoReturn(R.id.mainContainer, MovieNavigationFragment.newInstance(contentId, 0));
                    else if (type == 2)
                        MainActivity.loadFragmentNoReturn(R.id.mainContainer, TvNavigationFragment.newInstance(contentId, 0));
                    else if (type == 3)
                        MainActivity.loadFragmentNoReturn(R.id.mainContainer, ArtistNavigationFragment.newInstance(contentId, 0));
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
