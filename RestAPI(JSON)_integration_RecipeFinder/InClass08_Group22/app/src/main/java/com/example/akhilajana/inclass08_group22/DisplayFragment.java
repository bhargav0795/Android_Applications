package com.example.akhilajana.inclass08_group22;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DisplayFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class DisplayFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    DisplayListAdapter adapter;
    ArrayList<Recipe> finalList;
    RequestParams params;
    public ArrayList<Recipe> recipeList;

    public DisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        recipeList = new ArrayList<>();

        View view=inflater.inflate(R.layout.fragment_display, container, false);

        RecyclerView listView = (RecyclerView) view.findViewById(R.id.displayView);
        recipeList =  mListener.getResultList();


        if(recipeList.size()>0) {
            Log.d("demo", "onActivityCreated: " + recipeList);
            adapter = new DisplayListAdapter(recipeList, getActivity());
            listView.setAdapter(adapter);
            LinearLayoutManager horizontalLayoutManager
                    = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            listView.setLayoutManager(horizontalLayoutManager);
            listView.setNestedScrollingEnabled(false);
            adapter.notifyDataSetChanged();

            view.findViewById(R.id.btnFinish).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.gotopreviousActivity();
                }
            });
        }
        else
        {
            Toast.makeText(getActivity(), "Result List in Display fragment is empty",Toast.LENGTH_SHORT).show();
        }
        return view;
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
        ArrayList<Recipe> getResultList();
        void gotopreviousActivity();
    }

}
