package com.example.bhargavnallani.coursemanager;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Bhargav Nallani on 11/4/2017.
 */

public class courseDetailsTable {

    static final String TABLENAME = "Course";
    static final String COLUMN_ID = "_id";
    static final String COLUMN_COURSETITLE ="courseTitle";
    static final String COLUMN_COURSEDAY = "courseDay";
    static final String COLUMN_COURSETIMEHOURS = "courseTimeHours";
    static final String COLUMN_COURSETIMEMINUTES = "courseTimeMinutes";
    static final String COLUMN_TIMENOTATION = "timeNotation";
    static final String COLUMN_CREDITHOURS = "credithours";
    static final String COLUMN_SEMESTER = "semester";
    static final String COLUMN_COURSEINSTRUCTOR = "courseInstructor";
    static final String COLUMN_COURSEUSER = "courseUser";
    static final String COLUMN_COURSEINSTRUCTOR_PROFILE_PIC = "courseInstructorProfilePic";

    static public void onCreate(SQLiteDatabase db){

        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLENAME + "(");
        sb.append(COLUMN_ID + " integer primary key autoincrement,");
        sb.append(COLUMN_COURSETITLE + " text not null,");
        sb.append(COLUMN_COURSEDAY + " text not null,");
        sb.append(COLUMN_COURSETIMEHOURS + " text not null,");
        sb.append(COLUMN_COURSETIMEMINUTES + " text not null,");
        sb.append(COLUMN_TIMENOTATION + " text not null,");
        sb.append(COLUMN_CREDITHOURS + " text not null,");
        sb.append(COLUMN_SEMESTER + " text not null,");
        sb.append(COLUMN_COURSEINSTRUCTOR + " text not null,");
        sb.append(COLUMN_COURSEUSER + " text not null,");
        sb.append(COLUMN_COURSEINSTRUCTOR_PROFILE_PIC + " blob not null);");

        try {
            db.execSQL(sb.toString());
        }catch (SQLException e){
            e.printStackTrace();
        };

    }

    static public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " +TABLENAME);
        userDetailsTables.onCreate(db);
    }

}
