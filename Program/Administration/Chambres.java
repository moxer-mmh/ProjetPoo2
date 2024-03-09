package Administration;

public class Chambres {

    private int numero;
    private TypeChambre type;
    private boolean estReservee;


    public Chambres(int numero, TypeChambre type) {
        this.numero = numero;
        this.type = type;
        this.estReservee = false;
    }


    /// Getters & Setters

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public TypeChambre getType() {
        return type;
    }

    public void setType(TypeChambre type) {
        this.type = type;
    }

    public boolean estReservee() {
        return estReservee;
    }

    public void setEstReservee(boolean estReservee) {
        this.estReservee = estReservee;
    }

}


