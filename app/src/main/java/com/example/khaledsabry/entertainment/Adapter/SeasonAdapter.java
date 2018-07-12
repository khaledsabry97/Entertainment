package com.example.khaledsabry.entertainment.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Items.Season;
import com.example.khaledsabry.entertainment.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 12-Jul-18.
 */

public class SeasonAdapter extends RecyclerView.Adapter<SeasonAdapter.SeasonCardView>  {

    ArrayList<Season> seasons = new ArrayList<>();

    public SeasonAdapter(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }

    @NonNull
    @Override
    public SeasonCardView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_seasons,parent,false);


        return new SeasonCardView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeasonCardView holder, int position) {
        Season season = seasons.get(position);
        holder.updateUi(season);


    }

    @Override
    public int getItemCount() {
        return seasons.size();
    }

    class SeasonCardView extends RecyclerView.ViewHolder
    {
        ImageView poster;
        TextView seasonText;
View view;
        public SeasonCardView(View itemView) {
            super(itemView);
            seasonText = itemView.findViewById(R.id.seasonid);
            poster = itemView.findViewById(R.id.posterid);
            view  = itemView;
        }

        public void updateUi(Season season)
        {
            ImageController.putImageLowQuality(season.getPoster(),poster);
            seasonText.setText("Season "+season.getName());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
    }
}
