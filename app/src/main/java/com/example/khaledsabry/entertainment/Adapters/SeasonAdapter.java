package com.example.khaledsabry.entertainment.Adapters;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.Functions;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Fragments.TvViews.TvContentFragment;
import com.example.khaledsabry.entertainment.Interfaces.OnSeasonSuccess;
import com.example.khaledsabry.entertainment.Items.Season;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 12-Jul-18.
 */

public class SeasonAdapter extends RecyclerView.Adapter<SeasonAdapter.SeasonCardView> {

    ArrayList<Season> seasons;
    int tvId;
    DrawerLayout drawerLayout;

    public SeasonAdapter(ArrayList<Season> seasons, int tvId, DrawerLayout drawerLayout) {
        this.seasons = seasons;
        this.tvId = tvId;
        this.drawerLayout = drawerLayout;
    }

    @NonNull
    @Override
    public SeasonCardView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_seasons, parent, false);


        return new SeasonCardView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeasonCardView holder, int position) {
        Season season = seasons.get(position);
        holder.updateUi(season);


    }


    @Override
    public int getItemCount() {
        if (seasons == null)
            return 0;
        return seasons.size();
    }

    class SeasonCardView extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView seasonText;
        View view;

        public SeasonCardView(View itemView) {
            super(itemView);
            seasonText = itemView.findViewById(R.id.season_id);
            poster = itemView.findViewById(R.id.backdrop_id);
            view = itemView;
        }

        public void updateUi(final Season season) {
            ImageController.putImageLowQuality(season.getPoster(), poster);
            seasonText.setText(season.getName());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getSeasonPreview(season);

                    Functions.closeDrawerLayout(drawerLayout);

                }
            });
        }


        void getSeasonPreview(final Season season) {
            TmdbController tmdbController = new TmdbController();
            tmdbController.getTvSeason(tvId, season.getSeasonNumber(), new OnSeasonSuccess() {
                        @Override
                        public void onSuccess(final Season season1) {
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    for (int i = 0; i < season1.getEpisodes().size(); i++)
                                        season1.getEpisodes().get(i).setTvTitle(season.getTvTitle());
                                    MainActivity.getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            TvContentFragment.loadEpisodesFragment(season1.getEpisodes());

                                        }
                                    });
                                }
                            });

                        }

                    }
            );

            TvContentFragment.loadSeasonEpisodePreviewFragment(season);
        }
    }
}
