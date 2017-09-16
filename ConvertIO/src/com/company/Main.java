package com.company;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

public class Main {

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int seconds = reader.nextInt();
        reader.close();
        Date date = new Date(seconds * 1000L);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        String time = format.format(date);
        System.out.println(seconds +" equals: " + time +" in hours.");
    }
}
