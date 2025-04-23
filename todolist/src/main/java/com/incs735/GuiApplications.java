package com.incs735;

import javafx.application.Platform;
import javafx.fxml.FXML;

public class GuiApplications {
    
    /**
     * This closes the application safely
     */
    @FXML
    public static void actionClose(){

        Platform.exit(); //closes the GUI first

        System.exit(0); //Closes the program
    }

}
