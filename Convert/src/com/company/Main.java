package com.company;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int seconds = 4600;
        Date date = new Date(seconds * 1000L);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        String time = format.format(date);
        System.out.println(seconds +" equals: " + time +" in hours.");
    }
}
