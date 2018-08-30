package com.example.khaledsabry.entertainment.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Fragments.Artist.ArtistNavigationFragment;
import com.example.khaledsabry.entertainment.Fragments.FullPoster;
import com.example.khaledsabry.entertainment.Items.Artist;
import com.example.khaledsabry.entertainment.Items.Character;
import com.example.khaledsabry.entertainment.Items.Crew;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 29-Jun-18.
 */

//the recycler adapter for the crew
public class CrewRecyclerAdapter extends RecyclerView.Adapter<CrewRecyclerAdapter.CrewViewHolder> {

    ArrayList<Artist> crews;

    public CrewRecyclerAdapter(ArrayList<Artist> crews) {
        this.crews = crews;
    }

    @Override
    public CrewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardContent = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_cardview, parent, false);
        return new CrewViewHolder(cardContent);
    }

    @Override
    public void onBindViewHolder(final CrewViewHolder holder, int position) {
        final Artist crew = crews.get(position);
        holder.updateUi(crew);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.loadFragmentNoReturn(R.id.mainContainer, ArtistNavigationFragment.newInstance(crew.getId(),true));
            }
        });

    }

    @Override
    public int getItemCount() {
        if(crews == null)
            return 0;
        if (crews.size() > 20)
            return 20;
        return crews.size();
    }


    class CrewViewHolder extends RecyclerView.ViewHolder {
        ImageView posterImage;
        TextView jobName;
        TextView crewName;

        public CrewViewHolder(View itemView) {
            super(itemView);
            posterImage = itemView.findViewById(R.id.profile_image);
            jobName = itemView.findViewById(R.id.character_name);
            crewName = itemView.findViewById(R.id.artist_name);
        }

        public void updateUi(final Artist crew) {
            crewName.setText(crew.getName());
            jobName.setText(crew.getJob());
            ImageController.putImageLowQuality(crew.getPosterImage(), posterImage);
        }
    }

}
