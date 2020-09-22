package ovchip.p2_p3_p4.dao;

import ovchip.p2_p3_p4.domain.Reiziger;

import java.util.List;

public interface ReizigerDAO {

   boolean save(Reiziger reiziger);

   boolean update(Reiziger reiziger);

   boolean delete(Reiziger reiziger);

   Reiziger findById(int id);

   List<Reiziger> findByGbdatum(String datum);

   List<Reiziger> findAll();
}
