package com.example.khaledsabry.entertainment.Fragments;


import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.khaledsabry.entertainment.Adapter.TorrentAdapter;
import com.example.khaledsabry.entertainment.Controllers.TorrentController;
import com.example.khaledsabry.entertainment.Fragments.MovieView.MovieNavigationFragment;
import com.example.khaledsabry.entertainment.Interfaces.OnTorrentSearchSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.Torrent;
import com.example.khaledsabry.entertainment.R;

import java.security.Provider;
import java.util.ArrayList;


public class TorrentRecyclerFragment extends Fragment {

    RecyclerView recyclerView;
    static String searchName;
    Spinner provider;
    Spinner quality;
    Spinner resolution;
    EditText searchCustom;
    TorrentController torrentController;
    String search = "";

    public static TorrentRecyclerFragment newInstance(String searchName) {
        TorrentRecyclerFragment fragment = new TorrentRecyclerFragment();
        TorrentRecyclerFragment.searchName = searchName;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_torrent_recycler, container, false);
        recyclerView = view.findViewById(R.id.recyclerid);
        resolution = view.findViewById(R.id.resolutionspinnerid);
        provider = view.findViewById(R.id.providerspinnerid);
        quality = view.findViewById(R.id.qualityspinnerid);
        searchCustom = view.findViewById(R.id.customtextid);
        torrentController = new TorrentController();

        setResolution();
        setQuality();
        setProvider();


        search();


        return view;
    }

    private void search() {
        String mprovider = "";
        String mquality = "";
        String mresolution = "";

        if (!provider.getSelectedItem().toString().equals("All"))
            mprovider = " " + provider.getSelectedItem().toString();
        if (!quality.getSelectedItem().toString().equals("All"))
            mquality = " " + quality.getSelectedItem().toString();
        if (!resolution.getSelectedItem().toString().equals("All"))
            mresolution = " " + resolution.getSelectedItem().toString();

        if (!search.equals(searchName + mresolution + mquality + mprovider)) {
            search = searchName + mresolution + mquality + mprovider;
            torrentController.downloadSkyTorrent(search, new OnTorrentSearchSuccess() {
                @Override
                public void onSuccess(ArrayList<Torrent> torrents) {

                    setObjects(torrents);

                }
            });
        }

    }

    private void setProvider() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item);
        adapter.add("All");
        adapter.add("PSA");
        adapter.add("YTS");
        adapter.add("MkvCage");
        adapter.add("SPARKS");
        adapter.add("RARBG");
        adapter.add("Ganool");
        adapter.add("YIFY");


        provider.setAdapter(adapter);
        provider.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                search();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setQuality() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item);
        adapter.add("All");
        adapter.add("CAM");
        adapter.add("TS");
        adapter.add("HDTV");
        adapter.add("DVDSCR");
        adapter.add("HDRip");

        adapter.add("WEBRip");
        adapter.add("WEBDL");
        adapter.add("BRRip");
        adapter.add("BluRay");

        quality.setAdapter(adapter);
        quality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                search();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });
    }

    private void setResolution() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item);
        adapter.add("All");
        adapter.add("480p");
        adapter.add("720p");
        adapter.add("1080p");
        adapter.add("2160p");
        resolution.setAdapter(adapter);
        resolution.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                search();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });
    }

    private void setObjects(ArrayList<Torrent> torrents) {
        TorrentAdapter torrentAdapter = new TorrentAdapter(torrents);
        recyclerView.setAdapter(torrentAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);


    }

}
