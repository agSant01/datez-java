//
//  SignInCreateAccount.java
//  Miyan Media Uploader
//
//  Created by Gabriel Santiago on 7/19/17.
//  Copyright © 2017 Richport Solutions. All rights reserved.
//

package com.RichportSolutions.Miyan_Media_Uploader.Datez;

import android.util.Log;

import com.RichportSolutions.Miyan_Media_Uploader.Datez.Exceptions.InvalidDatezStateException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Datez {
    private static final String TAG = "Datez";
    private Date date;
    private Calendar calendar;
    private boolean state;

    private String[] months = {
            "January", "February", "March", "April", "May",
            "June", "July", "August", "September","October",
            "November", "December" };

    private String[] monthsShort = {
            "Jan", "Feb", "Mar", "Apr", "May",
            "Jun", "Jul", "Aug", "Sept","Oct",
            "Nov", "Dec" };

    public Datez(){
        calendar = Calendar.getInstance();
        state = true;
    }

    public Datez(String epochTime){
        Long epochTimeAsLong;
        try{
            epochTimeAsLong = Long.parseLong(epochTime);
            date = new Date(epochTimeAsLong);
            calendar = Calendar.getInstance();
            calendar.setTime(date);
            state = true;
        } catch (NumberFormatException e){
            e.printStackTrace();
            calendar = null;
            date = null;
            state = false;
        }
    }

    public Datez(long epochTime){
        date = new Date(epochTime);
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        state = true;
    }


    public Datez(String formattedDate, SimpleDateFormat formatter){
        Log.d("Datez", formattedDate + "   ");
        try{
            date = formatter.parse(formattedDate);
            calendar = Calendar.getInstance();
            calendar.setTime(date);
            state = true;
        } catch (ParseException e){
            e.printStackTrace();
            calendar = null;
            date = null;
            state = false;
        }
        Log.d("Datez", calendar.toString() + "");
    }

    public Integer secondAsInt(){
        if(!validState()) return -1;
        return calendar.get(Calendar.SECOND);
    }

    public Integer minuteAsInt(){
        if(!validState()) return -1;
        return calendar.get(Calendar.MINUTE);
    }

    public Integer hour24hr(){
        if(!validState()) return -1;
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public Integer dayOfMonth(){
        if(!validState()) return -1;
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public String dayOfMonthAsText(){
        if(!validState()) return "invalid";
        return Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
    }

    public Integer weekOfYear(){
        if(!validState()) return -1;
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public String weekOfYearAsText(){
        if(!validState()) return "invalid";
        return Integer.toString(calendar.get(Calendar.WEEK_OF_YEAR));
    }

    public Integer monthAsInt(){
        if(!validState()) return -1;
        return calendar.get(Calendar.MONTH);
    }

    public String monthAsText(){
        if(!validState()) return "invalid";
        return months[calendar.get(Calendar.MONTH)];
    }

    public String monthAsShortText(){
        if(!validState()) return "invalid";
        return months[calendar.get(Calendar.MONTH)];
    }

    public Integer yearAsInt() {
        if(!validState()) {
            Log.e(TAG, "Invalid state exception: yearsAsInt()");
            return null;
        }
        return calendar.get(Calendar.YEAR);
    }

    public String yearAsText(){
        if(!validState()){
            Log.e(TAG, "Invalid state exception: yearAsText()");
            return null;
        }
        return Integer.toString(calendar.get(Calendar.YEAR));
    }

    public Boolean isBefore(Datez date2) throws InvalidDatezStateException {
        if(!validState()) throw new InvalidDatezStateException("Invalid state exception: isBefore()");
        return this.date.before(new Date(date2.getEpochTimeAsLong()));
    }

    public Boolean isAfter(Datez date2) throws InvalidDatezStateException {
        if(!validState()) throw new InvalidDatezStateException("Invalid state exception: isAfter()");
        return this.date.after(new Date(date2.getEpochTimeAsLong()));
    }

    public String time12hr() {
        if(!validState()) {
            Log.e(TAG, "Invalid state exception: time12hr()");
            return null;
        }
        int i = calendar.get(Calendar.AM_PM);
        String minute = calendar.get(Calendar.MINUTE) + "";
        if (calendar.get(Calendar.MINUTE) < 10){
            minute = "0" + minute;
        }
        return calendar.get(Calendar.HOUR) + ":"
                + minute + " " + (i == 0 ? "AM" : "PM");
    }

    public Boolean validState(){
        return state;
    }

    public Long getEpochTimeAsLong(){
        if(!validState()){
            Log.e(TAG, "Invalid state exception: getEpochTimeAsLong()");
            return null;
        }
        return calendar.getTimeInMillis();
    }

    public String getEpochTimeAsText(){
        return Long.toString(date.getTime());
    }
}
