package com.example.bhargavnallani.coursemanager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bhargav Nallani on 11/4/2017.
 */

public class courseDAO {
    SQLiteDatabase db;


    public courseDAO(SQLiteDatabase db) {
        this.db = db;
    }


    public long save(CourseDetails course) {
        ContentValues values = new ContentValues();
        values.put(courseDetailsTable.COLUMN_COURSETITLE, course.getCourseTitle());
        values.put(courseDetailsTable.COLUMN_COURSEDAY, course.getDay());
        values.put(courseDetailsTable.COLUMN_COURSETIMEHOURS, course.getTimeHours());
        values.put(courseDetailsTable.COLUMN_COURSETIMEMINUTES, course.getTimeMinutes());
        values.put(courseDetailsTable.COLUMN_TIMENOTATION, course.getTimeNotation());
        values.put(courseDetailsTable.COLUMN_CREDITHOURS, course.getCreditHours());
        values.put(courseDetailsTable.COLUMN_SEMESTER, course.getSemester());
        values.put(courseDetailsTable.COLUMN_COURSEINSTRUCTOR, course.getInstructor());
        values.put(courseDetailsTable.COLUMN_COURSEUSER, course.getUser());
        values.put(courseDetailsTable.COLUMN_COURSEINSTRUCTOR_PROFILE_PIC,course.getInstructorImg());
        return db.insert(courseDetailsTable.TABLENAME, null, values);

    }

    public boolean update(CourseDetails course) {
        ContentValues values = new ContentValues();
        values.put(courseDetailsTable.COLUMN_COURSETITLE, course.getCourseTitle());
        values.put(courseDetailsTable.COLUMN_COURSEDAY, course.getDay());
        values.put(courseDetailsTable.COLUMN_COURSETIMEHOURS, course.getTimeHours());
        values.put(courseDetailsTable.COLUMN_COURSETIMEMINUTES, course.getTimeMinutes());
        values.put(courseDetailsTable.COLUMN_TIMENOTATION, course.getTimeNotation());
        values.put(courseDetailsTable.COLUMN_CREDITHOURS, course.getCreditHours());
        values.put(courseDetailsTable.COLUMN_SEMESTER, course.getSemester());
        values.put(courseDetailsTable.COLUMN_COURSEINSTRUCTOR, course.getInstructor());
        values.put(courseDetailsTable.COLUMN_COURSEUSER, course.getUser());
        values.put(courseDetailsTable.COLUMN_COURSEINSTRUCTOR_PROFILE_PIC,course.getInstructorImg());
        return db.update(courseDetailsTable.TABLENAME, values, courseDetailsTable.COLUMN_ID + "=?", new String[]{course.get_id() + ""}) > 0;

        // return false;
    }

    public boolean delete(CourseDetails course) {

        return db.delete(courseDetailsTable.TABLENAME, courseDetailsTable.COLUMN_ID + "=?", new String[]{course.get_id() + ""}) > 0;
        // return false;
    }

    public CourseDetails get(long id) {

        CourseDetails course = null;

        Cursor c = db.query(true, courseDetailsTable.TABLENAME, new String[]{courseDetailsTable.COLUMN_ID,courseDetailsTable.COLUMN_COURSETITLE,courseDetailsTable.COLUMN_COURSEDAY,courseDetailsTable.COLUMN_COURSETIMEHOURS,courseDetailsTable.COLUMN_COURSETIMEMINUTES,courseDetailsTable.COLUMN_TIMENOTATION,courseDetailsTable.COLUMN_CREDITHOURS,courseDetailsTable.COLUMN_SEMESTER,courseDetailsTable.COLUMN_COURSEINSTRUCTOR,courseDetailsTable.COLUMN_COURSEUSER,courseDetailsTable.COLUMN_COURSEINSTRUCTOR_PROFILE_PIC }, courseDetailsTable.COLUMN_ID + "=?", new String[]{id + ""}, null, null, null, null, null);

        if (c!=null && c.moveToFirst()){
            course = buildCourseFromCursor(c);

            if(!c.isClosed()){
                c.close();}
        }

        return course;
    }
   /* public CourseDetails getCourse(String username) {

        CourseDetails course = null;

        Cursor c = db.query(true, courseDetailsTable.TABLENAME, new String[]{courseDetailsTable.COLUMN_ID, courseDetailsTable.COLUMN_COURSETITLE, courseDetailsTable.COLUMN_COURSEDAY, courseDetailsTable.COLUMN_COURSETIMEHOURS, courseDetailsTable.COLUMN_COURSETIMEMINUTES, courseDetailsTable.COLUMN_TIMENOTATION,courseDetailsTable.COLUMN_CREDITHOURS,courseDetailsTable.COLUMN_SEMESTER, courseDetailsTable.COLUMN_COURSEINSTRUCTOR, courseDetailsTable.COLUMN_COURSEUSER }, courseDetailsTable.COLUMN_COURSEUSER + "=?", new String[]{username + ""}, null, null, null, null, null);

        if (c!=null && c.moveToFirst()){
            // user = buildNoteFromCursor(c);
            course = buildCourseFromCursor(c);

            if(!c.isClosed()){
                c.close();}
        }else if (c == null){
            course = null;
        }

        return course;
    }*/

