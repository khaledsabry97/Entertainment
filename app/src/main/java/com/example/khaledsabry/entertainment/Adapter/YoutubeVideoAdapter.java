package com.example.khaledsabry.entertainment.Adapter;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Items.Youtube;
import com.example.khaledsabry.entertainment.R;
import com.example.khaledsabry.entertainment.Fragments.YoutubeFragment;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 24-Jul-18.
 */

public class YoutubeVideoAdapter extends RecyclerView.Adapter<YoutubeVideoAdapter.YoutubeViewHolder> {

    private ArrayList<Youtube> youtubes;
    //position to check which position is selected
    private int selectedPosition = 0;


    public YoutubeVideoAdapter(ArrayList<Youtube> youtubes) {
        this.youtubes = youtubes;

        if (youtubes.size() > 0) {
            if(YoutubeFragment.youTubePlayer != null) {
                YoutubeFragment.youTubePlayer.cueVideo(youtubes.get(0).getId());
                YoutubeFragment.youTubePlayer.play();
            }
        }

    }

    @Override
    public YoutubeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_youtube, parent, false);
        return new YoutubeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(YoutubeViewHolder holder, final int position) {

        //if selected position is equal to that mean view is selected so change the cardview color
        if (selectedPosition == position) {
            holder.youtubeCardView.setCardBackgroundColor(ContextCompat.getColor(MainActivity.getActivity().getApplicationContext(), R.color.colorPrimary));
        } else {
            //if selected position is not equal to that mean view is not selected so change the cardview color to white back again
            holder.youtubeCardView.setCardBackgroundColor(ContextCompat.getColor(MainActivity.getActivity().getApplicationContext(), android.R.color.white));
        }

        holder.updateUi(position, youtubes.get(position).getTitle(), youtubes.get(position).getId(), youtubes.get(position).getPosterUrl());

    }

    @Override
    public int getItemCount() {
        if (youtubes != null)
            return youtubes.size();
        return 0;
    }

    /**
     * method the change the selected position when item clicked
     *
     * @param selectedPosition
     */
    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
        //when item selected notify the adapter
        notifyDataSetChanged();
    }


    public class YoutubeViewHolder extends RecyclerView.ViewHolder {
        public CardView youtubeCardView;
        TextView title;
        private ImageView poster;

        public YoutubeViewHolder(View itemView) {
            super(itemView);
            youtubeCardView = itemView.findViewById(R.id.cardview);
            poster = itemView.findViewById(R.id.poster);
            title = itemView.findViewById(R.id.titleId);
        }


        public void updateUi(final int position, String title, final String videoId, String imgUrl) {
            poster.setImageDrawable(null);
            this.title.setText(title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSelectedPosition(position);
                    YoutubeFragment.youTubePlayer.cueVideo(videoId);
                    YoutubeFragment.youTubePlayer.play();
                    YoutubeFragment.drawerLayout.closeDrawer(GravityCompat.END,true);
setSelectedPosition(position);

                }
            });

            ImageController.putImageToImageView(imgUrl, poster);

        }
    }

}

