package com.company;

import java.util.Scanner;

public class Main {

    public static int linearSearch(int [] arr, int key){

        for(int i = 0; i < arr.length ;i ++){
            if(arr[i] == key){
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        System.out.println("Enter key");
        int [] data = {223,4,61,347,14,2,1,467,12,23,424,420,615,143,332,516,11,344,976,945,483,543,345,876,678,765,567,654,123,324,523,235};
        int key = input.nextInt();
        int result = linearSearch(data,key);

        if(result == -1)
            System.out.println("Key not found in array");
        else
            System.out.println("Key " + key + " found at index:  " + result);
    }

}
