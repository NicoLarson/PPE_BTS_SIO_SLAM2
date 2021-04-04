package javafx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.SortedSet;

import com.sun.tools.javac.Main;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import personnel.GestionPersonnel;
import personnel.Ligue;

public class LiguesController implements Initializable{
	
	private GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();
	private SortedSet<Ligue> ligues = gestionPersonnel.getLigues();
	private ObservableList<Ligue> observableLigues;
	
	
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
		
		
		
		AnchorPane ajouteLigue = (AnchorPane)FXMLLoader.load(getClass().getResource("/javafx/view/Ligue.fxml"));
		anchorPane.getChildren().setAll(ajouteLigue);
		
	}
	@FXML
	public void ajouterLigue() throws IOException {
		AnchorPane ajouteLigue = (AnchorPane)FXMLLoader.load(getClass().getResource("/javafx/view/AjouterLigue.fxml"));
		anchorPane.getChildren().setAll(ajouteLigue);
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
	
}
