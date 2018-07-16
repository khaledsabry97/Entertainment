package com.example.khaledsabry.entertainment.Fragments.Tv;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Controllers.TorrentController;
import com.example.khaledsabry.entertainment.Interfaces.OnTorrentSearchSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnTvSuccess;
import com.example.khaledsabry.entertainment.Items.Episode;
import com.example.khaledsabry.entertainment.Items.Season;
import com.example.khaledsabry.entertainment.Items.Torrent;
import com.example.khaledsabry.entertainment.Items.Tv;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

public class TvContentFragment extends Fragment {

    ArrayList<Season> seasons = new ArrayList<>();
    static boolean loaded = false;
    int tvId;
    static Tv tv;
static ArrayList<Torrent> torrents = new ArrayList<>();
    public static TvContentFragment newInstance(int tvId) {
        TvContentFragment fragment = new TvContentFragment();
        fragment.tvId = tvId;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_content, container, false);
        loadFragment();
//        loadSeasonsFragment();
        //loadEpisodePreviewFragment();
        return view;


    }

    private void loadFragment() {
        TmdbController tmdbController = new TmdbController();
        if (!loaded) {
            tmdbController.getTv(tvId, new OnTvSuccess() {
                @Override
                public void onSuccess(Tv tv) {
                    TvContentFragment.tv = tv;
                    loaded = true;
getTvTorrent();
                    loadSeasonsFragment();
                }
            });
        } else {
            getTvTorrent();
            loadSeasonsFragment();
        }
    }

    public static void loadEpisodesFragment(ArrayList<Episode> episodes) {
        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.episodesId, EpisodeRecyclerFragment.newInstance(episodes)).commit();

    }

    private void loadSeasonsFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.seasonId, SeasonRecyclerFragment.newInstance(tv.getSeasons(), tvId)).commit();

    }

    public static void loadEpisodePreviewFragment(Episode episode) {
        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.previewId, EpisodePreviewFragment.newInstance(episode)).commit();

    }


    public void getTvTorrent() {
        TorrentController torrentController = new TorrentController();

        torrentController.getTv(tv.getTitle(), new OnTorrentSearchSuccess() {
            @Override
            public void onSuccess(ArrayList<Torrent> torrents) {

               TvContentFragment.torrents  = torrents;
            }
        });
    }
}
