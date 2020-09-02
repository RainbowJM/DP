package ovchip.p2_p3;

import ovchip.p2_p3.Reiziger;
import ovchip.p2_p3.ReizigerDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO {
    private Connection conn;


    public ReizigerDAOPsql(Connection conn){
        String url = "jdbc:postgresql://localhost:5433/ovchip";
         try {
             conn = DriverManager.getConnection(url, "postgres", "1234qwer");
         } catch (SQLException sql) {
            System.err.println("[SQLException] " + sql.getMessage());
         }
    }

    @Override
    public boolean save(Reiziger reiziger) {

        return false;
    }

    @Override
    public boolean update(Reiziger reiziger) {
        return false;
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        return false;
    }

    @Override
    public Reiziger findById(int id) {
        return null;
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) {
        return null;
    }

    @Override
    public List<Reiziger> findAll() {
        return null;
    }
}
