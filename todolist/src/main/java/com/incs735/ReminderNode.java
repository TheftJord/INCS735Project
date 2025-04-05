package com.incs735;

/**
 * 
 * @author Theft_Jord
 */
public class ReminderNode {
    //these are the variables that will be in the reminder nodes
    private String reminder; //this is the reminder that the user wants to save
    private Boolean status; //this is the completion status of the reminder that is above

 //---------------------------------------Setters----------------------------------------------------

    /**
     * This sets the value of the reminder text so we can use it later
     * @param setReminder
     */
    public void setReminder(String setReminder){
        this.reminder = setReminder; //this sets the reminder string to what the inputed value is
    }

    /**
     * This sets the value of the completion status boolean so we can use it later
     * @param setStatus
     */
    public void setStatus(Boolean setStatus){
        this.status = setStatus; //this sets the completion status of the task listed above
    }

 //----------------------------------------Getters--------------------------------------------------------

    /**
     * This returns the reminder string value for other classes to use
     * @return
     */
    public String getReminder(){
        return this.reminder; //returns reminder
    }

    /**
     * This returns the completion status value for other classes to use
     * @return
     */
    public Boolean getStatus(){
        return this.status; //returns status
    }
}
