package Design;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.EventObject;
import java.util.Map;
import java.util.TreeMap;
import java.awt.event.ActionEvent;

import Administration.Administrateur;
import Administration.Chambres;
import Administration.EtatChambres;
import Administration.EtatReservation;
import Administration.TypeChambre;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.AbstractListModel;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFormattedTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JChambre extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	// static DefaultListModel<TypeChambre> model = new DefaultListModel<>();

	static JComboBox<TypeChambre> typeChambre = new JComboBox<TypeChambre>();
	static JComboBox<EtatChambres> etatChambre = new JComboBox<EtatChambres>();
	
	static int row = 1;
	static int selectedRow;

	Administrateur r = new Administrateur();

	static JTable table = new JTable();

	/*
	 * static Object[][] data = { };
	 * 
	 * static String[] columnNames = {"Num", "Type", "Etat"};
	 * 
	 * static DefaultTableModel modelT = new DefaultTableModel(data, columnNames);
	 */

	static DefaultTableModel modelT;

	static JChambre frame = new JChambre();
	static JTextField numChambre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					numChambre.setText(String.valueOf(row));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("serial")
	public JChambre() {

		modelT = new DefaultTableModel(new Object[][] {}, new String[] { "Num", "Type", "Etat" });

		listeRoom();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 798, 594);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(27, 29, 728, 154);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("NUM-CHAMBRE");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(117, 11, 131, 36);
		panel.add(lblNewLabel);

		JLabel lblTypechambre = new JLabel("TYPE-CHAMBRE");
		lblTypechambre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTypechambre.setFont(new Font("Arial", Font.BOLD, 14));
		lblTypechambre.setBounds(285, 11, 131, 36);
		panel.add(lblTypechambre);
		
		JLabel lblEtatchambre = new JLabel("Etat-CHAMBRE");
		lblEtatchambre.setHorizontalAlignment(SwingConstants.CENTER);
		lblEtatchambre.setFont(new Font("Arial", Font.BOLD, 14));
		lblEtatchambre.setBounds(460, 11, 131, 36);
		panel.add(lblEtatchambre);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(102, 85, 542, 58);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JButton btnNewButton = new JButton("AJOUTER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				addRoom(row, null);

			}
		});
		btnNewButton.setBounds(21, 23, 108, 23);
		panel_1.add(btnNewButton);

		JButton btnModifier = new JButton("MODIFIER");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRow = table.getSelectedRow();
				
				  Chambres chambre = r.chambres.get(selectedRow+1);//indice de la map commance +1 SI ON le compare avec l indice de la table
				  
				  if (chambre != null) {
					  
					 chambre.setType((TypeChambre) typeChambre.getSelectedItem()); 
					 chambre.setEtatChambre((EtatChambres) etatChambre.getSelectedItem());
					 

						// table.setModel(tableModel);
						modelT = (DefaultTableModel) table.getModel();

						
						modelT.setValueAt(typeChambre.getSelectedItem(), selectedRow, 1);
						modelT.setValueAt(etatChambre.getSelectedItem(), selectedRow, 2);
				  
				  }
				 

				// Création d'un JT4able
				table.setModel(modelT);

			}
		});
		btnModifier.setBounds(156, 23, 105, 23);
		panel_1.add(btnModifier);

		JButton btnSupprimer = new JButton("SUPPRIMER");
		btnSupprimer.setBounds(297, 23, 108, 23);
		panel_1.add(btnSupprimer);

		JButton btnRechercher = new JButton("RETOUR");
		btnRechercher.setBounds(424, 23, 108, 23);
		panel_1.add(btnRechercher);
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JAdmin windowToBeClosed = new JAdmin();
				windowToBeClosed.setVisible(true);
				dispose();

			}
		});
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				table.setModel(modelT);
				if (selectedRow != -1) {
					modelT.removeRow(selectedRow);
					table.setModel(modelT);
				} else {
					// Or display an error message
				}
			}
		});
		typeChambre.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				typeChambre.setModel(new DefaultComboBoxModel(TypeChambre.values()));
			}
		});

		etatChambre.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				etatChambre.setModel(new DefaultComboBoxModel(EtatChambres.values()));
			}
		});
		typeChambre.setBackground(new Color(255, 255, 255));
		typeChambre.setModel(new DefaultComboBoxModel(TypeChambre.values()));
		typeChambre.setBounds(285, 44, 131, 30);
		panel.add(typeChambre);

		numChambre = new JTextField();
		numChambre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				row = Integer.parseInt(numChambre.getText());
			}

		});

		numChambre.setBounds(117, 47, 131, 30);
		panel.add(numChambre);
		numChambre.setColumns(10);
		
		
		etatChambre.setModel(new DefaultComboBoxModel(EtatChambres.values()));
		etatChambre.setBackground(Color.WHITE);
		etatChambre.setBounds(460, 44, 131, 30);
		panel.add(etatChambre);

		JLabel lblNewLabel_1 = new JLabel("INFORMATION CHAMBRES");
		lblNewLabel_1.setForeground(new Color(0, 64, 64));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(92, 0, 376, 27);
		contentPane.add(lblNewLabel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(27, 229, 728, 315);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) { // Check for double click

					selectedRow = table.getSelectedRow();
					 // Assuming you want the value from the second column

					// DefaultTableModel model = (DefaultTableModel) table.getModel();
					Object value1 = ((TableModel) modelT).getValueAt(selectedRow, 0);
					Object value2 = ((TableModel) modelT).getValueAt(selectedRow, 1);
					Object value3 = ((TableModel) modelT).getValueAt(selectedRow, 2);
					
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

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Création d'un JTable
		table.setModel(modelT);

		table.setBounds(102, 30, 551, 274);
		panel_2.add(table);
		table.setDefaultEditor(Object.class, (TableCellEditor) new DefaultCellEditor(new JTextField()) {
			@Override
			public boolean isCellEditable(EventObject e) {
				return false;
			}
		});

		/*
		 * for(Entry<Integer, Chambres> entry : chambre.entrySet()){ model.add(row,
		 * chambre.get(model)); row++; }
		 */

	}

	public void listeRoom() {
		int rowIndex = 0;

		for (Chambres rowList : r.chambres.values()) {

			Object[] data = { rowList.getNumero(), rowList.getType(), rowList.getEtatChambre() };
			System.out.println(rowList.getNumero());
			modelT.addRow(data);
			rowIndex++;
		}

		// Création d'un JTable
		table.setModel(modelT);

	}

	public void addRoom(int roomNumber, TypeChambre roomType) {
		;
		// int num=Integer.parseInt(numChambre.getText());

		roomType = (TypeChambre) typeChambre.getSelectedItem();

		if (r.chambres == null || !r.chambres.containsKey(roomNumber)) {

			r.chambres.put(roomNumber, new Chambres(roomNumber, roomType));
			System.out.println("Chambre ajoutée avec succès !");

			numChambre.setText(String.valueOf(roomNumber));

			Object[] data = { roomNumber, typeChambre.getSelectedItem(), EtatChambres.LIBRE };

			table.setModel(modelT);
			modelT.addRow(data);
			table.setModel(modelT);
			row = row + 1;
			numChambre.setText(String.valueOf(row));

		} else {

			JOptionPane.showMessageDialog(frame, "La chambre spécifiée existe déjà", "Erreur",
					JOptionPane.ERROR_MESSAGE);

		}
	}
}
