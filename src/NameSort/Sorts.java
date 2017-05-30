package NameSort;
/**
 * filename: Sorts.java 
 * class: Sorts 
 * Description: It applies quick sort algorithm to sort elements in an array
 * If array size is less then 4, it converts to use insertion sort
 *
 * @author Boram Kim
 * @version 1.2
 *
 * Compiler: Java 8 <br>
 * OS: Windows 10 <br>
 * Hardware: HP Laptop <br>
 * @author Boram Kim
 *
 * Log: 
 * 05/20/2017 BK Finished version 1 
 * 05/26/2017 BK My quick sort method is a bit different from Paul's, 
 *               and it does not use sortFirstMiddleLast method. 
 *               I thought mine would be more efficient but I realized it 
 *               might not be the case because mine calls more 
 *               recursive quick sort for the first half and the second half, 
 *               to the maximum of two times on each side. 
 *               I found a problem that one small group of 4 names would come 
 *               after where it should be when the list goes longer.
 * 05/30/2017 BK Upon completing the project, I found that my attempt to use
 *               Paul's quick sort is having a problem in first name. 
 *               Since I don't have much time anymore, I've decided to use
 *               my quick sort method instead. I did take more time on this
  * 
*/
public class Sorts 
{
    // number to decide whether to use quick sort or insertion sort 
    static final int NUMBER = 4; 
    public static void quickSort(Name[] array) 
            throws java.lang.ArrayIndexOutOfBoundsException
    { // helpter method
        quickSort(array, 0 , array.length - 1);
    }
// Following Paul's quick sort method
//    public static void quickSort(Name[] array, int from, int to) 
//            throws java.lang.ArrayIndexOutOfBoundsException 
//    { 
//        int index = 0;
//        if ( (to - from) + 1 > NUMBER)
//        {
//            index = partition(array, from, to);
//            quickSort(array, from, index - 1);
//            quickSort(array, index + 1, to);
//        }            
//        else
//            insertionSort(array, from, to);
//    }
    
    
// BELOW IS MY ORIGINAL QUICKSORT() METHOD   
    /**
     * Utilize quick sort method for array whose size is greater or equal to 4
     * @param array
     * @param from
     * @param to
     * @throws java.lang.ArrayIndexOutOfBoundsException 
     */
    public static void quickSort(Name[] array, int from, int to) 
            throws java.lang.ArrayIndexOutOfBoundsException 
    { // Sort left part of the index (by either quickSort (n > 4)
        // or insertionSort (n <= 4))
        int index = 0;
        index = partition(array, from, to);
        if ((index - from + 1) > NUMBER)
        {
                quickSort(array, from, index);
                quickSort(array, index, to);
        }
        else
        {
            insertionSort(array, from, index);
        }
        // Now sort right part of the index (by either quickSort (n > 4)
        // or insertionSort (n <= 4))
        index = partition(array, index + 1, to);
        if ( (to - index + 1) > NUMBER)
        {
                quickSort(array, from, index);
                quickSort(array, index, to);
        }
         else
            insertionSort(array, from, index);
         }
// Following Paul's partition method    
//    private static int partition(Name[] array, int from, int to) 
//            throws  java.lang.ArrayIndexOutOfBoundsException         
//    {// which sets a midpoint, calls sortFirstMiddleLast, moves data around 
//        //the pivot value, and returns the pivot index
//        
//        int mid = (from + to) / 2;
//        sortFirstMiddleLast(array, from, mid, to); 
//        swap(array, mid, to - 1);
//        
//        int pivotIndex = to - 1;
//        Name pivotVal = array[pivotIndex];
//        int front = from + 1;
//        int back = pivotIndex - 1;
//        
//        while (front <= back)
//        {
//            while (array[front].compareTo(pivotVal) < 0 && front <= back)
//                front++;
//            while (array[back].compareTo(pivotVal) > 0 && front <= back)
//                back--;
//            if (front <= back)
//                swap(array, front, back);            
//        }
//        swap(array, front, pivotIndex);
//        pivotIndex = front;
//        return pivotIndex;
//    }    
    
// BELOW IS MY ORIGINAL PARTITION METHOD    
    /**
     * Partitioning -- dividing to smaller ranges to sort (conquer)
     * @param array name array
     * @param from index to move the element from
     * @param to index to move the element to
     * @return
     * @throws java.lang.ArrayIndexOutOfBoundsException 
     */
    private static int partition(Name[] array, int from, int to) 
    throws  java.lang.ArrayIndexOutOfBoundsException
    {
     int mid = (from + to) / 2;
        // while lower index is less or equal to upper index
        while (from <= to)
        {  
            // while value at ith is lower than value at mid, 
            // proceed to the right
            while (array[from].compareTo(array[mid]) < 0)
                from++;
            // while value at jth is higher than value at mid, 
            // proceed to the left
            while (array[to].compareTo(array[mid]) > 0)
                to--;
            if (to < from)
                break;
            // if array[i] >= array[mid] >= array[j], then swap i and j
            if ( (array[from].compareTo(array[to]) > 0 
                  || array[from].compareTo(array[to]) == 0)
                    && 
                (array[from].compareTo(array[mid]) > 0 
                  || array[from].compareTo(array[mid]) == 0))
                swap(array, from, to);
            // index continue proceeding in their directions
            from++;
            to--;
        }
        return mid;
    }
    /**
     * Insertion sort method to sort arrays whose number is less than 4
     * @param array name array
     * @param from index to move the element from
     * @param to index to move the element to
     * @throws java.lang.ArrayIndexOutOfBoundsException 
     */
    public static void insertionSort(Name[] array, int from, int to)  
                        throws java.lang.ArrayIndexOutOfBoundsException 
    {
        Name temp = null;
        for (int i = from + 1; i <= to; i++)
        {
            temp = array[i];
            for (int j = i; j > from; --j)
            {
                if (temp.compareTo(array[j - 1]) < 0)
                    array[j] = array[j - 1];
                else
                    break;
                array[j - 1] = temp;
            }
        }
    }
    /**
     * Swaps two elements of different indexes in an array
     * @param array name array
     * @param from index to move the element from
     * @param to index to move the element to
     * @throws java.lang.ArrayIndexOutOfBoundsException 
     */
    private static void swap(Name[] array, int from, int to)  
                        throws java.lang.ArrayIndexOutOfBoundsException    
    {
        Name temp = null;
        temp = array[from];
        array[from] = array[to];
        array[to] = temp;
    }
    /**
     * Sort first, middle, and last index 
     * NOTE: UNSED FOR MY SORTING METHOD
     * @param array name array
     * @param from first index
     * @param mid (first + to) / 2
     * @param to last index
     * @throws java.lang.ArrayIndexOutOfBoundsException 
     */
    private static void sortFirstMiddleLast(Name[] array, int from, int mid, 
            int to)  throws java.lang.ArrayIndexOutOfBoundsException  
    { 
        if (array[from].compareTo(array[mid]) > 0)
            swap(array, from, mid);
        if (array[mid].compareTo(array[to]) > 0)
            swap(array, mid, to);
        if(array[from].compareTo(array[to]) > 0)
            swap(array, from, to);
    }   
}
