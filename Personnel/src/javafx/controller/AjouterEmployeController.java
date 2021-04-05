package javafx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

import java.io.IOException;

import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;
import personnel.Ligue;
import javafx.scene.control.PasswordField;

import javafx.scene.control.DatePicker;

public class AjouterEmployeController {
	
	
	private Ligue ligue = LiguesController.getLigue();
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private TextField nom;
	@FXML
	private TextField prenom;
	@FXML
	private TextField mail;
	@FXML
	private PasswordField password;
	@FXML
	private DatePicker dateArrive;

	
	@FXML
	public void quitter(ActionEvent event) throws IOException {
		AnchorPane anchor = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafx/view/Ligue.fxml"));
		anchorPane.getChildren().setAll(anchor);
		
	}
	
	@FXML
	public void valider(ActionEvent event) throws IOException {
		ligue.addEmploye(nom.getText(), prenom.getText(), mail.getText(), password.getText(), dateArrive.getValue(), null);
		AnchorPane anchor = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafx/view/Ligue.fxml"));
		anchorPane.getChildren().setAll(anchor);
	}
}
