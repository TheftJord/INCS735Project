package com.incs735;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;



public class ListProcesses {
    
    //variables
    private String selectedFile;
    private int count;
    LinkedList<ReminderNode> theList = new LinkedList<ReminderNode>(); // list that data is going to be saved to when pulled from json file

    //imported classes
    JsonData Json = new JsonData();

    // Gson variables needed
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    FileReader fr = null;

    /**
     * This makes nodes that can be added to a list for later use
     * @param reminder
     * @param status
     * @return
     */
    public ReminderNode makeItem(String reminder, Boolean status){

        ReminderNode Temp = new ReminderNode(); //makes a node for the information

        Temp.setAll(reminder, status); //sets the values to the created node

        return Temp; //returns the node so that it can be used
    }

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
            fr = new FileReader(selectedFile); // tries to link the selectedFile to the FileReader
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
            tempNode.setAll(tempReminder, tempStatus);
            
            theList.add(tempNode); // add the completed temp node to the linked list that we can use
        }

        return theList;
    }

    //still a work in progress
    public void SaveToJson(){
        LinkedList<JsonData> holder = new LinkedList<JsonData>();
        builder.setPrettyPrinting();
        if(selectedFile!=null){
            
        }
    }
}
