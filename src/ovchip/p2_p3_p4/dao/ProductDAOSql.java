package ovchip.p2_p3_p4.dao;

import ovchip.p2_p3_p4.domain.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDAOSql implements ProductDAO{
    private Connection conn;

    public ProductDAOSql(Connection conn){
        this.conn = conn;
    }
    @Override
    public boolean save(Product product) {
        try{
            String query = "INSERT INTO product (product_nummer, naam, beschrijving, prijs)" +
                    "VALUES (?,?,?,?);";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, product.getProduct_nummer());
            pst.setString(2, product.getNaam());
            pst.setString(3, product.getBeschrijving());
            pst.setInt(4, product.getPrijs());

            pst.executeUpdate();
            pst.close();
            return true;
        }catch (SQLException e){
            System.err.println("[SQLException] " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Product product) {
        try{
            String query = "UPDATE product SET naam = ?, beschrijving = ?, prijs = ? WHERE product_nummer = ?;";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, product.getNaam());
            pst.setString(2, product.getBeschrijving());
            pst.setInt(3, product.getPrijs());
            pst.setInt(4, product.getProduct_nummer());

            pst.executeUpdate();
            pst.close();
            return true;
        }catch (SQLException e){
            System.err.println("[SQLException] " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Product product) {
        try {
            String query = "DELETE FROM product WHERE product_nummer = ?;";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, product.getProduct_nummer());

            pst.executeUpdate();
            pst.close();
            return true;
        }catch (SQLException e){
            System.err.println("[SQLException] " + e.getMessage());
            return false;
        }
    }
}
