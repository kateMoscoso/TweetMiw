package com.tweetmiw.app.tweetmiw.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tweetmiw.app.tweetmiw.InitialActivity;
import com.tweetmiw.app.tweetmiw.R;
import com.tweetmiw.app.tweetmiw.Timeline;

public class Tab1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab1,container,false);
        return v;
    }

}