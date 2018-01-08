package com.example.bhargavnallani.coursemanager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bhargav Nallani on 11/3/2017.
 */

public class instructorDAO {

    SQLiteDatabase db;

    public instructorDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public long save(instructorDetails instructor) {
        ContentValues values = new ContentValues();
        values.put(instructorDetailsTable.COLUMN_FIRSTNAME, instructor.getFirstName());
        values.put(instructorDetailsTable.COLUMN_LASTNAME, instructor.getLastName());
        values.put(instructorDetailsTable.COLUMN_EMAIL, instructor.geteMail());
        values.put(instructorDetailsTable.COLUMN_WEBSITE, instructor.getWebsite());
        values.put(instructorDetailsTable.COLUMN_USERNAME, instructor.getUserName());
        values.put(instructorDetailsTable.COLUMN_PROFILE_PICTURE, instructor.getProfilePicture());

        return db.insert(instructorDetailsTable.TABLENAME, null, values);

    }

    public boolean update(instructorDetails instructor) {
        ContentValues values = new ContentValues();

        values.put(instructorDetailsTable.COLUMN_FIRSTNAME, instructor.getFirstName());
        values.put(instructorDetailsTable.COLUMN_LASTNAME, instructor.getLastName());
        values.put(instructorDetailsTable.COLUMN_EMAIL, instructor.geteMail());
        values.put(instructorDetailsTable.COLUMN_WEBSITE, instructor.getWebsite());
        values.put(instructorDetailsTable.COLUMN_USERNAME, instructor.getUserName());
        values.put(instructorDetailsTable.COLUMN_PROFILE_PICTURE, instructor.getProfilePicture());

        return db.update(instructorDetailsTable.TABLENAME, values, instructorDetailsTable.COLUMN_ID + "=?", new String[]{instructor.get_id() + ""}) > 0;

        // return false;
    }

    public boolean delete(instructorDetails instructor) {

        return db.delete(instructorDetailsTable.TABLENAME, instructorDetailsTable.COLUMN_ID + "=?", new String[]{instructor.get_id() + ""}) > 0;
        // return false;
    }

    public instructorDetails get(long id) {

        instructorDetails instructor = null;

        Cursor c = db.query(true, instructorDetailsTable.TABLENAME, new String[]{instructorDetailsTable.COLUMN_ID, instructorDetailsTable.COLUMN_FIRSTNAME, instructorDetailsTable.COLUMN_LASTNAME, instructorDetailsTable.COLUMN_EMAIL, instructorDetailsTable.COLUMN_WEBSITE,instructorDetailsTable.COLUMN_USERNAME,instructorDetailsTable.COLUMN_PROFILE_PICTURE }, instructorDetailsTable.COLUMN_ID + "=?", new String[]{id + ""}, null, null, null, null, null);

        if (c!=null && c.moveToFirst()){
            instructor = buildInstructorFromCursor(c);

            if(!c.isClosed()){
                c.close();}
        }

        return instructor;
    }
    public instructorDetails getInstructor(String firstname) {

        instructorDetails instructor = null;

        Cursor c = db.query(true, instructorDetailsTable.TABLENAME, new String[]{instructorDetailsTable.COLUMN_ID, instructorDetailsTable.COLUMN_FIRSTNAME, instructorDetailsTable.COLUMN_LASTNAME, instructorDetailsTable.COLUMN_EMAIL, instructorDetailsTable.COLUMN_WEBSITE,instructorDetailsTable.COLUMN_USERNAME, instructorDetailsTable.COLUMN_PROFILE_PICTURE}, instructorDetailsTable.COLUMN_FIRSTNAME + "=?", new String[]{firstname + ""}, null, null, null, null, null);

        if (c!=null && c.moveToFirst()){
            // user = buildNoteFromCursor(c);
            instructor = buildInstructorFromCursor(c);

            if(!c.isClosed()){
                c.close();}
        }else if (c == null){
            instructor = null;
        }

        return instructor;
    }

    public List<instructorDetails> getInstructorList(String username) {
        // Notes note = null;

        List<instructorDetails> instructorArray = new ArrayList<instructorDetails>();

        Cursor c = db.query(true, instructorDetailsTable.TABLENAME, new String[]{instructorDetailsTable.COLUMN_ID, instructorDetailsTable.COLUMN_FIRSTNAME, instructorDetailsTable.COLUMN_LASTNAME, instructorDetailsTable.COLUMN_EMAIL, instructorDetailsTable.COLUMN_WEBSITE,instructorDetailsTable.COLUMN_USERNAME, instructorDetailsTable.COLUMN_PROFILE_PICTURE}, instructorDetailsTable.COLUMN_USERNAME + "=?", new String[]{username + ""}, null, null, null, null, null);
        if (c!=null && c.moveToFirst()){
            do {
                instructorDetails instructor = buildInstructorFromCursor(c);
                if (instructor != null){
                    instructorArray.add(instructor);
                }
            }while (c.moveToNext());

            if(!c.isClosed()){
                c.close();}

        }
        return instructorArray;
    }


    public List<instructorDetails> getAll() {
        // Notes note = null;

        List<instructorDetails> instructorArray = new ArrayList<instructorDetails>();

        Cursor c = db.query(instructorDetailsTable.TABLENAME, new String[]{instructorDetailsTable.COLUMN_ID, instructorDetailsTable.COLUMN_FIRSTNAME, instructorDetailsTable.COLUMN_LASTNAME, instructorDetailsTable.COLUMN_EMAIL, instructorDetailsTable.COLUMN_WEBSITE, instructorDetailsTable.COLUMN_USERNAME, instructorDetailsTable.COLUMN_PROFILE_PICTURE},null,null,null,null,null);
        if (c!=null && c.moveToFirst()){
            do {
                instructorDetails instructor = buildInstructorFromCursor(c);
                if (instructor != null){
                    instructorArray.add(instructor);
                }
            }while (c.moveToNext());

            if(!c.isClosed()){
                c.close();}

        }
        return instructorArray;
    }

    public instructorDetails buildInstructorFromCursor(Cursor c){

        instructorDetails instructor = null;
        if (c != null){
            instructor = new instructorDetails();

            instructor.set_id(c.getLong(0));
            instructor.setFirstName(c.getString(1));
            instructor.setLastName(c.getString(2));
            instructor.seteMail(c.getString(3));
            instructor.setWebsite(c.getString(4));
            instructor.setUserName(c.getString(5));
            instructor.setProfilePicture(c.getBlob(6));

        }
        return instructor;
    }


}
