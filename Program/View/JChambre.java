package View;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.EventObject;
import java.awt.event.ActionEvent;

import Model.EtatChambres;
import Model.TypeChambre;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import Controller.CtrlChambre;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JChambre extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	static JComboBox<TypeChambre> typeChambre = new JComboBox<TypeChambre>();
	static JComboBox<EtatChambres> etatChambre = new JComboBox<EtatChambres>();

	static int row = 1;
	static int selectedRow;

	static JTable table = new JTable();

	static DefaultTableModel model;

	// static JChambre frame = new JChambre();
	static JTextField numChambre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					/*
					 * numChambre.setText(String.valueOf(row)); frame.setVisible(true);
					 */
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
	public JChambre() {

		model = new DefaultTableModel(new Object[][] {}, new String[] { "Num", "Type", "Etat" });

		CtrlChambre.listeRoom(model, table);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 798, 594);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(33, 51, 261, 418);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("NUM-CHAMBRE");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 131, 30);
		panel.add(lblNewLabel);

		JLabel lblTypechambre = new JLabel("TYPE-CHAMBRE");
		lblTypechambre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTypechambre.setFont(new Font("Arial", Font.BOLD, 14));
		lblTypechambre.setBounds(10, 81, 131, 30);
		panel.add(lblTypechambre);

		JLabel lblEtatchambre = new JLabel("Etat-CHAMBRE");
		lblEtatchambre.setHorizontalAlignment(SwingConstants.CENTER);
		lblEtatchambre.setFont(new Font("Arial", Font.BOLD, 14));
		lblEtatchambre.setBounds(10, 149, 131, 30);
		panel.add(lblEtatchambre);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(43, 253, 148, 130);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JButton btnAddRoom = new JButton("AJOUTER");
		btnAddRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CtrlChambre.actionAddRoom(btnAddRoom, typeChambre, numChambre, model, table, this);
			}
		});

		btnAddRoom.setBounds(21, 23, 108, 23);
		panel_1.add(btnAddRoom);

		JButton btnModifRoom = new JButton("MODIFIER");

		CtrlChambre.actionModifRoom(btnModifRoom, table, model, typeChambre, etatChambre);

		btnModifRoom.setBounds(21, 56, 105, 23);
		panel_1.add(btnModifRoom);

		JButton btnSupprimer = new JButton("SUPPRIMER");

		CtrlChambre.actionSupprimeRoom(btnSupprimer, table, model);

		btnSupprimer.setBounds(21, 90, 108, 23);
		panel_1.add(btnSupprimer);

		typeChambre.addMouseListener(new MouseAdapter() {
			@SuppressWarnings({})
			@Override
			public void mousePressed(MouseEvent e) {
				typeChambre.setModel(new DefaultComboBoxModel(TypeChambre.values()));
			}
		});

		etatChambre.addMouseListener(new MouseAdapter() {
			@SuppressWarnings({})
			@Override
			public void mousePressed(MouseEvent e) {
				etatChambre.setModel(new DefaultComboBoxModel(EtatChambres.values()));
			}
		});
		typeChambre.setBackground(new Color(255, 255, 255));
		typeChambre.setModel(new DefaultComboBoxModel(TypeChambre.values()));
		typeChambre.setBounds(10, 108, 192, 30);
		panel.add(typeChambre);

		numChambre = new JTextField();

		numChambre.setBounds(10, 40, 192, 30);
		panel.add(numChambre);
		numChambre.setColumns(10);

		etatChambre.setModel(new DefaultComboBoxModel(EtatChambres.values()));
		etatChambre.setBackground(Color.WHITE);
		etatChambre.setBounds(10, 174, 192, 30);
		panel.add(etatChambre);

		JLabel lblNewLabel_1 = new JLabel("INFORMATION CHAMBRES");
		lblNewLabel_1.setForeground(new Color(0, 64, 64));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(92, 0, 376, 27);
		contentPane.add(lblNewLabel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(298, 51, 458, 418);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		CtrlChambre.actionSelectRoom(table, model, typeChambre, etatChambre, numChambre);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Création d'un JTable
		table.setModel(model);

		table.setBounds(10, 11, 438, 396);
		panel_2.add(table);

		JButton btnRetour = new JButton("RETOUR");
		btnRetour.setBounds(638, 486, 108, 23);
		contentPane.add(btnRetour);
		CtrlChambre.actionRetour(btnRetour, this);

		table.setDefaultEditor(Object.class, (TableCellEditor) new DefaultCellEditor(new JTextField()) {
			@Override
			public boolean isCellEditable(EventObject e) {
				return false;
			}
		});

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 438, 396);
		panel_2.add(scrollPane);
	}

}
