import java.util.Arrays;
public class BubbleSort {

    public static void bubbleSort(int array[]){

        int n = array.length;
        int temp = 0;

        for (int i = 0; i < n; i++){
            for (int j = 1; j < (n-i);j ++){
                if (array[j-1] > array[j]){
                    temp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = temp;
                }
            }
        }

    }

    public static void main (String[] args){

        int[] data = {4,37,9,6,23,55,34,0,1};
        System.out.println("Unsorted Array" +Arrays.toString(data));
        bubbleSort(data);
        System.out.println("Sorted Array " + Arrays.toString(data));
    }
}
