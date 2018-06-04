package com.nastya.citizen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class VoteContentFragment extends Fragment {

    private TextView contentTextView;

    public static VoteContentFragment newInstance(Vote vote) {
        Bundle args = new Bundle();
        args.putSerializable("vote_key", vote);
        VoteContentFragment fragment = new VoteContentFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.vote_content_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((MainActivity) getActivity()).enableViews(true);

        contentTextView = view.findViewById(R.id.content_text_view);

        if (getArguments() != null) {
            Vote vote = (Vote) getArguments().getSerializable("vote_key");
            if (vote != null) {
                contentTextView.setText(vote.getContent());
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((MainActivity) getActivity()).enableViews(false);
    }
}
