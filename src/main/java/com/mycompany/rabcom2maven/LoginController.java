package com.mycompany.rabcom2maven;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import utils.SqlConnection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController implements Initializable {
    
    private Label label;
    @FXML
    private AnchorPane loginWindow;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button login;
    @FXML
    private Button exit_of;
    @FXML
    private Label errorMessage;
    
	Connection conn;
	PreparedStatement pst = null;
	ResultSet rs = null;
        
        @FXML
	public void processLogin(ActionEvent actionEvent) throws IOException {
		CheckConnection();

		try {
			String query = "select * from user where username=? and password=?";
			pst = conn.prepareStatement(query);
			pst.setString(1, username.getText());
			pst.setString(2, password.getText());
			rs = pst.executeQuery();

			if (rs.next()) {
				Stage stage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
				stage.setScene(new Scene(root));
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
				stage.setTitle("Rabcom patents");
                                stage.show();
			} else {
				errorMessage.setText("Неудачный ввод логина");
			}
			username.clear();
			password.clear();
			pst.close();
			rs.close();

		} catch (Exception e1) {
			errorMessage.setText("SQL ошибка");
			System.err.println(e1);
		}
	}       

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void CheckConnection() {
		conn = SqlConnection.DbConnector();
		if (conn == null) {
			System.out.println("Соединение с таблицей пользователей не удалось");
			System.exit(1);
		} else {
			System.out.println("Соединение с таблицей пользователей удалось");
		}    
    }

    @FXML
    private void processLogOf() {
        Stage stage = (Stage)exit_of.getScene().getWindow();
        stage.close();
    }
}
