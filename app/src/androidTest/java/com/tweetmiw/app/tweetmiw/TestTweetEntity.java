package com.tweetmiw.app.tweetmiw;

import com.tweetmiw.app.tweetmiw.entities.Tweet;
import com.tweetmiw.app.tweetmiw.entities.TwitterUser;

import junit.framework.TestCase;

public class TestTweetEntity extends TestCase {
    private Tweet  tweet;
    private TwitterUser usuario;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.usuario = new TwitterUser();
        this.usuario.setName("Luis");
        this.usuario.setFriends_count("2");
        this.tweet = new Tweet("Esto es un Tweet", usuario);
        this.tweet.setFavorites("2");
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

    }

    public void test_tweetFavorites(){
        assertTrue("2".equals(usuario.getFavorites_count()));
    }
}
