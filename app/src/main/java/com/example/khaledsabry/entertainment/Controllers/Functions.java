package com.example.khaledsabry.entertainment.Controllers;

import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.bumptech.glide.Glide;
import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Connection.ApiConnections;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by KhALeD SaBrY on 04-Jul-18.
 */

public class Functions {
  /*  private static final Functions ourInstance = new Functions();

    public static Functions getInstance() {
        return ourInstance;
    }

    private Functions() {
    }
*/
    public static void stopConnectionsAndStartImageGlide()
    {
        ApiConnections.getInstance().stopConnection();
        Glide.with(MainActivity.getActivity()).onStop();
        Glide.with(MainActivity.getActivity()).onStart();
    }
    public void movePoster( ViewPager viewPager, PagerAdapter viewPagerAdapter, int delayToStart, int repeatTime) {
        final ViewPager f_viewPager = viewPager;
        final PagerAdapter f_viewPagerAdapter = viewPagerAdapter;
        final int f_delayToStart = delayToStart;
        final int f_repeatTime = repeatTime ;
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (f_viewPagerAdapter == null || f_viewPager == null)
                    return;
                if (f_viewPagerAdapter.getCount() == f_viewPager.getCurrentItem() + 1)
                    f_viewPager.setCurrentItem(0, true);
                else
                    f_viewPager.setCurrentItem(f_viewPager.getCurrentItem() + 1, true);
            }
        };
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.post(runnable);

            }
        }, f_delayToStart, f_repeatTime);
    }
}
