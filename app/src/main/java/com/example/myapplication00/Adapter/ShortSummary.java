package com.example.myapplication00.Adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

import com.example.myapplication00.R;

public class ShortSummary extends AppCompatActivity {

    private Spanned getSpannedText(String text) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT);
        } else {
            return Html.fromHtml(text);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_summary);

        String s = getIntent().getStringExtra("MESSAGE");
        TextView textView = (TextView)findViewById(R.id.summary);
        textView.setText(getSpannedText(s));
        getSupportActionBar().setTitle(getIntent().getStringExtra("TITLE"));
    }
}