package com.incs735;

import java.io.IOException;
import javafx.fxml.FXML;

/**
 * 
 * @author Keerath_Kumar
 * @author Edmund_Tartaro
 */
public class HomeController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("");
    }

    /**
     * Closes the application safely
     * @throws IOException
     */
    @FXML
    private void closeApplication() throws IOException{
        GuiApplications.actionClose(); //calls close action in GuiApplication.java
    }
}
