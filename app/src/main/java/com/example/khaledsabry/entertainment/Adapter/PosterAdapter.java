package com.example.khaledsabry.entertainment.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Fragments.FullPoster;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 01-Jul-18.
 */

// for the posters and bacdrop for artist or movie or tv
public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHolder> {

    public ArrayList<String> imgs;
    ImageView poster;
    Button fullScreen,fhd,hd,sd;


    public PosterAdapter(ImageView poster) {
        this.poster = poster;
    }

    public void setButtons(Button fullScreen,Button fhd,Button hd,Button sd)
    {
        this.fullScreen = fullScreen;
        this.fhd = fhd;
        this.hd = hd;
        this.sd = sd;
    }
    public void setData(ArrayList<String> imgs) {
        this.imgs = imgs;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.poster_cardview, parent, false);

        return new PosterViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        final String posterUrl = imgs.get(position);
        holder.updateUi(posterUrl);

    }

    @Override
    public int getItemCount() {
        if (imgs == null)
            return 0;
        return imgs.size();
    }


    class PosterViewHolder extends RecyclerView.ViewHolder {
        ImageView posterImg;
        View view;

        public PosterViewHolder(View itemView) {
            super(itemView);
            posterImg = itemView.findViewById(R.id.posterimgid);
            view = itemView;
        }


        public void updateUi(final String posterUrl) {
            ImageController.putImageMidQuality(posterUrl, posterImg);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (posterImg.getDrawable() == null)
                        ImageController.putImageMidQuality(posterUrl, poster);
                    else
                        poster.setImageDrawable(posterImg.getDrawable());
                    poster.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MainActivity.getActivity().loadFragmentWithReturn(R.id.mainContainer, FullPoster.newInstance(posterUrl));
                        }
                    });

                }
            });
        }


    }

}
