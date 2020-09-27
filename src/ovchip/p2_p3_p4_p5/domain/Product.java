package ovchip.p2_p3_p4_p5.domain;

public class Product {
    private int product_nummer;
    private String naam;
    private String beschrijving;
    private int prijs;
    private OvChipkaart ovChipkaart;

    public Product(int product_nummer, String naam, String beschrijving, int prijs) {
        this.product_nummer = product_nummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
    }

    //getters
    public int getProduct_nummer() {
        return product_nummer;
    }

    public String getNaam() {
        return naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public int getPrijs() {
        return prijs;
    }

    public OvChipkaart getOvChipkaart() {
        return ovChipkaart;
    }

    //setters
    public void setProduct_nummer(int product_nummer) {
        this.product_nummer = product_nummer;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public void setPrijs(int prijs) {
        this.prijs = prijs;
    }

    public void setOvChipkaart(OvChipkaart ovChipkaart) {
        this.ovChipkaart = ovChipkaart;
    }

    @Override
    public String toString() {
        return "Product: " +
                "product_nummer = " + product_nummer +
                ", naam = " + naam +
                ", beschrijving = " + beschrijving +
                ", prijs = " + prijs ;
    }
}
