package com.example.khaledsabry.entertainment.Adapter;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Connection.WebApi;
import com.example.khaledsabry.entertainment.Fragments.MainMenu.ClassificationRecyclerFragment;
import com.example.khaledsabry.entertainment.Interfaces.OnWebSuccess;
import com.example.khaledsabry.entertainment.Items.Classification;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.SearchItem;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 22-Jul-18.
 */

public class MainRecyclersAdapter extends RecyclerView.Adapter<MainRecyclersAdapter.MainRecyclerViewHolder> {

    ArrayList<Classification> classifications = new ArrayList<>();

    public MainRecyclersAdapter(ArrayList<Classification> classifications) {
        this.classifications = classifications;
    }

    @NonNull
    @Override
    public MainRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mainrecycler, parent, false);
        return new MainRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainRecyclerViewHolder holder, int position) {
        Classification classification = classifications.get(position);
        holder.updateUi(classification);
    }

    @Override
    public int getItemCount() {
        return classifications.size();
    }

    public void putClassification(Classification classification) {
        classifications.add(classification);
        notifyDataSetChanged();
    }

    class MainRecyclerViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;
        TextView title;
        ImageView poster;
        CardView cardView;

        public MainRecyclerViewHolder(View itemView) {
            super(itemView);


            recyclerView = itemView.findViewById(R.id.recyclerid);
            title = itemView.findViewById(R.id.title);
            poster = itemView.findViewById(R.id.poster);

            cardView = itemView.findViewById(R.id.cardview);
        }

        void updateUi(final Classification classification) {

                  setObjects(classification);



        }



        private void setObjects(Classification classification)
        {
            title.setText(classification.getTitle());
            poster.setImageResource(classification.getImage());
            ClassificationAdapter adapter = new ClassificationAdapter(classification.getSearchItems());
            recyclerView.setAdapter(adapter);
            recyclerView.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
            linearLayoutManager.setSmoothScrollbarEnabled(true);
            recyclerView.setLayoutManager(linearLayoutManager);
        }

       private ArrayList<SearchItem> movies(ArrayList<Movie> movies, String type, Integer limit) {

            ArrayList<SearchItem> items = new ArrayList<>();
            int size;
            if (limit == null)
                size = movies.size();
            else
            {
                if (limit > movies.size())
                    size = movies.size();
                else
                    size = limit;
            }

            for (int i = 0; i < size; i++) {
                SearchItem searchItem = new SearchItem();
                if (type == null)
                    searchItem.setType("movie");
                else
                    searchItem.setType(type);
                searchItem.setMovie(movies.get(i));
                items.add(searchItem);
            }
            return items;
        }

    }
}
