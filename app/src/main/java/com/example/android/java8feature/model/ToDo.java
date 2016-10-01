package com.example.android.java8feature.model;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ToDo {

    private static final String TAG = ToDo.class.getSimpleName();

    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("completed")
    @Expose
    private Boolean completed;

    /**
     * @return The userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId The userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The completed
     */
    public Boolean getCompleted() {
        return completed;
    }

    /**
     * @param completed The completed
     */
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public void printTodo() {
        Log.d(TAG, this.getTitle());
    }

}