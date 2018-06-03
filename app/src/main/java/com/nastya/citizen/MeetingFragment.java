package com.nastya.citizen;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.jaredrummler.materialspinner.MaterialSpinnerAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MeetingFragment extends Fragment {


    private Context context;
    private TextView dateTextView;
    private TextView timeTextView;
    private MaterialSpinner spinner;
    private List<String> categoryList;

    public static MeetingFragment newInstance() {

        Bundle args = new Bundle();

        MeetingFragment fragment = new MeetingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryList = new ArrayList<>();
        categoryList.add("Строительство");
        categoryList.add("Жилищные вопросы");
        categoryList.add("Дороги");
        categoryList.add("Экология");
        categoryList.add("Транспорт");
        categoryList.add("Торговля");
        categoryList.add("Освещение");
        categoryList.add("Отопление");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.meeting_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        dateTextView = view.findViewById(R.id.dayInsert);
        timeTextView = view.findViewById(R.id.timeInsert);
        spinner = view.findViewById(R.id.category_select);
        spinner.setAdapter(new MaterialSpinnerAdapter<String>(context, categoryList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                view.setPadding(10, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
                return view;
            }
        });
        int topPadding = spinner.getPaddingTop();
        int rightPadding = spinner.getPaddingRight();
        int bottomPadding = spinner.getPaddingBottom();
        spinner.setPadding(0, topPadding, rightPadding, bottomPadding);

        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment = DatePickerFragment.newInstance();
                if (getFragmentManager() != null) {
                    datePickerFragment.setTargetFragment(MeetingFragment.this, Constants.DATE_PICKER_REQUEST_CODE);
                    datePickerFragment.show(getFragmentManager(), datePickerFragment.getClass().getName());
                }
            }
        });

        timeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment timePickerFragment = TimePickerFragment.newInstance();
                if (getFragmentManager() != null) {
                    timePickerFragment.setTargetFragment(MeetingFragment.this, Constants.TIME_PICKER_REQUEST_CODE);
                    timePickerFragment.show(getFragmentManager(), timePickerFragment.getClass().getName());
                }
            }
        });

        setCurrentDate();
        setCurrentTime();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case Constants.DATE_PICKER_REQUEST_CODE:
                    String dateText = data.getStringExtra(Constants.DATE_PICKER_EXTRA_CODE);
                    dateTextView.setText(dateText);
                    break;
                case Constants.TIME_PICKER_REQUEST_CODE:
                    String timeText = data.getStringExtra(Constants.TIME_PICKER_EXTRA_CODE);
                    timeTextView.setText(timeText);
                    break;
            }
        }
    }

    private void setCurrentDate() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        dateTextView.setText(new StringBuilder()
                // Месяц отсчитывается с 0, поэтому добавляем 1
                .append(String.format(Locale.getDefault(), "%02d", day)).append("/").append(String.format(Locale.getDefault(), "%02d", (month + 1))).append("/")
                .append(year));
    }

    private void setCurrentTime() {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        timeTextView.setText(new StringBuilder().append(hour).append(":").append(String.format(Locale.getDefault(), "%02d", minute)));
    }
}
