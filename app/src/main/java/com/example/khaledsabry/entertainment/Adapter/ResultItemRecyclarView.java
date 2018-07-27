package com.example.khaledsabry.entertainment.Adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Fragments.Artist.ArtistPreviewFragment;
import com.example.khaledsabry.entertainment.Fragments.MovieView.MovieNavigationFragment;
import com.example.khaledsabry.entertainment.Fragments.MovieView.MoviePreviewFragment;
import com.example.khaledsabry.entertainment.Fragments.Tv.TvPreviewFragment;
import com.example.khaledsabry.entertainment.Items.SearchItem;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 04-Jul-18.
 */

public class ResultItemRecyclarView extends RecyclerView.Adapter<ResultItemViewHolder> {

    ArrayList<SearchItem> searchItems = new ArrayList<>();

    public ResultItemRecyclarView(ArrayList<SearchItem> movies) {
        this.searchItems = movies;
    }

    @NonNull
    @Override
    public ResultItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_resultitem,parent,false);

        return new ResultItemViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ResultItemViewHolder holder, int position) {
        final SearchItem searchItem = searchItems.get(position);
        holder.updateUi(searchItem);


    }

    @Override
    public int getItemCount() {
        return searchItems.size();
    }



    public void selectFirstItem() {
        if(searchItems.size() <= 0)
            return;
        Fragment fragment = null;
        if (searchItems.get(0).getType().equals("movie"))
            fragment = MoviePreviewFragment.newInstance(searchItems.get(0).getMovie());
        else if (searchItems.get(0).getType().equals("tv"))
            fragment = TvPreviewFragment.newInstance(searchItems.get(0).getTv());
        else if (searchItems.get(0).getType().equals("person"))
            fragment = ArtistPreviewFragment.newInstance(searchItems.get(0).getArtist());

        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.searchresultitemid, fragment).commit();

    }
}
