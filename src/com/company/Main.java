package com.company;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Period;
import java.util.Date;
import java.util.TimeZone;

public class Main {

    public static void main(String[] args) {
	// write your code here
        long seconds = 4100;
        Date date = new Date(seconds * 1000L);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss"); //
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        String time = format.format(date);
        System.out.println(seconds + " seconds equals:"+ time +" in hours.");
    }
}
