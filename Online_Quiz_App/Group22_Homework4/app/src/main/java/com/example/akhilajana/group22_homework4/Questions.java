package com.example.akhilajana.group22_homework4;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by akhilajana on 10/1/17.
 */

public class Questions implements Parcelable{

    int id,answer;
    String[] choices;
    String text,image;

    public Questions(int id, int answer, String[] choices, String text, String image) {
        this.id = id;
        this.answer = answer;
        this.choices = choices;
        this.text = text;
        this.image = image;
    }

    protected Questions(Parcel in) {
        id = in.readInt();
        answer = in.readInt();
        choices = in.createStringArray();
        text = in.readString();
        image = in.readString();
    }

    @Override
    public String toString() {
        return "Questions{" +
                "id=" + id +
                ", answer=" + answer +
                ", choices=" + Arrays.toString(choices) +
                ", text='" + text + '\'' +
                ", image='" + image + '\'' +
                '}';
    }


    public static final Creator<Questions> CREATOR = new Creator<Questions>() {
        @Override
        public Questions createFromParcel(Parcel in) {
            return new Questions(in);
        }

        @Override
        public Questions[] newArray(int size) {
            return new Questions[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(answer);
        parcel.writeStringArray(choices);
        parcel.writeString(text);
        parcel.writeString(image);
    }

    //Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String[] getChoices() {
        return choices;
    }

    public void setChoices(String[] choices) {
        this.choices = choices;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



}
