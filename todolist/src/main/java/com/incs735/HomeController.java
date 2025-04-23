package com.incs735;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

/**
 * 
 * @author Keerath_Kumar
 * @author Edmund_Tartaro
 */
public class HomeController {

    // File Interface Variables
    FileChooser fileChooser = new FileChooser();
    File current = null, selectedFile;
    FileReader fr;
    // sets it so the file explorer can only see json files
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)","*.json");

    public void initialize(){

         // sets up file explorer
        fileChooser.getExtensionFilters().add(extFilter);

        try{
            current = new File(new File(".").getCanonicalPath()); // gets path for file
        } catch (IOException ex) {
            ex.printStackTrace(); // error report to know why file retrieval failed
        }

        fileChooser.setInitialDirectory(current); // sets the inital directory
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("");
    }

    /**
     * Closes the application safely
     * @throws IOException
     */
    @FXML
    private void closeApplication() throws IOException{
        GuiApplications.actionClose(); //calls close action in GuiApplication.java
    }
}
