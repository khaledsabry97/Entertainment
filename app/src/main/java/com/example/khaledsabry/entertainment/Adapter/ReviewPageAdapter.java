package com.example.khaledsabry.entertainment.Adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Items.Review;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 03-Jul-18.
 */

public class ReviewPageAdapter extends PagerAdapter {
    ArrayList<Review> reviews;

    public ReviewPageAdapter(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = LayoutInflater.from(container.getContext()).inflate(R.layout.cardview_review, container, false);
        TextView name = v.findViewById(R.id.title);
        TextView content = v.findViewById(R.id.contentid);

        name.setText(reviews.get(position).getAuthor());
        content.setText(reviews.get(position).getContent());


        container.addView(v, 0);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return reviews.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
}
