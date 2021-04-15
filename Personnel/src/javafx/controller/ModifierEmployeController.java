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
import personnel.DateImpossible;
import personnel.Employe;
import javafx.scene.control.PasswordField;

import javafx.scene.control.DatePicker;

public class ModifierEmployeController implements Initializable{
	
	private Employe employe = LigueController.getEmploye();
	
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private Label employeTitle;
	@FXML
	private TextField nom;
	@FXML
	private TextField prenom;
	@FXML
	private TextField mail;
	@FXML
	private TextField password;
	@FXML
	private DatePicker dateArrive;
	@FXML
	private DatePicker dateDepart;

	// Event Listener on Button.onAction
	@FXML
	public void quitter(ActionEvent event) throws IOException {
		AnchorPane anchor = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafx/view/Ligue.fxml"));
		anchorPane.getChildren().setAll(anchor);
	}
	
	@FXML
	public void valider(ActionEvent event) throws IOException, DateImpossible {
		update();
		AnchorPane anchor = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafx/view/Ligue.fxml"));
		anchorPane.getChildren().setAll(anchor);
	}
	@FXML
	public void resetArrive(ActionEvent event) {
		dateArrive.getEditor().clear();
		dateArrive.setValue(null);
	}
	@FXML
	public void resetDepart(ActionEvent event) {
		dateDepart.getEditor().clear();
		dateDepart.setValue(null);
	}
	public void loadEmploye() {
		employeTitle.setText(employe.getNom());
		nom.setText(employe.getNom());
		prenom.setText(employe.getPrenom());
		mail.setText(employe.getMail());
		password.setText(employe.getPass());
		dateArrive.setValue(employe.getDateArrive());
		dateDepart.setValue(employe.getDateDepart());
		
	}
	public void update() throws DateImpossible {
		employe.setNom(nom.getText());
		employe.setPrenom(prenom.getText());
		employe.setMail(mail.getText());
		employe.setPassword(password.getText());
		employe.setDateArrive(dateArrive.getValue());
		employe.setDateDepart(dateDepart.getValue());
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadEmploye();
		
	}
}
