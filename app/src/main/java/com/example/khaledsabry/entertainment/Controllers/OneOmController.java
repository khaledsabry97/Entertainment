package com.example.khaledsabry.entertainment.Controllers;

import com.example.khaledsabry.entertainment.Connection.ApiConnections;
import com.example.khaledsabry.entertainment.Connection.WebApi;

/**
 * Created by KhALeD SaBrY on 14-Jul-18.
 */

public class OneOmController {

    WebApi controller;
    ApiConnections connection;


    public OneOmController()
    {
        controller = WebApi.getInstance();
        connection = ApiConnections.getInstance();
    }

public void getTv(int tvId)
{}


}
