package com.example.bilal.madical.model.pojo;

import java.util.ArrayList;

/**
 * Created by bila on 1/30/2017.
 */

public class Task {
    String date;
    String user;
    String doctor;
    String doctor1;
    String doctor2;
    String time;
    String time1;
    String time2;
    String[] Doctors;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getDoctor1() {
        return doctor1;
    }

    public void setDoctor1(String doctor1) {
        this.doctor1 = doctor1;
    }

    public String getDoctor2() {
        return doctor2;
    }

    public void setDoctor2(String doctor2) {
        this.doctor2 = doctor2;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public String[] getDoctors() {
        return Doctors;
    }

    public void setDoctors(String[] doctors) {
        Doctors = doctors;
    }
}
