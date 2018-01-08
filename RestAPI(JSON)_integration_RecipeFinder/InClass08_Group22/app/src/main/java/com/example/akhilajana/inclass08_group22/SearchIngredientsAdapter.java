package com.example.akhilajana.inclass08_group22;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by akhilajana on 10/30/17.
 */

public class SearchIngredientsAdapter extends RecyclerView.Adapter {

    Context mContext;
    int count =1;
    String inputIng;
    ArrayList<String> ingredientsList = null;
    ArrayList<String> ingredientsList1 = null;



    public SearchIngredientsAdapter(Context context, ArrayList<String> ingrList, ArrayList<String> ingrList1) {

        this.mContext = context;
        this.ingredientsList = ingrList;
        ingredientsList1 = ingrList1;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        SearchIngredientsItemHolder holder = new SearchIngredientsItemHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final SearchIngredientsItemHolder holderObj = (SearchIngredientsItemHolder) holder;
        final EditText inputIngredient = holderObj.oneingredient;
        final ImageView addRemoveicon = holderObj.add_icon;

        addRemoveicon.setImageResource(R.drawable.remove);
        addRemoveicon.setTag("remove");
        if(position == ingredientsList.size()-1){

            addRemoveicon.setImageResource(R.drawable.add);
            addRemoveicon.setTag("add");
            inputIngredient.requestFocus();
        }
        else{
            addRemoveicon.setImageResource(R.drawable.remove);
            addRemoveicon.setTag("remove");
        }
        if(ingredientsList1.size() == position){
            ingredientsList1.add(position, "");
        }
        holderObj.oneingredient.setText(ingredientsList1.get(position));

        holderObj.oneingredient.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                ingredientsList1.set(position, s.toString());
            }
        });
        addRemoveicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if("add".equals(v.getTag().toString()) && (inputIngredient.getText().toString().isEmpty() || inputIngredient.getText().toString() == null))
                {
                    Toast.makeText(mContext, "Input an ingredient", Toast.LENGTH_LONG).show();

                }
                else if("add".equals(v.getTag().toString()) && !inputIngredient.getText().toString().isEmpty() && inputIngredient.getText().toString() != null && position< 4)
                {

                    if(ingredientsList.indexOf(inputIngredient.getText().toString()) < 0){
                        addRemoveicon.setImageResource(R.drawable.remove);
                        addRemoveicon.setTag("remove");
                        ingredientsList.add(position, inputIngredient.getText().toString());
                        notifyItemInserted(ingredientsList.size() - 1);

                    }
                    else if(ingredientsList.indexOf(inputIngredient.getText().toString()) < 0 && position == ingredientsList.size()){

                        Toast.makeText(mContext, "Only 5 ingredients can be added.", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(mContext, "Ingredient can't be repeated.", Toast.LENGTH_LONG).show();
                    }
                }
                else if("add".equals(v.getTag().toString()) && position== 4){

                    Toast.makeText(mContext, "Only 5 ingredients can be added.", Toast.LENGTH_LONG).show();
//                    addRemoveicon.setImageResource(R.drawable.remove);
//                    addRemoveicon.setTag("remove");

                }
                else if("remove".equals(v.getTag().toString())){

                    ingredientsList.remove(position);
                    ingredientsList1.remove(position);
                    notifyDataSetChanged();
                }
            }
        });

    }


    @Override
    public int getItemCount() {

        if(ingredientsList.size()< 4)
        {
            return ingredientsList.size();
        }
        else {
            return 4;
        }
    }

}
