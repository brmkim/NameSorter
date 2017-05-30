package NameSort;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * filename: NameSort.java class: NameSort Description...
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
 * 05/15/2017 BK Done separating last names and first names 
 * 05/23/2017 BK Removed first and last name ArrayList
 * 
 */
public class NameSort extends Application {

    BorderPane pane = new BorderPane();
    FileIO fileIO = new FileIO();
    TextArea textArea = new TextArea();
    ArrayList<Name> namesAL = new ArrayList<>();
    ArrayList<Name> namesALnoDuplicate = new ArrayList<>();
    Name[] nameArray;
    Name[] clonedArray;
    RadioButton firstNameRB = new RadioButton("First Name");
    RadioButton lastNameRB = new RadioButton("Last Name");
    String fName = "";

    @Override
    public void start(Stage primaryStage) {
        System.out.println("start method is invoked");

        pane.setTop(menuHBox(primaryStage));
        pane.setCenter(centerGridPane(""));

        Scene scene = new Scene(pane);
        primaryStage.setMinWidth(200);
        primaryStage.setMinHeight(500);
        primaryStage.setTitle("Name Sorter -- by Boram Kim");
        primaryStage.getIcons().add(new Image("list.png"));
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    private GridPane centerGridPane(String fname) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(5, 5, 5, 5));

        // Set file name
        if (fname.isEmpty())
            fname = "none";  // filename label says "none" initally
        Text filename = new Text("File Name: " + fname);
        filename.setFont(Font.font("Arial", 15));
        filename.setFill(Color.BLUE);        
        gridPane.add(filename, 1, 0);
        
        Label label = new Label("Sort by: ");
        gridPane.add(label, 19, 0);
        // Radio buttons
        // "Only one RadioButton can be selected when placed in a ToggleGroup"
        ToggleGroup group = new ToggleGroup();
        firstNameRB.setToggleGroup(group);       
        lastNameRB.setToggleGroup(group);
        gridPane.add(firstNameRB, 20, 0); // <------- need to be aligned to the right
        gridPane.add(lastNameRB, 21, 0);
        // set first name RadioButton as a default
        if (!firstNameRB.isSelected() && !lastNameRB.isSelected())
            firstNameRB.fire();
        firstNameRB.setOnAction((ActionEvent t) -> 
        {
            Name.setName("byFirst");  // the string literal in the argument is
            if (nameArray != null)
            {
                try 
                {                    // set as constant in the Name class
                    textArea.clear();
                    pane.setBottom(textAreaPane(nameArray));
                } catch (ArrayIndexOutOfBoundsException ex) { ex.getMessage();}
            }
        });
        lastNameRB.setOnAction((ActionEvent t) -> 
        {
            Name.setName("byLast");
            if (nameArray != null)
                {
                try 
                {
                    textArea.clear();
                    pane.setBottom(textAreaPane(nameArray));
                } catch (ArrayIndexOutOfBoundsException ex) { ex.getMessage();}
            }
        });
        return gridPane;
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
        menuBar.setMinWidth(700);
        menuFile.getItems().addAll(open, save, exit);
        hBox.getChildren().addAll(menuBar);
        //Open button action
        open.setOnAction((ActionEvent t) -> 
        {
            namesAL.clear(); // empty the previous ArrayLists
            textArea.clear(); // wipe out the text area
            // get ArrayList of Name Objects from FileIO class
            try 
            {
                namesAL = fileIO.readFile(fileIO.openFile(primaryStage));
            } catch (FileNotFoundException ex) { ex.getMessage(); }
            
            nameArray = new Name[namesAL.size()];
            for (int i = 0; i < namesAL.size(); i++) 
                nameArray[i] = namesAL.get(i);

            // Add file name to the GUI label
            Label fnLabel = new Label();
            try
            {
                fName = fileIO.file.getName();
            } catch (NullPointerException e) { e.getMessage();}        
            pane.setCenter(centerGridPane(fName));

            // Print nameArray in the TextAreaPane
            try 
            {
                if (firstNameRB.isSelected())
                {   
                    Name.setName("byFirst");
                    pane.setBottom(textAreaPane(nameArray));
                }
                else if (lastNameRB.isSelected())
                {
                    Name.setName("byLast");
                     pane.setBottom(textAreaPane(nameArray));
                }            
            } catch (ArrayIndexOutOfBoundsException ex) { ex.getMessage(); }
        });
        // Save button action
        save.setOnAction((ActionEvent t) -> 
        {
            PrintWriter printWriter = null;
            String filename = "";
            // Set text file suffix depends on which RadioButton is set
            if (firstNameRB.isSelected())
                filename = fName + "_fn.txt";
            else if (lastNameRB.isSelected())
                filename = fName + "_ln.txt";        

            try 
            {  
                printWriter  = new PrintWriter(new FileOutputStream(filename));
            } 
            catch (FileNotFoundException ex) { ex.getMessage(); }
            for (Name n: clonedArray)
                printWriter.println(n);
            printWriter.close( );

            // for my record
            System.out.println("Records sent to file " + filename + ".");
        });
        // Exit button action
        exit.setOnAction ((ActionEvent t) -> 
        {
            System.exit(0);
        });
        
    return hBox ;
    }
/**
 * 
 * @param nameArr Name array
 * @return StackPane in which has TextArea
 * @throws ArrayIndexOutOfBoundsException 
 */
    private StackPane textAreaPane(Name[] nameArr) 
            throws ArrayIndexOutOfBoundsException 
    {
        VBox root = new VBox();
        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(300, 400);
        stackPane.setStyle("-fx-background-color: Gainsboro;");
        
        root.getChildren().add(stackPane);
        textArea.setPadding(new Insets(5, 5, 5, 5));
        textArea.setEditable(false); // the text area shouldn't be editable
        textArea.setWrapText(true);

        stackPane.getChildren().add(textArea);
        stackPane.setAlignment(Pos.CENTER);
        //clone the nameArray -- which will be sorted
        clonedArray = nameArray.clone();
        Sorts.quickSort(clonedArray);
        for (Name n : clonedArray) {
            textArea.appendText(n + "\n");
        }
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
