package ovchip.p2_p3_p4_p5.dao;

import ovchip.p2_p3_p4_p5.domain.Adres;
import ovchip.p2_p3_p4_p5.domain.Reiziger;

import java.util.List;

public interface AdresDAO {

    public boolean save(Adres adres);

    public boolean update(Adres adres);

    public boolean delete(Adres adres);

    public Adres findById(int id);

    public Adres findByReiziger(Reiziger reiziger);

    public List<Adres> findAll();
}
