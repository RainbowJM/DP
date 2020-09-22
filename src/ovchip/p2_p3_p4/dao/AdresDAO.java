package ovchip.p2_p3_p4.dao;

import ovchip.p2_p3_p4.domain.Adres;
import ovchip.p2_p3_p4.domain.Reiziger;

import java.util.List;

public interface AdresDAO {

    public boolean save(Adres adres);

    public boolean update(Adres adres);

    public boolean delete(Adres adres);

    public Adres findById(int id);

    public Adres findByReiziger(Reiziger reiziger);

    public List<Adres> findAll();
}