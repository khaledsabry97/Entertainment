package com.example.khaledsabry.entertainment.Adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.R;
import com.example.khaledsabry.entertainment.Fragments.Youtube;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 24-Jul-18.
 */

public class YoutubeVideoAdapter extends RecyclerView.Adapter<YoutubeVideoAdapter.YoutubeViewHolder> {
    private static final String TAG = YoutubeVideoAdapter.class.getSimpleName();
    private ArrayList<String> youtubeVideoModelArrayList;

    //position to check which position is selected
    private int selectedPosition = 0;


    public YoutubeVideoAdapter(ArrayList<String> youtubeVideoModelArrayList) {
        this.youtubeVideoModelArrayList = youtubeVideoModelArrayList;

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

        /*  initialize the thumbnail image view , we need to pass Developer Key */
        holder.videoThumbnailImageView.initialize(com.example.khaledsabry.entertainment.Controllers.Settings.YoutubeApiKey, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                //when initialization is sucess, set the video id to thumbnail to load
                youTubeThumbnailLoader.setVideo(youtubeVideoModelArrayList.get(position));

                youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                    @Override
                    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                        //when thumbnail loaded successfully release the thumbnail loader as we are showing thumbnail in adapter
                        youTubeThumbnailLoader.release();
                    }

                    @Override
                    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                        //print or show error when thumbnail load failed
                        Log.e(TAG, "Youtube Thumbnail Error");
                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                //print or show error when initialization failed
                Log.e(TAG, "Youtube Initialization Failure");

            }
        });

        holder.updateUi(position,youtubeVideoModelArrayList.get(position));

    }

    @Override
    public int getItemCount() {
        if(youtubeVideoModelArrayList!= null)
            return youtubeVideoModelArrayList.size();
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
        public YouTubeThumbnailView videoThumbnailImageView;
        public CardView youtubeCardView;

        public YoutubeViewHolder(View itemView) {
            super(itemView);
            videoThumbnailImageView = itemView.findViewById(R.id.video_thumbnail_image_view);
            youtubeCardView = itemView.findViewById(R.id.youtube_row_card_view);

        }


        public void updateUi(final int position, final String videoId) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSelectedPosition(position);
                    Youtube.youTubePlayer.cueVideo(videoId);
                }
            });

        }
    }

}

