package com.corner23.android.sample.recyclerviewsample;

public class PhotoCard {

    private int imageId;
    private String title;

    public PhotoCard(int id, String title) {
        this.imageId = id;
        if (title == null) {
            this.title = "This is photo " + id;
        } else {
            this.title = title;
        }
    }

    public int getImageId() {
        return imageId;
    }

    public String getTitle() {
        return title;
    }
}
