package ovchip.p2_p3_p4.dao;

import ovchip.p2_p3_p4.domain.OvChipkaart;
import ovchip.p2_p3_p4.domain.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OvChipkaartDAOsql implements OvChipkaartDAO{
    private Connection conn;
    private ReizigerDAO rdao;

    public  OvChipkaartDAOsql(Connection conn){
        this.conn = conn;
    }
    public void setRdao(ReizigerDAO rdao) {
        this.rdao = rdao;
    }

    @Override
    public boolean save(OvChipkaart ovChipkaart) {
        try{
            String query = "INSERT INTO ov_chipkaart (kaart_nummer, geldig_tot, klasse, saldo, reiziger_id)" +
                    "VALUES (?,?,?,?,?);";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, ovChipkaart.getKaart_nummer());
            pst.setDate(2, ovChipkaart.getGeldig_tot());
            pst.setInt(3 ,ovChipkaart.getKlasse());
            pst.setInt(4,ovChipkaart.getSaldo());
            pst.setInt(5,ovChipkaart.getReiziger_id());

            if (ovChipkaart.getReiziger() != null){
                rdao.save(ovChipkaart.getReiziger());
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
    public boolean update(OvChipkaart ovChipkaart) {
        try {
            String query = "UPDATE ov_chipkaart SET kaart_nummer = ?, geldig_tot = ?, klasse = ?, saldo = ? WHERE reiziger_id = ?;";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, ovChipkaart.getKaart_nummer());
            pst.setDate(2, ovChipkaart.getGeldig_tot());
            pst.setInt(3, ovChipkaart.getKlasse());
            pst.setInt(4, ovChipkaart.getSaldo());
            pst.setInt(5, ovChipkaart.getReiziger_id());

            if (ovChipkaart.getReiziger() != null){
                rdao.update(ovChipkaart.getReiziger());
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
    public boolean delete(OvChipkaart ovChipkaart) {
        try{
            String query = "DELETE FROM ov_chipkaart WHERE kaart_nummer = ?;";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, ovChipkaart.getKaart_nummer());

            if (ovChipkaart.getReiziger() != null){
                rdao.delete(ovChipkaart.getReiziger());
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
    public OvChipkaart findByNummer(int kaart_nummer) {
        OvChipkaart ovChipkaart = null;
        try {
            String query = "SELECT * FROM ov_chipkaart WHERE kaart_nummer = ?;";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, kaart_nummer);

            ResultSet rs = pst.executeQuery();

            if (rs.next()){
                int knr = rs.getInt("kaart_nummer");
                Date datum = rs.getDate("geldig_tot");
                int k = rs.getInt("klasse");
                int s = rs.getInt("saldo");
                int idR = rs.getInt("reiziger_id");

                ovChipkaart = new OvChipkaart(knr, datum, k, s,idR);
                ovChipkaart.setReiziger(rdao.findById(idR));
            }
            pst.close();
            rs.close();
        }catch (SQLException e){
            System.err.println("[SQLException] " + e.getMessage());
        }
        return ovChipkaart;
    }

    @Override
    public List<OvChipkaart> findByReiziger(Reiziger reiziger) {
        List<OvChipkaart> ovChipkaarten = new ArrayList<>();
        try {
            String query = "SELECT * FROM ov_chipkaart WHERE reiziger_id = ?;";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, reiziger.getReiziger_id());

            ResultSet rs = pst.executeQuery();

            OvChipkaart ovChipkaart;

            if (rs.next()){
                int knr = rs.getInt("kaart_nummer");
                Date datum = rs.getDate("geldig_tot");
                int k = rs.getInt("klasse");
                int s = rs.getInt("saldo");
                int idR = rs.getInt("reiziger_id");

                ovChipkaart = new OvChipkaart(knr,datum,k,s,idR);
                ovChipkaart.setReiziger(ovChipkaart.getReiziger());
                ovChipkaarten.add(ovChipkaart);
            }
            pst.close();
            rs.close();
            return ovChipkaarten;
        }catch (SQLException e){
            System.err.println("[SQLException] " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<OvChipkaart> findAll() {
        List<OvChipkaart> ovChipkaarten = new ArrayList<>();
        try{
            String query = "SELECT * FROM ov_chipkaart ORDER BY kaart_nummer;";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            OvChipkaart ovChipkaart;

            while (rs.next()){
                int knr = rs.getInt("kaart_nummer");
                Date datum = rs.getDate("geldig_tot");
                int k = rs.getInt("klasse");
                int s = rs.getInt("saldo");
                int idR = rs.getInt("reiziger_id");

                ovChipkaart = new OvChipkaart(knr,datum,k,s,idR);
                ovChipkaart.setReiziger(ovChipkaart.getReiziger());
                ovChipkaarten.add(ovChipkaart);
            }
            st.close();
            rs.close();
            return ovChipkaarten;
        }catch(SQLException e){
            System.err.println("[SQLException] " + e.getMessage());
            return null;
        }
    }
}
