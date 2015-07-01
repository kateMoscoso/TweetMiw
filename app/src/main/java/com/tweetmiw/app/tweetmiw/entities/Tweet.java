package com.tweetmiw.app.tweetmiw.entities;

public class Tweet {
    private String message;
    private String favorites;
    private String created_at;
    private TwitterUser twitterUser;
    private Long id;

    public Tweet(String message, TwitterUser twitterUser){
        this.message = message;
        this.twitterUser = twitterUser;

    }

    public TwitterUser getTwitterUser() {
        return twitterUser;
    }

    public void setTwitterUser(TwitterUser twitterUser) {
        this.twitterUser = twitterUser;
    }

    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

