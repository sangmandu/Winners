package com.example.winners_app;

import android.graphics.drawable.Drawable;

public class PointRankItem {
    private Drawable iconDrawable;
    private String NameStr;
    private String PointStr;

    public void setIcon(Drawable icon){
        iconDrawable = icon;
    }

    public void setName(String Name){
        NameStr = Name;
    }
    public void setPoint(String Point){
        PointStr = Point;
    }

    public Drawable getIcon(){ return this.iconDrawable; }
    public String getName(){
        return this.NameStr;
    }
    public String getPoint(){
        return this.PointStr;
    }
}
