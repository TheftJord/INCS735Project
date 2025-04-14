package com.incs735;

import java.io.FileReader;
import java.util.LinkedList;

import com.google.gson.GsonBuilder;



public class ListProcesses {
    
    //variables

    //imported classes
    JsonData Json = new JsonData();

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

    public LinkedList<ReminderNode> convertJsonToList(){

        LinkedList<ReminderNode> listData = new LinkedList<ReminderNode>();

        GsonBuilder builder = new GsonBuilder();

        return listData;
    }
}
