package lv.itlat.karina;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import javax.naming.Binding;
import javax.naming.Name;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.*;
import java.util.Optional;
import java.util.UUID;

public class MainForm extends BorderPane {
    public TableView<Record> recordsTable;
    public TextField nameSearchText;
    public TextField emailSearchText;
    public TextField phoneSearchText;
    public Button EditButton;
    public Button DeleteButton;

    public MainForm() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
    }

    public void addRecord() throws SQLException {


        DataEntryForm dataEntryWindow = new DataEntryForm(this); //This object can do everything, cause it is it's own owner.
        var data = dataEntryWindow.showAndGet(null); /// THIS IS THE RECORD ITSELF 0_0 FCK DMN

        if (data != null) {
            recordsTable.getItems().add(data);
            RecordDAO.insertRecord(data);
        }
    }

    public void deleteRecord() throws SQLException{
        var selected=recordsTable.getSelectionModel().getSelectedItem();
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete confirmation");
        alert.setHeaderText("Deleting "+selected.getName());
        alert.setContentText("Are you sure???!!!");

        Optional<ButtonType> result=alert.showAndWait();
        if(result.get()==ButtonType.OK){
            recordsTable.getItems().remove(selected);
            RecordDAO.deleteRecord(selected);
        }
    }



    public void editRecord() throws SQLException{
        var selected = recordsTable.getSelectionModel().getSelectedItem();
        DataEntryForm dataEntryForm = new DataEntryForm(this);
        if(dataEntryForm.showAndGet(selected)!=null){
            RecordDAO.updateRecord(selected);
        }


    }

    public void initialize() throws SQLException {
        var records = RecordDAO.getAllRecords();
        recordsTable.getItems().setAll(records);
        DeleteButton.disableProperty().bind(Bindings.createBooleanBinding(()->{
            return recordsTable.getSelectionModel().getSelectedCells().size()==0;   // Making a button, that is disabled if nothing is selected
        },recordsTable.getSelectionModel().getSelectedItems()));

        EditButton.disableProperty().bind(Bindings.createBooleanBinding(()->{
            return recordsTable.getSelectionModel().getSelectedCells().size()==0;   // Making a button, that is disabled if nothing is selected
        },recordsTable.getSelectionModel().getSelectedItems()));

    }
    public void doSearch()throws SQLException{
        var name =nameSearchText.getText();
        var email=emailSearchText.getText();
        var phone=phoneSearchText.getText();
        var records=RecordDAO.findRecords(name,email,phone);
        recordsTable.getItems().setAll(records);
    }



}
