package com.incs735;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


/**
 * This is where all the processes for the todo list portion of the code exist 
 * @author Edmund Tartaro
 */
public class ListProcesses {

    /**
     * sets the JSON file we are using from other classes if need be
     * @param otherSelectedFile
     */
    public ListProcesses(File otherSelectedFile){
        this.selectedFile = otherSelectedFile;
    }
    
    //variables
    private File selectedFile;
    LinkedList<ReminderNode> theList = new LinkedList<ReminderNode>(); // list that data is going to be saved to when pulled from json file

    // Gson variables needed
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    FileReader fr = null;

    //---------------------------------------------------------------------Misc-----------------------------------------------------------------------------------

    /**
     * This makes nodes that can be added to a list for later use
     * @param reminder
     * @param status
     * @param priority
     * @return
     */
    public ReminderNode makeItem(String reminder, Boolean status, String priority){

        // creates node
        ReminderNode Temp = new ReminderNode(); //makes a node for the information

        // adds information to node
        Temp.setAll(reminder, status, priority); //sets the values to the created node

        // returns node
        return Temp; //returns the node so that it can be used
    }

    /**
     * This will go through the entire loop and once it does it will give the position to the call location
     * if -1 is the result that node doesn't exist in the present list
     * @param target
     * @return
     */
    public int getLocation(ReminderNode target){

        // variables
        int targetLocation = -1; // the location of the node on the list

        // Test Cycle
        for(int i=0;i<theList.size();i++){ // will iterate through the linked list
            if(theList.get(i).toCompare(target) == true){ // will check if the nodes match
                targetLocation = i; // if they match will set the targetLocation to match
                break; // will end the loop
            }
        }

        return targetLocation; // returns the location in the list to whomever called it
    }

    /**
     * This sets the file that we will be drawing from
     * @param setFile
     */
    public void setFile(File setFile){
        this.selectedFile = setFile;
    }

    /**
     * This is just for system testing and isn't needed for the final build
     */
    public void printString(){
        for(int i = 0;i<theList.size();i++){
           System.out.println(theList.get(i).toString());
        }
    }

    //--------------------------------------------------------------------Edit List-------------------------------------------------------------------------------


    /**
     * This adds new nodes the the linked list for use
     * @param reminder
     * @param status
     * @param priority
     */
    public void addToList(String reminder, Boolean status, String priority){

        // creates node
        ReminderNode temp = makeItem(reminder, status, priority); // makes the item that we are going to add to the list

        // adds node
        theList.add(temp); // adds the new node to the linked list

    }

    /**
     * This will change a pre-existing node on the list to have the new data
     * @param other
     * @param changeReminder
     * @param changeStatus
     * @param changePriority
     */
    public void editItem(ReminderNode other, String changeReminder, Boolean changeStatus, String changePriority){

        // the search
        int location = getLocation(other); // gets the location of the node in question 
        if(location == -1){ // double checks to see if the node is even in the list; see getLocation() javdoc for more information
            System.out.println("An Error has Occured: Selected Node Not Found - editItem()");
            return;
        }

        // editing node
        // checks to see that changeReminder is not null nor equal to what's already there 
        if(changeReminder.isEmpty() == false && changeReminder.compareTo(theList.get(location).getReminder()) != 0){ 
            theList.get(location).setReminder(changeReminder); // changes the old value with the new value
        }
        // checks to see that changeStatus is not null nor equal to what's already there
        if(changeStatus != theList.get(location).getStatus() && changeStatus != null){
            theList.get(location).setStatus(changeStatus); // changes the old value with the new value
        }
        // checks to see the changePriority is not null nor equal to what's already there
        if(changePriority.isEmpty() == false && changePriority.compareTo(theList.get(location).getPriority()) != 0){
            theList.get(location).setPriority(changePriority); // changes the old value with the new value
        }

    }

    /**
     * This will be how to remove a specific node from the list
     * @param clarien
     */
    public void removeItem(ReminderNode clarien){

        // the search
        int location = getLocation(clarien); // gets the location of the node in question 
        if(location == -1){ // double checks to see if the node is even in the list; see getLocation() javdoc for more information
            System.out.println("An Error has Occured: Selected Node Not Found - removeItem()");
            return;
        }

        // removal
        theList.remove(location); // removes node from specified location
    }


    //---------------------------------------------------------Save and Download--------------------------------------------------------------------------------------


    /**
     * This convert a Json file todo list into a usable LinkedList
     * This will not convert a json directory if we go that route and will need a specialized method and classes for that
     * @return
     */
    public void convertJsonToList(){

        if(selectedFile != null){
            // Linked List
            LinkedList<ReminderNode> listData = new LinkedList<ReminderNode>(); // The list that the data from the json file is going to go into to be processed

            // Links FileReader to the selected file
            try{
                fr = new FileReader(selectedFile.getName()); // tries to link the selectedFile to the FileReader
            } catch (FileNotFoundException ex){
                System.out.println("An Error has Occured: Selected File Not Found - convertJsonToList()"); // produces and error message if the selected file doesn't link properly
            }

            listData = gson.fromJson(fr, new TypeToken<LinkedList<ReminderNode>>(){}.getType());

            for(int i=0;i<listData.size();i++){
                
                // ReminderNode tempNode = new ReminderNode(); // sets up temprary node that the information will be saved to

                // This converts the data from the json list over to a ReminderNode that we can use
                addToList(listData.get(i).getReminder(), listData.get(i).getStatus(), listData.get(i).getPriority());
            }
        } else {
            System.out.println("An Error has Occured: Selected File Not Found - convertJsonToList()");
        }


    }

    /**
     * This will convert the Linked List of data into a JSON file to save it for later
     */
    public void saveToJson(){

        builder.setPrettyPrinting(); // makes code write the data in the proper JSON format when printing into the file

        // applying the data to the JSON file
        if(selectedFile!=null){ // makes sure that there is a file selected and is not null
            // variables
            String jsonString = gson.toJson(theList); // converts the list of data into a string that we can apply to the JSON file
            PrintStream ps; // primes a PrintStream that we will be using

            try {
                ps = new PrintStream(selectedFile.getPath()); // finish building the PrintStream that we will be using
                ps.println(jsonString); // prints the data string variable into the JSON file
            } catch(FileNotFoundException ex) {
                System.out.println("An Error has Occured: Could Not Print to Selected File - SaveToJson()"); // catches if something went wrong and reports where to the terminal
            }
        }
        else {
            System.out.println("An Error has Occured: Could Not Find Selected File - SaveToJson()");
        }
    }
}
