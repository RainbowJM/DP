package ovchip.p2_p3_p4_p5.dao;

import ovchip.p2_p3_p4_p5.domain.Adres;
import ovchip.p2_p3_p4_p5.domain.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOPsql implements AdresDAO {
    private  Connection conn;
    private ReizigerDAO rdao;

    public AdresDAOPsql(Connection conn){
        this.conn = conn;
    }

    public void setRdao(ReizigerDAO rdao) {
        this.rdao = rdao;
    }

    @Override
    public boolean save(Adres adres) {
        try {
            String query = "INSERT INTO adres (adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id)" +
                    "VALUES (?,?,?,?,?,?);";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, adres.getId());
            pst.setString(2, adres.getPostcode());
            pst.setString(3,adres.getHuisnummer());
            pst.setString(4,adres.getStraat());
            pst.setString(5, adres.getWoonplaats());
            pst.setInt(6, adres.getReiziger_id());

            pst.executeUpdate();
            pst.close();
            return true;
        }catch (SQLException e){
            System.err.println("[SQLException] " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Adres adres) {
        try{
            String query = "UPDATE adres SET  adres_id = ?, postcode = ?, huisnummer = ?, straat = ?, woonplaats = ?, reiziger_id = ?";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, adres.getId());
            pst.setString(2, adres.getPostcode());
            pst.setString(3, adres.getHuisnummer());
            pst.setString(4, adres.getStraat());
            pst.setString(5, adres.getWoonplaats());
            pst.setInt(6, adres.getReiziger_id());

            pst.executeUpdate();
            pst.close();
            return true;
        }catch (SQLException e){
            System.err.println("[SQLException] " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Adres adres) {
        try{
            String query = "DELETE FROM adres WHERE adres_id = ?;";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, adres.getId());

            pst.executeUpdate();
            pst.close();
            return true;
        }catch (SQLException e){
            System.err.println("[SQLException] " + e.getMessage());
            return false;
        }
    }

    @Override
    public Adres findById(int id) {
        Adres adres = null;
        try{
            String query = "SELECT *FROM adres WHERE adres_id =?;";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()){
                int idA = rs.getInt("adres_id");
                String pc = rs.getString("postcode");
                String hn = rs.getString("huisnummer");
                String st = rs.getString("straat");
                String wp = rs.getString("woonplaats");
                int idR = rs.getInt("reiziger_id");

                adres = new Adres(idA,pc,hn,st,wp,idR);
            }

            pst.close();
            rs.close();
        }catch (SQLException e){
            System.err.println("[SQLException] " + e.getMessage());
        }
        return adres;
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        Adres adres = null;
        try{
            String query = "SELECT * FROM adres WHERE reiziger_id = ?;";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, reiziger.getReiziger_id());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int idA = rs.getInt("adres_id");
                String pc = rs.getString("postcode");
                String hn = rs.getString("huisnummer");
                String s = rs.getString("straat");
                String wp = rs.getString("woonplaats");
                int idR = rs.getInt("reiziger_id");

                adres = new Adres(idA,pc,hn,s,wp,idR);
            }
            pst.close();
            rs.close();
        }catch(SQLException e){
            System.err.println("[SQLException] " + e.getMessage());
        }
        return adres;
    }


    @Override
    public List<Adres> findAll() {
        List<Adres> adressen = new ArrayList<>();
        try {
            String query = "SELECT * FROM adres ORDER BY adres_id;";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            Adres adres;

            while (rs.next()){
                int idA = rs.getInt("adres_id");
                String ps = rs.getString("postcode");
                String hn = rs.getString("huisnummer");
                String s = rs.getString("straat");
                String wp = rs.getString("woonplaats");
                int idR = rs.getInt("reiziger_id");

                adres = new Adres(idA, ps, hn, s, wp, idR);
                adressen.add(adres);
            }

            st.close();
            rs.close();
            return adressen;
        }catch (SQLException e){
            System.err.println("[SQLException " + e.getMessage());
            return null;
        }
    }
}
