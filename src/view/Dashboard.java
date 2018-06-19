package view;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import controller.AtsiliepimaiDao;
import controller.Validation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Atsiliepimai;

public class Dashboard {
	
	private BorderPane bpRoot;
	private Stage primaryStage;
	private TextField IdText;
	private TextField MiestasText;
	private TextField VardasText;
	private TextField ElPastasText;
	private TextField AtsiliepimasText;
	private GridPane atsiliepimaiGrid;
	private String items;

	public Dashboard(Stage primaryStage) {
		this.bpRoot = new BorderPane();
		Scene scene = new Scene(this.bpRoot,900,440);
		scene.getStylesheets().add(getClass().getResource("../view/application.css").toExternalForm());
		this.primaryStage = primaryStage;
		this.primaryStage.setScene(scene);
		this.primaryStage.setTitle("Atsiliepimai");
		this.primaryStage.setResizable(false);
		this.primaryStage.centerOnScreen();
		addElementsToScene();
		this.primaryStage.show();
	}
	
	private void addElementsToScene() {
		
		 Label Id = new Label("Id: ");
		 Label Miestas = new Label("Miestas: ");
		 Label Vardas = new Label("Vardas: ");
		 Label ElPastas = new Label("El.Pastas: ");
		 Label Atsiliepimas = new Label("Atsiliepimas: ");
		 
		 this.IdText = new TextField();
		 this.MiestasText = new TextField();
		 this.VardasText = new TextField();
		 this.ElPastasText = new TextField();
		 this.AtsiliepimasText = new TextField();
		 
		 Button btnAdd = new Button("Prideti");
		 btnAdd.setMinWidth(80);
		 Button btnDelete = new Button("Istrinti");
		 btnDelete.setMinWidth(80);
		 Button btnUpdate = new Button("Redaguoti");
		 btnUpdate.setMinWidth(80);
		 
		 TableView table = new TableView();
		 table.setEditable(true);
		 
		 TableColumn IdCol = new TableColumn("id");
		 IdCol.setCellValueFactory(new PropertyValueFactory<Atsiliepimai, Integer>("id"));
		 IdCol.setMinWidth(20);
		 
		 TableColumn MiestasCol = new TableColumn("Miestas");
		 MiestasCol.setCellValueFactory(new PropertyValueFactory<Atsiliepimai, String>("miestas"));
		 MiestasCol.setMinWidth(100);
		 
		 TableColumn VardasCol = new TableColumn("Vardas");
		 VardasCol.setCellValueFactory(new PropertyValueFactory<Atsiliepimai, String>("vardas"));
		 VardasCol.setMinWidth(100);
		 
		 TableColumn ElPastasCol = new TableColumn("El.Pastas");
		 ElPastasCol.setCellValueFactory(new PropertyValueFactory<Atsiliepimai, Integer>("elPastas"));
		 ElPastasCol.setMinWidth(200);
		 
		 TableColumn AtsiliepimasCol = new TableColumn("Atsiliepimas");
		 AtsiliepimasCol.setCellValueFactory(new PropertyValueFactory<Atsiliepimai, String>("atsiliepimas"));
		 AtsiliepimasCol.setMinWidth(200);
		 
		 table.getColumns().addAll(IdCol, MiestasCol, VardasCol, ElPastasCol, AtsiliepimasCol);
		 
		 ObservableList<Atsiliepimai> data = FXCollections.observableArrayList();
		 AtsiliepimaiDao atsiliepimaiDao = new AtsiliepimaiDao();
		 atsiliepimaiDao.showElements(data);
		 table.setItems(data);
		 
		 GridPane gpButtons = new GridPane();
		 gpButtons.add(btnAdd,0,0);
		 gpButtons.add(btnDelete,1,0);
		 gpButtons.add(btnUpdate,2,0);
		 
		 this.atsiliepimaiGrid = new GridPane();
		 atsiliepimaiGrid.add(Id, 0, 0);
		 atsiliepimaiGrid.add(IdText, 1, 0);
		 atsiliepimaiGrid.add(Miestas, 0, 1);
		 atsiliepimaiGrid.add(MiestasText, 1, 1);
		 atsiliepimaiGrid.add(Vardas, 0, 2);
		 atsiliepimaiGrid.add(VardasText, 1, 2);
		 atsiliepimaiGrid.add(ElPastas, 0, 3);
		 atsiliepimaiGrid.add(ElPastasText, 1, 3);
		 atsiliepimaiGrid.add(Atsiliepimas, 0, 4);
		 atsiliepimaiGrid.add(AtsiliepimasText, 1, 4);
		 atsiliepimaiGrid.add(gpButtons,0 , 5, 2, 1);
		 atsiliepimaiGrid.setPadding(new Insets(10, 10, 10, 10));
		 atsiliepimaiGrid.setVgap(10);
		 atsiliepimaiGrid.setHgap(10);
		 
		 
		 bpRoot.setCenter(atsiliepimaiGrid);
		 bpRoot.setRight(table);
	
	
		btnAdd.setOnAction(new EventHandler<ActionEvent>(){
			
	
			@Override
			public void handle(ActionEvent event){
				if(atsiliepimai_validate("add"))
				{
				Atsiliepimai atsiliepimas = new Atsiliepimai(
						MiestasText.getText().toString(),
						VardasText.getText().toString(),
						ElPastasText.getText().toString(),
						AtsiliepimasText.getText().toString());
				
				try {
					atsiliepimaiDao.addElement(atsiliepimas);
				} catch (MySQLIntegrityConstraintViolationException e) {
					e.printStackTrace();
				}	
				
				table.getItems().clear();
				
				atsiliepimaiDao.showElements(data);			
				}				
			}
			
		});
		
		btnDelete.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event){
				atsiliepimai_validate("delete");
				
				boolean IsExistingEntryIdByUser = false;
				
				for(int i = 0 ;i < data.size(); i++)
				{
					if(data.get(i).getId() == Integer.parseInt(IdText.getText()) )
					{
						IsExistingEntryIdByUser = true;
						
						atsiliepimaiDao.deleteAtsiliepimai(Integer.parseInt(IdText.getText()));
						table.getItems().clear();
						atsiliepimaiDao.showElements(data);
					}
				}
				
				if (!IsExistingEntryIdByUser) {
					showAlert(Alert.AlertType.ERROR, atsiliepimaiGrid.getScene().getWindow(), "Form Klaida!", "Toks id neegzistuoja");
				}
			}
	});
		
		btnUpdate.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				if(atsiliepimai_validate("update")) {
				int id = 0 ;
				Atsiliepimai updateElement = new Atsiliepimai(
						Integer.parseInt(IdText.getText().toString()),
						MiestasText.getText().toString(),
						VardasText.getText().toString(),
						ElPastasText.getText().toString(),
						AtsiliepimasText.getText().toString()
						);
				
				boolean IsExistingEntryIdByUser = false;
				
				for(int i = 0 ;i<data.size();i++)
				{
					if(data.get(i).getId() == Integer.parseInt(IdText.getText()) )
					{
						
						IsExistingEntryIdByUser = true;
						
						atsiliepimaiDao.updateElement(updateElement);
						table.getItems().clear();
						atsiliepimaiDao.showElements(data);
					}
				}
				
				if (!IsExistingEntryIdByUser) {
					showAlert(Alert.AlertType.ERROR, atsiliepimaiGrid.getScene().getWindow(), "Form Klaida!", "Toks id neegzistuoja");
				}
	
			}
		}
	});
	
	}
	
	private boolean atsiliepimai_validate(String action) {
		this.items = "";
		switch(action) {
		case "delete":
			if(!Validation.isValidID(IdText.getText().toString())){
				showAlert(Alert.AlertType.ERROR, atsiliepimaiGrid.getScene().getWindow(), "Form Klaida!", "Neteisingas ID formatas");	
				return false;
			}				
			else return true;
		case "update":
			if(!Validation.isValidID(IdText.getText().toString())){
				showAlert(Alert.AlertType.ERROR, atsiliepimaiGrid.getScene().getWindow(), "Form Klaida!", "Neteisingas ID formatas");
				return false;
			} else if (!Validation.isValidCredentialsForAdd(MiestasText.getText().toString())) {
				showAlert(Alert.AlertType.ERROR, atsiliepimaiGrid.getScene().getWindow(), "Form Klaida!", "Netinkamas Miestas");
				return false;
			} else if (!Validation.isValidCredentialsForAdd(VardasText.getText().toString())) {
				showAlert(Alert.AlertType.ERROR, atsiliepimaiGrid.getScene().getWindow(), "Form Klaida!", "Netinkamas Vardas");
				return false;
			} else if (!Validation.isValidEmail(ElPastasText.getText().toString())) {
				showAlert(Alert.AlertType.ERROR, atsiliepimaiGrid.getScene().getWindow(), "Form Klaida!", "Netinkamas El.Pastas");
				return false;
			} else if (!Validation.isValidAtsiliepimas(AtsiliepimasText.getText().toString())) {
				showAlert(Alert.AlertType.ERROR, atsiliepimaiGrid.getScene().getWindow(), "Form Klaida!", "Netinkamas Atsiliepimas");
				return false;
			}
			else return true;
		default:
			if (!Validation.isValidCredentialsForAdd(MiestasText.getText().toString())) {
				showAlert(Alert.AlertType.ERROR, atsiliepimaiGrid.getScene().getWindow(), "Form Klaida!", "Netinkamas Miestas");
				return false;
			} else if (!Validation.isValidCredentialsForAdd(VardasText.getText().toString())) {
				showAlert(Alert.AlertType.ERROR, atsiliepimaiGrid.getScene().getWindow(), "Form Klaida!", "Netinkamas Vardas");
				return false;
			} else if (!Validation.isValidEmail(ElPastasText.getText().toString())) {
				showAlert(Alert.AlertType.ERROR, atsiliepimaiGrid.getScene().getWindow(), "Form Klaida!", "Netinkamas El.Pastas");
				return false;
			} else if (!Validation.isValidAtsiliepimas(AtsiliepimasText.getText().toString())) {
				showAlert(Alert.AlertType.ERROR, atsiliepimaiGrid.getScene().getWindow(), "Form Klaida!", "Netinkamas Atsiliepimas");
				return false;
			}
		}
		return true;
	}
	
	private void showAlert(Alert.AlertType alerType, Window owner, String title, String message){
		Alert alert = new Alert(alerType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
		}
	
}