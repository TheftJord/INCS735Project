package com.incs735;

import com.google.gson.annotations.SerializedName;

/**
 * 
 * @author Edmund_Tartaro
 */
public class JsonData {

    //these are the variables that we will take from the JSON File
    @SerializedName("reminder")
    private static String reminder; //this is the reminder text that the user will input
    @SerializedName("status")
    private static Boolean status; //this is used to make sure whether or not the task is already completed

 //--------------------------------------Getter Methods--------------------------------------------------------------

    /**
     * This allows other classes have access to the reminder text
     * @return
     */
    public static String getReminder(){
        return reminder;
    }

    /**
     * This allows other classes check if the task listed before has been completed
     * @return
     */
    public static Boolean getStatus(){
        return status;
    }
}
