package com.nastya.citizen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<News> newsList;
    private Context context;
    private OnNewsSelectedListener listener;

    public NewsAdapter(List<News> newsList, Context context, OnNewsSelectedListener listener) {
        this.newsList = newsList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.news_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.bind(news, listener);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView categoryTextView;
        private TextView dateTextView;
        private ImageView imageView;

        public NewsViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.news_title);
            categoryTextView = itemView.findViewById(R.id.news_category);
            dateTextView = itemView.findViewById(R.id.news_time);
            imageView = itemView.findViewById(R.id.news_image);
        }

        public void bind(final News news, final OnNewsSelectedListener listener) {
            titleTextView.setText(news.getTitle());
            categoryTextView.setText(news.getCategory());
            dateTextView.setText(news.getDate());
            Picasso.get().load(news.getImage()).into(imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.newsSelected(news);
                }
            });
        }
    }

}
