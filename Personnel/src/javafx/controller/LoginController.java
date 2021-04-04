package javafx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import personnel.Employe;
import personnel.GestionPersonnel;
import javafx.scene.control.PasswordField;

public class LoginController implements Initializable{
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private PasswordField passwordLogin;
	@FXML
	private Label passwordIncorrect;

	private final GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();
	private Employe root = gestionPersonnel.getRoot();
	
	public GestionPersonnel getGestion() {
		
		return gestionPersonnel;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		
	}
	
	
	@FXML
	public void btnLogin() throws IOException {
		
		
		passwordIncorrect.setText("Password incorrect");
		
		if (passwordLogin.getText().equals("")) {
			passwordIncorrect.setText("Enter a password");
		}
		
		else if(passwordLogin.getText().equals(root.getPass())) {
			AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/javafx/view/Gerer.fxml"));
			anchorPane.getChildren().setAll(anchor);
			
			
		}
		else {
			passwordIncorrect.setText("Password incorrect");
			passwordLogin.setText("");
		}
	}
}
