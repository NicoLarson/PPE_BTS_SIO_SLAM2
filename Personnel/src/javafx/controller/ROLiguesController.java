package javafx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.SortedSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import personnel.GestionPersonnel;
import personnel.Ligue;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

public class ROLiguesController implements Initializable{
	
	private GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();
	private SortedSet<Ligue> ligues = gestionPersonnel.getLigues();
	private ObservableList<Ligue> observableLigues;
	private static Ligue ligue;
	
	@FXML private AnchorPane anchorPane;
	@FXML private TableView<Ligue> tableView;
	@FXML private TableColumn<Ligue, String> index;
	@FXML private TableColumn<Ligue, String> nomLigue;


	@FXML
	public void logout(ActionEvent event) throws IOException {
		AnchorPane anchor = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafx/view/Login.fxml"));
		anchorPane.getChildren().setAll(anchor);
	}
	
	@FXML
	public void selectLigue(ActionEvent event) throws IOException {
		
		ligue = tableView.getSelectionModel().getSelectedItem();
		LiguesController.setLigue(ligue);
		
		if (ligue != null) {
			String liguePath = (LoginController.getUser().estAdmin(ligue)||LoginController.getUser().estRoot())
					?"/javafx/view/Ligue.fxml":"/javafx/view/ROLigue.fxml";
			AnchorPane selectedLigue = (AnchorPane)FXMLLoader.load(getClass().getResource(liguePath));
			anchorPane.getChildren().setAll(selectedLigue);
			
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void loadLigues() {
		observableLigues = FXCollections.observableArrayList(ligues);
		nomLigue.setCellValueFactory(new PropertyValueFactory<>("nom"));
		tableView.setItems(observableLigues);
		index.setCellFactory( (Callback) p -> new TableCell()
			{
			    public void updateItem( Object item, boolean empty )
			    {
			        super.updateItem( item, empty );
			        setGraphic( null );
			        setText( empty ? null : getIndex() + 1 + "" );
			    }
			}
		);
		
	}
	
	public static Ligue getLigue() {
		return ligue;
	}
	public static void setLigue(Ligue ligue) {
		ROLiguesController.ligue = ligue;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		loadLigues();
		
	}
}
