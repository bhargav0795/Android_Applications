package com.example.akhilajana.inclass08_group22;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;


/**
 * Created by akhilajana on 10/14/17.
 */

public class SearchIngredientsItemHolder extends RecyclerView.ViewHolder {
    EditText oneingredient;
    ImageView add_icon;
    LinearLayout layout;


    public SearchIngredientsItemHolder(View itemView) {
        super(itemView);
        oneingredient= (EditText) itemView.findViewById(R.id.oneIngredient);
        add_icon= (ImageView) itemView.findViewById(R.id.add_icon);
        layout= (LinearLayout) itemView.findViewById(R.id.trackListItem1);

    }
}
