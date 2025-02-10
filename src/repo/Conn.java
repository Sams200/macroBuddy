package repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
    /*
    the connection to the database together with the path,
    username, and password
     */
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "postgres";
    private static final String password = "1234";
    public static Connection connect(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, user, password);
            return conn;
        }
        catch (SQLException e){
            System.out.println("Connection error");
            e.printStackTrace();
            return null;
        }

    }
}
