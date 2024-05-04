package Model;

public class Client {

	private String nom;
	private String prenom;
	private int id;

	public Client(String nom, String prenom, int id) {
		this.nom = nom;
		this.prenom = prenom;
		this.id = id;
	}

	/// Getters & Setters

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/*
	 * public static void makeReservation(String startDate, String
	 * endDate,JComboBox<TypeChambre> etatChambreC,String nom,String prenom,int row)
	 * {
	 * 
	 * 
	 * Client0 client = new Client0(nom, prenom, row);
	 * 
	 * 
	 * 
	 * if (Administrateur.chambres == null ||Administrateur.chambres.isEmpty()) {
	 * System.out.println("Aucune chambre n'est disponible."); } else {
	 * 
	 * for (Chambres rowList : Administrateur.chambres.values()) {
	 * 
	 * rowList.getNumero(); rowList.getType(); rowList.getEtatChambre();
	 * 
	 * row++; }
	 * 
	 * 
	 * boolean roomFound = false; for (Map.Entry<Integer, Chambres> entry :
	 * Administrateur.chambres.entrySet()) { Chambres chambre = entry.getValue(); if
	 * (chambre.getType() == etatChambreC.getSelectedItem() &&
	 * chambre.getEtatChambre() == EtatChambres.LIBRE) { roomFound = true;
	 * Reservations reservation = new Reservations(row, chambre, startDate, endDate,
	 * client); Administrateur.reservations.put(row, reservation);
	 * chambre.setEtatChambre(EtatChambres.EN_ATTENTE);
	 * System.out.println("Réservation effectuée avec succès !"); ++row; break; } }
	 * if (!roomFound) {
	 * System.out.println("Aucune chambre de ce type n'est disponible.");
	 * 
	 * } } }
	 * 
	 */

}
