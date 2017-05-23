/*Must contain at least the methods 
1) public static void quickSort(Comparable[] array)   
throws a java.lang.ArrayIndexOutOfBoundsException  if indices not in array  
which merely calls   quickSort(array, 0, array.length - 1) (see below) 
2) public static void quickSort(Comparable[] array, int from, int to)  
throws a java.lang.ArrayIndexOutOfBoundsException  
if indices not in array  which implements the Optimized quick sort algorithm 
as presented in class, utilizing the following methods: 
3) public static void insertionSort(Comparable[] array, int from, int to)  
throws a java.lang.ArrayIndexOutOfBoundsException  
if indices not in array  
4) private static int partition(Comparable[] array, int from, int to)  
throws a java.lang.ArrayIndexOutOfBoundsException  
if indices not in array  which sets a midpoint, calls sortFirstMiddleLast, 
moves data around the pivot value, and returns the pivot index 
5) private static void swap(Comparable[] array, int from, int to)  
throws a java.lang.ArrayIndexOutOfBoundsException  
if indices not in array 
6) private static void sortFirstMiddleLast(Comparable[] array, int from, 
int mid, int to)  throws a java.lang.ArrayIndexOutOfBoundsException  
if indices not in array 
 */
package NameSort;

/**
 *
 * @author boram
 */
public class Sorts 
{
    static final int NUMBER = 10; // number to decide whether to use quick sort or insertion sort 
    public static void quickSort(String[] array) 
            throws java.lang.ArrayIndexOutOfBoundsException
    { // helpter method
        quickSort(array, 0 , array.length - 1);
    }
    public static void quickSort(String[] array, int from, int to) 
            throws java.lang.ArrayIndexOutOfBoundsException 
    {
        int index = partition(array, from, to);
        // Sort left part of the index by either quickSort (n > 3)
        // or insertionSort (n <= 3)
        if ((index - from) > 3)
        {
            if (from < index)
                quickSort(array, from, index);
            if (index < to)
                quickSort(array, index + 1, to);
        }
        else
            insertionSort(array, from, index);
        // Now sort right part of the index by either quickSort (n > 3)
        // or insertionSort (n <= 3)
        index = partition(array, index + 1, to);
        if ( (to - index) > 3)
        {
            if (from < index)
                quickSort(array, from, index);
            if (index < to)
                quickSort(array, index + 1, to);
        }
         else
            insertionSort(array, from, index);

    }
    private static int partition(String[] array, int from, int to) 
            throws  java.lang.ArrayIndexOutOfBoundsException         
    {// " which sets a midpoint, calls sortFirstMiddleLast, moves data around 
        //the pivot value, and returns the pivot index
        int mid = (from + to) / 2;
        sortFirstMiddleLast(array, from, mid, to);
        
        int i = from;
        int j = to;
        mid = (from + to) / 2;
        // while lower index is less or equal to upper index
        while (i <= j)
        {  
            // while value at ith is lower than value at mid, 
            // proceed to the right
            while (array[i].compareTo(array[mid]) < 0)
                i++;
            // while value at jth is higher than value at mid, 
            // proceed to the left
            while (array[j].compareTo(array[mid]) > 0)
                j--;
            // if value in the lower index is greater or equal to the one in
            // the upper index AND is also greater or equal to the value 
            // in the mid point, swap the values in the lower and upper index!
            // (Or simply to put: array[i] >= array[mid] >= array[j] )
            if ( (array[i].compareTo(array[j]) > 0 
                  || array[i].compareTo(array[j]) == 0)
                    && 
                (array[i].compareTo(array[mid]) > 0 
                  || array[i].compareTo(array[mid]) == 0))
                swap(array, i, j);
            // index continue proceeding in their directions
            i++;
            j--;
        }
        return mid;
    }
    public static void insertionSort(String[] array, int from, int to)  
throws java.lang.ArrayIndexOutOfBoundsException 
    {
        String temp = new String();
        for (int i = 1; i < to; i++)
        {
            temp = array[i];
            int j = 0;
            for (j = i; j > from;j--)
            {
                if (temp.compareTo(array[j - 1]) < 0)
                    array[j] = array[j - 1];
                else
                    break;
            }
            array[j] = temp;
        }
    }
    private static void swap(String[] array, int from, int to)  
throws java.lang.ArrayIndexOutOfBoundsException    
    {
        String temp = new String();
        temp = array[from];
        array[from] = array[to];
        array[to] = temp;
    }
    private static void sortFirstMiddleLast(String[] array, int from, 
int mid, int to)  throws java.lang.ArrayIndexOutOfBoundsException  
    {
        int i = from;
        int j = to;
        String midVal = array[mid]; 
        if (array[i].compareTo(midVal) > 0)
            swap(array, i, mid);
        if (array[mid].compareTo(array[j]) > 0)
            swap(array, j, mid);
        if(array[i].compareTo(array[j]) > 0)
            swap(array, i, j);
    }            
}
