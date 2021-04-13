package javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import personnel.DateImpossible;
import personnel.Employe;

public class ModifierEmployeController implements Initializable {
	@FXML
	private AnchorPane anchorPane;
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
	@FXML
	private Label warning;
	private Employe employe = LigueController.getEmploye();

	private void loadData()
	{
		nom.setText(employe.getNom());
		prenom.setText(employe.getPrenom());
		mail.setText(employe.getMail());
		password.setText(employe.getPass());
		dateArrive.setValue(employe.getDateArrive());
		dateDepart.setValue(employe.getDateDepart());
		if (employe.estAdmin(employe.getLigue()))
			admin.setSelected(true);
	}
	
	private boolean checkDate()
	{
		try {
			employe.setDateArrive(dateArrive.getValue());
			employe.setDateDepart(dateDepart.getValue());
			return true;
		} catch(Exception e) {
			warning.setText(e.getMessage());
			return false;
		}
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
				checkField("prenom", prenom.getText()) &&
				checkField("mail", mail.getText()) &&
				checkField("password", password.getText()) && 
				checkDate());
	}
	
	private void updateAdmin()
	{
		if (admin.isSelected())
			employe.getLigue().changeAdmin(employe);
		else if (employe.estAdmin(employe.getLigue()))
			employe.getLigue().removeAdmin();
	}
	
	@FXML
	private void quitter() throws IOException
	{
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/javafx/view/Ligue.fxml"));
		anchorPane.getChildren().setAll(anchor);
	}
	
	@FXML
	private void valider() throws DateImpossible, IOException
	{
		if (checkRequiredFields()) {
			updateAdmin();
			employe.setNom(nom.getText());
			employe.setPrenom(prenom.getText());
			employe.setMail(mail.getText());
			employe.setPassword(password.getText());
			employe.setDateArrive(dateArrive.getValue());
			employe.setDateDepart(dateDepart.getValue());
			quitter();
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadData();
	}
}
