package com.example.akhilajana.inclass08_group22;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by akhilajana on 10/14/17.
 */

public class DisplayListItemHolder extends RecyclerView.ViewHolder {
    TextView title, ingredients,url;
    ImageView recipeImg;
    LinearLayout layout;


    public DisplayListItemHolder(View itemView) {
        super(itemView);
        title= (TextView) itemView.findViewById(R.id.title);
        ingredients= (TextView) itemView.findViewById(R.id.ingredients);
        url= (TextView) itemView.findViewById(R.id.url);
        recipeImg= (ImageView) itemView.findViewById(R.id.recipeImg);
        layout= (LinearLayout) itemView.findViewById(R.id.displayListItem);

    }
}
