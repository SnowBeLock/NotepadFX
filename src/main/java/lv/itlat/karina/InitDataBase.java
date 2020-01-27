package lv.itlat.karina;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InitDataBase {
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test");
        conn.close();
    }
}
