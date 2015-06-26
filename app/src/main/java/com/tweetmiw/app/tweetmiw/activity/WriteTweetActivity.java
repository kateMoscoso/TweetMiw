package com.tweetmiw.app.tweetmiw.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tweetmiw.app.tweetmiw.R;

import twitter4j.StatusUpdate;

/**
 * Created by katherin on 26/06/2015.
 */
public class WriteTweetActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet);
        StatusUpdate statusUpdate = new StatusUpdate("");
    }
}
