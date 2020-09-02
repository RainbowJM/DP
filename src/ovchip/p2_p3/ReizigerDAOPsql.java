package ovchip.p2_p3;


import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO {
    private final Connection conn;
    private AdresDAO adao;

    public ReizigerDAOPsql(Connection conn){
        this.conn = conn;
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
            String query = "UPDATE reiziger SET voorletters = ?, tussenvoegsel = ?, achternaam = ?, geboortenaam = ? WHERE reiziger_id = ?;";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, reiziger.getVoorletters());
            pst.setString(2, reiziger.getTussenvoegsel());
            pst.setString(3, reiziger.getAchternaam());
            pst.setDate(4, reiziger.getGeboortedatum());
            pst.setInt(5, reiziger.getReiziger_id());


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

                reiziger = new Reiziger(idR, le, t,anaam,datum );
            }

            pst.close();
            rs.close();
        }catch (SQLException e){
            System.err.println("[SQLException] " + e.getMessage());
        }
        return reiziger;
    }


    @Override
    // hoe moet je een list maken
    public List<Reiziger> findByGbdatum(String datum) {
        // waarom werk alleen linkedlist en geen list
        List<Reiziger> reizigers = new LinkedList<>();
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
        List<Reiziger> reizigers = new LinkedList<>();
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

                reiziger = new Reiziger(idR,le, t, anaam,date);
                reizigers.add(reiziger);
            }

            st.close();
            rs.close();
            return reizigers;
        }catch (SQLException e){
            System.err.println("[SQLException " + e.getMessage());
            return null;
        }
    }
}
