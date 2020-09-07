package ovchip.p2_p3_p4;

import java.sql.*;
import java.util.List;

public class Main {
    private static Connection connection;

    public static void main(String[] args) {
        try{
           getConnection();
        }catch (Exception e){
            System.err.println("[Exception] " + e.getMessage());
            closeConnection();
        }
    }

    private static Connection getConnection(){
        String url = "jdbc:postgresql://localhost:5433/ovchip";
        try {
            Connection conn = DriverManager.getConnection(url, "postgres", "1234qwer");
            ReizigerDAO rdao = new ReizigerDAOPsql(conn);
            AdresDAO adao = new AdresDAOPsql(conn);
            testReizigerDAO(rdao);
            testAdresDAO(adao);
        } catch (SQLException e){
            System.err.println("[SQLException] " + e.getMessage());
        }
        return null;
    }

    private static void closeConnection() {
        try {
            connection.close();
        }catch (SQLException e){
            System.err.println("[SQLExcepton] " + e.getMessage());
        }

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

        // Maak een nieuwe reizigers aan en persisteer deze in de database
        String gbdatum1 = "1981-03-14";
        Reiziger sietske = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum1));
        rdao.save(sietske);
//        rdao.delete(sietske);

        String gbdatum2 = "2000-07-22";
        Reiziger ellen = new Reiziger(50, "E", "L", "Lopez", java.sql.Date.valueOf(gbdatum2));
        rdao.save(ellen);
//        rdao.delete(ellen);

        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        System.out.println("\n----------------------------");

        // Haal alle reizigers op uit de database
        List<Reiziger> nReizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : nReizigers) {
            System.out.println(r);
        }
        System.out.println();

        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        // Delete de net aangemaakt reiziger
        rdao.delete(ellen);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers na het verwijderen van de net aangemaakt reiziger\n");
        System.out.println("\n----------------------------");

        // Update reiziger -!!
//        sietske =
        rdao.update(sietske);
        System.out.println("[Test] de updated versie van reiziger: " + sietske);
        System.out.println("\n----------------------------");

        // Find by id
        Reiziger reizigerId = rdao.findById(1);
        System.out.println("[Test] ReizigerDAO.findById() geeft: " + reizigerId);
        System.out.println();
        System.out.println("\n----------------------------");

        // Find by birthday
        List<Reiziger> reizigerG = rdao.findByGbdatum(gbdatum1);
        System.out.println("[Test] ReizigerDAo.findByGbdatum() geeft \n" + reizigerG);
        System.out.println("\n----------------------------");

        // Voeg aanvullende tests van de ontbrekende CRUD-operaties in.
    }

    private static void testAdresDAO(AdresDAO adao) throws SQLException {
        System.out.println("\n---------- Test AdresDAO -------------");

        // Haal alle reizigers op uit de database
        List<Adres> adressen = adao.findAll();
        System.out.println("[Test] AdresDAO.findAll() geeft de volgende adressen:");
        for (Adres a : adressen) {
            System.out.println(a);
        }
        System.out.println();

        // Maak een nieuwe adressen aan en persisteer deze in de database
        Adres a1 = new Adres(1, "3812RK", "13", "heidelberglaan", "Utrecht", 1);
        adao.save(a1);
        adao.delete(a1);

        Adres a2 = new Adres(1, "3832EK", "3", "laan", "Utrecht", 3);
        adao.save(a2);
        adao.delete(a2);

        System.out.print("[Test] Eerst " + adressen.size() + " adressen, na AdresDAO.save() ");
        System.out.println("\n----------------------------");

        // Haal alle reizigers op uit de database
        List<Adres> nAdressen = adao.findAll();
        System.out.println("[Test] AdresDAO.findAll() geeft de volgende adressen:");
        for (Adres a : nAdressen) {
            System.out.println(a);
        }
        System.out.println();

        adressen = adao.findAll();
        System.out.println(adressen.size() + " reizigers\n");

        // Delete de net aangemaakt adres
        adao.delete(a2);
        adressen = adao.findAll();
        System.out.println(adressen.size() + " adressen na het verwijderen van de net aangemaakt adres\n");
        System.out.println("\n----------------------------");
    }
}
