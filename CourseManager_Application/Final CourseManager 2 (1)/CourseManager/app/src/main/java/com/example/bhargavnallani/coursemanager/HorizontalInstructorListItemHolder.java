package com.example.bhargavnallani.coursemanager;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by akhilajana on 11/8/17.
 */

public class HorizontalInstructorListItemHolder extends RecyclerView.ViewHolder {

    ImageView instructorImage;
    RadioButton radioButtonCourse;
    TextView textInstructorName;
    LinearLayout layout;

    public HorizontalInstructorListItemHolder(View itemView) {

        super(itemView);

        instructorImage = itemView.findViewById(R.id.instructorImage);
        radioButtonCourse = itemView.findViewById(R.id.radioButtonCourse);
        radioButtonCourse.setChecked(false);
        textInstructorName = itemView. findViewById(R.id.textInstructorName);
        layout = itemView.findViewById(R.id.horizonatal_row_item);

    }
}
