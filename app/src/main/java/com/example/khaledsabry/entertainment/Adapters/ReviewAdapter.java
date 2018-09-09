package com.example.khaledsabry.entertainment.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Items.Review;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 03-Jul-18.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    ArrayList<Review> reviews;
    View view = null;

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReviewViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_review, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        holder.updateUi(reviews.get(position));
    }


    @Override
    public int getItemCount() {
        if (reviews == null)
            return 0;
        return reviews.size();
    }


    class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView name, date, reviewContent;
        ImageView poster, tomatoesPoster;

        public ReviewViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.reviewer_name_id);
            date = itemView.findViewById(R.id.date_id);
            reviewContent = itemView.findViewById(R.id.review_content_id);
            poster = itemView.findViewById(R.id.reviewer_poster_id);
            tomatoesPoster = itemView.findViewById(R.id.tomatoes_poster_id);
        }

        void updateUi(Review review) {
            name.setText(review.getAuthor());
            date.setText(review.getReviewDate());
            reviewContent.setText(review.getContent());
            ImageController.putImageToImageView(review.getAuthorImage(), poster);
            if (review.getRottenTomatoesType().equals("Fresh"))
                ImageController.putDrawableToImageView(R.drawable.fresh, tomatoesPoster);
            else if (review.getRottenTomatoesType().equals("Rotten"))
                ImageController.putDrawableToImageView(R.drawable.rotten, tomatoesPoster);

        }
    }
}
