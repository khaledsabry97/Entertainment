package com.example.khaledsabry.entertainment.Items;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 22-Jul-18.
 */

public class Classification {

    public enum type
    {
        boxoffice,
        dailyboxoffice,
        topGross;
    }
    private String title;
    private int image;
    private ArrayList<SearchItem> searchItems = new ArrayList<>();
    private type type;

    public Classification.type getType() {
        return type;
    }

    public void setType(Classification.type type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public ArrayList<SearchItem> getSearchItems() {
        return searchItems;
    }

    public void setSearchItems(ArrayList<SearchItem> searchItems) {
        this.searchItems = searchItems;
    }
}
