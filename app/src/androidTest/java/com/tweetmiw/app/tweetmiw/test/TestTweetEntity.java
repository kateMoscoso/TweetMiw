package com.tweetmiw.app.tweetmiw.test;

import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;

import com.tweetmiw.app.tweetmiw.entities.Tweet;
import com.tweetmiw.app.tweetmiw.entities.TwitterUser;

import junit.framework.TestCase;

public class TestTweetEntity extends TestCase {
    private Tweet tweet;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        tweet = new Tweet("Esto es un Tweet",new TwitterUser());
        tweet.setFavorites("2");
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

    }

    @SmallTest
    public void test_tweetFavorites(){
        assertTrue("2".equals(tweet.getFavorites()));
        assertTrue("Esto es un Tweet".equals(tweet.getMessage()));
    }
}
