package ovchip.p2_p3_p4.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OvChipkaart {
    private int kaart_nummer;
    private Date geldig_tot;
    private int klasse;
    private int saldo;
    private int reiziger_id;
    private Reiziger reiziger;
    private List<Product> producten = new ArrayList<>();

    public OvChipkaart(int kaart_nummer, Date geldig_tot, int klasse, int saldo, int id) {
        this.kaart_nummer = kaart_nummer;
        this.geldig_tot = geldig_tot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger_id = id;
    }

    // getters
    public int getKaart_nummer() {
        return kaart_nummer;
    }

    public Date getGeldig_tot() {
        return geldig_tot;
    }

    public int getKlasse() {
        return klasse;
    }

    public int getSaldo() {
        return saldo;
    }

    public int getReiziger_id() {
        return reiziger_id;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public List<Product> getProducten() {
        return producten;
    }

    //setters

    public void setKaart_nummer(int kaart_nummer) {
        this.kaart_nummer = kaart_nummer;
    }

    public void setGeldig_tot(Date geldig_tot) {
        this.geldig_tot = geldig_tot;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public void setReiziger_id(int reiziger_id) {
        this.reiziger_id = reiziger_id;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public void setProducten(List<Product> producten) {
        this.producten = producten;
    }

    @Override
    public String toString() {
        return "Ov_chipkaart: " +
                "kaart_nummer = " + kaart_nummer +
                ", geldig_tot = " + geldig_tot +
                ", klasse = " + klasse +
                ", saldo = " + saldo +
                ", reiziger_id = " + reiziger_id+
                ", " + (reiziger == null ? "Reiziger: null" : reiziger.toString());
    }
}
