package com.moxer_mmh.hotelmanagement.Model;

import java.util.Map;
import java.util.TreeMap;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.moxer_mmh.hotelmanagement.Controller.*;

public class Client {

	private String nom;
	private String prenom;
	private int id;

	public static Map<Integer, Client> clients = new TreeMap<>();

	public Client(String nom, String prenom, int id) {
		this.nom = nom;
		this.prenom = prenom;
		this.id = id;
	}

	public static int rowRes = 0;

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

	public static void makeReservation(String startday, String startmonth, String startyear, String endday,
			String endmonth, String endyear, String etatChambreC, String nom, String prenom) {

		Client client = new Client(nom,
				prenom, 0);
		if (Chambre.chambres == null || Chambre.chambres.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Aucune chambre n'est disponible.",
					"Erreur", JOptionPane.ERROR_MESSAGE);
		} else {

			for (Chambre rowList : Chambre.chambres.values()) {
				rowList.getNumero();
				rowList.getType();
				rowList.getEtatChambre();
			}

			boolean roomFound = false;
			for (Map.Entry<Integer, Chambre> entry : Chambre.chambres.entrySet()) {
				Chambre chambre = entry.getValue();
				if (chambre.getType().toString() == etatChambreC && chambre.getEtatChambre() == EtatChambres.LIBRE) {
					roomFound = true;

					String startDate = startday + "/" + startmonth + "/" + startyear;
					String endDate = endday + "/" + endmonth + "/" + endyear;

					try {
						validateDates(startDate, endDate);
					} catch (InvalidDateException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					Reservation r = new Reservation(++rowRes, chambre, startDate, endDate, client);

					Reservation.reservations.put(rowRes, r);
					Reservation.setReservations(Reservation.reservations);

					chambre.setEtatChambre(EtatChambres.EN_ATTENTE);
					System.out.println(rowRes +
							"Réservation effectuée avec succès !");
					break;
				}
			}
			if (!roomFound) {
				JOptionPane.showMessageDialog(null,
						"Aucune chambre de ce type n'est disponible.", "Erreur",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public static void mesReservations(String nom, String prenom, DefaultTableModel model) {

		model.setRowCount(0);

		for (Map.Entry<Integer, Reservation> entry : Reservation.reservations.entrySet()) {

			Reservation reservation = entry.getValue();
			Client client = reservation.getClient();

			if (client.getNom().equals(nom) && client.getPrenom().equals(prenom)) {

				Object[] rowData = {
						reservation.getId(),
						reservation.getChambre().getNumero(),
						reservation.getChambre().getType(),
						reservation.getDateDebut(),
						reservation.getDateFin(),
						reservation.getEtat()
				};
				model.addRow(rowData);
			}
		}

	}

	public static void annulReservationOld(String nom, String prenom,
			DefaultTableModel model, int selectedReservationId, String[] Datedebut,
			EtatReservation selectedReservationEtat) {

		for (Map.Entry<Integer, Reservation> entry : Reservation.getReservations().entrySet()) {

			Client client = entry.getValue().getClient();

			// Vérifier si la réservation appartient au client et correspond à l'ID
			// sélectionné
			if (client.getNom().equals(nom) && client.getPrenom().equals(prenom)) {

				Reservation reservation = Reservation.reservations.get(selectedReservationId);

				// Vérifier si la date de début de la réservation est dans le futur
				if (reservation.getEtat() == EtatReservation.EN_ATTENTE) {
					// && (Integer.parseInt(Datedebut[2]) > Integer.parseInt(Date.getTodayyear()) ||
					// (Integer.parseInt(Datedebut[2]) == (Integer.parseInt(Date.getTodayyear()) &&
					// (Integer.parseInt(Datedebut[1]) > (Integer.parseInt(Date.getTodaymonth() )||
					// ((Integer.parseInt(Datedebut[1]) == (Integer.parseInt(Date.getTodaymonth())
					// && (Integer.parseInt(Datedebut[0]) > (Integer.parseInt( Date.getTodayday())){
					reservation.setEtat(EtatReservation.ANNULEE);
					entry.getValue().getChambre().setEtatChambre(EtatChambres.LIBRE);
					System.out.println("Réservation " + selectedReservationId + " annulée avec succès.");

					// Mettre à jour la ligne correspondante dans la JTable
					int row = getRowFromReservationId(selectedReservationId, nom, prenom, model);
					if (row != -1) {
						model.setValueAt(EtatReservation.ANNULEE, row, 5);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Vous ne pouvez pas annuler une réservation en cours.",
							"Erreur", JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
		}

	}

	public static void modifyReservation(String nom, String prenom,
			DefaultTableModel model, int selectedReservationId, String[] Datedebut,
			EtatReservation selectedReservationEtat) {

		for (Map.Entry<Integer, Reservation> entry : Reservation.getReservations().entrySet()) {

			Client client = entry.getValue().getClient();

			// Vérifier si la réservation appartient au client et correspond à l'ID
			// sélectionné
			if (client.getNom().equals(nom) && client.getPrenom().equals(prenom)) {

				Reservation reservation = Reservation.reservations.get(selectedReservationId);

				// Vérifier si la date de début de la réservation est dans le futur
				if (reservation.getEtat() == EtatReservation.EN_ATTENTE) {
					// && (Integer.parseInt(Datedebut[2]) > Integer.parseInt(Date.getTodayyear()) ||
					// (Integer.parseInt(Datedebut[2]) == (Integer.parseInt(Date.getTodayyear()) &&
					// (Integer.parseInt(Datedebut[1]) > (Integer.parseInt(Date.getTodaymonth() )||
					// ((Integer.parseInt(Datedebut[1]) == (Integer.parseInt(Date.getTodaymonth())
					// && (Integer.parseInt(Datedebut[0]) > (Integer.parseInt( Date.getTodayday())){

					reservation.setEtat(EtatReservation.ANNULEE);
					entry.getValue().getChambre().setEtatChambre(EtatChambres.LIBRE);
					System.out.println("Réservation " + selectedReservationId + " annulée avec succès.");

					// Mettre à jour la ligne correspondante dans la JTable
					int row = getRowFromReservationId(selectedReservationId, nom, prenom, model);
					if (row != -1) {
						model.setValueAt(EtatReservation.ANNULEE, row, 5);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Vous ne pouvez pas annuler une réservation en cours.",
							"Erreur", JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
		}

	}

	private static int getRowFromReservationId(int reservationId, String nomClient, String prenomClient,
			DefaultTableModel model) {

		for (int row = 0; row < model.getRowCount(); row++) {
			int id = (int) model.getValueAt(row, 0);
			if (id == reservationId) {
				return row;
			}
		}
		return -1; // Aucune ligne trouvée pour cet ID de réservation
	}

	public static void validateDates(String startDate, String endDate) throws InvalidDateException {
		// Split the dates into components
		String[] startComponents = startDate.split("/");
		String[] endComponents = endDate.split("/");

		// Check if the components are valid
		if (startComponents.length != 3 || endComponents.length != 3) {
			throw new InvalidDateException("Les dates doivent être au format JJ/MM/AAAA.");
		}

		// Parse the components into integers
		int startDay, startMonth, startYear, endDay, endMonth, endYear;
		try {
			startDay = (int) Integer.parseInt(startComponents[0]);
			startMonth = (int) Integer.parseInt(startComponents[1]);
			startYear = (int) Integer.parseInt(startComponents[2]);
			endDay = (int) Integer.parseInt(endComponents[0]);
			endMonth = (int) Integer.parseInt(endComponents[1]);
			endYear = (int) Integer.parseInt(endComponents[2]);
		} catch (NumberFormatException e) {
			throw new InvalidDateException("Les dates doivent être des nombres entiers.");
		}

		// Check if the month and day values are valid
		if (startMonth < 1 || startMonth > 12 || endMonth < 1 || endMonth > 12) {
			throw new InvalidDateException("Les mois doivent être compris entre 1 et 12.");
		}

		int[] daysInMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (startDay < 1
				|| startDay > daysInMonth[startMonth - 1] + ((startMonth == 2 && isLeapYear(startYear)) ? 1 : 0)) {
			throw new InvalidDateException(
					"Les jours doivent être compris entre 1 et le nombre de jours dans le mois.");
		}
		if (endDay < 1 || endDay > daysInMonth[endMonth - 1] + ((endMonth == 2 && isLeapYear(endYear)) ? 1 : 0)) {
			throw new InvalidDateException(
					"Les jours doivent être compris entre 1 et le nombre de jours dans le mois.");
		}

		// Check if the start date is before the end date
		if (startYear > endYear || (startYear == endYear
				&& (startMonth > endMonth || ((startMonth == endMonth) && (startDay > endDay))))) {
			throw new InvalidDateException("La date de début doit être antérieure à la date de fin.");
		}

		// Check if the start date is before the Today date
		if (startYear < 2024 || (startYear == 2024 && (startMonth < 5 || ((startMonth == 5) && (startDay < 5))))) {
			throw new InvalidDateException("La date de début doit être postérieure à la date 05/05/2024.");
		}
	}

	private static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
	}

	public static void annulReservation(String nom, String prenom, int selectedReservationId, int selectedRow) {

		for (Map.Entry<Integer, Reservation> entry : Reservation.getReservations().entrySet()) {

			Client client = entry.getValue().getClient();

			// Vérifier si la réservation appartient au client et correspond à l'ID
			// sélectionné
			if (client.getNom().equals(nom) && client.getPrenom().equals(prenom)) {

				Reservation reservation = Reservation.reservations.get(selectedReservationId);

				if (selectedRow != -1) {

					Reservation.reservations.remove(selectedReservationId);
					reservation.getChambre().setEtatChambre(EtatChambres.LIBRE);
				}

			}
			break;
		}
	}

	public static void modifReservation(String nom, String prenom, int selectedReservationId, int selectedRow) {

		for (Map.Entry<Integer, Reservation> entry : Reservation.getReservations().entrySet()) {

			Client client = entry.getValue().getClient();

			// Vérifier si la réservation appartient au client et correspond à l'ID
			// sélectionné
			if (client.getNom().equals(nom) && client.getPrenom().equals(prenom)) {

				Reservation reservation = Reservation.reservations.get(selectedReservationId);

				if (selectedRow != -1) {

					reservation.getDateDebut();
				}

			}
			break;
		}
	}

}
