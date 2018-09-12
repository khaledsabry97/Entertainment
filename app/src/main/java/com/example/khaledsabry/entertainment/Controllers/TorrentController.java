package com.example.khaledsabry.entertainment.Controllers;

import com.example.khaledsabry.entertainment.Connection.ApiConnections;
import com.example.khaledsabry.entertainment.Connection.WebApi;
import com.example.khaledsabry.entertainment.Interfaces.OnWebSuccess;
import com.example.khaledsabry.entertainment.Items.Torrent;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 14-Jul-18.
 */

public class TorrentController {
    private WebApi webApi = WebApi.getInstance();

    public void downloadSkyTorrent(final String searchItem, final OnWebSuccess.OnTorrentSearch listener) {
        webApi.skyTorrent(searchItem, new OnWebSuccess.OnTorrentSearch() {
            @Override
            public void onSuccess(ArrayList<Torrent> torrents) {
                listener.onSuccess(torrents);
            }
        });

    }


}
