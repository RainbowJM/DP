package ovchip.p2_p3;


import java.sql.Date;

public class Reiziger {
    private int reiziger_id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;

    public Reiziger(int id, String fletters, String midden, String lletters, Date birthdate){
        reiziger_id = id;
        voorletters = fletters;
        tussenvoegsel = midden;
        achternaam = lletters;
        geboortedatum = birthdate;
    }

    public int getReiziger_id() {
        return reiziger_id;
    }

    //getters
    public String getVoorletters() {
        return voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    //setters

    public void setReiziger_id(int reiziger_id) {
        this.reiziger_id = reiziger_id;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public String toString() {
        return "Reiziger: " +
                "reiziger_id= " + reiziger_id +
                ", voorletters= " + voorletters +
                ", tussenvoegsel= " + tussenvoegsel +
                ", achternaam= " + achternaam +
                ", geboortedatum= " + geboortedatum;
    }
}
