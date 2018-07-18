package com.example.khaledsabry.entertainment.Fragments.Tv;


import android.os.Bundle;
import android.os.TestLooperManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Fragments.MovieView.MovieNavigationFragment;
import com.example.khaledsabry.entertainment.Items.Episode;
import com.example.khaledsabry.entertainment.R;


public class EpisodePreviewFragment extends Fragment {

    Episode episode;
    ImageView poster;
    TextView title;
    TextView rate;
    TextView airDate;
    TextView overView;
    public static EpisodePreviewFragment newInstance(Episode episode) {
        EpisodePreviewFragment fragment = new EpisodePreviewFragment();
fragment.episode = episode;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_episode_preview, container, false);
        title = view.findViewById(R.id.titleid);
        rate = view.findViewById(R.id.rateid);

        airDate = view.findViewById(R.id.airdateid);

        overView = view.findViewById(R.id.overviewid);
        poster = view.findViewById(R.id.posterid);

setObjects();

/*
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, TvNavigationFragment.newInstance(1,true)).addToBackStack(null).commit();
            }
        });*/
        return view;
    }

    private void setObjects() {
        title.setText(episode.getName());
        rate.setText(episode.getRateTmdb()+"/10");
        airDate.setText(episode.getAirDate());
        overView.setText(episode.getOverView());
        ImageController.putImageMidQuality(episode.getPoster(),poster);

    }

}
