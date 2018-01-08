package com.example.bhargavnallani.coursemanager;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by akhilajana on 11/8/17.
 */

public class CourseListItemHolder extends RecyclerView.ViewHolder {

    TextView courseTitleInflator,instructorNameInflator,courseTimeandData;
    ImageView courseListInflatrImage;
    LinearLayout layout;

    public CourseListItemHolder(View view) {

        super(view);

        courseTitleInflator = view.findViewById(R.id.courseTitleInflator);
        instructorNameInflator = view.findViewById(R.id.instructorNameInflator);
        courseTimeandData = view.findViewById(R.id.courseTimeandData);
        courseListInflatrImage = view.findViewById(R.id.courseListInflatrImage);
        layout = view.findViewById(R.id.course_list_row_item);

    }
}
