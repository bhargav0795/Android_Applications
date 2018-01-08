package com.example.bhargavnallani.coursemanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Bhargav Nallani on 11/3/2017.
 */

public class DataBaseInstructorManager {

    private Context mcontext;
    private DataBaseOpenHelper dbOpenHelper;
    private SQLiteDatabase db;
    private instructorDAO instructorDAO;

    public DataBaseInstructorManager(Context mcontext) {
        this.mcontext = mcontext;
        dbOpenHelper = new DataBaseOpenHelper(this.mcontext);
        db = dbOpenHelper.getWritableDatabase();
        instructorDAO = new instructorDAO(db);
    }
    public void close(){
        if(db!= null){
            db.close();
        }
    }

    public instructorDAO getUserDAO(){
        return this.instructorDAO;
    }

    public long saveInstructor(instructorDetails instructor){
        return this.instructorDAO.save(instructor);
    }

    public boolean deleteInstructor(instructorDetails instructor){
        return this.instructorDAO.delete(instructor);
    }

    public boolean updateUser(instructorDetails instructor){
        return this.instructorDAO.update(instructor);
    }

    public instructorDetails getID(long id){
        return this.instructorDAO.get(id);
    }

    public instructorDetails getInstructor(String instructor){return this.instructorDAO.getInstructor(instructor);}

    public List<instructorDetails> getInstructorList(String username){return this.instructorDAO.getInstructorList(username);}

    public List<instructorDetails> getAll(){
        return  this.instructorDAO.getAll();
    }

}
