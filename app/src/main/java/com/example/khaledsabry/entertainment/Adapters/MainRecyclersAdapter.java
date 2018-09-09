package com.example.khaledsabry.entertainment.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Items.Classification;
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
        if (classifications == null)
            return 0;
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


            recyclerView = itemView.findViewById(R.id.recycler_id);
            title = itemView.findViewById(R.id.title);
            poster = itemView.findViewById(R.id.backdrop_id);

            cardView = itemView.findViewById(R.id.cardview);
        }

        void updateUi(final Classification classification) {

            setObjects(classification);


        }


        private void setObjects(Classification classification) {
            title.setText(classification.getTitle());
            poster.setImageResource(classification.getImage());
            ClassificationAdapter adapter = new ClassificationAdapter(classification.getSearchItems());
            recyclerView.setAdapter(adapter);
            recyclerView.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
            linearLayoutManager.setSmoothScrollbarEnabled(true);
            recyclerView.setLayoutManager(linearLayoutManager);
        }

    }
}
