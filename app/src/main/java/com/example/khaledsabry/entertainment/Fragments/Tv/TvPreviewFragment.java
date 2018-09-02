package com.example.khaledsabry.entertainment.Fragments.Tv;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Items.Tv;
import com.example.khaledsabry.entertainment.R;


public class TvPreviewFragment extends Fragment {


    static Tv tv;

    ImageView backdrop;
    TextView title, rate, airDate, overView,genres;
View view;

    public static TvPreviewFragment newInstance(Tv tv) {
        TvPreviewFragment fragment = new TvPreviewFragment();
        TvPreviewFragment.tv = tv;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_movie_preview, container, false);

        title = view.findViewById(R.id.title_id);
        rate = view.findViewById(R.id.rate_id);
        overView = view.findViewById(R.id.overview_id);
        airDate = view.findViewById(R.id.release_data_id);
        backdrop = view.findViewById(R.id.backdrop_id);
        genres = view.findViewById(R.id.genres_id);


        setObjects();


        return view;
    }

    /**
     * set the objects on the ui fragment
     */
    private void setObjects() {
        if (tv == null)
            return;
        title.setText(tv.getTitle());
        rate.setText(tv.getRateTmdb() + "/10");
        airDate.setText("First Air Date : " + tv.getFirstAirDate());
        overView.setText(tv.getOverView());
        genres.setText(tv.getGenreList());
        ImageController.putImageMidQuality(tv.getBackDrop(), backdrop);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.loadFragmentNoReturn(R.id.mainContainer, TvNavigationFragment.newInstance(tv.getId(), 0));
            }
        });

        adjustView(title);
        adjustView(rate);
        adjustView(airDate);
        adjustView(overView);
        adjustView(genres);


    }

    /**
     * invisible the ui view if there is no text in it
     * @param textView the ui element
     */
   void adjustView(TextView textView)
    {
        if(textView.getText().toString().equals(""))
            textView.setVisibility(View.INVISIBLE);
    }


}
