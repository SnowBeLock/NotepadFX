package lv.itlat.karina;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainForm extends BorderPane {
    public MainForm() throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("main.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
    }

    public void addRecord()  {


        DataEntryForm dataEntryWindow=new DataEntryForm(this); //This object can do everything, cause it is it's own owner.
var data=dataEntryWindow.showAndGet();
    }
    public void Search() {
        System.out.println("Enter your search");

    }

}
