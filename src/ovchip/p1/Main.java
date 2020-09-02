package ovchip.p1;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5433/ovchip";
        try {
            Connection conn = DriverManager.getConnection(url, "postgres", "1234qwer");

            Statement stat = conn.createStatement();
            String query = "SELECT * from reiziger";

            ResultSet rs = stat.executeQuery(query);

            while (rs.next()) {
                String reisId = rs.getString("reiziger_id");
                String le = rs.getString("voorletters");
                String anaam = rs.getString("achternaam");
                System.out.println(reisId + " " + le + ". " + anaam);
            }
        } catch (SQLException sql) {
            System.err.println("[SQL Exception] " + sql.getMessage());;
        }
    }
}
