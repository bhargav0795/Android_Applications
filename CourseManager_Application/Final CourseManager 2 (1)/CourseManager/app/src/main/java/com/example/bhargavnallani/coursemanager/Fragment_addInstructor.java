package com.example.bhargavnallani.coursemanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_addInstructor.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Fragment_addInstructor extends Fragment {

    private OnFragmentInteractionListener mListener;
    DataBaseInstructorManager dm;
    List<instructorDetails> instructors;
    ArrayList<String> emailList;
    instructorDetails instructor;
    int count =0;
    byte[] instructorProfilePicture;

    ImageView editInstructorProfilePic;
    EditText editInstructorFirstName, editInstructorLastName, editInstructorEmail, editInstructorWebsite;
    Button buttonResetInstructor, buttonAddInstructor;

    public Fragment_addInstructor() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_instructor, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        editInstructorProfilePic = getActivity().findViewById(R.id.instructorProfilePicture);
        editInstructorFirstName = getActivity().findViewById(R.id.instructorFirstName);
        editInstructorLastName = getActivity().findViewById(R.id.instructorLastName);
        editInstructorEmail = getActivity().findViewById(R.id.instructorEmail);
        editInstructorWebsite = getActivity().findViewById(R.id.instructorWebsite);
        buttonAddInstructor = getActivity().findViewById(R.id.instructorBtnAdd);
        buttonResetInstructor = getActivity().findViewById(R.id.instructorBtnReset);

        dm = new DataBaseInstructorManager(getContext());
        emailList = new ArrayList<>();
        getActivity().setTitle(R.string.addInstructor);

        editInstructorProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mListener.setImage();
                Bitmap getImage =  mListener.getResultImg();
                editInstructorProfilePic.setImageBitmap(getImage);

            }
        });

        buttonAddInstructor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bitmap bitmap = mListener.getResultImg();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                if (bitmap != null) {

                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                instructorProfilePicture = baos.toByteArray();
                 }else instructorProfilePicture = null;

                String instructorFirstName = editInstructorFirstName.getText().toString();
                String instructorLastName = editInstructorLastName.getText().toString();
                String instructorEmail = editInstructorEmail.getText().toString();
                String instructorWebsite = editInstructorWebsite.getText().toString();

                if (!instructorFirstName.equals("") && !instructorLastName.equals("") && !instructorEmail.equals("") && !instructorWebsite.equals("") && instructorProfilePicture!= null) {
                    if (isValidEmail(instructorEmail)) {


                    userDetails user = mListener.getUserDetails();
                    String userName = user.getUsername();

                    instructors = dm.getAll();
                    Log.d("instructorDetails", instructors.toString());
                    if (instructors.isEmpty()) {

                        instructor = new instructorDetails(instructorFirstName, instructorLastName, instructorEmail, instructorWebsite, userName, instructorProfilePicture);
                        dm.saveInstructor(instructor);
                        Toast.makeText(getContext(), "Instructor Added", Toast.LENGTH_LONG).show();
                        Log.d("instructorList", dm.getAll().toString());
                    } else {
                        for (int i = 0; i < instructors.size(); i++) {
                            Log.d("email", instructors.get(i).geteMail());

                            emailList.add(instructors.get(i).geteMail());

                            if (emailList.contains(instructorEmail)) {
                                Toast.makeText(getContext(), "Email already taken, select an other one", Toast.LENGTH_LONG).show();
                                count = 0;
                                break;
                            } else {
                                count = 1;
                            }
                        }
                        if (count == 1) {
                            instructor = new instructorDetails(instructorFirstName, instructorLastName, instructorEmail, instructorWebsite, userName, instructorProfilePicture);
                            dm.saveInstructor(instructor);
                            Toast.makeText(getContext(), "Instructor Added", Toast.LENGTH_SHORT).show();

                        }
                    }

                    mListener.gotoInstructors();

                    editInstructorFirstName.setText("");
                    editInstructorLastName.setText("");
                    editInstructorWebsite.setText("");
                    editInstructorEmail.setText("");

                }else Toast.makeText(getContext(), "Enter a valid email address", Toast.LENGTH_SHORT).show();



                }else Toast.makeText(getContext(), "All fields are MANDATORY including the image", Toast.LENGTH_SHORT).show();
            }
        });

        buttonResetInstructor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(getContext());
                }
                builder.setTitle("Reset entry")
                        .setMessage("Are you sure you want to reset this entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                editInstructorFirstName.setText("");
                                editInstructorLastName.setText("");
                                editInstructorWebsite.setText("");
                                editInstructorEmail.setText("");

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
            }
        });



    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name

        void setImage();
        Bitmap getResultImg();

        userDetails getUserDetails();

        void gotoInstructors();
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflate) {
        MenuInflater inflater = getActivity().getMenuInflater();

        inflater.inflate(R.menu.action_bar, menu);

        super.onCreateOptionsMenu(menu, inflate);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_home:

                getFragmentManager().beginTransaction()
                        .replace(R.id.mainContainer, new Fragment_courseList(), "Course_List")
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.action_instructorFrame:

                getFragmentManager().beginTransaction()
                        .replace(R.id.mainContainer, new Fragment_instructorList(), "Add_Instructor")
                        .addToBackStack(null)
                        .commit();
                break;




            case R.id.action_add_instructor:

                getFragmentManager().beginTransaction()
                        .replace(R.id.mainContainer, new Fragment_addInstructor(), "Add_Instructor")
                        .addToBackStack(null)
                        .commit();
                break;


            case R.id.action_logout:

                getFragmentManager().beginTransaction()
                        .replace(R.id.mainContainer, new Fragment_login(), "Login")
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.action_exit:
                getActivity().finish();
        }
        return true;
    }


}
