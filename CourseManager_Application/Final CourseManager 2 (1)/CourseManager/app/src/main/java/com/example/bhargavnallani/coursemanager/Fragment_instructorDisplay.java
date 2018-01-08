package com.example.bhargavnallani.coursemanager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_instructorDisplay.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Fragment_instructorDisplay extends Fragment {

    private OnFragmentInteractionListener mListener;

    ImageView instructorImage;
    TextView instructorName,instructorWebsite,instructorEmail;
    instructorDetails instructorDetails;



    public Fragment_instructorDisplay() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instructorDetails = getArguments().getParcelable("instructor_display");
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_instructor_display, container, false);
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




        instructorImage = getActivity().findViewById(R.id.instructorImage);
        instructorName = getActivity().findViewById(R.id.instructorName);
        instructorWebsite = getActivity().findViewById(R.id.instructorWebsite);
        instructorEmail = getActivity().findViewById(R.id.instructorEmail);
        instructorImage = getActivity().findViewById(R.id.instructorImage);

        Bitmap bmp = BitmapFactory.decodeByteArray(instructorDetails.getProfilePicture(), 0, instructorDetails.getProfilePicture().length);
        instructorImage.setImageBitmap(bmp);

        //instructorImage.setImageBitmap(instructorDetails.getProfilePicture());
        instructorName.setText( "Instructor name: "+instructorDetails.getFirstName()+" "+instructorDetails.getLastName());
        instructorWebsite.setText("Instructor website: "+instructorDetails.getWebsite());
        instructorEmail.setText("Instructor Email: "+instructorDetails.geteMail());

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
        //instructorDetails getInstructedToDisplay();
        //void setInstructedToDisplay(instructorDetails instructor);
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
