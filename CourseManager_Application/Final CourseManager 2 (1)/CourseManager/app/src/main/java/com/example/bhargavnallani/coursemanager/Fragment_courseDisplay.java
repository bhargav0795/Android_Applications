package com.example.bhargavnallani.coursemanager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
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
 * {@link Fragment_courseDisplay.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Fragment_courseDisplay extends Fragment {

    private OnFragmentInteractionListener mListener;
    TextView displayTitle,displayInstructor,displaySchedule, displayCreditHours, displaySemester;
    CourseDetails courseDetails;
    ImageView displayInstructorImg;

    public Fragment_courseDisplay() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        courseDetails = getArguments().getParcelable("course_display");
        setHasOptionsMenu(true);
       // Log.d("hiiiiii", "onCreate: "+courseDetails.toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_course_display, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        displayTitle = getActivity().findViewById(R.id.displayTitle);
        displayInstructor = getActivity().findViewById(R.id.displayInstructor);
        displaySchedule = getActivity().findViewById(R.id.displaySchedule);
        displayCreditHours = getActivity().findViewById(R.id.displayCreditHours);
        displaySemester = getActivity().findViewById(R.id.displaySemester);
        displayInstructorImg = getActivity().findViewById(R.id.displayInstructorImg);

        Bitmap bmp = BitmapFactory.decodeByteArray(courseDetails.getInstructorImg(), 0, courseDetails.getInstructorImg().length);
        displayInstructorImg.setImageBitmap(bmp);
        displayTitle.setText(courseDetails.getCourseTitle());
        displayInstructor.setText(courseDetails.getInstructor());
        displaySchedule.setText(courseDetails.getDay()+" "+courseDetails.getTimeHours()+":"+courseDetails.getTimeMinutes()+" "+courseDetails.getTimeNotation());
        displayCreditHours.setText(courseDetails.getCreditHours());
        displaySemester.setText(courseDetails.getSemester());

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
