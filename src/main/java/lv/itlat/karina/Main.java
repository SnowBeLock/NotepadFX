package lv.itlat.karina;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage stage) throws Exception {

       var root=new MainForm();       //adding class named Main fxml, and loading all the resources. That's because name of the package is the same(not just the name)

        stage.setTitle("Notepad");
        var scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
