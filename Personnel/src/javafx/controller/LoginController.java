package javafx.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import personnel.Employe;
import personnel.GestionPersonnel;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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
		Platform.runLater(() -> passwordLogin.requestFocus());
	}
	
	@FXML
	private void checkLogin(String password) throws IOException
	{
		if (password.equals(root.getPass())) {
			AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/javafx/view/Gerer.fxml"));
			anchorPane.getChildren().setAll(anchor);
		} else {
			passwordIncorrect.setText("Mot de passe incorrect");
			passwordLogin.setText("");
		}
	}
	
	@FXML
	public void btnLogin() throws IOException {		
		checkLogin(passwordLogin.getText());
	}

	@FXML
	private void handleOnKeyPressed(KeyEvent event) throws IOException
	{
		if (event.getCode() == KeyCode.ENTER)
			checkLogin(passwordLogin.getText());
	}
}
