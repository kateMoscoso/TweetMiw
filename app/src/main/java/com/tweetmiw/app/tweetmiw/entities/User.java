package com.tweetmiw.app.tweetmiw.entities;

/**
 * Created by katherin on 02/06/2015.
 */
public class User {
    private String id_user;
    private String screen_name;

    public User(String id_user, String screen_name){
        this.id_user = id_user;
        this.screen_name = screen_name;

    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }
}
