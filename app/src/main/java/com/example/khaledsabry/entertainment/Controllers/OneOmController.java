package com.example.khaledsabry.entertainment.Controllers;

import com.example.khaledsabry.entertainment.Connection.ApiConnections;
import com.example.khaledsabry.entertainment.Connection.OneOm;

/**
 * Created by KhALeD SaBrY on 14-Jul-18.
 */

public class OneOmController {

    OneOm controller;
    ApiConnections connection;


    public OneOmController()
    {
        controller = OneOm.getInstance();
        connection = ApiConnections.getInstance();
    }

public void getTv(int tvId)
{}


}
