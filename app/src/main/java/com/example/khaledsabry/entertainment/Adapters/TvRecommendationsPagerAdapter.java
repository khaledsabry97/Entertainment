package com.example.khaledsabry.entertainment.Adapters;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Fragments.TvView.TvNavigationFragment;
import com.example.khaledsabry.entertainment.Items.Tv;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class TvRecommendationsPagerAdapter extends PagerAdapter {
    ImageView backdrop;
    TextView title, genres, overview, rate;
    CircleImageView poster;
    ArrayList<Tv> tvs;

    public TvRecommendationsPagerAdapter(ArrayList<Tv> tvs) {
        super();
        this.tvs = tvs;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.cardview_recommendations_similar, container, false);
        final Tv tv = tvs.get(position);

        //get the ui from the layout
        poster = view.findViewById(R.id.poster_image);
        backdrop = view.findViewById(R.id.backdrop_image);
        title = view.findViewById(R.id.title);
        genres = view.findViewById(R.id.genres_id);
        overview = view.findViewById(R.id.overview_id);
        rate = view.findViewById(R.id.rate);

        //upload the poster and backDropImage
        ImageController.putImageLowQuality(tv.getPosterImage(), poster);
        ImageController.putImageMidQuality(tv.getBackDrop(), backdrop);

        //set the ui components
        title.setText(tv.getTitle() + " [" + tv.getYear() + "]");
        genres.setText(tv.getGenreList());
        overview.setText(tv.getOverView());
        rate.setText(tv.getRateTmdb() + "");

        //to let you watch the movie details when you click it
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.loadFragmentNoReturn(R.id.mainContainer, TvNavigationFragment.newInstance(tv.getId(), 0));
            }
        });

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        if (tvs == null)
            return 0;
        if (tvs.size() > 20)
            return 20;
        return tvs.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
}
