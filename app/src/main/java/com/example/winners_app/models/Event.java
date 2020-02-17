package com.example.winners_app.models;

import com.shrikanthravi.collapsiblecalendarview.data.Day;

/**
 * Created by shrikanthravi on 06/03/18.
 */

public class Event {

    private Day mDate;
    private int mColor;
    private String mTitle;

    public Event(int year, int month, int day, int mColor, String mTitle) {
        this.mDate = new Day(year, month, day);
        this.mColor = mColor;
        this.mTitle = mTitle;
    }

    public Day getmDate() {
        return mDate;
    }

    public void setmDate(Day mDate) {
        this.mDate = mDate;
    }

    public int getmColor() {
        return mColor;
    }

    public void setmColor(int mColor) {
        this.mColor = mColor;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}
