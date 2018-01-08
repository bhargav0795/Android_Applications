package com.example.bhargavnallani.coursemanager;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_instructorList.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Fragment_instructorList extends Fragment {

    private OnFragmentInteractionListener mListener;
    DataBaseInstructorManager dI;
    instructorDetails instructorToBeDisplayed;
    RecyclerView recyclerInstructorsList;
    TextView noInstructorsAvailable;
    InstructorListAdapter InstructorListAdapter;
    RecyclerView.LayoutManager mLayoutmanager;

    public Fragment_instructorList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_instructor_list, container, false);
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

        setHasOptionsMenu(true);

        dI = new DataBaseInstructorManager(getContext());
        instructorToBeDisplayed = new instructorDetails();

        recyclerInstructorsList = getActivity().findViewById(R.id.recyclerInstructorsList);
        noInstructorsAvailable = getActivity().findViewById(R.id.noInstructorsAvailable);

        String user = mListener.getUserDetails().getUsername();

        List<instructorDetails> InstructorsList = dI.getInstructorList(user);

        recyclerInstructorsList.setHasFixedSize(true);
        mLayoutmanager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true);
        recyclerInstructorsList.setLayoutManager(mLayoutmanager);


        if(InstructorsList.size() > 0){
            InstructorListAdapter = new InstructorListAdapter(InstructorsList, getContext(),adapterListener,user);
            recyclerInstructorsList.setAdapter(InstructorListAdapter);

        }else {
            noInstructorsAvailable.setText("You haven’t added any instructor yet, please add at least one instructor to continue " );

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

        userDetails getUserDetails();

    }

    InstructorListAdapter.instructorListAdapterListener adapterListener = new InstructorListAdapter.instructorListAdapterListener() {
        @Override
        public void setInstructedToDisplay(instructorDetails instructorDetailsObj) {

            instructorToBeDisplayed = instructorDetailsObj;

            Fragment_instructorDisplay displayFragment = new Fragment_instructorDisplay();
            Bundle bundle = new Bundle();
            bundle.putParcelable("instructor_display",instructorDetailsObj);
            displayFragment.setArguments(bundle);

            getFragmentManager().beginTransaction()
                        .replace(R.id.mainContainer, displayFragment, "display_instructor")
                        .addToBackStack(null)
                        .commit();

        }

        @Override
        public instructorDetails getclickedInstructor() {
            return instructorToBeDisplayed;
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
