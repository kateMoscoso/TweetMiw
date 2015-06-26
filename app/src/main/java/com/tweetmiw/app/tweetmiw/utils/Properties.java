package com.tweetmiw.app.tweetmiw.utils;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

public class Properties  {
    private static Properties mInstance= null;

    public String token;
    public String secret;
    public Twitter twitter;

    protected Properties(){}

    public static synchronized Properties getInstance(){
        if(null == mInstance){
            mInstance = new Properties();
        }
        return mInstance;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Twitter getTwitter() {
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.setOAuthConsumerKey(ConstantsUtils.CONSUMER_KEY);
        builder.setOAuthConsumerSecret(ConstantsUtils.CONSUMER_SECRET);

        AccessToken accessToken = new AccessToken(token, secret);
        this.twitter = new TwitterFactory(builder.build()).getInstance(accessToken);
        return this.twitter;
    }

    public void setTwitter(Twitter twitter) {
        this.twitter = twitter;
    }
}
