package javafx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import javafx.event.ActionEvent;

public class GererController {
	
	@FXML
	AnchorPane anchorPane;

	@FXML
	public void accessRoot(ActionEvent event) throws IOException {
		AnchorPane accessRoot = (AnchorPane)FXMLLoader.load(getClass().getResource("/javafx/view/Root.fxml"));
		anchorPane.getChildren().setAll(accessRoot);
	}
	
	@FXML
	public void accessLigues(ActionEvent event) throws IOException {
		AnchorPane accessLigues = (AnchorPane)FXMLLoader.load(getClass().getResource("/javafx/view/Ligues.fxml"));
		anchorPane.getChildren().setAll(accessLigues);
	}
}
