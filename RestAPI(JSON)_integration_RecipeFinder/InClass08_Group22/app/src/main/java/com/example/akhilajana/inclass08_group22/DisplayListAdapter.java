package com.example.akhilajana.inclass08_group22;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by akhilajana on 10/14/17.
 */

public class DisplayListAdapter extends RecyclerView.Adapter {

    ArrayList<Recipe> recipeList;
    Recipe recipe;
    Context context;


    public DisplayListAdapter(ArrayList<Recipe> recipeList, Context context) {
        this.recipeList=recipeList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_item, parent, false);
        DisplayListItemHolder holder=new DisplayListItemHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final DisplayListItemHolder holderObj = (DisplayListItemHolder) holder;
        recipe = recipeList.get(position);

        holderObj.title.setText(recipe.getTitle());
        holderObj.ingredients.setText(recipe.getIngredients());
        if(!recipe.getImageURL().isEmpty() && recipeList.size()>0) {
            Picasso.with(context).load(recipe.getImageURL()).into(holderObj.recipeImg);
        }
        else
        {
            Picasso.with(context).load(R.drawable.no_image_found).into(holderObj.recipeImg);
        }
        holderObj.url.setText(recipe.getHref());

        }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

}
