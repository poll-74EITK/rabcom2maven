/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javafx.scene.control.Alert;

/**
 *
 * @author Pavel Papsh
 */
public class DialogManager {
    public static void showInfoDialog(String title, String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
//        alert.setHeaderText(null);
        alert.setHeaderText("");
        alert.setContentText(text);
        alert.showAndWait();
    }
    public static void showErrorDialog(String title, String text){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
//        alert.setHeaderText(null);
        alert.setHeaderText("");
        alert.setContentText(text);
        alert.showAndWait();
    }
}
