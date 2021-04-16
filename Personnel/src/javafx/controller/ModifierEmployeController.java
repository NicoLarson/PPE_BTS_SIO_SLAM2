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
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
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
	@FXML
	private CheckBox admin;

	// Event Listener on Button.onAction
	@FXML
	public void quitter(ActionEvent event) throws IOException {
		AnchorPane anchor = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafx/view/Ligue.fxml"));
		anchorPane.getChildren().setAll(anchor);
	}
	
	@FXML
	public void valider(ActionEvent event) throws IOException, DateImpossible {
		update();
		
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
		if (employe.estAdmin(employe.getLigue()))
            admin.setSelected(true);
		
		
	}
	public void update() {
		try {
			employe.setNom(nom.getText());
			employe.setPrenom(prenom.getText());
			employe.setMail(mail.getText());
			employe.setPassword(password.getText());
			updateAdmin();
			employe.setDateArrive(dateArrive.getValue());
			employe.setDateDepart(dateDepart.getValue());
			
		}catch(DateImpossible e) {
			Alert error = new Alert(Alert.AlertType.ERROR);
			error.setTitle("Date Impossible");
			error.setContentText("La date d'arrivée doit être inférieur à la date de départ");
			error.showAndWait();
		}
		
	}
	
	private void updateAdmin()
    {
        if (admin.isSelected()) {
        	employe.getLigue().changeAdmin(employe);
        	System.out.println("selected");
        }
            
        else if (employe.estAdmin(employe.getLigue())) {
        	employe.getLigue().removeAdmin();
        	
        }
            
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadEmploye();
		
	}
}
