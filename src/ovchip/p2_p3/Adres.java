package ovchip.p2_p3;

public class Adres {
    private int id;
    private String postcode;

    public Adres(){
    }

    //getters
    public int getId() {
        return id;
    }

    public String getPostcode() {
        return postcode;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return "Adres: " +
                "id= " + id + ", postcode= " + postcode;
    }
}
