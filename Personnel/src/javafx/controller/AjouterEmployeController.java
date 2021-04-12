package javafx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Map;

import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;
import personnel.Ligue;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
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
	private Label warning;

	
	@FXML
	private void quitter(ActionEvent event) throws IOException {
		AnchorPane anchor = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafx/view/Ligue.fxml"));
		anchorPane.getChildren().setAll(anchor);
	}
	
	private boolean checkField(String fieldName, String value)
	{
		if (value.length() == 0) {
			warning.setText("Le champ " + fieldName + " est obligatoire");
			return false;
		}
		return true;
	}
	
	private boolean checkRequiredFields()
	{
		return (checkField("nom", nom.getText()) && 
				checkField("prénom", prenom.getText()) && 
				checkField("mail", mail.getText()) && 
				checkField("mot de passe", password.getText()));
	}
	
	@FXML
	private void valider(ActionEvent event) throws IOException {
		if (checkRequiredFields()) {
			ligue.addEmploye(nom.getText(), prenom.getText(), mail.getText(), password.getText(), dateArrive.getValue(), null);
			AnchorPane anchor = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafx/view/Ligue.fxml"));
			anchorPane.getChildren().setAll(anchor);
		}
	}
}
