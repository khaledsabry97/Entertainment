package com.example.khaledsabry.entertainment.Controllers;

import com.example.khaledsabry.entertainment.Connection.ApiConnections;
import com.example.khaledsabry.entertainment.Connection.TorrentApi;

/**
 * Created by KhALeD SaBrY on 14-Jul-18.
 */

public class OneOmController {

    TorrentApi controller;
    ApiConnections connection;


    public OneOmController()
    {
        controller = TorrentApi.getInstance();
        connection = ApiConnections.getInstance();
    }

public void getTv(int tvId)
{}


}
