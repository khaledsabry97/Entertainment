package com.example.khaledsabry.entertainment.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Controllers.Controller;
import com.example.khaledsabry.entertainment.Items.News;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 09-Aug-18.
 */

public class NewsFeedAdpater extends RecyclerView.Adapter<NewsFeedAdpater.NewsFeedViewHolder> {
    ArrayList<News> news = new ArrayList<>();


    public NewsFeedAdpater(ArrayList<News> news) {
        this.news = news;
    }

    @NonNull
    @Override

    public NewsFeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_news_bar,parent,false);
        return new NewsFeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsFeedViewHolder holder, int position) {
        News news = this.news.get(position);
        holder.updateUi(news);
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    class NewsFeedViewHolder extends RecyclerView.ViewHolder {
        TextView title;


        public NewsFeedViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.newstitle);
        }

        public void updateUi(News news) {
            title.setText(news.getTitle());
            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Controller().toast("hello");
                }
            });
        }
    }
}
