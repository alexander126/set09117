package com.company;

import java.util.Arrays;

public class Main {

    public static int [] selectionSort ( int [] array ) {

        for (int i = 0; i < array.length - 1; i++)
        {
            int index = i;
            for (int j = i + 1; i < array.length; j++){
                if(array[j] < array[index]){
                    index = j;
                }
            }
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
            return array;
    }

     public static void main ( String [] args )
      {

          int [] unsorted = {10 ,34 ,2 ,56 ,7 ,67 ,88 ,42};
          System.out.println (" Unsorted Array "+ Arrays . toString (unsorted));
          int [] sorted = selectionSort (unsorted) ;
           System.out.println (" Sorted Array "+ Arrays. toString (sorted));
      }
}


