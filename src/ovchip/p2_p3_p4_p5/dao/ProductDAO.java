package ovchip.p2_p3_p4_p5.dao;

import ovchip.p2_p3_p4_p5.domain.OvChipkaart;
import ovchip.p2_p3_p4_p5.domain.Product;

import java.util.List;

public interface ProductDAO {

    boolean save(Product product);

    boolean update(Product product);

    boolean delete(Product product);

    List<Product> findByOVChipkaart(OvChipkaart ovChipkaart);

    List<Product> findAll();
}
