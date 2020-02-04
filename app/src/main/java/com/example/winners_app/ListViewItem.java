package com.example.winners_app;

import android.graphics.drawable.Drawable;

public class ListViewItem {
    private int type;

    private String name;

    private Drawable image1 ;
    private Drawable image2 ;
    private Drawable image3 ;

    public void setType(int type) { this.type = type ; }

    public void setName(String title) {
        name = title ;
    }

    public void setImage1(Drawable image) {
        image1 = image ;
    }
    public void setImage2(Drawable image) {
        image2 = image ;
    }
    public void setImage3(Drawable image) {
        image3 = image ;
    }

    public int getType() { return this.type ; }

    public String getName() {
        return this.name ;
    }

    public Drawable getImage1() { return this.image1 ;   }
    public Drawable getImage2() { return this.image2 ;   }
    public Drawable getImage3() { return this.image3 ;   }
}