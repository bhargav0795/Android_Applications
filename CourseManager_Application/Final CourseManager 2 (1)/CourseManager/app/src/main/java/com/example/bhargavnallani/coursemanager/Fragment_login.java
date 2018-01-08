package com.example.bhargavnallani.coursemanager;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_login.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Fragment_login extends Fragment {

    EditText editUserName, editPassword;
    Button buttonLogin;
    TextView textViewSignup;

    DataBaseDataManager dm;

    private OnFragmentInteractionListener mListener;

    public Fragment_login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
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

        //new actionMenu();
        getActivity().setTitle(R.string.coursemanager);

        buttonLogin = getActivity().findViewById(R.id.buttonLogin);
        textViewSignup = getActivity().findViewById(R.id.textViewSignup);
        dm= new DataBaseDataManager(getContext());

        textViewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.gotoSignUp();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editUserName = getActivity().findViewById(R.id.editUserName);
                editPassword = getActivity().findViewById(R.id.editPassword);


                if (!editUserName.getText().toString().equals("") &&!editPassword.getText().toString().equals(""))
                {
                    userDetails user = dm.getUser(editUserName.getText().toString());
                    if (user != null) {
                        if (editUserName.getText().toString().equals(user.getUsername()) &&
                                editPassword.getText().toString().equals(user.getPassword()))
                        {
                            mListener.gotoCourseManager(user);
                        }
                        else
                            Toast.makeText(getContext(), "Username Does not exist, please SIGNUP", Toast.LENGTH_LONG).show();
                    }
                    else if (user == null)
                    {
                        Toast.makeText(getContext(), "Username Does not exist, please SIGNUP", Toast.LENGTH_LONG).show();
                    }

                }
                else
                {
                    Toast.makeText(getContext(), "All fields are mandatory", Toast.LENGTH_LONG).show();
                }
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
        void gotoSignUp();
        void gotoCourseManager(userDetails user);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {

        MenuItem item=menu.findItem(R.id.action_logout);
        item.setVisible(false);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflate) {
        MenuInflater inflater = getActivity().getMenuInflater();
        super.onCreateOptionsMenu(menu, inflate);

        inflater.inflate(R.menu.action_bar, menu);
        menu.findItem(R.id.action_home).setEnabled(false);
        menu.findItem(R.id.action_instructorFrame).setEnabled(false);
        menu.findItem(R.id.action_add_instructor).setEnabled(false);
        menu.findItem(R.id.action_logout).setEnabled(false);


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_home:

                getFragmentManager().beginTransaction()
                        .replace(R.id.mainContainer, new Fragment_login(), "display_course")
                        .addToBackStack(null)
                        .commit();

                Toast.makeText(getActivity(),"Please login to access the menu",Toast.LENGTH_LONG).show();

            case R.id.action_instructorFrame:

                getFragmentManager().beginTransaction()
                        .replace(R.id.mainContainer, new Fragment_login(), "display_course")
                        .addToBackStack(null)
                        .commit();

                Toast.makeText(getActivity(),"Please login to access the menu",Toast.LENGTH_LONG).show();


            case R.id.action_add_instructor:

                getFragmentManager().beginTransaction()
                        .replace(R.id.mainContainer, new Fragment_login(), "display_course")
                        .addToBackStack(null)
                        .commit();

                Toast.makeText(getActivity(),"Please login to access the menu",Toast.LENGTH_LONG).show();

            case R.id.action_logout:

                getFragmentManager().beginTransaction()
                        .replace(R.id.mainContainer, new Fragment_login(), "display_course")
                        .addToBackStack(null)
                        .commit();

                Toast.makeText(getActivity(),"Please login to access the menu",Toast.LENGTH_LONG).show();

            case R.id.action_exit:
                getActivity().finish();
        }
        return true;
    }
}
