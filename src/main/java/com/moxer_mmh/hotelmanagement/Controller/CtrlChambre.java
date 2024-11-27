package com.moxer_mmh.hotelmanagement.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.moxer_mmh.hotelmanagement.Model.*;
import com.moxer_mmh.hotelmanagement.View.*;

public class CtrlChambre {
	public static int roomNumber;
	static int selectedRow;

	public static void actionAddRoom(JButton btnAddRoom, JComboBox<TypeChambre> typeChambre, JTextField numChambre,
			DefaultTableModel model, JTable tableChambre) {

		btnAddRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					int roomNumber = Integer.parseInt(numChambre.getText().trim());

					Admin.addRoom((TypeChambre) typeChambre.getSelectedItem(), roomNumber, model);

					tableChambre.setModel(model);

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,
							"Veuillez entrer un numéro de chambre valide", ex.getMessage(), JOptionPane.ERROR_MESSAGE);
				}

			}
		});
	}

	public static void actionListeRoom(DefaultTableModel model, JTable table) {
		for (Chambre rowList : Chambre.chambres.values()) {
			Object[] data = { rowList.getNumero(), rowList.getType(), rowList.getEtatChambre() };
			System.out.println(rowList.getNumero());
			model.addRow(data);
		}

		// Création d'un JTable
		table.setModel(model);

	}

	public static void actionModifyRoom(JButton btnModifRoom, JTable table, DefaultTableModel model,
			JComboBox<TypeChambre> typeChambre, JComboBox<EtatChambres> etatChambre) {

		btnModifRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				selectedRow = table.getSelectedRow();

				Admin.modifyRoom(selectedRow, model, typeChambre, etatChambre);
			}
		});
	}

	public static void actionDeleteRoom(JButton btnSupprimer, JComboBox<TypeChambre> typeChambre,
			JComboBox<EtatChambres> etatChambre, JTextField numChambre, JTable table, DefaultTableModel model) {
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(model);
				int selectedRow = table.getSelectedRow();
				Admin.deleteRoom(model, selectedRow);

				numChambre.setText(null);
				etatChambre.setSelectedItem(EtatChambres.LIBRE);
				typeChambre.setSelectedItem(TypeChambre.SIMPLE);

			}
		});
	}

	public static void actionSelectRoom(JTable table, DefaultTableModel model, JComboBox<TypeChambre> typeChambre,
			JComboBox<EtatChambres> etatChambre,
			JTextField numChambre) {
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {

					selectedRow = table.getSelectedRow();

					Object value1 = ((TableModel) model).getValueAt(selectedRow, 0);
					Object value2 = ((TableModel) model).getValueAt(selectedRow, 1);
					Object value3 = ((TableModel) model).getValueAt(selectedRow, 2);

					typeChambre.removeAllItems();
					etatChambre.removeAllItems();
					if (selectedRow != -1) {
						numChambre.setText(value1 == null ? "" : value1.toString());

						typeChambre.addItem((TypeChambre) value2);

						etatChambre.addItem((EtatChambres) value3);

					} else {
						numChambre.setText("");
					}
				}
			}

		});

	}

	public static void actionRetour(JButton retour, JFrame frame) {

		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JAdmin windowToBeClosed = new JAdmin();
				windowToBeClosed.setVisible(true);
				frame.dispose();

			}
		});

	}

}
