package Model;

import java.util.Map;
import java.util.TreeMap;

public class Chambre {

    private int numero;
    private TypeChambre type;
    private EtatChambres estReservee;
    public static Map<Integer, Chambre> chambres = new TreeMap<>();


    public Chambre(int numero, TypeChambre type) {
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
    
    
    public static Map<Integer, Chambre> getChambres() {
        return chambres;
    }

    public static void setChambres( Map<Integer, Chambre>  newValue) {
        // Add validation logic here if needed
    	chambres = newValue;
    }



}


