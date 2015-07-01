package com.tweetmiw.app.tweetmiw.test;

import android.app.Application;
import android.content.Context;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
    @SmallTest
    public void test_prueba(){
        assertTrue(5>1);
    }

}