package javafx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import personnel.Employe;
import javafx.scene.control.CheckBox;

import javafx.scene.control.DatePicker;

public class ROModifierEmployeController implements Initializable {
	@FXML private AnchorPane anchorPane;
	@FXML private Label employeAffiche;
	@FXML private Label warning;
	@FXML private TextField nom;
	@FXML private TextField prenom;
	@FXML private TextField mail;
	@FXML private PasswordField password;
	@FXML private DatePicker dateArrive;
	@FXML private DatePicker dateDepart;
	@FXML private CheckBox admin;
	
	private Employe employe = LigueController.getEmploye();

	// Event Listener on Button.onAction
	@FXML
	public void quitter(ActionEvent event) throws IOException {
		
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/javafx/view/ROLigue.fxml"));
		anchorPane.getChildren().setAll(anchor);
	}
	
	public void loadEmploye() {
		employeAffiche.setText(employe.getNom());
		nom.setText(employe.getNom());
		prenom.setText(employe.getPrenom());
		mail.setText(employe.getMail());
		password.setText(employe.getPass());
		dateArrive.setValue(employe.getDateArrive());
		dateDepart.setValue(employe.getDateDepart());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		loadEmploye();
		
	}
}
