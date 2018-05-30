package com.nastya.citizen;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;

public class TroubleFragment extends Fragment {

    private static final int PICK_FIRST_IMAGE = 1;
    private static final int PICK_SECOND_IMAGE = 2;
    private static final int PICK_THIRD_IMAGE = 3;
    private static final int PICK_FOURTH_IMAGE = 4;

    private ImageView firstImageView;
    private ImageView secondImageView;
    private ImageView thirdImageView;
    private ImageView fourthImageView;

    public static TroubleFragment newInstance() {

        Bundle args = new Bundle();

        TroubleFragment fragment = new TroubleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.trouble_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView(view);
    }

    private void setupView(View view) {
        firstImageView = view.findViewById(R.id.first_image);
        secondImageView = view.findViewById(R.id.second_image);
        thirdImageView = view.findViewById(R.id.third_image);
        fourthImageView = view.findViewById(R.id.fourth_image);

        firstImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery(PICK_FIRST_IMAGE);
            }
        });

        secondImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery(PICK_SECOND_IMAGE);
            }
        });

        thirdImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery(PICK_THIRD_IMAGE);
            }
        });

        fourthImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery(PICK_FOURTH_IMAGE);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);

                switch (requestCode) {

                    case PICK_FIRST_IMAGE:
                        firstImageView.setImageBitmap(bitmap);
                        break;
                    case PICK_SECOND_IMAGE:
                        secondImageView.setImageBitmap(bitmap);
                        break;
                    case PICK_THIRD_IMAGE:
                        thirdImageView.setImageBitmap(bitmap);
                        break;
                    case PICK_FOURTH_IMAGE:
                        fourthImageView.setImageBitmap(bitmap);
                        break;

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void openGallery(int imageCode) {
        Intent intent = new Intent();
// Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), imageCode);
    }
}
