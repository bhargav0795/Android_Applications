package com.example.bhargavnallani.coursemanager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bhargav Nallani on 11/2/2017.
 */

public class userDAO {

    SQLiteDatabase db;

    public userDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public long save(userDetails user) {
        ContentValues values = new ContentValues();
        values.put(userDetailsTables.COLUMN_USERNAME, user.getUsername());
        values.put(userDetailsTables.COLUMN_PASSWORD, user.getPassword());
        values.put(userDetailsTables.COLUMN_FIRSTNAME, user.getFirstname());
        values.put(userDetailsTables.COLUMN_LASTNAME, user.getLastname());
        values.put(userDetailsTables.COLUMN_PROFILE_PICTURE, user.getProfile_picture());
        return db.insert(userDetailsTables.TABLENAME, null, values);

    }

    public boolean update(userDetails user) {
        ContentValues values = new ContentValues();
        values.put(userDetailsTables.COLUMN_USERNAME, user.getUsername());
        values.put(userDetailsTables.COLUMN_PASSWORD, user.getPassword());
        values.put(userDetailsTables.COLUMN_FIRSTNAME, user.getFirstname());
        values.put(userDetailsTables.COLUMN_LASTNAME, user.getLastname());
        values.put(userDetailsTables.COLUMN_PROFILE_PICTURE, user.getProfile_picture());

        return db.update(userDetailsTables.TABLENAME, values, userDetailsTables.COLUMN_ID + "=?", new String[]{user.get_id() + ""}) > 0;

        // return false;
    }

    /*public boolean delete(userDetailsTables user) {

        return db.delete(NotesTable.TABLENAME, NotesTable.COLUMN_ID + "=?", new String[]{user.get_id() + ""}) > 0;
        // return false;
    }*/

    public userDetails get(long id) {

        userDetails user = null;

        Cursor c = db.query(true, userDetailsTables.TABLENAME, new String[]{userDetailsTables.COLUMN_ID, userDetailsTables.COLUMN_USERNAME, userDetailsTables.COLUMN_PASSWORD, userDetailsTables.COLUMN_FIRSTNAME, userDetailsTables.COLUMN_LASTNAME}, userDetailsTables.COLUMN_ID + "=?", new String[]{id + ""}, null, null, null, null, null);

        if (c!=null && c.moveToFirst()){

            user = buildUserFromCursor(c);

            if(!c.isClosed()){
                c.close();}
        }

        return user;
    }
    public userDetails getUser(String username) {

        userDetails user = null;

        Cursor c = db.query(true, userDetailsTables.TABLENAME, new String[]{userDetailsTables.COLUMN_ID, userDetailsTables.COLUMN_USERNAME, userDetailsTables.COLUMN_PASSWORD, userDetailsTables.COLUMN_FIRSTNAME, userDetailsTables.COLUMN_LASTNAME,userDetailsTables.COLUMN_PROFILE_PICTURE}, userDetailsTables.COLUMN_USERNAME + "=?", new String[]{username + ""}, null, null, null, null, null);

        if (c!=null && c.moveToFirst()){
            user = buildUserFromCursor(c);

            if(!c.isClosed()){
                c.close();}
        }else if (c == null){
            user = null;
        }

        return user;
    }


    public List<userDetails> getAll() {
        // Notes note = null;

        List<userDetails> userArray = new ArrayList<userDetails>();

        Cursor c = db.query(userDetailsTables.TABLENAME,new String[]{userDetailsTables.COLUMN_ID, userDetailsTables.COLUMN_USERNAME, userDetailsTables.COLUMN_PASSWORD, userDetailsTables.COLUMN_FIRSTNAME, userDetailsTables.COLUMN_LASTNAME,userDetailsTables.COLUMN_PROFILE_PICTURE},null,null,null,null,null);
        if (c!=null && c.moveToFirst()){
            do {
                userDetails user = buildUserFromCursor(c);
                if (user != null){
                    userArray.add(user);
                }
            }while (c.moveToNext());

            if(!c.isClosed()){
                c.close();}

        }
        return userArray;
    }

    public userDetails buildUserFromCursor(Cursor c){

        userDetails user = null;
        if (c != null){

            user = new userDetails();
            user.set_id(c.getLong(0));
            user.setUsername(c.getString(1));
            user.setPassword(c.getString(2));
            user.setFirstname(c.getString(3));
            user.setLastname(c.getString(4));
            user.setProfile_picture(c.getBlob(5));
        }
        return user;
    }

}
