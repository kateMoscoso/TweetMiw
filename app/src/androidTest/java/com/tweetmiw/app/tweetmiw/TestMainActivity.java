package com.tweetmiw.app.tweetmiw;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

import com.twitter.sdk.android.core.identity.TwitterLoginButton;

/**
 * Created by katherin on 01/07/2015.
 */
public class TestMainActivity  extends ActivityInstrumentationTestCase2<MainActivity>{
    MainActivity activity;
    public TestMainActivity() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
    }
    @SmallTest
    public void testActivityExists() {
        assertNotNull(activity);
    }
    @SmallTest
    public void buttonNotNult() {
        TwitterLoginButton loginButton = (TwitterLoginButton)
                activity.findViewById(R.id.login_button);
        assertNotNull(loginButton);
    }
}
