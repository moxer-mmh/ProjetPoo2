package Controller;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Client;
import Model.EtatReservation;
import Model.TypeChambre;
import View.JMain;

public class CtrlReservationClient {

	public static int row;
	private static int selectedReservationId = -1;

	public static void actionMakeReservation(JButton btnEnregistrer, JComboBox<String> startdayComboBox,
			JComboBox<String> startmonthComboBox, JComboBox<String> startyearComboBox, JComboBox<String> enddayComboBox,
			JComboBox<String> endmonthComboBox, JComboBox<String> endyearComboBox, JComboBox<TypeChambre> etatChambreC,
			String nom, String prenom) {

		btnEnregistrer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Client.makeReservation(startdayComboBox.getSelectedItem().toString(),
						startmonthComboBox.getSelectedItem().toString(), startyearComboBox.getSelectedItem().toString(),
						enddayComboBox.getSelectedItem().toString(), endmonthComboBox.getSelectedItem().toString(),
						endyearComboBox.getSelectedItem().toString(),
						etatChambreC.getSelectedItem().toString(), nom, prenom);
			}
		});

	}

	public static void actionMesReservations(JButton reservClient, JPanel pMesReserv, JPanel pReservation,
			JPanel contentPane, JPanel panel_1, String nom,
			String prenom, DefaultTableModel model, JTable tableClient, int selectedReservationId,
			EtatReservation selectedReservationEtat) {

		reservClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// reaffiche le panel caché
				pMesReserv.setBounds(300, 50, 564, 247);
				contentPane.add(pMesReserv);
				pMesReserv.setVisible(true);
				panel_1.setVisible(true);

				Client.mesReservations(nom, prenom, model);

				pReservation.setVisible(false);

			}
		});

	}

	public static void actionAnnulResrv(JButton btnAnnuler, String nom, String prenom,
			DefaultTableModel model, JTable tableResrvClient, EtatReservation selectedReservationEtat) {

		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				selectedReservationId = (int) tableResrvClient.getValueAt(tableResrvClient.getSelectedRow(), 0);
				String[] Datedebut = tableResrvClient.getValueAt(tableResrvClient.getSelectedRow(), 3).toString()
						.split("/");

				Client.annulReservation(nom, prenom, model, selectedReservationId, Datedebut, selectedReservationEtat);

			}
		});
	}

	public static void actionVisiblePanelReserv(JButton btnReservation, JPanel pReservation, JPanel panel_1,
			JPanel pMesReserv) {

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

	public static void actionRetour(JButton retour, JFrame frame) {

		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JMain f = new JMain();
				f.setVisible(true);
				frame.dispose();

			}
		});
	}

}
