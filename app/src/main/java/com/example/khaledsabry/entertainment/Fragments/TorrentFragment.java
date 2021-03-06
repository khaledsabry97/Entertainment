package com.example.khaledsabry.entertainment.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Adapters.TorrentAdapter;
import com.example.khaledsabry.entertainment.Controllers.TorrentController;
import com.example.khaledsabry.entertainment.Interfaces.OnWebSuccess;
import com.example.khaledsabry.entertainment.Items.Torrent;
import com.example.khaledsabry.entertainment.R;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;


public class TorrentFragment extends Fragment {

    RecyclerView recyclerView;
    static String searchName;
    // Spinner provider;
    // Spinner quality;
    // Spinner resolution;

    MaterialSpinner provider;
    MaterialSpinner quality;
    MaterialSpinner resolution;
    MaterialSpinner codec;
    MaterialSpinner features;
    SearchView customSearch;
    TorrentController torrentController;
    String search = "";

    TorrentAdapter torrentAdapter;

    public static TorrentFragment newInstance(String searchName) {
        TorrentFragment fragment = new TorrentFragment();
        TorrentFragment.searchName = searchName;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_torrent_recycler, container, false);
        recyclerView = view.findViewById(R.id.recycler_id);
        resolution = view.findViewById(R.id.resolutionspinnerid);
        provider = view.findViewById(R.id.providerspinnerid);
        quality = view.findViewById(R.id.qualityspinnerid);
        customSearch = view.findViewById(R.id.customtextid);
        codec = view.findViewById(R.id.codecspinnerid);
        features = view.findViewById(R.id.featuresspinnerid);
        torrentController = new TorrentController();
        setupRecyclerView();

        setResolution();
        setQuality();
        setProvider();
        setCodec();
        setCustomSearch();
        setFeatures();

        search();


        return view;
    }

    /**
     * setup the recycler view to show the torrents files
     */
    private void setupRecyclerView() {
        torrentAdapter = new TorrentAdapter();
        recyclerView.setAdapter(torrentAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    /**
     * the function that is responsible for the search to torrents
     */
    private void search() {
        String mprovider = "";
        String mquality = "";
        String mresolution = "";
        String mcodec = "";
        String mfeatures = "";

        if (features.getItems().get(features.getSelectedIndex()).toString().equals("SoundTrack"))
            mfeatures = " " + features.getItems().get(features.getSelectedIndex()).toString();

        if (!resolution.getItems().get(resolution.getSelectedIndex()).toString().equals("All"))
            mresolution = " " + resolution.getItems().get(resolution.getSelectedIndex()).toString();

        if (!quality.getItems().get(quality.getSelectedIndex()).toString().equals("All"))
            mquality = " " + quality.getItems().get(quality.getSelectedIndex()).toString();

        if (!codec.getItems().get(codec.getSelectedIndex()).toString().equals("All"))
            mcodec = " " + codec.getItems().get(codec.getSelectedIndex()).toString();

        if (!provider.getItems().get(provider.getSelectedIndex()).toString().equals("All"))
            mprovider = " " + provider.getItems().get(provider.getSelectedIndex()).toString();

        String searchString = searchName + mresolution + mquality + mcodec + mprovider + mfeatures;
        if (!search.equals(searchString)) {
            search = searchString;
            torrentController.downloadSkyTorrent(search, new OnWebSuccess.OnTorrentSearch() {
                @Override
                public void onSuccess(ArrayList<Torrent> torrents) {

                    torrentAdapter.setTorrents(torrents);

                }
            });
        }

    }


    /**
     * set the different types of codecs
     */
    private void setCodec() {
        ArrayList<String> adapter = new ArrayList<>();

        adapter.add("All");
        adapter.add("x264");
        adapter.add("x265");
        adapter.add("10bit x265");


        codec.setItems(adapter);
        codec.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                resetFeatures();
                search();
            }
        });
    }

    /**
     * set the different types of providers
     */
    private void setProvider() {
        ArrayList<String> adapter = new ArrayList<>();
        adapter.add("All");
        adapter.add("PSA");
        adapter.add("YTS");
        adapter.add("MkvCage");
        adapter.add("SPARKS");
        adapter.add("RARBG");
        adapter.add("Ganool");
        adapter.add("MZABI");
        adapter.add("Joy");
        adapter.add("YIFY");


        provider.setItems(adapter);
        provider.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                resetFeatures();
                search();
            }
        });
    }

    /**
     * set the different types of qualities
     */
    private void setQuality() {
        ArrayList<String> adapter = new ArrayList<>();
        adapter.add("All");
        adapter.add("BluRay");
        adapter.add("BRRip");
        adapter.add("WEB-DL");
        adapter.add("WEBRip");
        adapter.add("HDRip");
        adapter.add("DVDSCR");
        adapter.add("HDTV");
        adapter.add("TS");
        adapter.add("CAM");


        quality.setItems(adapter);
        quality.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                resetFeatures();
                search();
            }
        });

        quality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getActivity().hideSystemUI();
            }
        });
    }

    /**
     * set the differnet types of resolutions
     */
    private void setResolution() {

        ArrayList<String> adapter = new ArrayList<>();
        adapter.add("All");
        adapter.add("480p");
        adapter.add("720p");
        adapter.add("1080p");
        adapter.add("2160p");
        resolution.setItems(adapter);
        resolution.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                resetFeatures();
                search();
            }
        });
    }

    /**
     * set the different types of features
     */
    private void setFeatures() {
        ArrayList<String> adapter = new ArrayList<>();

        adapter.add("None");
        adapter.add("SoundTrack");


        features.setItems(adapter);
        features.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                resetCodec();
                resetProvider();
                resetQuality();
                resetResolution();
                search();
            }
        });
    }


    /**
     * @deprecated this is deprecated and only the app will decide which string will search on
     */
    private void setCustomSearch() {
        customSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                torrentController.downloadSkyTorrent(query, new OnWebSuccess.OnTorrentSearch() {
                    @Override
                    public void onSuccess(ArrayList<Torrent> torrents) {
                        torrentAdapter.setTorrents(torrents);
                    }
                });


                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                return false;
            }


        });

    }


    /**
     * reset resolution,quality,provider,codec and features
     */
    private void resetResolution() {
        resolution.setSelectedIndex(0);
    }

    private void resetQuality() {
        quality.setSelectedIndex(0);
    }

    private void resetProvider() {
        provider.setSelectedIndex(0);
    }

    private void resetCodec() {
        codec.setSelectedIndex(0);
    }

    private void resetFeatures() {
        features.setSelectedIndex(0);
    }
}
