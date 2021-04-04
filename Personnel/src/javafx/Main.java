package javafx;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private static Stage stage;
	@Override
	public void start(Stage primaryStage) throws IOException {
			stage = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("view/Login.fxml"));
			stage.getIcons().add(new Image("manage_accounts.png"));
			stage.setScene(new Scene(root));
			stage.setResizable(false);
			stage.show();
		
	}
	public static void main(String[] args) {
		launch(args);
	}
	public static Stage getStage() {
		return stage;
	}
}