package model.dbutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static junit.framework.TestCase.assertTrue;

/*This class contains method which handles various db operations*/


public class DBModel {

    static Connection conn;
    static int row_count = 0;

    public Connection connectToDB(String serverUrl, String username, String pass) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    serverUrl, username, pass);

        } catch (Exception e) {
        }

        return conn;
    }

    public int executeQuery(String fileName) {
        try {
            String currentDir = System.getProperty("user.dir");
            File file = new File(currentDir + "/src/test/resources/query/" + fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String readSQLScript = br.readLine();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(readSQLScript);
            while (rs.next())
                row_count = rs.getInt(1);
        } catch (Exception e) {
        }
        return row_count;
    }

    public void verifyresultCount(String count) {
        int expectedcount = Integer.parseInt(count);
        System.out.println("DDD"+expectedcount);
        System.out.println("SSS"+row_count);
        assertTrue(expectedcount == row_count);
    }
}
