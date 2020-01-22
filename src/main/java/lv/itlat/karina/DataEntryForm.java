package lv.itlat.karina;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class DataEntryForm extends BorderPane {

    private final Stage stage = new Stage();
    public TextField nameField;
    public TextField emailField;
    public TextField phoneField;

    private boolean isOk = false;


    public DataEntryForm(Pane parent) {
        try {
            var loader = new FXMLLoader(getClass().getResource("data-entry.fxml"));

            loader.setRoot(this);
            loader.setController(this);
            loader.load();


            stage.initOwner(parent.getScene().getWindow());
            stage.initModality(Modality.NONE);

            stage.setTitle("Add record");
            var scene = new Scene(this);
            stage.setScene(scene);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

    public Record showAndGet() {
        stage.showAndWait();
        if (isOk) {
            var record = new Record();
            record.setName(nameField.getText());
            record.setEmail(emailField.getText());
            record.setPhone(phoneField.getText());
            return record;
        } else {
            return null;
        }
    }

    public void cancelPressed() {
        stage.hide();

    }

    public void okPressed() {
        stage.hide();
        isOk = true;
    }
}
