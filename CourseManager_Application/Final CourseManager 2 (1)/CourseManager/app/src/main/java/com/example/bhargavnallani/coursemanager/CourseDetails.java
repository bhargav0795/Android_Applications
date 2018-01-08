package com.example.bhargavnallani.coursemanager;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

/**
 * Created by Bhargav Nallani on 11/4/2017.
 */

public class CourseDetails implements Parcelable {

    String courseTitle, day, timeHours, timeMinutes, timeNotation, creditHours, semester,
            instructor, user;
    byte [] instructorImg;
    long _id;

    public CourseDetails() {
    }

    public CourseDetails(String courseTitle, String day, String timeHours, String timeMinutes, String timeNotation, String creditHours, String semester, String instructor, String user, byte[] instructorImg) {
        this.courseTitle = courseTitle;
        this.day = day;
        this.timeHours = timeHours;
        this.timeMinutes = timeMinutes;
        this.timeNotation = timeNotation;
        this.creditHours = creditHours;
        this.semester = semester;
        this.instructor = instructor;
        this.user = user;
        this.instructorImg = instructorImg;
    }

    protected CourseDetails(Parcel in) {
        courseTitle = in.readString();
        day = in.readString();
        timeHours = in.readString();
        timeMinutes = in.readString();
        timeNotation = in.readString();
        creditHours = in.readString();
        semester = in.readString();
        instructor = in.readString();
        user = in.readString();
        instructorImg = in.createByteArray();

    }

    public static final Creator<CourseDetails> CREATOR = new Creator<CourseDetails>() {
        @Override
        public CourseDetails createFromParcel(Parcel in) {
            return new CourseDetails(in);
        }

        @Override
        public CourseDetails[] newArray(int size) {
            return new CourseDetails[size];
        }
    };

    public String getTimeNotation() {
        return timeNotation;
    }

    public void setTimeNotation(String timeNotation) {
        this.timeNotation = timeNotation;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTimeHours() {
        return timeHours;
    }

    public void setTimeHours(String timeHours) {
        this.timeHours = timeHours;
    }

    public String getTimeMinutes() {
        return timeMinutes;
    }

    public void setTimeMinutes(String timeMinutes) {
        this.timeMinutes = timeMinutes;
    }

    public String getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(String creditHours) {
        this.creditHours = creditHours;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public byte[] getInstructorImg() {
        return instructorImg;
    }

    public void setInstructorImg(byte[] instructorImg) {
        this.instructorImg = instructorImg;
    }

    @Override
    public String toString() {
        return "CourseDetails{" +
                "courseTitle='" + courseTitle + '\'' +
                ", day='" + day + '\'' +
                ", timeHours='" + timeHours + '\'' +
                ", timeMinutes='" + timeMinutes + '\'' +
                ", timeNotation='" + timeNotation + '\'' +
                ", creditHours='" + creditHours + '\'' +
                ", semester='" + semester + '\'' +
                ", instructor='" + instructor + '\'' +
                ", user='" + user + '\'' +
                ", instructorImg=" + Arrays.toString(instructorImg) +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(courseTitle);
        parcel.writeString(day);
        parcel.writeString(timeHours);
        parcel.writeString(timeMinutes);
        parcel.writeString(timeNotation);
        parcel.writeString(creditHours);
        parcel.writeString(semester);
        parcel.writeString(instructor);
        parcel.writeString(user);
        parcel.writeByteArray(instructorImg);

    }
}
