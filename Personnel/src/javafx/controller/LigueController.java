package javafx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.SortedSet;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import personnel.Employe;
import personnel.Ligue;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

public class LigueController implements Initializable {
	
	private ObservableList<Employe> observableEmploye;
	private Ligue ligue;
	private static Employe employe;
	
	private SortedSet<Employe> employes;
	@FXML private AnchorPane anchorPane;
	@FXML private TableView<Employe> employeTable;
	@FXML private TableColumn<Employe, String> index;
	@FXML private TableColumn<Employe, String> nomEmploye;
	@FXML private Label nomLigue;
	

	@FXML
	public void quitter(ActionEvent event) throws IOException {
		AnchorPane accessLigues = (AnchorPane)FXMLLoader.load(getClass().getResource("/javafx/view/Ligues.fxml"));
		anchorPane.getChildren().setAll(accessLigues);
	}

	@FXML
	public void ajouterEmploye(ActionEvent event) throws IOException {
		AnchorPane anchor = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafx/view/AjouterEmploye.fxml"));
		anchorPane.getChildren().setAll(anchor);
	}

	@FXML
	public void selectEmploye(ActionEvent event) throws IOException {
		employe = employeTable.getSelectionModel().getSelectedItem();

		if (employe != null) {
			AnchorPane anchor = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafx/view/ModifierEmploye.fxml"));
			anchorPane.getChildren().setAll(anchor);
		}
	}

	@FXML
	private void updateLigue() throws IOException
	{
		AnchorPane anchor = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafx/view/ModifierLigue.fxml"));
		anchorPane.getChildren().setAll(anchor);
	}
	
	@FXML
	public void supprimer(ActionEvent event) {
		employe = employeTable.getSelectionModel().getSelectedItem();

		if (employe != null) {
			employe.remove();
			loadEmployes();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void loadEmployes() {
		employes = ligue.getEmployes();
		nomLigue.setText(ligue.getNom());
		
		observableEmploye = FXCollections.observableArrayList(employes);
		nomEmploye.setCellValueFactory(new PropertyValueFactory<>("nom"));
		employeTable.setItems(observableEmploye);
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
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ligue = LiguesController.getLigue();
		loadEmployes();
	}
	
	public static Employe getEmploye() {
		return employe;
	}
	

}
