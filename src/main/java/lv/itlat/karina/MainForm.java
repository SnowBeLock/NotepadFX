package lv.itlat.karina;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainForm extends BorderPane {
    public TableView<Record> recordsTable;

    public MainForm() throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("main.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
    }

    public void addRecord()  {


        DataEntryForm dataEntryWindow=new DataEntryForm(this); //This object can do everything, cause it is it's own owner.
var data=dataEntryWindow.showAndGet(); /// THIS IS THE RECORD ITSELF 0_0 FCK DMN

if(data!=null){
    recordsTable.getItems().add(data);
}
    }
    public void Search() {
        System.out.println("Enter your search");

    }
    public void editRecord(){
        var selected=recordsTable.getSelectionModel().getSelectedItem();
        System.out.println(selected.getName());
       // System.out.println("HAHA"); // here should be a method, that can change created content.
    }

}
