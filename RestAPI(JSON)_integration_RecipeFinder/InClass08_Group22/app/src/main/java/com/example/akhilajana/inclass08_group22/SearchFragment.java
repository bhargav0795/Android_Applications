package com.example.akhilajana.inclass08_group22;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SearchFragment extends Fragment implements View.OnClickListener {

    OnFragmentInteractionListener mListener;
    int clicked = 0;

    ArrayList<String> ingredientsList;
    ArrayList<String> ingredientsList1;
    ImageView btnAdd1;
    RecyclerView listView;
    SearchIngredientsAdapter adapter;
    EditText editDish,firstIngredient;
    Button btnSearch1;
    RequestParams params;
    int CountIngredients = 1;
//    int ingredientsListSize =0;


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        ingredientsList = new ArrayList<>();
        ingredientsList1 = new ArrayList<>();

        editDish = (EditText) view.findViewById(R.id.editDish);
        editDish.requestFocus();
        listView = (RecyclerView) view.findViewById(R.id.ingredientsView);
        btnSearch1 = (Button) view.findViewById(R.id.btnSearch);
        firstIngredient = (EditText) view.findViewById(R.id.firstIngredient);
        btnAdd1 = (ImageView) view.findViewById(R.id.first_add_icon);


        btnAdd1.setOnClickListener(this);
        btnSearch1.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnSearch: {

                if (editDish.getText().toString() != null && !editDish.getText().toString().isEmpty()) {


                    ingredientsList.remove(null);
                    ingredientsList1.remove(null);
                    ingredientsList1.remove("");
                    String inputIngredients = "";

                    if(clicked == 0){
                        inputIngredients = firstIngredient.getText().toString();
                    }else {
                        ingredientsList.add(0,firstIngredient.getText().toString());
                    for (int i = 0; i < ingredientsList1.size(); i++) {
                        if (!inputIngredients.isEmpty()) {
                            inputIngredients = inputIngredients + ",";
                        }
                        if (!ingredientsList1.get(i).isEmpty()) {
                            inputIngredients = inputIngredients + ingredientsList1.get(i);
                        }
                    }}
                    Log.d("helooo", "onClick: "+inputIngredients);
                    params = new RequestParams("GET", "http://www.recipepuppy.com/api/?");

                    if(!inputIngredients.isEmpty()) {
                        params.addParam("i", firstIngredient.getText().toString() +"," + inputIngredients);
                       // params.addParam(",", inputIngredients);
                    }
                    params.addParam("q", editDish.getText().toString());

                    new GetDataWithParams(getActivity()).execute(params);


                } else {
                    Toast.makeText(getActivity(), "Please enter a dish name", Toast.LENGTH_SHORT).show();
                }

                break;
            }

            case R.id.first_add_icon:
            {
                if(editDish.getText().toString()!=null && firstIngredient !=null && !firstIngredient.getText().toString().isEmpty()) {

                    btnAdd1.setImageResource(R.drawable.remove);
                    clicked = 1;
                    btnAdd1.setTag("remove");

                    if(view.getTag().toString()=="remove" && ingredientsList.size()>0)
                    {
                        ingredientsList.remove(ingredientsList.get(0));
                    }



                    //ingredientsList = ;
                    ingredientsList.add(0,firstIngredient.getText().toString());
                   // ingredientsList1.add(0,firstIngredient.getText().toString());
                    Log.d("list",ingredientsList.toString());

                    LinearLayoutManager horizontalLayoutManager
                            = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    horizontalLayoutManager.setStackFromEnd(false);
                    listView.setLayoutManager(horizontalLayoutManager);
                    adapter = new SearchIngredientsAdapter(getActivity(),ingredientsList, ingredientsList1);
                    listView.setAdapter(adapter);
                    listView.setHasFixedSize(true);
                    listView.setNestedScrollingEnabled(false);
                    adapter.notifyDataSetChanged();


                }
                else
                {
                    Toast.makeText(getActivity(),"Please enter first ingredient", Toast.LENGTH_SHORT).show();
                }

                break;

            }


        }
    }

    public interface OnFragmentInteractionListener {

        void addIngredients();
        String convertListToString();
        ArrayList<String> getIngList();

    }
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




