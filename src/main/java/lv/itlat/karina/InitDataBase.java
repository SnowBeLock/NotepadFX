package lv.itlat.karina;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InitDataBase {
    public static void main(String[] args) throws SQLException {
        RecordDAO.initDB();
    }
}
