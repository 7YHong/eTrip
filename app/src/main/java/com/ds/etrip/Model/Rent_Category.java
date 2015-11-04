package com.ds.etrip.Model;

import java.io.Serializable;

/**
 * Created by 7YHong on 2015/11/4.
 */
public class Rent_Category implements Serializable{
    String text;
    int DrawableID;

    public Rent_Category(String text, int drawableID) {
        this.text = text;
        DrawableID = drawableID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getDrawableID() {
        return DrawableID;
    }

    public void setDrawableID(int drawableID) {
        DrawableID = drawableID;
    }
}
