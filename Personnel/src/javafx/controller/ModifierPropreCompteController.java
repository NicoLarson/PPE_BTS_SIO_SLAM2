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

import javafx.scene.layout.AnchorPane;
import personnel.Employe;
import javafx.scene.control.DatePicker;

public class ModifierPropreCompteController implements Initializable {
	
	
	@FXML private AnchorPane anchorPane;
	@FXML private Label employeAffiche;
	@FXML private Label warning;
	@FXML private TextField nom;
	@FXML private TextField prenom;
	@FXML private TextField mail;
	@FXML private TextField password;
	@FXML private DatePicker dateArrive;
	@FXML private DatePicker dateDepart;
	
	private Employe emp;

	
	@FXML
	public void sauvegarder(ActionEvent event) {
		
		emp.setPassword(password.getText());
		
	}
	
	
	@FXML
	public void quitter(ActionEvent event) throws IOException {
		
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/javafx/view/ROLigue.fxml"));
		anchorPane.getChildren().setAll(anchor);
	}
	
	public void loadEmploye() {
		employeAffiche.setText(emp.getNom());
		nom.setText(emp.getNom());
		prenom.setText(emp.getPrenom());
		mail.setText(emp.getMail());
		password.setText(emp.getPass());
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		emp = LigueController.getEmploye();
		loadEmploye();
		
	}
}
