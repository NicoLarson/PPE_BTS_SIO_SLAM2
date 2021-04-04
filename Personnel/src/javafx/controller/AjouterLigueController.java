package javafx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import personnel.GestionPersonnel;
import personnel.SauvegardeImpossible;

public class AjouterLigueController {
	
	private GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private TextField nomLigue;
	@FXML
	private Label nomVide;

	
	@FXML
	public void quitter() throws IOException {
		
		AnchorPane accessLigues = (AnchorPane)FXMLLoader.load(getClass().getResource("/javafx/view/Ligues.fxml"));
		anchorPane.getChildren().setAll(accessLigues);
	}
	
	@FXML
	public void ajouterLigue() throws SauvegardeImpossible, IOException {
		
		if(nomLigue.getText().equals(""))
			fadeInAndOut();
		else {
			gestionPersonnel.addLigue(nomLigue.getText());
			quitter();
		}

	}
	
	public void fadeInAndOut() {
		
		FadeTransition in = new FadeTransition(Duration.millis(150),nomVide);
		FadeTransition out = new FadeTransition(Duration.millis(300),nomVide);
		PauseTransition pause = new PauseTransition(Duration.millis(1500));
		in.setFromValue(0);
		in.setToValue(1);
		out.setFromValue(1.0);
		out.setToValue(0);
		in.play();
		in.setOnFinished((e)->pause.play());
		pause.setOnFinished((e)->out.play());
	}
}
