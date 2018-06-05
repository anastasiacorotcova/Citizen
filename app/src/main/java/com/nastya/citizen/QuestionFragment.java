package com.nastya.citizen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class QuestionFragment extends Fragment {

    private EditText questionEditText;
    private CheckBox checkBox;
    private Button sendButton;

    public static QuestionFragment newInstance() {

        Bundle args = new Bundle();

        QuestionFragment fragment = new QuestionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.question_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        questionEditText = view.findViewById(R.id.questionInsert);
        checkBox = view.findViewById(R.id.question_check_box);
        sendButton = view.findViewById(R.id.question_send_button);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionEditText.getText().clear();
                checkBox.setChecked(false);
                Toast.makeText(getContext(), "Ваша заявка принята!", Toast.LENGTH_LONG).show();
            }
        });

    }
}
