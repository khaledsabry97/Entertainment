package com.example.khaledsabry.entertainment.Adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Items.Review;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 03-Jul-18.
 */

public class ReviewPageAdapter extends PagerAdapter {
    ArrayList<Review> reviews;


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }
}
