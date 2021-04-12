package javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import personnel.Ligue;

public class ModifierLigueController implements Initializable{
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private TextField nomLigue;
	@FXML
	private Label message;
	private Ligue ligue = LiguesController.getLigue();
	private String currentName = LiguesController.getLigue().getNom();
	
	@FXML
	private void quitter() throws IOException
	{
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/javafx/view/Ligue.fxml"));
		anchorPane.getChildren().setAll(anchor);
	}
	
	@FXML
	private void updateLigue() throws IOException
	{
		if (nomLigue.getLength() == 0) {
			nomLigue.setText(currentName);
			message.setText("Le nom le la Ligue ne peut pas être vide ✘");
		} else {
			ligue.setNom(nomLigue.getText());
			quitter();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nomLigue.setText(currentName);
	}
}
