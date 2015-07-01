package com.tweetmiw.app.tweetmiw.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.tweetmiw.app.tweetmiw.R;
import com.tweetmiw.app.tweetmiw.entities.Tweet;
import com.tweetmiw.app.tweetmiw.entities.TwitterUser;

import java.util.ArrayList;
import java.util.List;

public class TimelineActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_pager_list);
        new TweetsSearchTask().execute();
    }

    private class TweetsSearchTask extends AsyncTask<Object, Void, ArrayList<Tweet>> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog = new ProgressDialog(TimelineActivity.this);
            progressDialog.setMessage(getResources().getString(R.string.user_loading));
            progressDialog.show();
        }

        @Override
        protected ArrayList<Tweet> doInBackground(Object... param) {

            ArrayList<Tweet> tweets = new ArrayList<Tweet>();

            try {

                twitter4j.Twitter twitter = com.tweetmiw.app.tweetmiw.utils.Properties.getInstance().getTwitter();
                List<twitter4j.Status> statuses = twitter.getHomeTimeline();
               // String timeline = TwitterUtils.getTimelineForSearchTerm(ConstantsUtils.MEJORANDROID_TERM);
                Tweet tweet;
                TwitterUser user = new TwitterUser();
                int i = 0;
                for (twitter4j.Status status : statuses) {
                    user.setName(status.getUser().getName());
                    user.setScreen_name(status.getUser().getScreenName());

                    tweet = new Tweet(status.getText(), user);
                    tweets.add(i, tweet);
                    i++;
                }

            } catch (Exception e) {
                Log.e("error: ", Log.getStackTraceString(e));

            }
            return tweets;
        }

        @Override
        protected void onPostExecute(ArrayList<Tweet> tweets){
            progressDialog.dismiss();

            if (tweets.isEmpty()) {
                Toast.makeText(TimelineActivity.this, getResources().getString(R.string.label_tweets_not_found),
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(TimelineActivity.this, getResources().getString(R.string.label_tweets_downloaded),
                        Toast.LENGTH_SHORT).show();
            }
        }


    }
}
