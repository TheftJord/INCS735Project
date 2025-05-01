package com.incs735;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;



public class ListProcesses {
    
    //variables
    private File selectedFile;
    private int count;
    LinkedList<ReminderNode> theList = new LinkedList<ReminderNode>(); // list that data is going to be saved to when pulled from json file

    //imported classes
    JsonData Json = new JsonData();

    // Gson variables needed
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    FileReader fr = null;

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

    // WIP
    public void editItem(){

    }

    // WIP
    public void removeItem(){

    }


    //---------------------------------------------------------Save and Download--------------------------------------------------------------------------------------


    /**
     * This convert a Json file todo list into a usable LinkedList
     * This will not convert a json directory if we go that route and will need a specialized method and classes for that
     * @return
     */
    public LinkedList<ReminderNode> convertJsonToList(){

        // Linked List
        LinkedList<JsonData> listData = new LinkedList<JsonData>(); // The list that the data from the json file is going to go into to be processed

        // Links FileReader to the selected file
        try{
            fr = new FileReader(selectedFile.getName()); // tries to link the selectedFile to the FileReader
        } catch (FileNotFoundException ex){
            System.out.println("An Error has Occured: Selected File Not Found - convertJsonToList()"); // produces and error message if the selected file doesn't link properly
        }

        listData = gson.fromJson(fr, new TypeToken<LinkedList<JsonData>>(){}.getType());
        count = listData.size();

        for(int i=0;i<count;i++){
            
            ReminderNode tempNode = new ReminderNode(); // sets up temprary node that the information will be saved to
            
            listData.get(i); //statically sets what node in the data list we are on IMPORTANT DO NOT TOUCH

            // This converts the data from the json list over to a ReminderNode that we can use
            String tempReminder = JsonData.getReminder();
            Boolean tempStatus = JsonData.getStatus();
            String tempPriority = JsonData.getPriority();
            tempNode.setAll(tempReminder, tempStatus, tempPriority);
            
            theList.add(tempNode); // add the completed temp node to the linked list that we can use
        }

        return theList;
    }

    /**
     * This will convert the Linked List of data into a JSON file to save it for later
     */
    public void saveToJson(){
        // LinkedList<JsonData> holder = new LinkedList<JsonData>(); // keeping just incase I need it make sure to remove if not nessacary 
        
        builder.setPrettyPrinting(); // makes code write the data in the proper JSON format when printing into the file

        // applying the data to the JSON file
        if(selectedFile!=null){ // makes sure that there is a file selected and is not null
            
            // variables
            String jsonString = gson.toJson(theList); // converts the list of data into a string that we can apply to the JSON file
            PrintStream ps; // primes a PrintStream that we will be using

            try {
                ps = new PrintStream(selectedFile.getName()); // finish building the PrintStream that we will be using
                ps.println(jsonString); // prints the data string variable into the JSON file
            } catch(FileNotFoundException ex) {
                System.out.println("An Error has Occured: Could Not Print to Selected File - SaveToJson()"); // catches if something went wrong and reports where to the terminal
            }
        }
    }
}
