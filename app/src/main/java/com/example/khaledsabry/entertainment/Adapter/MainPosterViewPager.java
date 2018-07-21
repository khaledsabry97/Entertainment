package com.example.khaledsabry.entertainment.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Fragments.FullPoster;
import com.example.khaledsabry.entertainment.Interfaces.OnImagesDownloaded;
import com.example.khaledsabry.entertainment.R;

import java.nio.InvalidMarkException;
import java.util.ArrayList;
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
        images = posters;
    /*    ImageController.downloadImage(images, 1,8, new OnImagesDownloaded() {
            @Override
            public void onSuccess(int position) {
                imagesDownloaded.add(position,true);
            }
        });
*/
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.slideshowimage, container, false);
        ImageView imageView = view.findViewById(R.id.imageids);
        ImageController.putImageMidQuality(images.get(position), imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, FullPoster.newInstance(images.get(position))).addToBackStack(null).commit();
            }
        });
        container.addView(view, 0);

        return view;

    }
/*
    private void setImage(int position) {
        allowToMove = false;
        if(position==getCount()-1)
            ImageController.downloadImage(images.get(0), new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    allowToMove =true;

                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    allowToMove = true;
                    return false;
                }
            });
else
        ImageController.downloadImage(images.get(position + 1), new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                allowToMove =true;
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                allowToMove = true;
                return false;
            }
        });
    }
*/
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
