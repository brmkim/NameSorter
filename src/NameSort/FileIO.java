package NameSort;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author boram
 */
public class FileIO 
{
    File file;
   File openFile(Stage stage)
    {
        String fname = "";
        
        file = new File(fname);
        FileChooser fc = new FileChooser();
        fc.setTitle("Open");
        String currentDir = System.getProperty("user.dir")
                 + File.separator;
        StringBuilder sb = null;
        fc.setInitialDirectory(new File(currentDir));
        // or fx.setInitialDirectory(new File("."));
//        fc.getExtensionFilters().addAll(
//            new FileChooser.ExtensionFilter("Dictionary Files", 
//                    new String[] {"*.Dic", "*.DIC", "*.dic"})); 
        fc.initialDirectoryProperty();
       // File selectedFile = fc.showOpenDialog(stage);
        file = fc.showOpenDialog(stage);
        // creating an ArrayList because the getJumble event handler calls
        // this method that also calls readDic method which returns ArrayList
        try
        {// check before reading dic file
            if (file.exists() && file.canRead());
                readFile(file); 
        }
        catch (NullPointerException e)
        {
            // how to handle it?
        }
        return file;
    }
    ArrayList<String> readFile(File selectedFile)
    {
        ArrayList<String> namesAL = new ArrayList<>();
        // return value was StringBuilder
        StringBuilder sb = new StringBuilder();   
        String currentLine = "";
        
        try
        {
            BufferedReader br;
            try (FileReader fr = new FileReader(selectedFile)) 
            {
                br = new BufferedReader(fr);
                while(currentLine != null)                
                { // while the currentLine is not null, read the line from the
                    // BufferedREader as string, and append it to ArrayList
                    currentLine = br.readLine();
                    namesAL.add(currentLine);
                }
                fr.close();
            }
            br.close();  // gotta close FileReader and BufferedReader!
        }
        catch (IOException ex) 
        {
            ex.getMessage();
        }

        return namesAL;
    }
}
