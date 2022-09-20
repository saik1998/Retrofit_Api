package com.firstapp.retrofit_api;

import com.google.gson.annotations.SerializedName;

public class ModelClass {

    private int userId;

    public int getUserId() {
        return userId;
    }

    private int id;

    private String title;

    private String body;

    @SerializedName("author")
    private String reference;

    public String getReference() {
        return reference;
    }



    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
