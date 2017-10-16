import java.util.Arrays;

public class InsertionSort {
    public static void insertionSort ( int array []) {

        int n = array.length;
        for(int i = 1; i < n; i++){
            int key = array[i];
            int j = i - 1;
            while ((j > -1) && (array[j] > key)){
                array [j + 1] = array [j];
                j--;
            }
            array[j+1] = key;
        }
    }

         public static void main ( String [] args ) {

         int [] data = { 4, 2, 9, 6, 23 , 12 , 34 , 0, 1 };
         System . out . println (" Unsorted Array "+ Arrays . toString ( data ));
         insertionSort ( data );
         System . out . println (" Sorted Array "+ Arrays. toString ( data ) );
         }
}
