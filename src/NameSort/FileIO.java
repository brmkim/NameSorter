package NameSort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
 * filename: FileIO.java 
 * class: FileIO 
 * Description: Opens text file containing names, trim white spaces,
 * remove duplicate entries and finally add it into an ArrayList of Name
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
 * 05/29/2017 BK Added loops to remove duplicate entries here because
 *               doing it using for loop with ArrayList of String is far easier
 *               than handling Name array and Name object
 */
public class FileIO 
{
    File file;
    /**
     * Open text file which contains names
     * @param stage JavaFX state
     * @return File object
     * @throws FileNotFoundException 
     */
    File openFile(Stage stage) throws FileNotFoundException
    {
        String fname = "";
        
        file = new File(fname);
        FileChooser fc = new FileChooser();
        fc.setTitle("Open");
        String currentDir = System.getProperty("user.dir")
                 + File.separator;
        fc.setInitialDirectory(new File(currentDir));
        fc.initialDirectoryProperty();
        fc.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Text Files", 
                    new String[] {"*.TXT", "*.Txt", "*.txt"})); 
        file = fc.showOpenDialog(stage);
        try
        {// check before reading file
            if (file.exists() || file.canRead());
                readFile(file);
        }
        catch (NullPointerException e) { e.getMessage();}
        
        return file;
    }
    /**
     * Read text file and remove any white space and duplicate entry, and
     * put it into an ArrayList of Name that contains separated first and 
     * last names on each object
     * @param selectedFile File object
     * @return ArrayList of Name objects
     */
    ArrayList<Name> readFile(File selectedFile)
    {
        ArrayList<String> strAL = new ArrayList<>();
        ArrayList<String> strALnoDuplicate = new ArrayList<>();
        ArrayList<Name> nameAL = new ArrayList<>();
        BufferedReader br;
        int wsIndex = 0;  // index of white space between first & last 
        try
        {
            try 
            {
                br = new BufferedReader(new FileReader(file));
                String line;
                try 
                {
                    while ((line = br.readLine()) != null)
                    {
                        String temp = line.trim(); // first trim the string
                        if (!temp.equals(""))  // remove the empty line in list
                            strAL.add(temp);
                    }  
                    br.close();
                } catch (IOException ex) { ex.getMessage(); }
            } catch (FileNotFoundException ex) { ex.getMessage(); }
        }catch (NullPointerException e) { e.getMessage();}
         
        // Remove duplicate: this might be inefficient but works
        for (int i = 0; i < strAL.size(); i++)
        {
            String thisName = strAL.get(i);     
            for (int j = 0; j < strAL.size(); j++)
            {
                String thatName = strAL.get(j);
                if (i == j || thisName.equals(thatName))
                    break;
            }
            if(!strALnoDuplicate.contains(thisName))
                strALnoDuplicate.add(thisName);
        }
        // Now create ArrayList<Name>
        final char SPACECHAR = ' ';
        for (int n = 0; n < strALnoDuplicate.size(); n++) 
        {
            try 
            {// set white space as a boundary between first and last names
                wsIndex = strALnoDuplicate.get(n).lastIndexOf(SPACECHAR);
                
                
                // If the length of the element is 1, 
                // it's considered as having only the last name. 
                // It's first name then is a single white space
                if (wsIndex < 0) 
                {
                    nameAL.add(new Name(" ",
                            strALnoDuplicate.get(n).substring(wsIndex + 1)));
                } 
                else 
                {
                    // put first name then last name into ArrayList<Name>
                    nameAL.add(new Name(strALnoDuplicate.get(n).substring(0, wsIndex), 
                    strALnoDuplicate.get(n).substring(wsIndex + 1)));
                }
            } catch (NullPointerException e) { e.getMessage(); }
        }
       
        return nameAL;    
    }
}
