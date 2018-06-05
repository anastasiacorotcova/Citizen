package com.nastya.citizen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VoteFragment extends Fragment implements OnVoteSelectedListener {

    private FirebaseDatabase firebaseDatabase;

    public static VoteFragment newInstance() {

        Bundle args = new Bundle();

        VoteFragment fragment = new VoteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private RecyclerView voteRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.vote_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        voteRecyclerView = view.findViewById(R.id.vote_recycler_view);
        voteRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        DatabaseReference reference = firebaseDatabase.getReference("Voting");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                VoteAdapter adapter = new VoteAdapter(collectNews((Map<String, Object>) dataSnapshot.getValue()), getContext(), VoteFragment.this);
                voteRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private List<Vote> collectNews(Map<String, Object> news) {

        List<Vote> voteList = new ArrayList<>();

        for (Map.Entry<String, Object> entry : news.entrySet()) {

            Map singleNews = (Map) entry.getValue();
            voteList.add(new Vote(
                    entry.getKey(),
                    singleNews.get("Content").toString()
            ));
        }
        return voteList;
    }

    @Override
    public void voteSelected(Vote vote) {
        Fragment newFragment = VoteContentFragment.newInstance(vote);
        FragmentTransaction transaction;
        if (getFragmentManager() != null) {
            transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.main_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
