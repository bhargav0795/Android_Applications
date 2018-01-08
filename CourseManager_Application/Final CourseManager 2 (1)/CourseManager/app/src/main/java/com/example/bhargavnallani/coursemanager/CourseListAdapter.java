package com.example.bhargavnallani.coursemanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Bhargav Nallani on 11/5/2017.
 */

public class CourseListAdapter extends RecyclerView.Adapter {

    List<CourseDetails> courseList;

    Context context;
    MainActivity activity;
    CourseDetails courseObj;

    DataBaseCourseManager dc;
    courseListAdapterListener listener;

    public CourseListAdapter(List<CourseDetails> mData,Context context, courseListAdapterListener listener) {

        this.courseList = mData;
        this.context = context;
        this.listener = listener;


        dc = new DataBaseCourseManager(context);
        courseObj = new CourseDetails();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_list_inflator, parent, false);
        CourseListItemHolder holder = new CourseListItemHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        CourseListItemHolder holderObj = (CourseListItemHolder) holder;
        courseObj = courseList.get(position);

        Bitmap bmp = BitmapFactory.decodeByteArray(courseObj.getInstructorImg(), 0, courseObj.getInstructorImg().length);

        holderObj.courseListInflatrImage.setImageBitmap(bmp);
        holderObj.courseTitleInflator.setText(courseObj.getCourseTitle());
        holderObj.instructorNameInflator.setText(courseObj.getInstructor());
        holderObj.courseTimeandData.setText(courseObj.getDay() + " "+ courseObj.getTimeHours()+":"+courseObj.getTimeMinutes()+" "+courseObj.getTimeNotation());


        holderObj.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //display course details
                listener.setCourseToDisplay(courseObj);

            }
        });

        holderObj.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                //delete course

                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(context);
                }
                builder.setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                courseList.remove(position);
                                dc.deleteNote(courseObj);
                                notifyDataSetChanged();
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                return true;
            }
        });
    }

    public interface courseListAdapterListener
    {
        void setCourseToDisplay(CourseDetails courseDetailObj);

    }
}

