package com.example.bhargavnallani.coursemanager;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_courseList.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Fragment_courseList extends Fragment {

    private OnFragmentInteractionListener mListener;
    ImageView buttonAddCourse;
    RecyclerView recyclerCourseList;
    RecyclerView.Adapter courseListdapter;
    RecyclerView.LayoutManager mLayoutmanager;
    TextView courseListNotAvailable;
    CourseDetails courseDetailsToDisplay;
    //DataBaseCourseManager DC;

    DataBaseCourseManager dc;
    String username;
    ArrayList<CourseDetails> courseList;


    public Fragment_courseList() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        courseList = new ArrayList<>();
        courseDetailsToDisplay = new CourseDetails();
        //courseList = getArguments().getParcelableArrayList("display_course");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course_list, container, false);
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

        courseListNotAvailable = getActivity().findViewById(R.id.courseListNotAvailable);
        recyclerCourseList = getActivity().findViewById(R.id.recyclerCourseList);
        buttonAddCourse = getActivity().findViewById(R.id.buttonAddCourse);
        dc = new DataBaseCourseManager(getContext());

        getActivity().setTitle(R.string.coursemanager);

        //user details
        username = mListener.getUserDetails().getUsername();

        //inflate course list for a user
        recyclerCourseList.setHasFixedSize(true);
        mLayoutmanager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true);
        recyclerCourseList.setLayoutManager(mLayoutmanager);

        if(dc.getCourse(username) != null){
            if(courseList.size() != dc.getCourse(username).size()) {
                courseList.addAll(dc.getCourse(username));
            }
        }




        if (courseList.size() != 0 ) {

            Log.d("COURSELIST",courseList.toString());
            courseListdapter = new CourseListAdapter(courseList, getContext(), adapterListener);
            recyclerCourseList.setAdapter(courseListdapter);
        } else courseListNotAvailable.setText("No courses added to display");

        buttonAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.gotoCreateCourse();

            }
        });
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
        userDetails getUserDetails();
        void gotoCreateCourse();
    }

    CourseListAdapter.courseListAdapterListener adapterListener = new CourseListAdapter.courseListAdapterListener() {
        @Override
        public void setCourseToDisplay(CourseDetails courseDetailObj) {

            courseDetailsToDisplay = courseDetailObj;

            Fragment_courseDisplay displayFragment = new Fragment_courseDisplay();
            Bundle bundle = new Bundle();
            bundle.putParcelable("course_display",courseDetailObj);
            displayFragment.setArguments(bundle);

            getFragmentManager().beginTransaction()
                    .replace(R.id.mainContainer, displayFragment, "display_course")
                    .addToBackStack(null)
                    .commit();
        }
    };

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
