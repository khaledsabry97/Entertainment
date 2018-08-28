package com.example.khaledsabry.entertainment.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Fragments.FullPoster;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 01-Jul-18.
 */

// for the posters and bacdrop for artist or movie or tv
public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImagesViewHolder> {

    //images
    public ArrayList<String> imgs;
    //big poster
    ImageView poster;
    //choose quality of the images
    RadioButton fhd, hd, sd;

    public String currentImageUrl;


    public ImagesAdapter(ImageView poster) {
        this.poster = poster;
    }

    /**
     * use it later to determine the viewing photo quality
     *
     * @param fhd 1080p
     * @param hd  720p
     * @param sd  480p
     */
    public void setOptions(RadioButton fhd, RadioButton hd, RadioButton sd) {
        this.fhd = fhd;
        this.hd = hd;
        this.sd = sd;
    }

    /**
     * set the images
     *
     * @param imgs posters/backDrops
     */
    public void setData(ArrayList<String> imgs) {
        currentImageUrl = null;
        this.imgs = imgs;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_poster, parent, false);

        return new ImagesViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ImagesViewHolder holder, int position) {
        final String posterUrl = imgs.get(position);
        holder.updateUi(posterUrl);

    }

    @Override
    public int getItemCount() {
        if (imgs == null)
            return 0;
        return imgs.size();
    }


    class ImagesViewHolder extends RecyclerView.ViewHolder {
        ImageView posterImg;
        View view;

        public ImagesViewHolder(View itemView) {
            super(itemView);
            posterImg = itemView.findViewById(R.id.posterimgid);
            view = itemView;
        }


        public void updateUi(final String posterUrl) {
            //if the currentImageUrl is null then the images is loaded new
            //so specify the first image you got and loaded on the big poster
            if (currentImageUrl == null) {
                loadImagesByResulution(posterUrl);
                currentImageUrl = imgs.get(0);

            }
            //load the images with 720p quality on the recyclerview
            ImageController.putImageMidQuality(posterUrl, posterImg);

            //if you click on the big poster show it in the full screenview
            poster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.loadFragmentWithReturn(R.id.mainContainer, FullPoster.newInstance(currentImageUrl));
                }
            });

            //if you click on one of items in the recycler view then show it on the big poster
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    setImageOnBigPosterView(posterUrl);
                }
            });
        }

        /**
         * download the image according to the specified quality
         *
         * @param imgUrl image link to download
         */
        private void loadImagesByResulution(String imgUrl) {
            if (sd.isChecked())
                ImageController.putImageLowQuality(imgUrl, poster);
            else if (hd.isChecked())
                ImageController.putImageMidQuality(imgUrl, poster);
            else if (fhd.isChecked())
                ImageController.putImageHighQuality(imgUrl, poster);

        }


        /**
         * set the currentImageUrl and dowload the image according to the specified quality
         *
         * @param imgUrl image link to show
         */
        void setImageOnBigPosterView(final String imgUrl) {
            currentImageUrl = imgUrl;
            loadImagesByResulution(imgUrl);

        }


    }

}
