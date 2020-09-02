package ovchip.p2_p3;

import java.sql.Connection;
import java.util.List;

public class AdresDAOPsql implements AdresDAO{
    Connection conn;
    ReizigerDAO rdao;

    public AdresDAOPsql(Connection conn){
        this.conn = conn;
    }

    @Override
    public boolean save(Adres adres) {
        return false;
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
