package NameSort;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author boram
 */
public class Name implements Serializable, Cloneable, Comparable
{
    private String firstName = "";
    private String lastName = "";
    private static String selectedField = "";
    private final static String byFirst = "byFirst";
    private final static String byLast = "byLast";
    public Name()
    {
        //defualt constructor
    }
    public Name(String fn, String ln)
    {// "a constructor with two String parameters (first and last name) 
        //have the last name be the default selected field. 
        selectedField = byLast;
        firstName = fn;
        lastName = ln;
    }
    public static void setName(String selected) 
    {
        if (selected.equals("byFirst"))
            selectedField = byFirst;
        else
            selectedField = byLast;            
    }
    @Override
    public int compareTo(Object o)  throws ClassCastException 
    { // returns -1 if the current object comes before, 
      // 0 if equal, 1 if it comes after. Utilizes the 
      // compareToIgnoreCase method of the String properties. 
      // throws a ClassCastException if the two objects are not both Names.
        int returnVal = 0;
        if (selectedField.equals(byFirst))
            returnVal = firstName.compareToIgnoreCase((String) o);
        else
            returnVal = lastName.compareToIgnoreCase((String) o);
//     
        return returnVal;
    }
    @Override
    public String toString()
    {  // " returns a String in the format “Last, First” if last is the 
      // selected field; “First Last” otherwise. "
        String returnStr = "";
        if (selectedField.equals(byFirst))
            returnStr = lastName + ", " + firstName;
        else
            returnStr = firstName + " " + lastName;
        return returnStr;
    }
    @Override
    public boolean equals(Object o)
    {// to compare names if they are equal   
        return  this.firstName.toLowerCase().equals(o);
    }
    @Override
    public Name clone() 
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
