package com.example.khaledsabry.entertainment.Controllers;

/**
 * Created by KhALeD SaBrY on 27-Jun-18.
 */

public class Controller {
    private static Controller instance = null;

    private Controller() {

    }

    public static Controller getInstance() {
        if (instance == null)
            instance = new Controller();
        return instance;
    }




}
