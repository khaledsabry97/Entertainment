package com.example.khaledsabry.entertainment.Controllers;

import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.example.khaledsabry.entertainment.Activities.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by KhALeD SaBrY on 27-Jun-18.
 */

public class Controller {
    /*
    private static Controller instance = null;

    private Controller() {

    }

    public static Controller getInstance() {
        if (instance == null)
            instance = new Controller();
        return instance;
    }
    */
    public void toast(String msg)
    {
        Toast.makeText(MainActivity.getActivity().getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }






}
