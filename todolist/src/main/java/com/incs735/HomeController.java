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

    // settings up ListProcesses
    ListProcesses lp = new ListProcesses(null);

    // File Interface Variables
    FileChooser fileChooser = new FileChooser();
    File current = null;
    File selectedFile;
    FileReader fr;
    // sets it so the file explorer can only see json files
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)","*.json");

    /**
     * Initializer class that will set up the following processes that will be used later
     * - File Explorer
     * - List Processes (will select file for it initially)
     */
    public void initialize(){

         // sets up file explorer
        fileChooser.getExtensionFilters().add(extFilter);

        try{
            current = new File(new File(".").getCanonicalPath()); // gets path for file
        } catch (IOException ex) {
            ex.printStackTrace(); // error report to know why file retrieval failed
        }

        fileChooser.setInitialDirectory(current); // sets the inital directory

        selectedFile = fileChooser.showOpenDialog(null); // makes user choose initial list on start up

        lp.setFile(selectedFile); // sets selected file on List Processes VERY IMPORTANT
    }

//----------------------------------------------------------GUI Actions------------------------------------------------------------------

    /**
     * Used for testing delete when Implementing
     * @throws IOException
     */
    @FXML
    private void switchToSecondary() throws IOException {
        loadFromJson();
        lp.printString();
    }

    /**
     * Closes the application safely
     * @throws IOException
     */
    @FXML
    private void closeApplication() throws IOException{
        GuiApplications.actionClose(); //calls close action in GuiApplication.java
    }

    //------------------------------------------------------List Processes-----------------------------------------------------------

    /**
     * This will remove items from the list
     * you will need the values in the node for this to work
     * @param reminder
     * @param status
     * @param priority
     */
    public void removeItem(String reminder, Boolean status, String priority){

        // Remove item from list
        lp.removeItem(lp.makeItem(reminder, status, formatPriority(priority))); // removes item
    }

    /**
     * This will remove items from the list
     * you will need the values in the node for this to work
     * this is incase you have status as a string
     * @param reminder
     * @param status
     * @param priority
     */
    public void removeItem(String reminder, String status, String priority){
        
        // Remove item from list
        lp.removeItem(lp.makeItem(reminder, convertToBoolean(status), priority)); // converts the String status into a boolean then removes item
    }

    /**
     * This will edit a pre-existing item
     * you will need the values in the node for this to work
     * @param oldReminder
     * @param oldStatus
     * @param oldPriority
     * @param newReminder
     * @param newStatus
     * @param newPriority
     */
    public void editItem(String oldReminder, Boolean oldStatus, String oldPriority, String newReminder, Boolean newStatus, String newPriority){

        // Edit item from list
        lp.editItem(lp.makeItem(oldReminder, oldStatus, formatPriority(oldPriority)), newReminder, newStatus, formatPriority(newPriority));
    }

    /**
     * This will edit a pre-existing item
     * you will need the values in the node for this to work
     * this is incase you have status as a string
     * @param oldReminder
     * @param oldStatus
     * @param oldPriority
     * @param newReminder
     * @param newStatus
     * @param newPriority
     */
    public void editItem(String oldReminder, String oldStatus, String oldPriority, String newReminder, String newStatus, String newPriority){

        // Edit item from list
        lp.editItem(lp.makeItem(oldReminder, convertToBoolean(oldStatus), formatPriority(oldPriority)), newReminder, convertToBoolean(newStatus), formatPriority(newPriority));
    }

    /**
     * this will add the item to list
     * @param reminder
     * @param status
     * @param priority
     */
    public void addItem(String reminder, Boolean status, String priority){

        // Add item to the list
        lp.addToList(reminder, status, formatPriority(priority));
    }

    /**
     * this will add the item to list
     * this is incase you have status as a string
     * @param reminder
     * @param status
     * @param priority
     */
    public void addItem(String reminder, String status, String priority){

        // Add item to the list
        lp.addToList(reminder, convertToBoolean(status), formatPriority(priority));
    }

    /**
     * This will load files from a JSON file
     */
    public void loadFromJson(){

        // selects file to use
        selectedFile = fileChooser.showOpenDialog(null); // allows use to pick file
        lp.setFile(selectedFile); // sets the file to the list

        // converts what is on the file to a linked list
        lp.convertJsonToList();
    }

    public void saveToJson(){

        // selects file to use
        selectedFile = fileChooser.showSaveDialog(null);
        lp.setFile(selectedFile);

        // converts list to JSON file
        lp.saveToJson();
    }

//-------------------------------------------------------------------MISC------------------------------------------------------------------------------

    /**
     * Returns a boolean value from a string
     * @param status
     * @return
     */
    private Boolean convertToBoolean(String status){
        //converts String true or false to Boolean
        Boolean tempStatus = null;
        if(status.toUpperCase().compareTo("TRUE") == 0){ // checks if value says true
            tempStatus = true; 
        }
        else if(status.toUpperCase().compareTo("FALSE") == 0){ // checks if value says false
            tempStatus = false;
        }
        else {
            System.out.println("An Error Has Occurred: Non True or False Value - removeItem() | HomeController"); // error reporting
        } 

        return tempStatus; // returns boolean
    }

    /**
     * makes it so the High, Medium, and Low are all printed the same (OCD reasons)
     * @param priority
     * @return
     */
    private String formatPriority(String priority){
        // variables
        String returnValue = "Low"; // variable to return; default Low incase of failure

        // check values
         if(priority.toUpperCase().compareTo("HIGH") == 0){ // checks to see if variable says high
            returnValue = "High";
         }
         else if(priority.toUpperCase().compareTo("MEDIUM") == 0 || priority.toUpperCase().compareTo("MID") == 0){ // checks to see if variable says medium
            returnValue = "Medium";
         }

         return returnValue; // returns values    
    }
}
