package com.tweetmiw.app.tweetmiw.entities;

/**
 * Created by katherin on 02/06/2015.
 */
public class User {
    private ProfileUser profile;
    private boolean seguido;



    public User(){
    }

    public ProfileUser getProfile() {
        return profile;
    }

    public void setProfile(ProfileUser profile) {
        this.profile = profile;
    }

    public boolean isSeguido() {
        return seguido;
    }

    public void setSeguido(boolean seguido) {
        this.seguido = seguido;
    }
}
