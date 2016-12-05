package com.example.best.the.androidproject;

import java.util.Calendar;

/**
 * Created by NP550P5C-SO4PL on 2016-12-04.
 */

public class TestTask {

    private String name;
    private Calendar startDate;

    public TestTask(String name, Calendar startDate){
        this.name = name;
        this.startDate = startDate;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }
}
