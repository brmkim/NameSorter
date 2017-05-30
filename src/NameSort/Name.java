package NameSort;

import java.io.Serializable;

/**
 * filename: Name.java 
 * class: Name 
 * Description: Name class 
 *
 * @author Boram Kim
 * @version 1.1
 *
 * Compiler: Java 8 <br>
 * OS: Windows 10 <br>
 * Hardware: HP Laptop <br>
 * @author Boram Kim
 *
 * Log: 
 * 05/20/2017 BK Finished version 1 
 * 05/29/2017 BK Decided not to use the equals method to remove duplicate names
 *               Instead doing the duplicate removal process right in the 
 *               FileIO class upon loading the file 
 */
public class Name implements Serializable, Cloneable, Comparable
{    
    static NameSort nameSortObj = new NameSort();
    private final static String BYFIRST = "byFirst";
    private final static String BYLAST = "byLast";
    private String firstName = "";
    private String lastName = "";
    private static String selectedField = BYLAST; // default
    
    /**
     * Default constructor
     */
    public void Name(){ }
    /**
     * Name constructor that uses two string parameters: one for first and
     * the other one for last name. Last name is selected as default globally.
     * @param fn first name string
     * @param ln last name string
     */
    public Name(String fn, String ln)
    {
        firstName = fn;
        lastName = ln;       
    }
    /**
     * Mutator for selection of name cue to be sorted by
     * @param selected string that indicates either first or last
     */
    public static void setName(String selected) 
    {
        if (selected.equals(BYFIRST))
            selectedField = BYFIRST;
        else
            selectedField = BYLAST;            
    }
    /**
     * CompareTo method that compares by either first or last name as
     * selected by the user. It returns -1 if the current object comes before,
     * 0 if equal, 1 if it comes after. Utilizes the compareToIgnoreCase method
     * of the String properties. 
     * @param o
     * @return
     * @throws ClassCastException 
     */
    @Override
    public int compareTo(Object o)  throws ClassCastException 
    { 
        Name objName = (Name) o;
        if (!(objName instanceof Name))
            throw new ClassCastException();
        int returnVal = 0;
        if (selectedField.equals(BYFIRST))
            returnVal = firstName.compareToIgnoreCase(objName.firstName);
        else if (selectedField.equals(BYLAST))
            returnVal = lastName.compareToIgnoreCase(objName.lastName);
        
        return returnVal;
    }
    /**
     * Overriding toString method. Prints "first last" format or "last, first"
     * format
     * @return string object 
     */
    @Override
    public String toString()
    {  // " returns a String in the format “Last, First” if last is the 
      // selected field; “First Last” otherwise. "
        String returnStr = "";
        if (selectedField.equals(BYFIRST))
            returnStr = firstName + " " + lastName; 
        else if (selectedField.equals(BYLAST))
            returnStr = lastName + ", " + firstName;
        return returnStr;
    }    
    /**
     * Compare if first name and last names are equal 
     * NOTE: THIS IS INCOMPLETE and UNUSED
     *   REMOVING DUPLICATE IS DONE IN FILEIO CLASS
     * @param o comparing string object 
     * @return boolean value
     */
    @Override
    public boolean equals(Object o)
    {// to compare names if they are equal   
        if (o == null)
            return false;
        else if (!(o instanceof String))
            return false;
        return  this.firstName.toLowerCase().equals(o) 
                && this.lastName.toLowerCase().equals(o);
    }
    /**
     * Overriding clone method. Clones name array to be sorted so that  
     * the original name array won't be changed
     * @return Name object
     * @throws CloneNotSupportedException 
     */
    @Override
    public Name clone() throws CloneNotSupportedException 
    { // returns a copy of the name. 
        Name cloned;
        try
        {
            cloned = (Name) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            throw new AssertionError();
        }
        return cloned;
    }
}
