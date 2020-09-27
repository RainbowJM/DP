package ovchip.p2_p3_p4.dao;

import ovchip.p2_p3_p4.domain.Product;

public interface ProductDAO {

    boolean save(Product product);

    boolean update(Product product);

    boolean delete(Product product);

}
