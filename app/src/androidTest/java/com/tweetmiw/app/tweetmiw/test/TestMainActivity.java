package com.tweetmiw.app.tweetmiw.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

import com.tweetmiw.app.tweetmiw.MainActivity;
import com.tweetmiw.app.tweetmiw.R;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

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
