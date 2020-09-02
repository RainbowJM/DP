package ovchip.p2_p3;


import ovchip.p2_p3.Reiziger;
import ovchip.p2_p3.ReizigerDAO;

import java.sql.*;
import java.util.List;

public class Main {
    private Connection connection;

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

    private static Connection getConnection(){
        String url = "jdbc:postgresql://localhost:5433/ovchip";
        try {
            Connection conn = DriverManager.getConnection(url, "postgres", "1234qwer");
            ReizigerDAO rdao = new ReizigerDAOPsql(conn);
        } catch (SQLException e){
            System.err.println("[SQLException] " + e.getMessage());
        }
        return null;
    }

    private static void closeConnection(Connection conn) throws SQLException {
        conn.close();
    }

    /**
     * P2. Reiziger DAO: persistentie van een klasse
     *
     * Deze methode test de CRUD-functionaliteit van de Reiziger DAO
     *
     * @throws SQLException
     */
    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        // Maak een nieuwe reiziger aan en persisteer deze in de database
        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        // Voeg aanvullende tests van de ontbrekende CRUD-operaties in.
    }

}
