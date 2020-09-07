package ovchip.p2_p3_p4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AdresDAOPsql implements AdresDAO{
    Connection conn;
    ReizigerDAO rdao;

    public AdresDAOPsql(Connection conn){
        this.conn = conn;
    }

    @Override
    public boolean save(Adres adres) {
        try {
            String query = "";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, adres.getId());
            pst.setString(2, adres.getPostcode());
            pst.setString(3,adres.getHuisnummer());
            pst.setString(4,adres.getStraat());
            pst.setString(5, adres.getWoonplaats());
//            pst.set(6, adres.getReiziger());

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
        return false;
    }

    @Override
    public boolean delete(Adres adres) {
        return false;
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        return null;
    }

    @Override
    public List<Adres> findAll() {
        return null;
    }
}
