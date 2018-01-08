package com.example.akhilajana.inclass08_group22;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by akhilajana on 10/2/17.
 */

public class Recipe implements Parcelable{

    String title,href,ingredients,imageURL;

    protected Recipe(Parcel in) {
        title = in.readString();
        href = in.readString();
        ingredients = in.readString();
        imageURL = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public Recipe() {

    }

    public Recipe(String title, String href, String ingredients, String imageURL) {
        this.title = title;
        this.href = href;
        this.ingredients = ingredients;
        this.imageURL = imageURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(href);
        parcel.writeString(ingredients);
        parcel.writeString(imageURL);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "title='" + title + '\'' +
                ", href='" + href + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public static Creator<Recipe> getCREATOR() {
        return CREATOR;
    }
}
