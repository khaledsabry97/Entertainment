package com.example.khaledsabry.entertainment.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Fragments.TvView.TvContentFragment;
import com.example.khaledsabry.entertainment.Items.Episode;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 12-Jul-18.
 */

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodeCardView> {
    ArrayList<Episode> episodes = new ArrayList<>();

    public EpisodeAdapter(ArrayList<Episode> episodes) {
        this.episodes = episodes;
    }

    @NonNull
    @Override
    public EpisodeCardView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_episodes, parent, false);


        return new EpisodeCardView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeCardView holder, int position) {
        Episode episode = episodes.get(position);
        holder.updateUi(episode);

    }

    @Override
    public int getItemCount() {
        return episodes.size();
    }

    class EpisodeCardView extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView episodeText;
        View view;

        public EpisodeCardView(View itemView) {
            super(itemView);
            episodeText = itemView.findViewById(R.id.episodeid);
            //     poster = itemView.findViewById(R.id.posterid);
            view = itemView;
        }

        public void updateUi(final Episode episode) {
            // ImageController.putImageLowQuality(episode.getPoster(),poster);
            episodeText.setText("" + episode.getEpisodeNumber());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TvContentFragment.loadSeasonEpisodePreviewFragment(episode);

                }
            });

        }
    }
}
