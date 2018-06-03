package com.nastya.citizen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsContentFragment extends Fragment {

    private TextView titleTextView;
    private TextView categoryTextView;
    private TextView dateTextView;
    private TextView contentTextView;
    private ImageView imageView;

    public static NewsContentFragment newInstance(News news) {

        Bundle args = new Bundle();
        args.putSerializable("news_content", news);
        NewsContentFragment fragment = new NewsContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.news_content_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        News news = null;
        if (getArguments() != null) {
            news = (News) getArguments().getSerializable("news_content");
        }

        titleTextView = view.findViewById(R.id.title_text_view);
        categoryTextView = view.findViewById(R.id.category_text_view);
        dateTextView = view.findViewById(R.id.date_text_view);
        contentTextView = view.findViewById(R.id.content_text_view);
        imageView = view.findViewById(R.id.image_view);

        if (news != null) {
            titleTextView.setText(news.getTitle());
            categoryTextView.setText(news.getCategory());
            dateTextView.setText(news.getDate());
            contentTextView.setText(news.getContent());
            Picasso.get().load(news.getImage()).into(imageView);
        }
    }
}
