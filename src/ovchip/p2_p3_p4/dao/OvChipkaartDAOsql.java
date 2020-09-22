package ovchip.p2_p3_p4.dao;

import ovchip.p2_p3_p4.domain.OvChipkaart;
import ovchip.p2_p3_p4.domain.Reiziger;

import java.sql.Connection;
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
        return false;
    }

    @Override
    public boolean update(OvChipkaart ovChipkaart) {
        return false;
    }

    @Override
    public boolean delete(OvChipkaart ovChipkaart) {
        return false;
    }

    @Override
    public OvChipkaart findById(int id) {
        return null;
    }

    @Override
    public List<OvChipkaart> findByReiziger(Reiziger reiziger) {
        return null;
    }

    @Override
    public List<OvChipkaart> findall() {
        return null;
    }
}
