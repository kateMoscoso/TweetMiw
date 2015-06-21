package com.tweetmiw.app.tweetmiw.utils;

/**
 * Created by katherin on 21/06/2015.
 */
public class Properties  {
    private static Properties mInstance= null;

    public String token;
    public String secret;

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
}
