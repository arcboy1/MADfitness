package com.example.madfitness.POJO;


import java.util.Date;

public class Workout {
    private int id;
    private Date date;

    public Workout(int id, Date date) {
        this.id = id;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Date: " + date;
    }
}
