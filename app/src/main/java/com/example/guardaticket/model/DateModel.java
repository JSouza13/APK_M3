package com.example.guardaticket.model;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class DateModel extends RealmObject {
    @PrimaryKey
    private String id;
    private Date date;

    @Ignore
    private Calendar dateCalendar;

    public DateModel() {
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public Calendar getDate() {
        if(date != null){
            dateCalendar = Calendar.getInstance();
            dateCalendar.setTime(date);
        }
        return dateCalendar;
    }

    public void setDate(Calendar date) {
        this.dateCalendar = date;
        this.date = date.getTime();
    }
}
