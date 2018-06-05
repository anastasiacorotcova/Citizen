package com.nastya.citizen;

import java.io.Serializable;

public class News implements Serializable {

    private String title;
    private String content;
    private String category;
    private String date;
    private String image;

    public News(String title, String content, String category, String date, String image) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.date = date;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }
}
