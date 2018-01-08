package com.example.bhargavnallani.coursemanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_addCourse.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Fragment_addCourse extends Fragment implements AdapterView.OnItemSelectedListener {

    instructorDetails clickedInstructor;

    private OnFragmentInteractionListener mListener;


    DataBaseCourseManager dm;
    String user;
    List<instructorDetails> instuctorsList;
    ArrayList<CourseDetails> coursesList;
    CourseDetails course;

    EditText editCourseTitle, editCourseHours, editCourseMinutes;
    RadioGroup radioGroupCredits;
    Spinner spinnerDate, spinnerTime, spinnerSemister;
    TextView textInstructorAdd;
    RecyclerView recyclerCourse;
    RecyclerView.Adapter mAdapterHorizontal;
    RecyclerView.LayoutManager mLayoutmanager;
    Button buttonSubmitCourse, buttonResetCourse;
    String[] days = {"SELECT", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"};
    String[] timeNote = {"SELECT", "AM", "PM"};
    String[] semester = {"SELECT", "Fall", "Spring"};
    String courseCredits, day, time, sem;

    public Fragment_addCourse() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_course, container, false);
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


        editCourseTitle = getActivity().findViewById(R.id.editCourseTitle);
        editCourseHours = getActivity().findViewById(R.id.editCourseHours);
        editCourseHours.setFilters(new InputFilter[]{new editFilter("1", "12")});
        editCourseMinutes = getActivity().findViewById(R.id.editCourseMinutes);
        editCourseMinutes.setFilters(new InputFilter[]{new editFilter("0", "59")});
        radioGroupCredits = getActivity().findViewById(R.id.radioGroupCredits);
        spinnerDate = getActivity().findViewById(R.id.spinnerDate);
        spinnerTime = getActivity().findViewById(R.id.spinnerTime);
        spinnerSemister = getActivity().findViewById(R.id.spinnerSemister);
        textInstructorAdd = getActivity().findViewById(R.id.textInstructorAdd);
        recyclerCourse = getActivity().findViewById(R.id.recyclerCourse);
        buttonSubmitCourse = getActivity().findViewById(R.id.buttonSubmitCourse);
        buttonResetCourse = getActivity().findViewById(R.id.buttonResetCourse);

        getActivity().setTitle(R.string.create_course);
        clickedInstructor = new instructorDetails();
        dm = new DataBaseCourseManager(getContext());
        instuctorsList = new ArrayList<instructorDetails>();
        coursesList = new ArrayList<CourseDetails>();

        recyclerCourse.setHasFixedSize(true);
        mLayoutmanager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true);
        recyclerCourse.setLayoutManager(mLayoutmanager);

        //inflate instructors for  a particular user
        user = mListener.getUserDetails().getUsername();
        instuctorsList = mListener.getInstructors(user);
        Log.d("instructorsList", instuctorsList.toString());

        if (instuctorsList.size() > 0) {
            mAdapterHorizontal = new HorizontalInstructorListAdapter(instuctorsList,getActivity(), adapterListener);
            recyclerCourse.setAdapter(mAdapterHorizontal);
        } else {
            textInstructorAdd.setText("You haven’t added any instructor yet, please add at least one instructor to continue ");
            buttonSubmitCourse.setEnabled(false);
        }

        //set spinner values
        spinnerDate.setAdapter(spinnerFunction(days));
        spinnerTime.setAdapter(spinnerFunction(timeNote));
        spinnerSemister.setAdapter(spinnerFunction(semester));

        spinnerDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                day = days[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinnerTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                time = timeNote[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerSemister.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sem = semester[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        radioGroupCredits.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.oneCredit:
                        courseCredits = "1";
                        break;
                    case R.id.twoCredit:
                        courseCredits = "2";
                        break;
                    case R.id.threeCredit:
                        courseCredits = "3";
                        break;
                }
            }
        });

        buttonResetCourse.setOnClickListener(new View.OnClickListener() {
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
                                editCourseTitle.setText("");
                                editCourseMinutes.setText("");
                                editCourseHours.setText("");
                                radioGroupCredits.clearCheck();
                                spinnerSemister.setSelection(0);
                                spinnerDate.setSelection(0);
                                spinnerTime.setSelection(0);
                                sem = null;
                                time = null;
                                semester = null;


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

        buttonSubmitCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String courseTitle = editCourseTitle.getText().toString();
                String timeHours = editCourseHours.getText().toString();
                String timeMinutes = editCourseMinutes.getText().toString();


                if ((courseTitle != null && instuctorsList.size() >0 && timeHours != null && timeMinutes != null && courseCredits != null && day != null && time != null && sem != null && day != "SELECT" && time != "SELECT" && sem != "SELECT") &&
                    (!courseTitle.isEmpty() && !instuctorsList.isEmpty() && !timeHours.isEmpty() && !timeMinutes.isEmpty() && !courseCredits.isEmpty() && !day.isEmpty() && !time.isEmpty() && !sem.isEmpty()))
                {

                  //  clickedInstructor = mListener.getclickedInstructor();
                    if (clickedInstructor != null) {

                        String instructor = clickedInstructor.getFirstName();
                        byte[] instructorImg = clickedInstructor.getProfilePicture();
                        course = new CourseDetails(courseTitle, day, timeHours, timeMinutes, time, courseCredits, sem, instructor, user,instructorImg);

                        dm.saveCourse(course);
                      //  coursesList.addAll(dm.getAll());
                        Log.d("CourseList",dm.getAll().toString());

                        mListener.gotoCourseManager(mListener.getUserDetails());
                        Toast.makeText(getContext(), "Course created", Toast.LENGTH_LONG).show();


                    } else
                        Toast.makeText(getContext(), "Select an instructor", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getContext(), "All fields are mandatory", Toast.LENGTH_LONG).show();
            }
        });

    }

    public ArrayAdapter spinnerFunction(String[] str) {

        ArrayAdapter aa = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, str);
        //ArrayAdapter adapter = new ArrayAdapter()
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        return aa;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    HorizontalInstructorListAdapter.horizontalAdapterListener adapterListener = new HorizontalInstructorListAdapter.horizontalAdapterListener() {
        @Override
        public void setclickedInstructor(instructorDetails instructorDetails) {
            clickedInstructor = instructorDetails;
        }

        @Override
        public instructorDetails getclickedInstructor() {
            return clickedInstructor;
        }
    };

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
        List<instructorDetails> getInstructors(String user);
        void gotoCourseManager(userDetails user);

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
