package lv.itlat.karina;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage primaryStage;

    public void start(Stage stage) throws Exception {
        Main.primaryStage=stage;


        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));         //adding class named Main fxml, and loading all the resources. That's because name of the package is the same(not just the name)

        stage.setTitle("Notepad");
        var scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
