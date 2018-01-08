package com.example.bhargavnallani.coursemanager;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Fragment_instructorDisplay.OnFragmentInteractionListener,Fragment_courseDisplay.OnFragmentInteractionListener,Fragment_login.OnFragmentInteractionListener,Fragment_register.OnFragmentInteractionListener,Fragment_courseList.OnFragmentInteractionListener,Fragment_addInstructor.OnFragmentInteractionListener, Fragment_addCourse.OnFragmentInteractionListener, Fragment_instructorList.OnFragmentInteractionListener {

    userDetails userDetails;
    DataBaseInstructorManager dI;
    DataBaseCourseManager Dm;
    DataBaseDataManager DD;
    private String userChoosenTask;
    Bitmap resultImg;
    private static int REQUEST_CAMERA = 0;
    private static int SELECT_FILE = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction()
                .add(R.id.mainContainer, new Fragment_login(), "Login")
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

//        dI.close();
  //      Dm.close();
   //     DD.close();

    }

    public void gotoCourseManager(userDetails user){
        this.userDetails = user;
        getFragmentManager().beginTransaction()
                .replace(R.id.mainContainer, new Fragment_courseList(), "Course_List")
                .addToBackStack(null)
                .commit();
    }

    public void gotoInstructors()
    {
        getFragmentManager().beginTransaction()
                .replace(R.id.mainContainer, new Fragment_instructorList(), "Instructor_List")
                .addToBackStack(null)
                .commit();
    }

    public void gotoSignUp(){
        getFragmentManager().beginTransaction()
                .replace(R.id.mainContainer, new Fragment_register(), "Signup")
                .addToBackStack(null)
                .commit();
    }

    public void gotoCreateCourse(){
        getFragmentManager().beginTransaction()
                .replace(R.id.mainContainer, new Fragment_addCourse(), "Add_Course")
                .addToBackStack(null)
                .commit();
    }

    public userDetails getUserDetails(){
        Log.d("userDetailsMain" ,userDetails.toString());
        return userDetails;
    }


    //Instructors
    @Override
    public List<instructorDetails> getInstructors(String user) {
        dI = new DataBaseInstructorManager(this);
        List<instructorDetails> instructorList = dI.getInstructorList(user);
        return instructorList;
    }

    @Override
    public void setImage() {

        final CharSequence[] options = { "Take Photo", "Choose from Gallery", "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result= Utility.checkPermission(MainActivity.this);
                if (options[item].equals("Take Photo")) {
                    userChoosenTask="Take Photo";
                    if(result)
                        cameraIntent();
                } else if (options[item].equals("Choose from Gallery")) {
                    userChoosenTask="Choose from Gallery";
                    if(result)
                        galleryIntent();
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }


        });
        builder.show();
    }


    public void galleryIntent() {

        Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        //intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);

    }

    public void cameraIntent() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);

    }

    @Override
    public Bitmap getResultImg() {

        return resultImg;
    }


        @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if(userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                resultImg = onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                resultImg = onCaptureImageResult(data);
        }

    }

    private Bitmap onCaptureImageResult(Intent data) {

        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return thumbnail;


    }

    private Bitmap onSelectFromGalleryResult(Intent data) {

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }}
        return (bm);

    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() > 0){
            getFragmentManager().popBackStack();
        }else{

            super.onBackPressed();}
    }

    //Action bar
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.action_bar, menu);

        return super.onCreateOptionsMenu(menu);
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

                gotoInstructors();

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
                finish();
        }
        return true;
    }*/


}
