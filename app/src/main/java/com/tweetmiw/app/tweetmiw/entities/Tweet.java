package com.tweetmiw.app.tweetmiw.entities;

import java.util.Date;

/**
 * Created by katherin on 02/06/2015.
 */
public class Tweet {
    private String message;
    private User user;
    private String favorites;
    private String created_at;

    public Tweet(String message, User user){
        this.message = message;
        this.user = user;

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

    public String getFavorites() {
        return favorites;
    }

    public void setFavorites(String favorites) {
        this.favorites = favorites;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}

