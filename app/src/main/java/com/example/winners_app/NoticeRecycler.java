package com.example.winners_app;

import android.graphics.drawable.Drawable;

public class NoticeRecycler {
    private Drawable iconDrawable ;
    private String titleStr ;
    private String contentsStr ;
    private String createdStr ;

    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setContents(String contents){
        contentsStr = contents ;
    }
    public void setCreated(String created) {
        createdStr = created ;
    }

    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getContents() {
        return this.contentsStr ;
    }
    public String getCreated() {
        return this.createdStr ;
    }
}
