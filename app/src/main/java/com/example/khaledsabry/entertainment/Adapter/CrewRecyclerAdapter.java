package com.example.khaledsabry.entertainment.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Fragments.FullPoster;
import com.example.khaledsabry.entertainment.Items.Character;
import com.example.khaledsabry.entertainment.Items.Crew;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 29-Jun-18.
 */

public class CrewRecyclerAdapter extends RecyclerView.Adapter<CrewViewHolder> {

    ArrayList<Crew> crews = new ArrayList<>();

    public CrewRecyclerAdapter(ArrayList<Crew> crews) {
        this.crews = crews;
    }

    @Override
    public CrewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardContent = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_cardview, parent, false);

        return new CrewViewHolder(cardContent);
    }

    @Override
    public void onBindViewHolder(final CrewViewHolder holder, int position) {
        final Crew crew = crews.get(position);
        holder.updateUi(crew);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, FullPoster.newInstance(crew.getArtist().getPosterImage())).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        if (crews.size() > 20)
            return 20;
        return crews.size();
    }
}
