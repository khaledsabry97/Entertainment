package com.example.khaledsabry.entertainment.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Fragments.NewsFragment;
import com.example.khaledsabry.entertainment.Items.News;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 05-Aug-18.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private ArrayList<News> news = new ArrayList<>();

    public void setNews(ArrayList<News> news) {
        this.news = news;
    }

    public ArrayList<News> getNews() {
        return news;
    }

    public NewsAdapter(ArrayList<News> news) {
        this.news = news;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        News news = this.news.get(position);

        holder.updateUi(news);
    }

    @Override
    public int getItemCount() {
        if (news == null)
            return 0;
        return news.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView content;
        private TextView time;
        private TextView source;
        private TextView writtenBy;
        private TextView viewSource;
        private ImageView poster;

        public NewsViewHolder(View itemView) {
            super(itemView);


            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            time = itemView.findViewById(R.id.date);
            source = itemView.findViewById(R.id.source);
            writtenBy = itemView.findViewById(R.id.genres_id);
            viewSource = itemView.findViewById(R.id.viewfull);
            poster = itemView.findViewById(R.id.poster);

        }


        void updateUi(final News news) {
            poster.setVisibility(View.VISIBLE);
            title.setText(news.getTitle());
            content.setText(news.getContent());
            time.setText(news.getTime());
            source.setText(" |  " + news.getSource());
            writtenBy.setText(" |  " + news.getWrittenBy());
            viewSource.setText("See full article at " + news.getSource());
            if (news.getImage() == null)
                poster.setVisibility(View.GONE);
            else
                ImageController.putImageToImageView(news.getImage(), poster);

            viewSource.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        NewsFragment.fragment.loadWebView(news.getUrl());

                    } catch (Exception e) {

                    }
                }
            });

        }
    }
}
