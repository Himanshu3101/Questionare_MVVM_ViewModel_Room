package com.himanshu.questionare.utils;

import android.text.format.DateFormat;

import androidx.room.TypeConverter;

import java.util.Calendar;
import java.util.Date;

public class DateConverter {
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    public static String getCurrentDateTime(){
        Date currentDate =  Calendar.getInstance().getTime();
        String day = (String) DateFormat.format("dd", currentDate); // 20
        String monthString = (String) DateFormat.format("MMM", currentDate); // Jun
        String time = (String) DateFormat.format("hh:mm aa", currentDate.getTime()); // 02:00 am
        String finaDate = day+" "+monthString+" "+time;
        return finaDate;
    }
}
