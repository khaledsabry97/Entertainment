package com.example.khaledsabry.entertainment.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Fragments.FullPoster;
import com.example.khaledsabry.entertainment.R;

import java.nio.InvalidMarkException;
import java.util.ArrayList;


/**
 * Created by KhALeD SaBrY on 01-Jul-18.
 */

public class MainPosterViewPager extends PagerAdapter {

    public ArrayList<String> images =  new ArrayList<>();


    public MainPosterViewPager(ArrayList<String> posters) {
images = posters;

    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        //View view = layoutInflater.inflate(R.layout.slideshowimage,container,false);

        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.slideshowimage,container,false);
        ImageView imageView = view.findViewById(R.id.imageids);
        ImageController.putImageHighQuality(images.get(position),imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, FullPoster.newInstance(images.get(position))).addToBackStack(null).commit();
            }
        });
        container.addView(view, position);

        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);

    }

    @Override
    public int getCount() {

        return 10;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
