package com.example.arthur.birthdaynotifier;

import android.util.Log;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by arthur on 11.04.16.
 */
public class MyContact {
    private final int ID;
    private String name;
    private GregorianCalendar birthdate;

    public MyContact(int ID, String name, GregorianCalendar birthdate) {
        this.ID = ID;
        this.name = name;
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GregorianCalendar getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(GregorianCalendar birthdate) {
        this.birthdate = birthdate;
    }

    public int getID() {
        return ID;
    }

    /**
     * @return true if contact has birthday
     */
    public boolean hasBirthday() {
        int birthDay = this.birthdate.get(Calendar.DAY_OF_MONTH);
        int birthMonth = this.birthdate.get(Calendar.MONTH);
        int day = new GregorianCalendar().get(Calendar.DAY_OF_MONTH);
        int month = new GregorianCalendar().get(Calendar.MONTH);
        return (birthDay == day) && (birthMonth == month);
    }
}
