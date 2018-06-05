package com.nastya.citizen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class VoteContentFragment extends Fragment {

    private TextView contentTextView;
    private Button acceptButton;
    private Button declineButton;
    private LinearLayout controlsButtonLayout;

    private FirebaseAuth auth;
    private FirebaseDatabase database;

    private Vote vote;

    public static VoteContentFragment newInstance(Vote vote, boolean voted) {
        Bundle args = new Bundle();
        args.putSerializable("vote_key", vote);
        args.putBoolean("voted_key", voted);
        VoteContentFragment fragment = new VoteContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

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
        acceptButton = view.findViewById(R.id.accept_button);
        declineButton = view.findViewById(R.id.decline_button);
        controlsButtonLayout = view.findViewById(R.id.buttons_control_layout);

        if (getArguments() != null) {
            vote = (Vote) getArguments().getSerializable("vote_key");
            if (vote != null) {
                contentTextView.setText(vote.getContent());
            }
            if (getArguments().getBoolean("voted_key")) {
                controlsButtonLayout.setVisibility(View.GONE);
            }
        }

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> voteMap = new HashMap<>();
                voteMap.put("Result", "1");
                database.getReference("Users").child("User1").child("Voting").child(vote.getTitle()).setValue(voteMap);
                getActivity().onBackPressed();
            }
        });

        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> voteMap = new HashMap<>();
                voteMap.put("Result", "0");
                database.getReference("Users").child("User1").child("Voting").child(vote.getTitle()).setValue(voteMap);
                getActivity().onBackPressed();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((MainActivity) getActivity()).enableViews(false);
    }
}
