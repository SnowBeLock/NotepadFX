package lv.itlat.karina;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InitDataBase {
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test");  //~ - means that it is LOCAL DATA BASE(not internet)(Home directory)
        Statement stmt = conn.createStatement();
        stmt.execute("     CREATE TABLE records (\n" +
                "        id UUID NOT NULL PRIMARY KEY,\n" +
                "        name VARCHAR(200) NOT NULL,\n" +
                "        email VARCHAR(400),\n" +
                "        phone VARCHAR(50)\n" +
                "    )\n");

        stmt.execute(
                "    INSERT INTO\n" +
                "    records (id,name,email,phone)\n" +
                "    VALUES (\n" +
                "        'ac026cf2-412e-11ea-b77f-2e728ce88125',\n" +
                "        'Thomas Kolesnikov',\n" +
                "        'Tommy@gmailis.elve',\n" +
                "        '1337420'\n" +
                "    )");


        conn.close();
    }
}
