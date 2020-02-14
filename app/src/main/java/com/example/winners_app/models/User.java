package com.example.winners_app.models;

public class User {
    private String profile_name;
    private String profile_image;

    public User(String name, String image) {
        this.profile_name = name;
        this.profile_image = image;
    }

    public String getName() {
        return profile_name;
    }

    public void setName(String name) {
        this.profile_name = name;
    }

    public String getImage() {
        return profile_image;
    }

    public void setImage(String date) {
        this.profile_image = date;
    }

}
