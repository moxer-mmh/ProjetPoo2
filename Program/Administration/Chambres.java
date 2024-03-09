package Administration;

public class Chambres {

    private int numero;
    private String type;
    private boolean estReservee;


    public Chambres(int numero, String type) {
        this.numero = numero;
        this.type = type;
        this.estReservee = false;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean estReservee() {
        return estReservee;
    }

    public void setEstReservee(boolean estReservee) {
        this.estReservee = estReservee;
    }

}


