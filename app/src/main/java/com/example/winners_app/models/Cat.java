package com.example.winners_app.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Cat implements Parcelable {

    private String title;
    private String image;

    public Cat(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public Cat(Cat cat) {
        this.title = cat.title;
        this.image = cat.image;
    }


    protected Cat(Parcel in) {
        title = in.readString();
        image = in.readString();
    }

    public static final Creator<Cat> CREATOR = new Creator<Cat>() {
        @Override
        public Cat createFromParcel(Parcel in) {
            return new Cat(in);
        }

        @Override
        public Cat[] newArray(int size) {
            return new Cat[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(image);
    }
    public void add(String title, String image){
        this.title = title;
        this.image = image;
    }
}