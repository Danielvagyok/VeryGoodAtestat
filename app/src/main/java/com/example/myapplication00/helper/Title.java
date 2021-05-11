package com.example.myapplication00.helper;

public class Title {
    private String mTitle;
    private int mImageResource;

    public Title(String title, int imageResource){

        mImageResource = imageResource;
        mTitle = title;

    }

    public int getmImageResource() {
        return mImageResource;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void changeTitle(String text){
        mTitle = text;
    }
}
