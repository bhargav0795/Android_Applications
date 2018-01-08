package com.example.bhargavnallani.coursemanager;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
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

import java.io.ByteArrayOutputStream;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_register.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Fragment_register extends Fragment {

    ImageView registerProfilePicture;
    EditText registerFirstName, registerLastName, registerUserName, registerPassword;
    Button buttonRegister;
    private String userChoosenTask;
    byte[] byteArray;


    DataBaseDataManager dm;
    int count = 0;


    private OnFragmentInteractionListener mListener;

    public Fragment_register() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
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


        registerProfilePicture = getActivity().findViewById(R.id.registerProfilePicture);
        registerFirstName = getActivity().findViewById(R.id.registerFirstName);
        registerLastName = getActivity().findViewById(R.id.registerLastName);
        registerUserName = getActivity().findViewById(R.id.registerUserName);
        registerPassword = getActivity().findViewById(R.id.registerPassword);
        buttonRegister = getActivity().findViewById(R.id.buttonRegister);

        dm = new DataBaseDataManager(getContext());
        getActivity().setTitle(R.string.register);

        registerProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mListener.setImage();
                Bitmap getImage = mListener.getResultImg();
                registerProfilePicture.setImageBitmap(getImage);
            }
        });


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bitmap bmp = mListener.getResultImg();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                if (bmp != null) {

                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byteArray = stream.toByteArray();
                }else byteArray = null;

                final String firstName = registerFirstName.getText().toString();
                final String lastName = registerLastName.getText().toString();
                final String userName = registerUserName.getText().toString();
                final String password = registerPassword.getText().toString();

                if (!firstName.equals("") && !lastName.equals("") && !userName.equals("") && !password.equals("") && byteArray != null) {
                    if (password.length() > 4) {


                    final List<userDetails> users = dm.getAll();
                    Log.d("userDetails", users.toString());
                    if (users.isEmpty()) {
                        userDetails userDetails = new userDetails(userName, password, firstName, lastName, byteArray);
                        dm.saveUser(userDetails);
                        Toast.makeText(getContext(), "Welcome to Course Manager!", Toast.LENGTH_SHORT).show();
                        mListener.gotoCourseManager(userDetails);
                    } else {
                        for (int i = 0; i < users.size(); i++) {
                            Log.d("username", users.get(i).getUsername());
                            String user = dm.getUser(users.get(i).getUsername()).getUsername().toString();
                            Log.d("user", user);
                            if (user.equals(userName)) {
                                Toast.makeText(getContext(), "Username already taken, select an other one", Toast.LENGTH_SHORT).show();
                                count = 0;
                                break;
                            } else {
                                count = 1;
                            }
                        }
                        if (count == 1) {
                            userDetails userDetails = new userDetails(userName, password, firstName, lastName, byteArray);
                            dm.saveUser(userDetails);
                            mListener.gotoCourseManager(userDetails);
                        }

                    }
                }else Toast.makeText(getContext(), "Password length should be greater than 4 charecters", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "All fields are MANDATORY", Toast.LENGTH_SHORT).show();
                }
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

        void gotoCourseManager(userDetails user);
        void setImage();
        Bitmap getResultImg();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflate) {
        MenuInflater inflater = getActivity().getMenuInflater();

        inflater.inflate(R.menu.action_bar, menu);

        menu.findItem(R.id.action_home).setEnabled(false);
        menu.findItem(R.id.action_instructorFrame).setEnabled(false);
        menu.findItem(R.id.action_add_instructor).setEnabled(false);
        menu.findItem(R.id.action_logout).setEnabled(false);
        //menu.findItem(R.id.action_home).setEnabled(false);

        super.onCreateOptionsMenu(menu, inflate);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_home:

                getFragmentManager().beginTransaction()
                        .replace(R.id.mainContainer, new Fragment_register(), "display_course")
                        .addToBackStack(null)
                        .commit();

                Toast.makeText(getContext(),"Please login to access the menu",Toast.LENGTH_LONG).show();

            case R.id.action_instructorFrame:

                getFragmentManager().beginTransaction()
                        .replace(R.id.mainContainer, new Fragment_register(), "display_course")
                        .addToBackStack(null)
                        .commit();

                Toast.makeText(getContext(),"Please login to access the menu",Toast.LENGTH_LONG).show();


            case R.id.action_add_instructor:

                getFragmentManager().beginTransaction()
                        .replace(R.id.mainContainer, new Fragment_register(), "display_course")
                        .addToBackStack(null)
                        .commit();

                Toast.makeText(getContext(),"Please login to access the menu",Toast.LENGTH_LONG).show();

            case R.id.action_logout:

                getFragmentManager().beginTransaction()
                        .replace(R.id.mainContainer, new Fragment_register(), "display_course")
                        .addToBackStack(null)
                        .commit();

                Toast.makeText(getContext(),"Please login to access the menu",Toast.LENGTH_LONG).show();

            case R.id.action_exit:
                getActivity().finish();
        }
        return true;
    }
}
