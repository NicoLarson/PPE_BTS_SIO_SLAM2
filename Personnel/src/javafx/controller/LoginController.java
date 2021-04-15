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
import personnel.Ligue;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LoginController implements Initializable{
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private PasswordField password;
	@FXML
	private TextField login;
	@FXML
	private Label warning;

	private final GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();
	private Employe root = gestionPersonnel.getRoot();
	private static Employe user;
	
	
	public GestionPersonnel getGestion() {
		return gestionPersonnel;
	}
	
	public static Employe getUser() {
		return user;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Platform.runLater(() -> login.requestFocus());
	}
	
	private void checkLogin(String username, String pwd) throws IOException
	{
		if (username.equals(root.getNom()) && pwd.equals(root.getPass()))
		{
			user = root;
			AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/javafx/view/Gerer.fxml"));
			anchorPane.getChildren().setAll(anchor);
		} 
		
		else 
		{
			for(Ligue ligue : gestionPersonnel.getLigues()) 
			{
				
				for(Employe employe : ligue.getEmployes()) 
				{
					
					if (username.equals(employe.getMail()) && pwd.equals(employe.getPass())) 
					{
						user = employe;
						AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/javafx/view/ROLigues.fxml"));
						anchorPane.getChildren().setAll(anchor);
					}
				}
			}
			warning.setText("Mot de passe incorrect");
			password.setText("");
		}
	}
	
	@FXML
	private void btnLogin() throws IOException {		
		checkLogin(login.getText(), password.getText());
	}

	@FXML
	private void handleOnKeyPressed(KeyEvent event) throws IOException
	{
		if (event.getCode() == KeyCode.ENTER)
			checkLogin(login.getText(), password.getText());
	}
}
