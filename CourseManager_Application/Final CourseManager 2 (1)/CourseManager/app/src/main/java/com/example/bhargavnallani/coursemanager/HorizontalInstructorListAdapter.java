package com.example.bhargavnallani.coursemanager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Bhargav Nallani on 11/4/2017.
 */

public class HorizontalInstructorListAdapter extends RecyclerView.Adapter {

    List<instructorDetails> instructorList;
    Context mContext;
    horizontalAdapterListener listener;
    //MainActivity activity;
    //Activity course;
    instructorDetails instructorDetailsObj;

    int position;
    int count=0;
//    String isSelected = "false";
   int positionselected;

    public HorizontalInstructorListAdapter(List<instructorDetails> mData, Context context, horizontalAdapterListener listener) {
        this.instructorList = mData;
        this.mContext =  context;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.horizontal_instructor_list_inflator, parent, false);
        HorizontalInstructorListItemHolder viewHolder = new HorizontalInstructorListItemHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final HorizontalInstructorListItemHolder holderObj = (HorizontalInstructorListItemHolder) holder;
        instructorDetailsObj = instructorList.get(position);
        this.position = position;

        Bitmap bmp = BitmapFactory.decodeByteArray(instructorDetailsObj.getProfilePicture(), 0, instructorDetailsObj.getProfilePicture().length);

        holderObj.instructorImage.setImageBitmap(bmp);
        holderObj.textInstructorName.setText(instructorDetailsObj.getFirstName() + " " + instructorDetailsObj.getLastName());
        holderObj.radioButtonCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (count == 0 && !instructorDetailsObj.isSelected) {
                    //clciking on a new object
                    holderObj.radioButtonCourse.setChecked(true);
                    listener.setclickedInstructor(instructorDetailsObj);
                    count=1;
                    positionselected = position;
                    instructorDetailsObj.setSelected(true);
                }
                else if (positionselected == position && instructorDetailsObj.isSelected && count ==1) {
                    //clciking on a sleected obj
                    holderObj.radioButtonCourse.setChecked(false);
                    listener.setclickedInstructor(null);
                    count = 0;
                    instructorDetailsObj.setSelected(false);
                   positionselected = 0;
                }
                else if (count == 1) {
                    holderObj.radioButtonCourse.setChecked(false);
                    Toast.makeText(mContext, "Can only select one instructor, uncheck the already selected instructor to select again", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return instructorList.size();
    }


    public interface horizontalAdapterListener
    {
        void setclickedInstructor(instructorDetails instructorDetails);

        instructorDetails getclickedInstructor();
        //instructorDetails getclickedInstructor();
    }
}
