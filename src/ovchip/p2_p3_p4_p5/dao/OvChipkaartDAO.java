package ovchip.p2_p3_p4_p5.dao;

import ovchip.p2_p3_p4_p5.domain.OvChipkaart;
import ovchip.p2_p3_p4_p5.domain.Reiziger;

import java.util.List;

public interface OvChipkaartDAO {

    boolean save(OvChipkaart ovChipkaart);

    boolean update(OvChipkaart ovChipkaart);

    boolean delete(OvChipkaart ovChipkaart);

    OvChipkaart findByNummer(int kaart_nummer);

    List<OvChipkaart> findByReiziger(Reiziger reiziger);

    List<OvChipkaart> findAll();
}
