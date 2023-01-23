package peaksoft.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final static String url = "jdbc:postgresql://localhost:5432/task1";
    private final static String username = "postgres";
    private final static String password = "postgres";

    public static Connection getConnection() {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url,username,password);
            System.out.println("Successfully connected to database ...");
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
