package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of students in the group:");
        int studentsNum = Integer.parseInt(input.nextLine());
        int[] matricNum = new int [studentsNum];

        for (int i = 0;i < matricNum.length; i++)
        {
            System.out.println("Enter your matriculation number here: ");
            matricNum[i] = input.nextInt();
        }
    }
}
