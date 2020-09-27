package ovchip.p2_p3_p4_p5.dao;

import ovchip.p2_p3_p4_p5.domain.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO {
    private Connection conn;
    private AdresDAO adao;
    private OvChipkaartDAO odao;

    public ReizigerDAOPsql(Connection conn){
        this.conn = conn;
    }

    public void setAdao(AdresDAO adao) {
        this.adao = adao;
    }

    public void setOdao(OvChipkaartDAO odao) {
        this.odao = odao;
    }

    @Override
    public boolean save(Reiziger reiziger) {
        try {
            String query = "INSERT INTO reiziger (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum)" +
                    "VALUES (?,?,?,?,?);";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, reiziger.getReiziger_id());
            pst.setString(2, reiziger.getVoorletters());
            pst.setString(3, reiziger.getTussenvoegsel());
            pst.setString(4, reiziger.getAchternaam());
            pst.setDate(5, reiziger.getGeboortedatum());

            if (reiziger.getAdres() != null){
                adao.save(reiziger.getAdres());
            }

            pst.executeUpdate();
            pst.close();
            return true;
        }catch (SQLException e){
            System.err.println("[SQLException] " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Reiziger reiziger) {
        try {
            String query = "UPDATE reiziger SET voorletters = ?, tussenvoegsel = ?, achternaam = ?, geboortedatum = ? WHERE reiziger_id = ?;";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, reiziger.getVoorletters());
            pst.setString(2, reiziger.getTussenvoegsel());
            pst.setString(3, reiziger.getAchternaam());
            pst.setDate(4, reiziger.getGeboortedatum());
            pst.setInt(5, reiziger.getReiziger_id());

            if (reiziger.getAdres() != null){
                adao.update(reiziger.getAdres());
            }


            pst.executeUpdate();
            pst.close();
            return true;
        }catch (SQLException e){
            System.err.println("[SQLException] " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        try {
            String query = "DELETE FROM reiziger WHERE reiziger_id = ?;";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, reiziger.getReiziger_id());

            if (reiziger.getAdres() != null){
                adao.delete(reiziger.getAdres());
            }

            pst.executeUpdate();
            pst.close();
            return true;
        }catch (SQLException e){
            System.err.println("[SQLException] " + e.getMessage());
            return false;
        }
    }

    @Override
    public Reiziger findById(int id) {
        Reiziger reiziger = null;
        try{
            String query = "SELECT * FROM reiziger WHERE reiziger_id = ?;";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int idR = rs.getInt("reiziger_id");
                String le = rs.getString("voorletters");
                String t = rs.getString("tussenvoegsel");
                String anaam = rs.getString("achternaam");
                Date datum = rs.getDate("geboortedatum");

                reiziger = new Reiziger(idR, le, t,anaam,datum);
                reiziger.setAdres(adao.findByReiziger(reiziger));
            }

            pst.close();
            rs.close();
        }catch (SQLException e){
            System.err.println("[SQLException] " + e.getMessage());
        }
        return reiziger;
    }


    @Override
    public List<Reiziger> findByGbdatum(String datum) {
        List<Reiziger> reizigers = new ArrayList<>();
        try{
            String query = "SELECT * FROM reiziger WHERE geboortedatum = ?;";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setDate(1, Date.valueOf(datum));

            ResultSet rs = pst.executeQuery();

            Reiziger reiziger;

            while (rs.next()){
                int idR = rs.getInt("reiziger_id");
                String le = rs.getString("voorletters");
                String t = rs.getString("tussenvoegsel");
                String anaam = rs.getString("achternaam");
                Date date = rs.getDate("geboortedatum");

                reiziger = new Reiziger(idR,le, t, anaam, date);
                reiziger.setAdres(adao.findByReiziger(reiziger));
                reizigers.add(reiziger);
            }

            pst.close();
            rs.close();
            return reizigers;
        }catch(SQLException e){
            System.err.println("[SQLException] " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Reiziger> findAll() {
        List<Reiziger> reizigers = new ArrayList<>();
        try {
            String query = "SELECT * FROM reiziger ORDER BY reiziger_id;";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            Reiziger reiziger;

            while (rs.next()){
                int idR = rs.getInt("reiziger_id");
                String le = rs.getString("voorletters");
                String t = rs.getString("tussenvoegsel");
                String anaam = rs.getString("achternaam");
                Date date = rs.getDate("geboortedatum");

                reiziger = new Reiziger(idR,le,t,anaam,date);
                reiziger.setAdres(adao.findByReiziger(reiziger));
                reiziger.setOvChipkaarten(odao.findByReiziger(reiziger));
                reizigers.add(reiziger);
            }

            st.close();
            rs.close();
            return reizigers;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
