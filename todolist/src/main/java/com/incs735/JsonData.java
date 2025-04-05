package com.incs735;

/**
 * 
 * @author Theft_Jord
 */
public class JsonData {
    //these are the variables that we will take from the JSON File
    private String reminder; //this is the reminder text that the user will input
    private Boolean status; //this is used to make sure whether or not the task is already completed

 //--------------------------------------Getter Methods--------------------------------------------------------------

    /**
     * This allows other classes have access to the reminder text
     * @return
     */
    public String getReminder(){
        return this.reminder;
    }

    /**
     * This allows other classes check if the task listed before has been completed
     * @return
     */
    public Boolean getStatus(){
        return this.status;
    }
}
