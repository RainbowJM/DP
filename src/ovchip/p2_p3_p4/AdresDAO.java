package ovchip.p2_p3_p4;

import java.util.List;

public interface AdresDAO {

    public boolean save(Adres adres);

    public boolean update(Adres adres);

    public boolean delete(Adres adres);

    public Adres findById(int id);

    public List<Adres> findByReiziger(Adres reiziger_id);

    public List<Adres> findAll();
}
