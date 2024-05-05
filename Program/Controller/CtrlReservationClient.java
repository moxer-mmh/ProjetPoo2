package Controller;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Chambre;
import Model.Client;
import Model.EtatChambres;
import Model.EtatReservation;
import Model.Reservation;
import Model.TypeChambre;
import View.JMain;

public class CtrlReservationClient {

	public static int row;
	private static int selectedReservationId = -1;

	public static void action(JButton btnReservation, JPanel pReservation) {

		btnReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				pReservation.setVisible(true);
				Insets insets = pReservation.getInsets(); //
				pReservation.setBounds(300 + insets.left, 20 + insets.top, 800, 700); //

			}
		});

	}

	public static void actionRetour(JButton retour, JFrame frame) {

		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JMain f = new JMain();
				f.setVisible(true);
				frame.dispose();

			}
		});
	}

	public static void actionEnregistrer(JButton btnEnregistrer, String nom, String prenom) {

		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// addReservation(nom, prenom);

			}
		});

	}

	public static void actionReservClient(JButton reservClient, JPanel pMesReserv, JPanel pReservation,
			JPanel contentPane, JPanel panel_1, String nom,
			String prenom, DefaultTableModel model, JTable tableClient, int selectedReservationId,
			EtatReservation selectedReservationEtat) {

		reservClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				pMesReserv.setBounds(300, 50, 564, 247);
				contentPane.add(pMesReserv);

				pMesReserv.setVisible(true);
				panel_1.setVisible(true);
				remplirTableReservations(Reservation.reservations, nom, prenom, model, tableClient,
						selectedReservationId,
						selectedReservationEtat);
				pReservation.setVisible(false);

			}
		});

	}

	public static void actionAnnulResrv(JButton btnAnnuler, String nom, String prenom,
			DefaultTableModel model, JTable tableResrvClient, EtatReservation selectedReservationEtat) {

		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for (Map.Entry<Integer, Reservation> entry : Reservation.getReservations().entrySet()) {

					Client client = entry.getValue().getClient();
					selectedReservationId = (int) tableResrvClient.getValueAt(tableResrvClient.getSelectedRow(), 0);

					// Vérifier si la réservation appartient au client et correspond à l'ID
					// sélectionné
					if (client.getNom().equals(nom) && client.getPrenom().equals(prenom))
					// && entry.getValue().getClient().getId() == selectedReservationId)
					{

						Reservation reservation = Reservation.reservations.get(selectedReservationId);
						reservation.setEtat(EtatReservation.ANNULEE);
						entry.getValue().getChambre().setEtatChambre(EtatChambres.LIBRE);
						System.out.println("Réservation " + selectedReservationId + " annulée avec succès.");

						// Mettre à jour la ligne correspondante dans la JTable
						int row = getRowFromReservationId(selectedReservationId, nom, prenom, model);
						if (row != -1) {
							model.setValueAt(EtatReservation.ANNULEE, row, 5);
						}
						break;
					}
				}
			}
		});

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

	public static void remplirTableReservations(Map<Integer, Reservation> reservations, String nom, String prenom,
			DefaultTableModel model, JTable tableClient, int selectedReservationId,
			EtatReservation selectedReservationEtat) {

		model.setRowCount(0);

		for (Map.Entry<Integer, Reservation> entry : reservations.entrySet()) {

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

	public static void makeReservation(JButton btnEnregistrer, JComboBox<String> startdayComboBox, JComboBox<String> startmonthComboBox, JComboBox<String> startyearComboBox, JComboBox<String> enddayComboBox, JComboBox<String> endmonthComboBox, JComboBox<String> endyearComboBox, JComboBox<TypeChambre> etatChambreC, String nom, String prenom) {

		btnEnregistrer.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent e) {
				Client client = new Client(nom, prenom, row);
				if (Chambre.chambres == null || Chambre.chambres.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Aucune chambre n'est disponible.", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				} else {
	
					for (Chambre rowList : Chambre.chambres.values()) {
						rowList.getNumero();
						rowList.getType();
						rowList.getEtatChambre();
						++row;
					}
	
					boolean roomFound = false;
					for (Map.Entry<Integer, Chambre> entry : Chambre.chambres.entrySet()) {
						Chambre chambre = entry.getValue();
						if (chambre.getType() == etatChambreC.getSelectedItem()
								&& chambre.getEtatChambre() == EtatChambres.LIBRE) {
							roomFound = true;
	
							String startDate = (String) startdayComboBox.getSelectedItem() + "/" + (String) startmonthComboBox.getSelectedItem() + "/" + (String) startyearComboBox.getSelectedItem();
							String endDate = (String) enddayComboBox.getSelectedItem() + "/" + (String) endmonthComboBox.getSelectedItem() + "/" + (String) endyearComboBox.getSelectedItem();
	
							try {
								validateDates(startDate, endDate);
							} catch (InvalidDateException e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
								return;
							}
	
							Reservation r = new Reservation(row, chambre, startDate, endDate, client);
							Reservation.reservations.put(row, r);
							Reservation.setReservations(Reservation.reservations);
	
							chambre.setEtatChambre(EtatChambres.EN_ATTENTE);
							System.out.println(row + "Réservation effectuée avec succès !");
							++row;
							break;
						}
					}
					if (!roomFound) {
						JOptionPane.showMessageDialog(null, "Aucune chambre de ce type n'est disponible.", "Erreur",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
	
		});
	
	}

	public static void actionNouvelleReserv(JButton btnReservation, JPanel pReservation, JPanel panel_1,JPanel pMesReserv) {

		btnReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pReservation.setVisible(true);
				Insets insets = pReservation.getInsets(); // Get insets for border padding
				pReservation.setBounds(300 + insets.left, 20 + insets.top, 800, 700); //
				panel_1.setVisible(false);
				pMesReserv.setVisible(false);
			}
		});

	}

	public static void validateDates(String startDate, String endDate) throws InvalidDateException{
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
	
		int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		if (startDay < 1 || startDay > daysInMonth[startMonth - 1] + ((startMonth == 2 && isLeapYear(startYear)) ? 1 : 0)) {
			throw new InvalidDateException("Les jours doivent être compris entre 1 et le nombre de jours dans le mois.");
		}
		if (endDay < 1 || endDay > daysInMonth[endMonth - 1] + ((endMonth == 2 && isLeapYear(endYear)) ? 1 : 0)) {
			throw new InvalidDateException("Les jours doivent être compris entre 1 et le nombre de jours dans le mois.");
		}

		// Check if the start date is before the end date
		if (startYear > endYear || (startYear == endYear && (startMonth > endMonth || ((startMonth == endMonth) && (startDay > endDay))))) {
			throw new InvalidDateException("La date de début doit être antérieure à la date de fin.");
		}
	}

	private static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
	}
}


