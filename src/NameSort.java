/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * filename: NameSort.java
 * class: NameSort
 * Description...
 *  
 * @author Boram Kim 
 * @version 1.0
 * 
 * Compiler: Java 8 <br>
 * OS: Windows 10 <br>
 * Hardware: HP Laptop <br>
 * @author Boram Kim 
 * 
 * Log:
 * 05/13/2017 BK Finished making JavaFX GUI form
 */
public class NameSort extends Application {
    BorderPane pane = new BorderPane();
    FileIO fileIO = new FileIO();
    ArrayList<String> namesAL = new ArrayList<>();
    @Override
    public void start(Stage primaryStage) {
        System.out.println("start method is invoked");
                
        pane.setTop(menuHBox(primaryStage));
        pane.setCenter(centerGridPane(""));
        pane.setBottom(textAreaPane(namesAL));
        
        Scene scene = new Scene(pane);
        primaryStage.setMinWidth(200);
        primaryStage.setMinHeight(500);        
        primaryStage.setTitle("Name Sorter -- by Boram Kim");
        primaryStage.getIcons().add(new Image("list.jpg"));
        primaryStage.setScene(scene);
        primaryStage.show();
       
    }
    private HBox menuHBox(Stage primaryStage)
    {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(0, 0, 5, 0));

        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");        
        MenuItem open = new MenuItem("Open");
        MenuItem save = new MenuItem("Save");
        MenuItem exit = new MenuItem("Exit");
        
        menuBar.getMenus().addAll(menuFile);
        menuBar.setMinWidth(800);
        menuFile.getItems().addAll(open, save,exit);
        hBox.getChildren().addAll(menuBar);
        open.setOnAction((ActionEvent t) -> {
            // get ArrayList of names from file
            namesAL = fileIO.readFile(fileIO.openFile(primaryStage));
           
//            for (String s: namesAL)
//                  System.out.println(s + " ");  // testing purpose
            // add file name to the GUI label
            Label fnLabel = new Label();
            String fName = fileIO.file.getName();
            pane.setCenter(centerGridPane(fName));
            pane.setBottom(textAreaPane(namesAL));
            
        });
        exit.setOnAction((ActionEvent t) -> {
            System.exit(0);
        });
        return hBox;
    }
  
    private GridPane centerGridPane(String fname)
    {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(5, 5, 5, 5));
        
        // file name
        Text filename = new Text("File Name: " + fname);
        filename.setFont(Font.font("Arial", 15));
        filename.setFill(Color.BLUE);
        gridPane.add(filename, 1, 0);
        // a label 
        Label label = new Label("Sort by: ");
        gridPane.add(label, 19, 0);
        // radio buttons
        RadioButton firstNameRB = new RadioButton();
        firstNameRB.setText("First Name");
        RadioButton lastNameRB = new RadioButton("Last Name");        
        gridPane.add(firstNameRB, 20, 0); // <------- need to be aligned to the right
        gridPane.add(lastNameRB, 21, 0);
        
        return gridPane;
    }
    private StackPane textAreaPane(ArrayList<String> al)
    {
        VBox root = new VBox();
        StackPane stackPane = new StackPane();
        
        stackPane.setPrefSize(300, 400);
        stackPane.setStyle("-fx-background-color: Gainsboro;");
        root.getChildren().add(stackPane);
        
        TextArea textArea = new TextArea();
        textArea.setPadding(new Insets(5, 5, 5, 5));
        textArea.setEditable(false); // the text area shouldn't be editable
        textArea.setWrapText(true);
        
        stackPane.getChildren().add(textArea);
        stackPane.setAlignment(Pos.CENTER);
        // Removing an empty line:
        // usign Iterator to avoid ConcurrentModificationException by removing
        // an element in an ArrayList
        Iterator<String> iter = al.iterator();
        while (iter.hasNext())
        {   
            try
            {
                String str = iter.next();
                if (str.equals(""))
                iter.remove();
            }
            catch (NullPointerException e)
            {
                e.getMessage();
            }
        }
//        for (String s: al)
//            System.out.println(s);
        // Isolate the last name    
        ArrayList<String> lastNames = new ArrayList<>();
        ArrayList<String> firstNames = new ArrayList<>();        
        for (String str2: al)
        {
            try
            {
                str2 = str2.trim();
            } catch (NullPointerException e){}
        }
        int wsIndex = 0;
        for (String str3: al)
        {   
            try
            {
                wsIndex = str3.lastIndexOf(' ');

                // if the length of the element is 1, 
                // it's considered as having only the first name
                if (wsIndex < 0)
                   firstNames.add(str3.substring(0));
                else
                {
                   lastNames.add(str3.substring(wsIndex + 1));
                   firstNames.add(str3.substring(0, wsIndex));
                }
            }
            catch (NullPointerException e){}
        }
        // test printing
        for (String a: lastNames)
            textArea.appendText(a + "\n");//  System.out.println("Last name: " + a);
        for (String b: firstNames)
              textArea.appendText(b + "\n");//  System.out.println("First name: " + b);
        
        // now sort!
        
        
         return stackPane;
    
            
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("main() invoked");
        launch(args);
    }
    
}
