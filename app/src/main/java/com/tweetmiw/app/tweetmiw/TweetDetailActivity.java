package com.tweetmiw.app.tweetmiw;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.tweetmiw.app.tweetmiw.adapters.ViewPagerAdapter;
import com.tweetmiw.app.tweetmiw.entities.ProfileUser;
import com.tweetmiw.app.tweetmiw.entities.Tweet;
import com.tweetmiw.app.tweetmiw.entities.User;
import com.tweetmiw.app.tweetmiw.fragments.TweetDetailFragment;
import com.tweetmiw.app.tweetmiw.holders.ViewHolderTweet;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import java.util.ArrayList;

/**
 * Created by katherin on 13/06/2015.
 */
public class TweetDetailActivity extends AppCompatActivity {
    Tweet tweet;

    /*
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tweet_detail);

            Intent intent = getIntent();
            User usuario = new User();
            ProfileUser profileUser = new ProfileUser();
            profileUser.setName("Usuario");
            profileUser.setScreen_name("@screen_name");
            usuario.setProfile(profileUser);
            tweet = new Tweet("esto es un tweet", usuario);


            Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);
            setSupportActionBar(toolbar);//modifico el action Bar pordefecto de l

        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_initial, menu);
            return true;
        }
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_detail);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // If the screen is now in landscape mode, we can show the
            // dialog in-line with the list so we don't need this activity.
            finish();
            return;
        }

        if (savedInstanceState == null) {
            // During initial setup, plug in the details fragment.
            TweetDetailFragment details = new TweetDetailFragment();
            details.setArguments(getIntent().getExtras());

            //getFragmentManager().beginTransaction().add(R.id.contentPanel,details) add(android.R.id.content, details).commit();

        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);
        setSupportActionBar(toolbar);//modifico el action Bar pordefecto de l
    }
}
