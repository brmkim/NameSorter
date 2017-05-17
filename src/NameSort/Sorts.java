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
    public static void quickSort(String[] array) 
            throws java.lang.ArrayIndexOutOfBoundsException
    { // helpter method
        quickSort(array, 0 , array.length - 1);
    }
    public static void quickSort(String[] array, int from, int to) 
            throws java.lang.ArrayIndexOutOfBoundsException 
    {
        if (from < to)
        {
            int mid = partition(array, from, to);
            quickSort(array, from, mid);
            quickSort(array, mid + 1, to);
        }
    }
    private static int partition(String[] array, int from, int to) 
            throws  java.lang.ArrayIndexOutOfBoundsException         
    {// " which sets a midpoint, calls sortFirstMiddleLast, moves data around 
        //the pivot value, and returns the pivot index
        int returnVal = 0;
        String pivot = array[from];  
        int i = from - 1;
        int j = to + 1;
        boolean neg = false;
        if (array[i].compareTo(pivot) < 0)
            neg = true; //
        else
            neg = false;   //(array[j].compareTo(pivot) > 0)
        // below needs to be inside one loop?
        do
        {
            i += 1;
        } while (neg);
        do
        {
            j -= 1;
        } while (neg);
        
        if (i >= j)
            returnVal = j;
        //swap(array[i], array[j]);
        return returnVal;
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
                if (temp.compareToIgnoreCase(array[j - i]) < 0)
                    array[j] = array[j - 1];
                else
                    break;
            }
            array[j] = temp;
        }
        
    }
    private static void swap(String[] array, int from, int to)  
throws java.lang.ArrayIndexOutOfBoundsException    
    {// if indices not in array 
        
        // fixed it later (just wrote it arbitrarily
        String temp = "";
        temp = array[from];
        array[from] = array[to];
        array[to] = temp;
    }
    private static void sortFirstMiddleLast(Comparable[] array, int from, 
int mid, int to)  throws java.lang.ArrayIndexOutOfBoundsException  
    {
        
    }
            
}
