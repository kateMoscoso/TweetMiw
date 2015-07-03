package com.tweetmiw.app.tweetmiw.test;

import android.app.DownloadManager;
import android.test.suitebuilder.annotation.SmallTest;

import com.tweetmiw.app.tweetmiw.InitialActivity;
import com.tweetmiw.app.tweetmiw.utils.ConstantsUtils;
import com.tweetmiw.app.tweetmiw.utils.SessionManager;

import junit.framework.TestCase;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by katherin on 01/07/2015.
 */
public class TestTwitter4j extends TestCase{
    InitialActivity initialActivity = new InitialActivity();
    SessionManager sessionManager = new SessionManager(initialActivity);
    Twitter twitter = sessionManager.getTwitter();

    @Override
    protected void setUp() throws Exception {
        super.setUp();

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    @SmallTest
    public void test_tweetquery() {
        Query query = new Query("source:twitter4j yusukey");
        QueryResult result = null;
        try {
            result = twitter.search(query);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        for (Status status : result.getTweets()) {
            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
        }
    }
}
