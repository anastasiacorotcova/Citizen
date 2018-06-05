package com.nastya.citizen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class VoteAdapter extends RecyclerView.Adapter<VoteAdapter.VoteViewHolder> {

    private List<Vote> voteList;
    private List<String> votedList;
    private OnVoteSelectedListener listener;
    private Context context;

    public VoteAdapter(List<Vote> voteList, List<String> votedList, Context context, OnVoteSelectedListener listener) {
        this.voteList = voteList;
        this.votedList = votedList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VoteViewHolder(LayoutInflater.from(context).inflate(R.layout.vote_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VoteViewHolder holder, int position) {
        Vote vote = voteList.get(position);
        boolean voted = votedList.contains(vote.getTitle());
        holder.bind(vote, listener, voted);
    }

    @Override
    public int getItemCount() {
        return voteList.size();
    }

    public class VoteViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView contentTextView;

        public VoteViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_text_view);
            contentTextView = itemView.findViewById(R.id.content_text_view);
        }

        public void bind(final Vote vote, final OnVoteSelectedListener listener, final boolean voted) {
            String title = context.getString(R.string.quiz) + (getLayoutPosition() + 1) + ":";
            titleTextView.setText(title);
            contentTextView.setText(vote.getContent());
            if (voted) {
                itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.accept_color));
            } else {
                itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.dark_gray));
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.voteSelected(vote, voted);
                }
            });
        }
    }
}
