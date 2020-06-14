//
//  TimeFrame.java
//  Miyan Media Uploader
//
//  Created by Gabriel Santiago on 8/09/17.
//  Copyright © 2017 Richport Solutions. All rights reserved.
//

package com.RichportSolutions.Miyan_Media_Uploader.Datez;

import java.util.concurrent.TimeUnit;

public class TimeFrame  {
    private long dateDifference;
    private boolean state;

    public TimeFrame(Datez recent, Datez old){
        if (recent == null || old == null){
            state = false;
            return;
        }
        this.dateDifference = recent.getEpochTimeAsLong() - old.getEpochTimeAsLong();
        state = true;
    }

    public TimeFrame(Datez old){
        this(new Datez(), old);
    }

    public int yearsApart() {
        int i = (int) TimeUnit.DAYS.convert(dateDifference, TimeUnit.MILLISECONDS);
        return i/365;
    }

    public int daysApart() {
        return (int) TimeUnit.DAYS.convert(dateDifference, TimeUnit.MILLISECONDS);
    }

    public int monthsApart() {
        int i = (int) TimeUnit.DAYS.convert(dateDifference, TimeUnit.MILLISECONDS);
        return i/30;
    }

    public int weeksApart() {
        int i = (int) TimeUnit.DAYS.convert(dateDifference, TimeUnit.MILLISECONDS);
        return i/7;
    }

    public int hoursApart() {
        return (int) TimeUnit.HOURS.convert(dateDifference, TimeUnit.MILLISECONDS);
    }

    public int minutesApart() {
        return (int) TimeUnit.MINUTES.convert(dateDifference, TimeUnit.MILLISECONDS);
    }

    public int secondsApart(){
        return (int) TimeUnit.SECONDS.convert(dateDifference, TimeUnit.MILLISECONDS);
    }

    public boolean hasValidState(){
        return state;
    }

    public String timeAgoSinceDate(){
        return getTimeAgo(false);
    }

    public String timeAgoSinceDateNumeric(){
        return getTimeAgo(true);
    }

    private String getTimeAgo(boolean numericDates){
        if (!hasValidState()) return "invalid state";
        if (yearsApart() >= 2) {
            return yearsApart() + " years ago";
        } else if (yearsApart() >= 1){
            if (numericDates){
                return "1 year ago";
            } else {
                return "Last year";
            }
        } else if (monthsApart() >= 2) {
            return monthsApart() + " months ago";
        } else if (monthsApart() >= 1){
            if (numericDates){
                return "1 month ago";
            } else {
                return "Last month";
            }
        } else if (weeksApart() >= 2) {
            return weeksApart() + " weeks ago";
        } else if (weeksApart() >= 1){
            if (numericDates){
                return "1 week ago";
            } else {
                return "Last week";
            }
        } else if (daysApart() >= 2) {
            return daysApart() + " days ago";
        } else if (daysApart() >= 1){
            if (numericDates){
                return "1 day ago";
            } else {
                return "Yesterday";
            }
        } else if (hoursApart() >= 2) {
            return hoursApart() + " hours ago";
        } else if (hoursApart() >= 1){
            if (numericDates){
                return "1 hour ago";
            } else {
                return "An hour ago";
            }
        } else if (minutesApart() >= 2) {
            return minutesApart() + " minutes ago";
        } else if (minutesApart() >= 1){
            if (numericDates){
                return "1 minute ago";
            } else {
                return "A minute ago";
            }
        } else {
            return "Just now";
        }
    }
}
