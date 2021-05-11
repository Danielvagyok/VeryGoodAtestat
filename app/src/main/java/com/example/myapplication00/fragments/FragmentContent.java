package com.example.myapplication00.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication00.R;
import com.example.myapplication00.helper.Title;

import java.util.ArrayList;
import java.util.List;

public class FragmentContent extends Fragment {

    public static final String KEY_TITLE = "Title";
    public static final String KEY_IMAGE = "Image";

    public FragmentContent() {
        // Required empty public constructor
    }


    public static FragmentContent newInstance(Title param1) {
        FragmentContent fragment = new FragmentContent();
        Bundle args = new Bundle();
        args.putString(KEY_TITLE, param1.getmTitle());
        args.putInt(KEY_IMAGE, param1.getmImageResource());
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //in the original instead of fragment_content : fragment_fragment_content
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String title = getArguments().getString(KEY_TITLE);
        int imageResource = getArguments().getInt(KEY_IMAGE);

        TextView text = (TextView) view.findViewById(R.id.option);
        ImageView image = (ImageView) view.findViewById(R.id.bookImage);

        text.setText(title);
        image.setImageResource(imageResource);
    }
}