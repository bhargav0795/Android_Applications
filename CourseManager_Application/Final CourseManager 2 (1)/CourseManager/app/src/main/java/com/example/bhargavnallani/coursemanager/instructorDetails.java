package com.example.bhargavnallani.coursemanager;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

/**
 * Created by Bhargav Nallani on 11/3/2017.
 */

public class instructorDetails implements Parcelable{

    String firstName, lastName, eMail, Website, userName;
    byte[] profilePicture;
    Long _id;
    boolean isSelected;

    public instructorDetails() {
    }

    public instructorDetails(String firstName, String lastName, String eMail, String website, String userName, byte[] profilePicture) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.Website = website;
        this.userName = userName;
        this.profilePicture = profilePicture;
    }

    public instructorDetails(String firstName, String lastName, String eMail, String website, byte[] profilePicture) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.Website = website;
        this.profilePicture = profilePicture;
    }

    protected instructorDetails(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        eMail = in.readString();
        Website = in.readString();
        userName = in.readString();
        profilePicture = in.createByteArray();
        isSelected = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(eMail);
        dest.writeString(Website);
        dest.writeString(userName);
        dest.writeByteArray(profilePicture);
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<instructorDetails> CREATOR = new Creator<instructorDetails>() {
        @Override
        public instructorDetails createFromParcel(Parcel in) {
            return new instructorDetails(in);
        }

        @Override
        public instructorDetails[] newArray(int size) {
            return new instructorDetails[size];
        }
    };

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public String toString() {
        return "instructorDetails{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", eMail='" + eMail + '\'' +
                ", Website='" + Website + '\'' +
                ", userName='" + userName + '\'' +
                ", profilePicture=" + Arrays.toString(profilePicture) +
                ", isSelected=" + isSelected +
                '}';
    }
}
