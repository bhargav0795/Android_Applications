package com.example.bhargavnallani.coursemanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Bhargav Nallani on 11/2/2017.
 */

public class DataBaseDataManager {
    private Context mcontext;
    private DataBaseOpenHelper dbOpenHelper;
    private SQLiteDatabase db;
    private userDAO UserDAO;

    public DataBaseDataManager(Context context) {
        this.mcontext = context;
        dbOpenHelper = new DataBaseOpenHelper(this.mcontext);
        db = dbOpenHelper.getWritableDatabase();
        UserDAO = new userDAO(db);
    }

    public void close(){
        if(db!= null){
            db.close();
        }
    }

    public userDAO getUserDAO(){
        return this.UserDAO;
    }

    public long saveUser(userDetails user){
        return this.UserDAO.save(user);
    }

    /*public boolean deleteNote(userDetails notes){
        return this.UserDAO.delete(notes);
    }*/

    public boolean updateUser(userDetails notes){
        return this.UserDAO.update(notes);
    }

    public userDetails getID(long id){
        return this.UserDAO.get(id);
    }

    public userDetails getUser(String user){return this.UserDAO.getUser(user);}

    public List<userDetails> getAll(){
        return  this.UserDAO.getAll();
    }

}
