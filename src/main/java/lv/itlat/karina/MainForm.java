package lv.itlat.karina;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import javax.naming.Name;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.*;
import java.util.UUID;

public class MainForm extends BorderPane {
    public TableView<Record> recordsTable;
    public TextField nameSearchText;
    public TextField emailSearchText;
    public TextField phoneSearchText;

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

    public void Search() {
        System.out.println("Enter your search");
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
    }
    public void doSearch()throws SQLException{
        var name =nameSearchText.getText();
        var records=RecordDAO.findRecords(name,"","");
        recordsTable.getItems().setAll(records);
    }
}
