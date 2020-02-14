package com.example.winners_app.models;

import android.graphics.drawable.Drawable;

public class Competition {
    private String name;
    private String web;
    private String loc;
    private String addr;
    private String note;
    private int year;
    private int month;
    private int day;
    private int hr;
    private int min;
    private Drawable image;

    public Competition(String name, String web, String loc, String addr, String note, int year, int month, int day, int hr, int min, Drawable image) {
        this.name = name;
        this.web = web;
        this.loc = loc;
        this.addr = addr;
        this.note = note;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hr = hr;
        this.min = min;
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

    public String getLoc_name() {
        return loc;
    }

    public void setLoc_name(String loc) {
        this.loc = loc;
    }

    public String getLocation() {
        return addr;
    }

    public void setLocation(String addr) {
        this.addr = addr;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHr() {
        return hr;
    }

    public void setHr(int hr) {
        this.hr = hr;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}
