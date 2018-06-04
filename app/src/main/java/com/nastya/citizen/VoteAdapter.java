package com.nastya.citizen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class VoteAdapter extends RecyclerView.Adapter<VoteAdapter.VoteViewHolder> {

    private List<Vote> voteList;
    private Context context;

    public VoteAdapter(List<Vote> voteList, Context context) {
        this.voteList = voteList;
        this.context = context;
    }

    @NonNull
    @Override
    public VoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VoteViewHolder(LayoutInflater.from(context).inflate(R.layout.vote_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VoteViewHolder holder, int position) {
        Vote vote = voteList.get(position);
        holder.bind(vote);
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

        public void bind(Vote vote) {
            String title = vote.getTitle() + ":";
            titleTextView.setText(title);
            contentTextView.setText(vote.getContent());
        }
    }
}
