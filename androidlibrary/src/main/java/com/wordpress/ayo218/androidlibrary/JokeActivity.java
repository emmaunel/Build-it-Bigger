package com.wordpress.ayo218.androidlibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String INTENT_JOKE = "joke";

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        textView = findViewById(R.id.txt_joke);

        Bundle intent_bundle = getIntent().getExtras();
        if (intent_bundle != null && intent_bundle.containsKey(INTENT_JOKE)) {
            textView.setText(intent_bundle.getString(INTENT_JOKE));
        }
    }
}
