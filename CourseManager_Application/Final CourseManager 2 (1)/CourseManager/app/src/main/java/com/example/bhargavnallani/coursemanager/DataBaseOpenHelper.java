package com.example.bhargavnallani.coursemanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bhargav Nallani on 11/2/2017.
 */

public class DataBaseOpenHelper extends SQLiteOpenHelper {

    static final String DB_NAME ="myCourseManager1.db";
    static final int DB_VERSION = 2;

    public DataBaseOpenHelper(Context context){
       super(context, DB_NAME,null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        userDetailsTables.onCreate(db);
        instructorDetailsTable.onCreate(db);
        courseDetailsTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        userDetailsTables.onUpgrade(db,oldversion,newversion);
        instructorDetailsTable.onUpgrade(db,oldversion,newversion);
        courseDetailsTable.onUpgrade(db,oldversion,newversion);
    }

}
