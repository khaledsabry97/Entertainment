package com.example.khaledsabry.entertainment.Interfaces;

import com.example.khaledsabry.entertainment.Items.Torrent;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 14-Jul-18.
 */

public interface OnTorrentSearchSuccess {
    void onSuccess(ArrayList<Torrent> torrent);
}
