package com.example.khaledsabry.entertainment.Adapters;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Enums.Type;
import com.example.khaledsabry.entertainment.Fragments.MovieViews.MovieNavigationFragment;
import com.example.khaledsabry.entertainment.Fragments.WebFragment;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.News;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;

//the content that appears in the main menu big screen
public class MainContentAdapter extends PagerAdapter {

    ArrayList<Object> objects = new ArrayList<>();
    ArrayList<Type> types = new ArrayList<>();
    ViewGroup container;
    private View view;

    public void addFragment(HashMap<Object, Type> objectTypeHashMap) {
        objects.addAll(Arrays.asList(objectTypeHashMap.keySet().toArray()));
        types.addAll(objectTypeHashMap.values());
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        this.container = container;
        View view = getView(position);
        container.addView(view);
        return view;

    }

    @Override
    public int getCount() {
        if (objects == null)
            return 0;
        return objects.size();
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    public View getView(int position) {
        View view = null;
        Object object = objects.get(position);
        switch (types.get(position)) {
            case watch_so_much:
                view = getViewWachSoMuch((Movie) object);
                break;

            case news:
                view = getViewNews((News) object);

                break;
            case movies_now_playing:
                view = getViewMovieNowPlaying((Movie) object);
                break;
            case youtube:
                view = getViewYoutube((String) object);
                break;

        }


        return view;
    }

    private View getViewYoutube(String object) {

        WebFragment webFragment = WebFragment.newInstance(WebFragment.Type.youtube);
       View view = webFragment.onCreateView(LayoutInflater.from(container.getContext()),container,null);
       webFragment.loadYoutubeVideoId(object);

        return view;
    }

    private View getViewMovieNowPlaying(final Movie movie) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.cardview_recommendations_similar, container, false);
        ImageView backDropImage = view.findViewById(R.id.backdrop_image);
        CircleImageView posterImage = view.findViewById(R.id.poster_image);
        TextView title = view.findViewById(R.id.title);
        TextView genres = view.findViewById(R.id.genres_id);
        TextView overView = view.findViewById(R.id.overview_id);
        TextView rate = view.findViewById(R.id.rate);

        title.setText(movie.getTitle());
        genres.setText(movie.getGenreList());
        overView.setText(movie.getOverView());
        rate.setText(Float.toString(movie.getTmdbRate()));
        ImageController.putImageLowQuality(movie.getPosterImage(), posterImage);
        ImageController.putImageMidQuality(movie.getBackDropPoster(), backDropImage);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.loadFragmentNoReturn(R.id.mainContainer, MovieNavigationFragment.newInstance(movie.getId(), 0));
            }
        });

        return view;
    }

    private View getViewWachSoMuch(final Movie movie) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.cardview_recommendations_similar, container, false);
        ImageView backDropImage = view.findViewById(R.id.backdrop_image);
        CircleImageView posterImage = view.findViewById(R.id.poster_image);
        TextView title = view.findViewById(R.id.title);
        TextView genres = view.findViewById(R.id.genres_id);
        TextView overView = view.findViewById(R.id.overview_id);
        TextView rate = view.findViewById(R.id.rate);

        title.setText(movie.getTitle());
        genres.setText(movie.getGenreList());
        overView.setText(movie.getOverView());
        rate.setText(Float.toString(movie.getTmdbRate()));
        ImageController.putImageLowQuality(movie.getPosterImage(), posterImage);
        ImageController.putImageMidQuality(movie.getBackDropPoster(), backDropImage);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.loadFragmentNoReturn(R.id.mainContainer, MovieNavigationFragment.newInstance(movie.getId(), 0));
            }
        });
        return view;
    }


    private View getViewNews(News news) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.cardview_news, container, false);
        NewsAdapter.NewsViewHolder newsViewHolder = new NewsAdapter.NewsViewHolder(view);
        newsViewHolder.updateUi(news);
        view = newsViewHolder.itemView;
        return view;
    }


    public void moveView(final ViewPager viewPager, PagerAdapter viewPagerAdapter, int delayToStart, int repeatTime) {
        final ViewPager f_viewPager = viewPager;
        final PagerAdapter f_viewPagerAdapter = viewPagerAdapter;
        final int f_delayToStart = delayToStart;
        final int f_repeatTime = repeatTime;
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (f_viewPagerAdapter == null || f_viewPager == null)
                    return;
                if (f_viewPagerAdapter.getCount() == f_viewPager.getCurrentItem() + 1) {
                    f_viewPager.setCurrentItem(0, true);
                } else
                    f_viewPager.setCurrentItem(f_viewPager.getCurrentItem() + 1, true);
            }
        };
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.post(runnable);

            }
        }, f_delayToStart, f_repeatTime);
    }

}
