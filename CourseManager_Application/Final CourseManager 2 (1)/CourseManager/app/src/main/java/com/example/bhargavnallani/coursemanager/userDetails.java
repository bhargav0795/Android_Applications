package com.example.bhargavnallani.coursemanager;

import java.util.Arrays;

/**
 * Created by Bhargav Nallani on 11/2/2017.
 */

public class userDetails {

    long _id;
    String username, password, firstname, lastname;
    byte [] profile_picture;


    public userDetails() {
    }

    public userDetails(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public userDetails(String username, String password, String firstname, String lastname, byte[] profile_picture) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.profile_picture = profile_picture;
    }

    @Override
    public String toString() {
        return "userDetails{" +
                "_id=" + _id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", profile_picture=" + Arrays.toString(profile_picture) +
                '}';
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public byte[] getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(byte[] profile_picture) {
        this.profile_picture = profile_picture;
    }
}
