package com.example.winners_app.models;

import android.graphics.drawable.Drawable;

import java.util.Calendar;

public class Competition {
    private String name;
    private String web;
    private String loc;
    private String addr;
    private String note;
    private Calendar datetime;
    private Drawable image;

    public Competition(String name, String web, String loc, String addr, String note, Calendar datetime, Drawable image) {
        this.name = name;
        this.web = web;
        this.loc = loc;
        this.addr = addr;
        this.note = note;
        this.datetime = datetime;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Calendar getDatetime() {
        return datetime;
    }

    public void setDatetime(Calendar datetime) {
        this.datetime = datetime;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}
