package com.example.tranvanmanh.week5excersise;

import io.realm.RealmObject;

/**
 * Created by tranvanmanh on 4/4/2018.
 */

public class TaskItem extends RealmObject{

    private String nameTask, date, priority;

    public TaskItem(){}

    public TaskItem(String nameTask, String date, String priority) {
        this.nameTask = nameTask;
        this.date = date;
        this.priority = priority;
    }

    public String getNameTask() {
        return nameTask;
    }

    public String getDate() {
        return date;
    }

    public String getPriority() {
        return priority;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
