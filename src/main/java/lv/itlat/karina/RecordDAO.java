package lv.itlat.karina;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//class dlja connecta s bazoj dannix

public class RecordDAO {
    public static String COON_URL = "jdbc:h2:~/test;AUTO_SERVER=TRUE";

    public static Connection getConnection()throws SQLException {

        Connection conn = DriverManager.getConnection(COON_URL);
        return conn;
    }

    public static void initDB() throws SQLException{
         try (Connection conn= getConnection(); var stmt=conn.createStatement()) //try WITH RESOURCES
        {
            stmt.execute("     CREATE TABLE records (\n" +
                    "        id UUID NOT NULL PRIMARY KEY,\n" +
                    "        name VARCHAR(200) NOT NULL,\n" +
                    "        email VARCHAR(400),\n" +
                    "        phone VARCHAR(50)\n" +
                    "    )\n");
         }
    }

    public static List<Record> getAllRecords() throws SQLException{
        var result = new ArrayList<Record>();
        try (var conn = getConnection(); var stmt = conn.createStatement()){ //try WITH RESOURCES
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

                result.add(record);
            }
            return result;
    }
    }

    public static List<Record> findRecords(String fname,String femail, String fphone) throws SQLException{
        var result = new ArrayList<Record>();
        try (var conn = getConnection(); var stmt = conn.prepareStatement("SELECT * FROM records WHERE UPPER(name) LIKE ? AND UPPER(email) LIKE ? AND UPPER(phone) LIKE ?")){ //try WITH RESOURCES
            stmt.setString(1,"%"+fname.toUpperCase()+"%");
            stmt.setString(2,"%"+femail.toUpperCase()+"%");
            stmt.setString(3,"%"+fphone.toUpperCase()+ "%");
            ResultSet rs=stmt.executeQuery();
            while (rs.next()){
                var name=rs.getString("name");
                var email=rs.getString("email");
                var phone=rs.getString("phone");
                var id=(UUID)rs.getObject("ID");

                Record record=new Record();
                record.setPhone(phone);
                record.setEmail(email);
                record.setName(name);
                record.setId(id);
                result.add(record);
            }
            return result;
        }
    }

    //dobavlajem v bazu dannix
    public static void insertRecord(Record record)throws SQLException{
        try (var conn = getConnection();
             var stmt = conn.prepareStatement("INSERT INTO records" +
                     " (id,name,email,phone) VALUES (?,?,?,?)")){ //try w res...

            stmt.setObject(1,record.getId());
            stmt.setString(2,record.getName());
            stmt.setString(3,record.getEmail());
            stmt.setString(4,record.getPhone());

            stmt.executeUpdate();
        }
    }
    public static void updateRecord(Record record) throws SQLException{
        try(var conn=getConnection(); var stmt=conn.prepareStatement("UPDATE records SET\n" +
                "    name = ?,\n" +
                "    email=?,\n" +
                "    phone=?\n" +
                "    WHERE id=?")) {
            stmt.setString(1,record.getName());
            stmt.setString(2,record.getEmail());
            stmt.setString(3,record.getPhone());
            stmt.setObject(4,record.getId());

            stmt.executeUpdate();
        }

    }

    public static void deleteRecord(Record record)throws SQLException{
        try(var conn=getConnection(); var stmt=conn.prepareStatement("DELETE FROM records WHERE id=?")) {
            stmt.setObject(1,record.getId());

            stmt.executeUpdate();
        }
    }
}
