package com.example.khaledsabry.entertainment.Adapters;

import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Fragments.FullPoster;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by KhALeD SaBrY on 01-Jul-18.
 */

//pager adapter for the main image slide show
public class MainPosterViewPager extends PagerAdapter {
private boolean allowToMove = true;
    public ArrayList<String> images = new ArrayList<>();
    private ArrayList<Boolean> imagesDownloaded = new ArrayList<>();


    public MainPosterViewPager(ArrayList<String> posters) {
        if(posters.size() > 0) {
            images = posters;
          shuffle();
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.slideshowimage, container, false);


        ImageView imageView = view.findViewById(R.id.imageids);
        ImageController.putImageMidQuality(images.get(position), imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.loadFragmentWithReturn(R.id.mainContainer, FullPoster.newInstance(images.get(position)));
            }
        });
        //container.addView(view, 0);
        container.addView(view,0);


        return view;

    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);

    }

    @Override
    public int getCount() {
        if(images.size() >10)
            return 10;
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    /**
     * shuffle the deck of images to random one
     * important: keep the main poster in his first place always
     * this function is called at the start and when the view pager
     * reaches the end of the recycler
     */
    void shuffle()
    {
        if(images.size() == 0)
            return;

        String mainPoster = images.get(0);
        images.remove(0);
        Collections.shuffle(images);
        images.add(0,mainPoster);
    }

    public void movePoster(final ViewPager viewPager, PagerAdapter viewPagerAdapter, int delayToStart, int repeatTime) {
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
                if (f_viewPagerAdapter.getCount() == f_viewPager.getCurrentItem() + 1) {
                    shuffle();
                    f_viewPager.setCurrentItem(0, true);
                }
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
