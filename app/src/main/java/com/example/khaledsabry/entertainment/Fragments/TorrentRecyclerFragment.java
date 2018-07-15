package com.example.khaledsabry.entertainment.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Adapter.TorrentAdapter;
import com.example.khaledsabry.entertainment.Controllers.TorrentController;
import com.example.khaledsabry.entertainment.Fragments.MovieView.MovieNavigationFragment;
import com.example.khaledsabry.entertainment.Interfaces.OnTorrentSearchSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.Torrent;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;


public class TorrentRecyclerFragment extends Fragment {

    RecyclerView recyclerView;
    static String searchName;
    public static TorrentRecyclerFragment newInstance(String searchName) {
        TorrentRecyclerFragment fragment = new TorrentRecyclerFragment();
        TorrentRecyclerFragment.searchName = searchName;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_torrent_recycler, container, false);
        recyclerView = view.findViewById(R.id.recyclerid);

        TorrentController torrentController = new TorrentController();
        torrentController.downloadSkyTorrent(searchName, new OnTorrentSearchSuccess() {
            @Override
            public void onSuccess(ArrayList<Torrent> torrents) {

                setObjects(torrents);

            }
        });

        return view;
    }

    private void setObjects(ArrayList<Torrent> torrents) {
        TorrentAdapter torrentAdapter = new TorrentAdapter(torrents);
        recyclerView.setAdapter(torrentAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

}
