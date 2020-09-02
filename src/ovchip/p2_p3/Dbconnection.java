package ovchip.p2_p3;

import java.sql.*;

public class Dbconnection {
    public static Connection conn;

    public static void startConnection(){
        String url = "jdbc:postgresql://localhost:5433/ovchip";
        try {
            conn = DriverManager.getConnection(url, "postgres", "1234qwer");
        } catch (SQLException sql) {
            System.err.println("[SQLException] " + sql.getMessage());
        }
    }



}
