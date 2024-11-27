package com.moxer_mmh.hotelmanagement.Model;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.moxer_mmh.hotelmanagement.Controller.*;

public class Admin {

	public static void addRoom(TypeChambre typeChambre, int numChambre, DefaultTableModel model) {

		if (!Chambre.chambres.containsKey(numChambre)) {
			Chambre.chambres.put(numChambre, new Chambre(numChambre, typeChambre));
			System.out.println("Chambre ajoutée avec succès !");

			Object[] data = { numChambre, typeChambre, EtatChambres.LIBRE };
			model.addRow(data);
		}
	}

	public static void modifyRoom(int selectedRow, DefaultTableModel model,
			JComboBox<TypeChambre> typeChambre, JComboBox<EtatChambres> etatChambre) {

		try {
			Chambre chambre = Chambre.chambres.get(selectedRow + 1);

			if (chambre != null) {
				if (chambre.getEtatChambre() != EtatChambres.RESERVEE) {
					chambre.setType((TypeChambre) typeChambre.getSelectedItem());
					chambre.setEtatChambre((EtatChambres) etatChambre.getSelectedItem());

					model.setValueAt(typeChambre.getSelectedItem(), selectedRow, 1);
					model.setValueAt(etatChambre.getSelectedItem(), selectedRow, 2);

					JOptionPane.showMessageDialog(null, "Modification effectuée avec succès", "Confirmation",
							JOptionPane.INFORMATION_MESSAGE);

				} else {
					throw new ExeptionChambre("Impossible de modifier une chambre déjà réservée.");
				}
			}

		} catch (ExeptionChambre e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}

	}

	@SuppressWarnings("static-access")
	public static void deleteRoom(DefaultTableModel model, int selectedRow) {
		try {
			if (selectedRow != -1) {
				Chambre chambre = Chambre.chambres.get(selectedRow + 1);
				if (chambre.getEtatChambre() == EtatChambres.LIBRE) {
					chambre.chambres.remove(selectedRow + 1);
					model.removeRow(selectedRow);
					JOptionPane.showMessageDialog(null, "Suppression effectuée avec succès", "Confirmation",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					throw new ExeptionChambre("Impossible de supprimer une chambre déjà réservée ou en attente.");
				}
			}
		} catch (ExeptionChambre e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
}
