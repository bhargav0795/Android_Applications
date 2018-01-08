package com.example.bhargavnallani.coursemanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Bhargav Nallani on 11/4/2017.
 */

public class DataBaseCourseManager {



    private Context mcontext;
    private DataBaseOpenHelper dbOpenHelper;
    private SQLiteDatabase db;
    private courseDAO courseDAO;

    public DataBaseCourseManager(Context mcontext) {
        this.mcontext = mcontext;
        dbOpenHelper = new DataBaseOpenHelper(this.mcontext);
        db = dbOpenHelper.getWritableDatabase();
        courseDAO = new courseDAO(db);
    }



    public void close(){
        if(db!= null){
            db.close();
        }
    }

    public courseDAO getCourseDAO(){
        return this.courseDAO;
    }

    public long saveCourse(CourseDetails course){
        return this.courseDAO.save(course);
    }

    public boolean deleteNote(CourseDetails course){
        return this.courseDAO.delete(course);
    }

    public boolean updateCourse(CourseDetails course){
        return this.courseDAO.update(course);
    }

    public CourseDetails getID(long id){
        return this.courseDAO.get(id);
    }

    public List<CourseDetails> getCourse(String course){return this.courseDAO.getCourse(course);}

    public List<CourseDetails> getAll(){
        return  this.courseDAO.getAll();
    }
}
