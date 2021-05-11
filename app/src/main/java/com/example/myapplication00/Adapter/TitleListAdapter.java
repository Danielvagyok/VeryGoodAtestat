package com.example.myapplication00.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication00.R;
import com.example.myapplication00.helper.Title;

import java.util.ArrayList;

public class TitleListAdapter extends RecyclerView.Adapter<TitleListAdapter.TitleViewHolder> {
    private ArrayList<Title> mTitles;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }

    public static class TitleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView;

        public TitleViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.bookImage);
            mTextView = itemView.findViewById(R.id.bookTitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
    public TitleListAdapter(ArrayList<Title> exampleList) {
        mTitles = exampleList;
    }
    @Override
    public TitleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        TitleViewHolder evh = new TitleViewHolder(v, mListener);
        return evh;
    }
    @Override
    public void onBindViewHolder(TitleViewHolder holder, int position) {
        Title currentItem = mTitles.get(position);

        holder.mImageView.setImageResource(currentItem.getmImageResource());
        holder.mTextView.setText(currentItem.getmTitle());
    }
    @Override
    public int getItemCount() {
        return mTitles.size();
    }
}