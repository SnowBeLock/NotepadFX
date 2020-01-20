package lv.itlat.karina;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    public void addRecord() throws IOException {

        var stage =new Stage();



        Parent root = FXMLLoader.load(
                getClass().getResource("data-entry.fxml"));         //adding class named Main fxml, and loading all the resources. That's because name of the package is the same(not just the name)

        stage.initOwner(Main.primaryStage);
        stage.initModality(Modality.NONE);

        stage.setTitle("Hmmmmmmm..... record");
        var scene=new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
