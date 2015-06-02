package com.tweetmiw.app.tweetmiw.entities;

import java.util.Date;

/**
 * Created by katherin on 02/06/2015.
 */
public class Tweet {
    private String message;
    private User user;
    private int favorites;
    private Date created_at;

    public Tweet(String message, User user){
        this.message = message;
        this.user = user;
        this.favorites = 0;

    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }
}

