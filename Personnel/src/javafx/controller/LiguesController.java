package javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.SortedSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import personnel.GestionPersonnel;
import personnel.Ligue;


public class LiguesController implements Initializable{
	
	private GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();
	private SortedSet<Ligue> ligues = gestionPersonnel.getLigues();
	private ObservableList<Ligue> observableLigues;
	private static Ligue ligue;
	
	@FXML private AnchorPane anchorPane;
	@FXML private TableView<Ligue> tableView;
	@FXML private TableColumn<Ligue, String> index;
	@FXML private TableColumn<Ligue, String> nomLigue;
	

	@FXML
	public void quitter() throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/javafx/view/Gerer.fxml"));
		anchorPane.getChildren().setAll(anchor);
	}
	@FXML
	public void selectLigue(ActionEvent event) throws IOException {
		ligue = tableView.getSelectionModel().getSelectedItem();
		
		if (ligue != null) {
			//MainApp.getStage().setUserData(tableView.getSelectionModel().getSelectedItem());
			AnchorPane selectedLigue = (AnchorPane)FXMLLoader.load(getClass().getResource("/javafx/view/Ligue.fxml"));
			anchorPane.getChildren().setAll(selectedLigue);
		}
		
	}
	@FXML
	public void ajouterLigue() throws IOException {
		AnchorPane ajouteLigue = (AnchorPane)FXMLLoader.load(getClass().getResource("/javafx/view/AjouterLigue.fxml"));
		anchorPane.getChildren().setAll(ajouteLigue);
	}
	@FXML
	public void supprimer(ActionEvent event) {
		ligue = tableView.getSelectionModel().getSelectedItem();
		
		if (ligue != null) {
			ligue.remove();
			loadLigues();
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadLigues();
		
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
	
}
