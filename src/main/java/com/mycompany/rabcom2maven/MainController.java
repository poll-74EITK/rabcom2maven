/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rabcom2maven;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.layout.StackPane;


import utils.ImportTask;
import utils.RingProgressIndicator;

/**
 * FXML Controller class
 *
 * @author Pavel Papsh
 */
public class MainController implements Initializable {


        
    @FXML
    //Button btnImportXSLToDB;
    StackPane stackPane;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    @FXML
    private void importXSLToDB() throws IOException, FileNotFoundException, SQLException {
        ImportTask.importXSLToDB();
    }
     
}