    public List<CourseDetails> getCourse(String username) {
        // Notes note = null;

        List<CourseDetails> courseArray = new ArrayList<CourseDetails>();

        Cursor c = db.query(true, courseDetailsTable.TABLENAME, new String[]{courseDetailsTable.COLUMN_ID,courseDetailsTable.COLUMN_COURSETITLE,courseDetailsTable.COLUMN_COURSEDAY,courseDetailsTable.COLUMN_COURSETIMEHOURS,courseDetailsTable.COLUMN_COURSETIMEMINUTES,courseDetailsTable.COLUMN_TIMENOTATION,courseDetailsTable.COLUMN_CREDITHOURS,courseDetailsTable.COLUMN_SEMESTER,courseDetailsTable.COLUMN_COURSEINSTRUCTOR,courseDetailsTable.COLUMN_COURSEUSER, courseDetailsTable.COLUMN_COURSEINSTRUCTOR_PROFILE_PIC },courseDetailsTable.COLUMN_COURSEUSER + "=?", new String[]{username + ""}, null, null, null, null, null);
        if (c!=null && c.moveToFirst()){
            do {
                CourseDetails course = buildCourseFromCursor(c);
                if (course != null){
                    courseArray.add(course);
                }
            }while (c.moveToNext());

            if(!c.isClosed()){
                c.close();}

        }
        return courseArray;
    }


    public List<CourseDetails> getAll() {
        // Notes note = null;

        List<CourseDetails> courseArray = new ArrayList<CourseDetails>();

        Cursor c = db.query(courseDetailsTable.TABLENAME,new String[]{courseDetailsTable.COLUMN_ID, courseDetailsTable.COLUMN_COURSETITLE, courseDetailsTable.COLUMN_COURSEDAY, courseDetailsTable.COLUMN_COURSETIMEHOURS, courseDetailsTable.COLUMN_COURSETIMEMINUTES, courseDetailsTable.COLUMN_TIMENOTATION,courseDetailsTable.COLUMN_CREDITHOURS,courseDetailsTable.COLUMN_SEMESTER, courseDetailsTable.COLUMN_COURSEINSTRUCTOR, courseDetailsTable.COLUMN_COURSEUSER,courseDetailsTable.COLUMN_COURSEINSTRUCTOR_PROFILE_PIC },null,null,null,null,null);
        if (c!=null && c.moveToFirst()){
            do {
                CourseDetails course = buildCourseFromCursor(c);
                if (course != null){
                    courseArray.add(course);
                }
            }while (c.moveToNext());

            if(!c.isClosed()){
                c.close();}

        }
        return courseArray;
    }

    public CourseDetails buildCourseFromCursor(Cursor c){

        CourseDetails course = null;
        if (c != null){

            course = new CourseDetails();
            course.set_id(c.getLong(0));
            course.setCourseTitle(c.getString(1));
            course.setDay(c.getString(2));
            course.setTimeHours(c.getString(3));
            course.setTimeMinutes(c.getString(4));
            course.setTimeNotation(c.getString(5));
            course.setCreditHours(c.getString(6));
            course.setSemester(c.getString(7));
            course.setInstructor(c.getString(8));
            course.setUser(c.getString(9));
            course.setInstructorImg(c.getBlob(10));

        }
        return course;
    }

}
