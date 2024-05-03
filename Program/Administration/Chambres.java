package Administration;

public class Chambres {

    private int numero;
    private TypeChambre type;
    private EtatChambres estReservee;

    public Chambres(int numero, TypeChambre type) {
        this.numero = numero;
        this.type = type;
        this.estReservee = EtatChambres.LIBRE;
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

    public EtatChambres getEtatChambre() {
        return estReservee;
    }

    public void setEtatChambre(EtatChambres estReservee) {
        this.estReservee = estReservee;
    }

}
