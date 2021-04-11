package javafx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import personnel.Employe;
import personnel.GestionPersonnel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class RootController implements Initializable {
	
	private GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();
	private Employe root = gestionPersonnel.getRoot();
	
	@FXML
	private Label rootTitle;
	@FXML
	private Label message;
	@FXML
	private TextField nomTextField;
	@FXML
	private TextField prenomTextField;
	@FXML
	private TextField mailTextField;
	@FXML
	private TextField passwordTextField;
	@FXML
	private AnchorPane anchorPane;
	

	private void setTextField() {
		rootTitle.setText(root.getNom());
		nomTextField.setText(root.getNom());
		prenomTextField.setText(root.getPrenom());
		mailTextField.setText(root.getMail());
		passwordTextField.setText(root.getPass());
	}
	
	private void setMessage(String str, String color)
	{
		message.setText(str);
		message.setStyle("-fx-text-fill: " + color + ";");
	}
	
	private boolean checkRequiredFields()
	{
		boolean flag = true;
		
		if (nomTextField.getText().length() == 0) {
			setMessage("Le champ nom est obligatoire", "red");
			nomTextField.setText(root.getNom());
			flag = false;
		}
		if (passwordTextField.getText().length() == 0) {
			setMessage("Le champ mot de passe est obligatoire", "red");
			passwordTextField.setText(root.getPass());
			flag = false;
		}
		return flag;
	}
	
	@FXML
	public void sauvegarder() {
		if (checkRequiredFields()) {
			if(!nomTextField.getText().equals(root.getNom())) {root.setNom(nomTextField.getText());rootTitle.setText(nomTextField.getText());}
			if(!prenomTextField.getText().equals(root.getPrenom())) {root.setPrenom(prenomTextField.getText());}
			if(!mailTextField.getText().equals(root.getMail())) {root.setMail(mailTextField.getText());}
			if(!passwordTextField.getText().equals(root.getPass())) {root.setPassword(passwordTextField.getText());}
			setMessage("Données mises à jour 🗸", "green");
		}
	}
	
	@FXML
	public void quitter() throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/javafx/view/Gerer.fxml"));
		anchorPane.getChildren().setAll(anchor);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setTextField();
	}
}
