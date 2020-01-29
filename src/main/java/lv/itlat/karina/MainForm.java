package lv.itlat.karina;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import javax.naming.Name;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.*;
import java.util.UUID;

public class MainForm extends BorderPane {
    public TableView<Record> recordsTable;

    public MainForm() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
    }

    public void addRecord() {


        DataEntryForm dataEntryWindow = new DataEntryForm(this); //This object can do everything, cause it is it's own owner.
        var data = dataEntryWindow.showAndGet(null); /// THIS IS THE RECORD ITSELF 0_0 FCK DMN

        if (data != null) {
            recordsTable.getItems().add(data);
        }
    }

    public void Search() {
        System.out.println("Enter your search");

    }

    public void editRecord() {
        var selected = recordsTable.getSelectionModel().getSelectedItem();
            DataEntryForm dataEntryForm=new DataEntryForm(this);
            dataEntryForm.showAndGet(selected);


    }
    public void initialize() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test;AUTO_SERVER=TRUE");  //~ - means that it is LOCAL DATA BASE(not internet)(Home directory)
        Statement stmt = conn.createStatement();

        ResultSet rs=stmt.executeQuery("select * from records");
        while (rs.next()){
            var id=(UUID)rs.getObject("ID");
            var name=rs.getString("name");
            var email=rs.getString("email");
            var phone=rs.getString("phone");

            Record record=new Record();
            record.setPhone(phone);
            record.setEmail(email);
            record.setName(name);
            record.setId(id);
            recordsTable.getItems().add(record);
        }

        conn.close();
    }

}
