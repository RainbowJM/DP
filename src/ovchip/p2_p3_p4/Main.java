package ovchip.p2_p3_p4;

import ovchip.p2_p3_p4.dao.*;
import ovchip.p2_p3_p4.domain.Adres;
import ovchip.p2_p3_p4.domain.Reiziger;

import java.sql.*;
import java.util.List;

public class Main {
    private static Connection connection;

    public static void main(String[] args) {
        try{
            ReizigerDAOPsql rdao = new ReizigerDAOPsql(getConnection());
            AdresDAOPsql adao = new AdresDAOPsql(getConnection());
            OvChipkaartDAOsql odao = new OvChipkaartDAOsql(getConnection());

            //Connecting DAO's
            rdao.setAdao(adao);
            rdao.setOdao(odao);
            odao.setRdao(rdao);

            //testDAO
            testReizigerDAO(rdao);
            testAdresDAO(adao);

        }catch (Exception e){
            e.printStackTrace();
            closeConnection();
        }
    }

    private static Connection getConnection(){
        String url = "jdbc:postgresql://localhost:5433/ovchip";
        try {
            connection = DriverManager.getConnection(url, "postgres", "1234qwer");
        } catch (SQLException e){
            System.err.println("[SQLException] " + e.getMessage());
        }
        return connection;
    }

    private static void closeConnection() {
        try {
            connection.close();
        }catch (SQLException e){
            System.err.println("[SQLExcepton] " + e.getMessage());
        }
    }

    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println("------");

        // Maak een nieuwe reizigers aan en persisteer deze in de database
        String gbdatum1 = "1981-03-14";
        Reiziger sietske = new Reiziger(8, "S", "", "Boers", java.sql.Date.valueOf(gbdatum1));
        rdao.save(sietske);
//        rdao.delete(sietske);

        String gbdatum2 = "2000-07-22";
        Reiziger ellen = new Reiziger(9, "E", "L", "Lopez", java.sql.Date.valueOf(gbdatum2));
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
        Adres a1 = new Adres(1, "3812RK", "13", "heidelberglaan", "Utrecht", 8);
        adao.save(a1);
        adao.delete(a1);

        Adres a2 = new Adres(2, "3832EK", "3", "laan", "Utrecht", 9);
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

//    private static void testOvChipkaartDAO(OvChipkaartDAO odao) throws SQLException {
//        System.out.println("\n---------- Test OvChipkaartDAO -------------");
//
//        // Haal alle ovchipkaarten op uit de database
//        List<OvChipkaart> ovChipkaarten = odao.findAll();
//        System.out.println("[Test] OvChipkaartDAO.findAll() geeft de volgende ovchipkaarten:");
//        for (OvChipkaart o : ovChipkaarten) {
//            System.out.println(o);
//        }
//        System.out.println();
//
//        // Maak een nieuwe ovchipkaarten aan en persisteer deze in de database
//        String gdatum1 = "2030-03-14";
//        OvChipkaart o1 = new OvChipkaart(1, java.sql.Date.valueOf(gdatum1),1,15,1);
//        odao.save(o1);
//        //odao.delete(o1);
//
//        String gdatum2 = "2050-03-21";
//        OvChipkaart o2 = new OvChipkaart(2, java.sql.Date.valueOf(gdatum2),2,40,2);
//        odao.save(o2);
//        //odao.delete(o2);
//
//        System.out.print("[Test] Eerst " + ovChipkaarten.size() + " ovchipkaarten, na OvChipkaartDAO.save() ");
//        System.out.println("\n----------------------------");
//
//        // Haal alle reizigers op uit de database
//        List<OvChipkaart> nOvChipkaarten = odao.findAll();
//        System.out.println("[Test] OvChipkaartDAO.findAll() geeft de volgende ovchipkaarten:");
//        for (OvChipkaart o : nOvChipkaarten) {
//            System.out.println(o);
//        }
//        System.out.println();
//
//        ovChipkaarten = odao.findAll();
//        System.out.println(ovChipkaarten.size() + " ovchipkaarten\n");
//
//        // Delete de net aangemaakt adres
//        odao.delete(o2);
//        ovChipkaarten = odao.findAll();
//        System.out.println(ovChipkaarten.size() + " ovchipkaarten na het verwijderen van de net aangemaakt ovchipkaart\n");
//        System.out.println("\n----------------------------");
//    }
}
