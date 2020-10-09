package ovchip.p2_p3_p4_p5.dao;

import ovchip.p2_p3_p4_p5.domain.OvChipkaart;
import ovchip.p2_p3_p4_p5.domain.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOSql implements ProductDAO{
    private Connection conn;
    private OvChipkaartDAO odao;

    public ProductDAOSql(Connection conn){
        this.conn = conn;
    }

    public void setOdao(OvChipkaartDAO odao){
        this.odao = odao;
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

            for (OvChipkaart ov :  product.getOvChipkaarten()) {
                String queryOv = "INSERT INTO ov_chipkaart (kaart_nummer, geldig_tot, klasse, saldo, reiziger_id)" +
                        "VALUES (?,?,?,?,?);";

                PreparedStatement pstOv = conn.prepareStatement(queryOv);
                pstOv.setInt(1, ov.getKaart_nummer());
                pstOv.setDate(2, ov.getGeldig_tot());
                pstOv.setInt(3 ,ov.getKlasse());
                pstOv.setInt(4,ov.getSaldo());
                pstOv.setInt(5,ov.getReiziger_id());

                pstOv.executeUpdate();
                pstOv.close();
            }

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

            for (OvChipkaart ov :  product.getOvChipkaarten()) {
                String queryOv = "DELETE FROM ov_chipkaart WHERE kaart_nummer = ?;";

                PreparedStatement pstOv = conn.prepareStatement(queryOv);
                pstOv.setInt(1, ov.getKaart_nummer());

                pstOv.executeUpdate();
                pstOv.close();
            }

            for (OvChipkaart ov :  product.getOvChipkaarten()) {
                String queryOvUp = "INSERT INTO ov_chipkaart (kaart_nummer, geldig_tot, klasse, saldo, reiziger_id)" +
                        "VALUES (?,?,?,?,?);";

                PreparedStatement pstOvIns = conn.prepareStatement(queryOvUp);

                pstOvIns.setInt(1, ov.getKaart_nummer());
                pstOvIns.setDate(2, ov.getGeldig_tot());
                pstOvIns.setInt(3 ,ov.getKlasse());
                pstOvIns.setInt(4,ov.getSaldo());
                pstOvIns.setInt(5,ov.getReiziger_id());

                pstOvIns.executeUpdate();
                pstOvIns.close();
            }


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

            for (OvChipkaart ov :  product.getOvChipkaarten()) {
                String queryOv = "DELETE FROM ov_chipkaart WHERE kaart_nummer = ?;";

                PreparedStatement pstOv = conn.prepareStatement(queryOv);

                pstOv.setInt(1, ov.getKaart_nummer());

                pstOv.executeUpdate();
                pstOv.close();
            }

            pst.executeUpdate();
            pst.close();
            return true;
        }catch (SQLException e){
            System.err.println("[SQLException] " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Product> findByOVChipkaart(OvChipkaart ovChipkaart) {
        List<Product> products = new ArrayList<>();
        try {
            String query =
                    "SELECT * FROM product LEFT JOIN ov_chipkaart_product ocp on pro.product_nummer = ocp.product_nummer where kaart_nummer = ?";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, ovChipkaart.getKaart_nummer());

            ResultSet rs = pst.executeQuery();

            OvChipkaart ov;
            Product product;

            if (rs.next()){
                int knr = rs.getInt("kaart_nummer");
                Date datum = rs.getDate("geldig_tot");
                int k = rs.getInt("klasse");
                int s = rs.getInt("saldo");
                int idR = rs.getInt("reiziger_id");

                int pnr = rs.getInt("product_nummer");
                String n = rs.getString("naam");
                String b = rs.getString("beschrijving");
                int p = rs.getInt("prijs");

                ov = new OvChipkaart(knr,datum,k,s,idR);
                product = new Product(pnr, n,b,p);
                product.addOvChipkaart(ov);

                products.add(product);
            }
            pst.close();
            rs.close();
            return products;
        }catch (SQLException e){
            System.err.println("[SQLException] " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try {
            String query =
                    "SELECT * FROM product LEFT JOIN ov_chipkaart_product ocp on pro.product_nummer = ocp.product_nummer where kaart_nummer = ?";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            Product product;

            while (rs.next()){
                int pnr = rs.getInt("product_nummer");
                String n = rs.getString("naam");
                String b = rs.getString("beschrijving");
                int p = rs.getInt("prijs");

                int knr = rs.getInt("kaart_nummer");

                product = new Product(pnr,n,b,p);
                product.addOvChipkaart(odao.findByNummer(knr));
                products.add(product);
            }
            st.close();
            rs.close();
            return products;
        }catch (SQLException e){
            System.err.println("[SQLException] " + e.getMessage());
            return null;
        }
    }
}
