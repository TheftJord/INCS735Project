package com.incs735;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

/**
 * 
 * @author Keerath_Kumar
 * @author Edmund_Tartaro
 */
public class HomeController {

    // Variables
    public boolean status = false; // default false for performance
    public String priority = null;
    public String reminder = null;

    // Temporary Node
    ReminderNode selectedNode = new ReminderNode(); // this is used to know which node is selected for remove and edit nodes

    // settings up ListProcesses
    ListProcesses lp = new ListProcesses(null);

    // File Interface Variables
    FileChooser fileChooser = new FileChooser();
    File current = null;
    File selectedFile;
    FileReader fr;
    // sets it so the file explorer can only see json files
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)","*.json");

    // FXML variables
    @FXML
    private TextField reminderText; // this will be used to get the text from the 
    @FXML
    private ListView<ReminderNode> viewDisplay; // this will show the To Do List
    @FXML
    private SplitMenuButton prioritySelector; // this will be needed to change the name to show the selection for the priority
    @FXML
    private SplitMenuButton statusSelector; // this will be needed to change the name to show the selection for the status


//-------------------------------------------------------------------initializer------------------------------------------------------------------------------

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

        // displays the initial file on bootup
        lp.convertJsonToList(); // converts the file to a list
        populateListView(); // displays the list in the list view
    }

//----------------------------------------------------------GUI Actions------------------------------------------------------------------


    /**
     * This is how items will be added to the list from the GUI
     */
    @FXML
    private void actionAddToList(){

        // get values
        reminder = reminderText.getText(); // gets value from the text field

        // double check all values are selected
        if(reminder == null || reminder.isEmpty()){ // double checks to make sure that the reminder text is not NULL
            reminder = "No Text Provided";
        }
        if(priority == null){ // double checks to make sure that the priority was selected and if not will default to "Low"
            priority = "Low";
        }

        //add it to the list
        addItem(reminder, status, priority); // adds item to list

        // displays it on the list view
        populateListView();

        // reset to default
        clearChoices();
    }

    /**
     * This is how items will be edited from the GUI
     */
    @FXML
    private void actionEditListItem(){
        
        // get values
        reminder = reminderText.getText();

        // makes sure that something is in the textfield
        if(reminder == null || reminder.isEmpty()){ // double checks to make sure that the reminder text is not NULL
            reminder = "No Text Provided";
        }

        // edits it on the list
        editItem(selectedNode, reminder, status, priority);

        // displays it on the list view
        populateListView();

        // reset to default
        clearChoices();
    }

    /**
     * This is how items will be removed from the GUI
     */
    @FXML
    private void actionRemoveListItem(){
        
        // removes item from the list
        removeItem(selectedNode);

        // displays the updated list on listview
        populateListView();

        // resets to default
        clearChoices();
    }

    /**
     * This sets the priority for the task
     * This is "High" choice
     */
    @FXML
    private void actionSetHighPriority(){

        // save value
        priority = "High";

        // changes GUI to display choice
        prioritySelector.setText(priority);
    }

    /**
     * This sets the priority for the task
     * This is "Medium" choice
     */
    @FXML
    private void actionSetMediumPriority(){

        // save value
        priority = "Medium";

        // changes GUI to display choice
        prioritySelector.setText(priority);
    }

    /**
     * This sets the priority for the task
     * This is "Low" choice
     */
    @FXML
    private void actionSetLowPriority(){

        // save value
        priority = "Low";

        // changes GUI to display choice
        prioritySelector.setText(priority);
    }

    /**
     * This sets the status for the task
     * This is "Complete" choice
     */
    @FXML
    private void actionSetStatusComplete(){

        // save value
        status = true;

        // changes GUI to display choice
        statusSelector.setText("Completed");
    }

    /**
     * This sets the status for the task
     * this is "Incomplete" choice
     */
    @FXML
    private void actionSetStatusIncomplete(){

        // save value
        status = false;

        // changes GUI to display choice
        statusSelector.setText("Incomplete");
    }

    /**
     * Closes the application safely
     * @throws IOException
     */
    @FXML
    private void closeApplication() throws IOException{
        saveToJson();
        GuiApplications.actionClose(); //calls close action in GuiApplication.java
    }

    /**
     * clears the list completely to start fresh
     */
    @FXML
    private void actionClearList(){

        // prompts user to save first
        saveToJson();

        // clears list that is store
        lp.theList.clear();

        // clears the list view
        viewDisplay.getItems().clear();
    }

    /**
     * this method allows the user to pick the item that they are going to edit or remove
     */
    @FXML
    private void actionSelectNode(){
        
        // sets selectedNode to the node that was selected
        selectedNode = viewDisplay.getSelectionModel().getSelectedItem();

        // setting the values from the node
        reminder = selectedNode.getReminder();
        status = selectedNode.getStatus();
        priority = selectedNode.getPriority();

        // setting the information into the proper fields
        reminderText.setText(selectedNode.getReminder()); // sets the textfield to match the selected file
        prioritySelector.setText(selectedNode.getPriority()); // sets the priority dropdown to match the selected file
        if(selectedNode.getStatus() == true){ // sets the status dropdown to "Completed" if value is true
            statusSelector.setText("Completed");
        }
        else{ // sets the status dropdowm to "Incompleted" if values is anyother then true for safe fail
            statusSelector.setText("Incomplete");
        }
    }

    //------------------------------------------------------List Processes-----------------------------------------------------------

    /**
     * This will remove items from the list
     * you will need the values in the node for this to work
     * @param currentNode
     */
    public void removeItem(ReminderNode currentNode){

        // Remove item from list
        lp.removeItem(currentNode); // removes item
    }

    /**
     * This will edit a pre-existing item
     * you will need the values in the node for this to work
     * @param currentNode
     * @param newReminder
     * @param newStatus
     * @param newPriority
     */
    public void editItem(ReminderNode currentNode, String newReminder, Boolean newStatus, String newPriority){

        // Edit item from list
        lp.editItem(currentNode, newReminder, newStatus, formatPriority(newPriority));
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
     * This will load files from a JSON file
     */
    public void loadFromJson(){

        // selects file to use
        selectedFile = fileChooser.showOpenDialog(null); // allows use to pick file
        lp.setFile(selectedFile); // sets the file to the list

        // converts what is on the file to a linked list
        lp.convertJsonToList();

        // loads it onto the list
        populateListView();
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

    /**
     * This is used clear the choices for next use
     */
    @FXML
    private void clearChoices(){

        // to reset the values
        status = false;
        priority = null;
        reminder = null;

        // resest values
        prioritySelector.setText("Priority");
        statusSelector.setText("Status");
        reminderText.clear();
    }

    /**
     * This populates the linked list with the most up to date information
     */
    @FXML
    private void populateListView(){

        // clears list view
        viewDisplay.getItems().clear();

        // populates list view with objects
        viewDisplay.getItems().addAll(lp.theList);
    }
}
