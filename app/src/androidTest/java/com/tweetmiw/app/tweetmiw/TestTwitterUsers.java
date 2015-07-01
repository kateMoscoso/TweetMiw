package com.tweetmiw.app.tweetmiw;

import android.test.suitebuilder.annotation.SmallTest;

import com.tweetmiw.app.tweetmiw.entities.Tweet;
import com.tweetmiw.app.tweetmiw.entities.TwitterUser;

import junit.framework.TestCase;

public class TestTwitterUsers extends TestCase {
    private TwitterUser usuario;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        usuario = new TwitterUser();
        usuario.setName("Luis");
        usuario.setFriends_count("2");
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    @SmallTest
    public void test_usuarioSeguido(){
        assertTrue("Luis".equals(usuario.getName()));
    }
}
