package com.example.bhargavnallani.coursemanager;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Bhargav Nallani on 11/2/2017.
 */

public class userDetailsTables {

    static final String TABLENAME = "user";
    static final String COLUMN_ID = "_id";
    static final String COLUMN_USERNAME ="username";
    static final String COLUMN_PASSWORD = "password";
    static final String COLUMN_FIRSTNAME = "firstname";
    static final String COLUMN_LASTNAME = "lastname";
    static final String COLUMN_PROFILE_PICTURE = "profilepic";

    static public void onCreate(SQLiteDatabase db){

        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLENAME + "(");
        sb.append(COLUMN_ID + " integer primary key autoincrement,");
        sb.append(COLUMN_USERNAME + " text not null,");
        sb.append(COLUMN_PASSWORD + " text not null,");
        sb.append(COLUMN_FIRSTNAME + " text not null,");
        sb.append(COLUMN_LASTNAME + " text not null,");
        sb.append(COLUMN_PROFILE_PICTURE + " blob not null);");

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
