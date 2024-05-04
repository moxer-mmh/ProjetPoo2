package Controller;

import java.awt.Component;
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

import Model.Chambre;
import Model.EtatChambres;
import Model.TypeChambre;
import View.JAdmin;
import View.JMain;



public class CtrlChambre {
	public static int roomNumber=1;	
	static int selectedRow;
	
public static void actionAddRoom(JButton btnAddRoom,JComboBox<TypeChambre> typeChambre,JTextField numChambre,
		DefaultTableModel model,JTable tableChambre,ActionListener actionListener) {
	
	if (Chambre.chambres == null || !Chambre.chambres.containsKey(roomNumber)) {

		Chambre.chambres.put(roomNumber, new Chambre(roomNumber, (TypeChambre) typeChambre.getSelectedItem()));
		System.out.println("Chambre ajoutée avec succès !");

		numChambre.setText(String.valueOf(roomNumber));

		Object[] data = { roomNumber, typeChambre.getSelectedItem(), EtatChambres.LIBRE };

	
		model.addRow(data);
		tableChambre.setModel(model);
		roomNumber = roomNumber + 1;
		numChambre.setText(String.valueOf(roomNumber));

	} else {

		
		  JOptionPane.showMessageDialog((Component) actionListener,
		  "La chambre spécifiée existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
		 

	}
}

		
public static void listeRoom( DefaultTableModel model,JTable table) {
	int rowIndex = 0;

	for (Chambre rowList : Chambre.chambres.values()) {
		Object[] data = { rowList.getNumero(), rowList.getType(), rowList.getEtatChambre() };
		System.out.println(rowList.getNumero());
		model.addRow(data);
		rowIndex++;
	}

	// Création d'un JTable
	table.setModel(model);

}

public static void actionModifRoom(JButton btnModifRoom,JTable table,DefaultTableModel model,
		JComboBox<TypeChambre> typeChambre,JComboBox<EtatChambres> etatChambre )
{
	
	btnModifRoom.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			selectedRow = table.getSelectedRow();
			
			  Chambre chambre = Chambre.chambres.get(selectedRow+1);//indice de la map commance +1 SI ON le compare avec l indice de la table
			  
			  if (chambre != null) {
				  
				 chambre.setType((TypeChambre) typeChambre.getSelectedItem()); 
				 chambre.setEtatChambre((EtatChambres) etatChambre.getSelectedItem());
				 

					// table.setModel(tableModel);
				    table.setModel(model);

					
					model.setValueAt(typeChambre.getSelectedItem(), selectedRow, 1);
					model.setValueAt(etatChambre.getSelectedItem(), selectedRow, 2);
			  
			  }
			 
			

		}
	});
	
}

public static void actionSupprimeRoom(JButton btnSupprimer,JTable table,DefaultTableModel model)
{
	btnSupprimer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
	table.setModel(model);
	selectedRow = table.getSelectedRow();
	if (selectedRow != -1) {
		model.removeRow(selectedRow);
		table.setModel(model);
	} else {
		// Or display an error message
	}
	
		}
	});	
}
	

public static void actionSelectRoom(JTable table,DefaultTableModel model,JComboBox<TypeChambre> typeChambre,JComboBox<EtatChambres> etatChambre,
		JTextField numChambre)
{
	table.addMouseListener(new MouseAdapter() {
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) { // Check for double click

			selectedRow = table.getSelectedRow();
			 // Assuming you want the value from the second column

			// DefaultTableModel model = (DefaultTableModel) table.getModel();
			Object value1 = ((TableModel) model).getValueAt(selectedRow, 0);
			Object value2 = ((TableModel) model).getValueAt(selectedRow, 1);
			Object value3 = ((TableModel) model).getValueAt(selectedRow, 2);
			
			typeChambre.removeAllItems();
			etatChambre.removeAllItems();
			if (selectedRow != -1) {
				numChambre.setText(value1 == null ? "" : value1.toString());

				typeChambre.addItem((TypeChambre) value2);
				
				etatChambre.addItem((EtatChambres) value3);
				
				// typeChambre.setModel(new DefaultComboBoxModel(TypeChambre.values()));

			} else {
				numChambre.setText(""); // Or display an error message
			}
		}
	}

});	
	
	
}


public static void actionRetour(JButton retour,JFrame frame) {
	
	retour.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JAdmin windowToBeClosed = new JAdmin();
			windowToBeClosed.setVisible(true);
			frame.dispose();
			
		}
	});		
				
			}
		
		 	
}


