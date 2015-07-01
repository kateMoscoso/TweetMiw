package com.tweetmiw.app.tweetmiw;

import com.tweetmiw.app.tweetmiw.entities.Tweet;
import com.tweetmiw.app.tweetmiw.entities.TwitterUser;

import junit.framework.TestCase;

/**
 * Created by katherin on 30/06/2015.
 */
public class TestTweetEntity extends TestCase {
    private Tweet  tweet;
    private TwitterUser usuario;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        usuario = new TwitterUser();
        usuario.setName("Luis");
        usuario.setFriends_count("2");
        tweet = new Tweet("Esto es un Tweet", usuario);
        tweet.setFavorites("2");
    }

    public void test_tweetFavorites(){
        assertTrue("2".equals(usuario.getFavorites_count()));
    }
}
