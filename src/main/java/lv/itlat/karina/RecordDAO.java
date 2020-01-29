package lv.itlat.karina;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
}
