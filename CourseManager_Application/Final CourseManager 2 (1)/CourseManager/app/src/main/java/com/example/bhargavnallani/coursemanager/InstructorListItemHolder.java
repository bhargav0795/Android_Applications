package com.example.bhargavnallani.coursemanager;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by akhilajana on 11/8/17.
 */

public class InstructorListItemHolder extends RecyclerView.ViewHolder {

    ImageView instructorListInflatorImage;
    TextView instructorName,instructorEmail;
    LinearLayout layout;

    public InstructorListItemHolder(View itemView)
    {
        super(itemView);
        instructorListInflatorImage = itemView.findViewById(R.id.instructorListInflatorImage);
        instructorName = itemView.findViewById(R.id.instructorName);
        instructorEmail = itemView.findViewById(R.id.instructorEmail);
        //instructorWebsite = itemView.findViewById(R.id.instructorWebsite);
        layout = itemView.findViewById(R.id.instructor_row_item);
    }
}
