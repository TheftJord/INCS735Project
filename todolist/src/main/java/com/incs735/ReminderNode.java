package com.incs735;

/**
 * 
 * @author Edmund_Tartaro
 */
public class ReminderNode {
    
    // these are the variables that will be in the reminder nodes
    private String reminder; // this is the reminder that the user wants to save
    private Boolean status; // this is the completion status of the reminder that is above
    private String priority; // this is the priority of the task above

 //---------------------------------------Setters----------------------------------------------------

    /**
     * This sets the value of the reminder text so we can use it later
     * @param setReminder
     */
    public void setReminder(String setReminder){
        this.reminder = setReminder; // this sets the reminder string to what the inputed value is
    }

    /**
     * This sets the value of the completion status boolean so we can use it later
     * @param setStatus
     */
    public void setStatus(Boolean setStatus){
        this.status = setStatus; // this sets the completion status of the task listed above
    }

    /**
     * this sets the value of the priority so we can use it later
     * @param setPriority
     */
    public void setPriority(String setPriority){
        this.priority = setPriority; // this sets the priority of the task listed above
    }

    /**
     * This sets all the values of this node at once so we can make it all at once when needed
     * @param setReminder
     * @param setStatus
     */
    public void setAll(String setReminder, Boolean setStatus, String setPriority){
        this.reminder = setReminder; // sets reminder text
        this.status = setStatus; // sets completion status
        this.priority = setPriority; // sets priority
    }

 //----------------------------------------Getters--------------------------------------------------------

    /**
     * This returns the reminder string value for other classes to use
     * @return
     */
    public String getReminder(){
        return this.reminder; // returns reminder
    }

    /**
     * This returns the completion status value for other classes to use
     * @return
     */
    public Boolean getStatus(){
        return this.status; // returns status
    }

    /**
     * this returns the value of the priority for other classes to use
     * @return
     */
    public String getPriority(){
        return this.priority; // returns priority
    }
}
