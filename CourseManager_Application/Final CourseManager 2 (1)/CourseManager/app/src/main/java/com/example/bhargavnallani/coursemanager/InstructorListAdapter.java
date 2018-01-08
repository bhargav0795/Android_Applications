package com.example.bhargavnallani.coursemanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Bhargav Nallani on 11/5/2017.
 */

public class InstructorListAdapter extends RecyclerView.Adapter {

    List<instructorDetails> instructorList;
    Context context;
    instructorDetails instructorObj;
    DataBaseInstructorManager DI;
    String userLogged;

    instructorListAdapterListener listener;

    public InstructorListAdapter(List<instructorDetails> mData, Context context, instructorListAdapterListener listener, String user) {

        this.instructorList = mData;
        this.context = context;
        this.listener = listener;
        this.userLogged = user;

        this.instructorObj = new instructorDetails();
        DI = new DataBaseInstructorManager(context);
    }


    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.instructor_list_inflator, parent, false);
        InstructorListItemHolder holder = new InstructorListItemHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        Log.d("insList", "onBindViewHolder: "+instructorList);

        InstructorListItemHolder holderObj = (InstructorListItemHolder) holder;
        instructorObj = instructorList.get(position);

        Bitmap bmp = BitmapFactory.decodeByteArray(instructorObj.getProfilePicture(), 0, instructorObj.getProfilePicture().length);

        holderObj.instructorListInflatorImage.setImageBitmap(bmp);
        holderObj.instructorName.setText(instructorObj.getFirstName()+" "+instructorObj.getLastName());
        holderObj.instructorEmail.setText(instructorObj.geteMail());

        holderObj.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //display instructor details
                listener.setInstructedToDisplay(instructorObj);
            }
        });

        holderObj.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                //delete instructor

                Log.d("clicked","instructions");
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(context);
                }
                builder.setTitle("Delete instructor")
                        .setMessage("Are you sure you want to delete this instructor?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                DI.deleteInstructor(instructorObj);
                                instructorList= DI.getInstructorList(userLogged);
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

    @Override
    public int getItemCount() {
        return instructorList.size();
    }

    public interface instructorListAdapterListener
    {
        void setInstructedToDisplay(instructorDetails instructorDetailsObj);
        instructorDetails getclickedInstructor();

    }
}
