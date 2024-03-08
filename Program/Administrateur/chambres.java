package Administrateur;

enum TypeChambre {
    SIMPLE, DOUBLE, SUITE
}

public class chambres {

    private int numero;
    private TypeChambre typeChambre;
    private boolean disponible;
    private int prix;

    public chambres(int numero, TypeChambre typeChambre, boolean disponible, int prix) {
        this.numero = numero;
        this.typeChambre = typeChambre;
        this.disponible = disponible;
        this.prix = prix;
    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public TypeChambre getTypeChambre() {
        return this.typeChambre;
    }

    public void setTypeChambre(TypeChambre typeChambre) {
        this.typeChambre = typeChambre;
    }

    public int getPrix() {
        return this.prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public boolean getDisponible() {
        return this.disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void afficherChambre() {
        System.out.println("Chambre numero: " + this.numero);
        System.out.println("Type de chambre: " + this.typeChambre);
        System.out.println("Prix: " + this.prix);
        System.out.println("Disponible: " + this.disponible);
    }

}


