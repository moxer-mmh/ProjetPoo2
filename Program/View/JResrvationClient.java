package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Controller.CtrlReservationClient;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Model.Date;
import Model.EtatReservation;
import Model.ModelTable;
import Model.TypeChambre;

import javax.swing.ListSelectionModel;

public class JResrvationClient extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField prenomC;
	private static JTextField nomC;

	/**
	 * Launch the application.
	 */
	static JComboBox<TypeChambre> etatChambreC = new JComboBox<TypeChambre>();
	static int selectedRow;

	JPanel pReservation = new JPanel();
	

	JTable tableMesReserv;
	static DefaultTableModel model;
	private int selectedReservationId = -1;
	private EtatReservation selectedReservationEtat; // État de la réservation sélectionnée

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JResrvationClient frame = new JResrvationClient(null, null);
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JResrvationClient(String nom, String prenom) {

		model = new ModelTable(
				new Object[][] {},
				new String[] { "ID", "Chambre", "Type", "Date Début", "Date Fin", "État" });
		


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 901, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.setTitle("THE WHITE SWAN HOTEL");
		ImageIcon icon = new ImageIcon("C:\\Users\\TRETEC\\OneDrive\\Bureau\\ProjetPoo2-main (1)\\ProjetPoo2-main\\Program\\View\\logo.png");
		setIconImage(icon.getImage());
		
		Color sideColor = new Color(44, 62, 80);


		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 234, 447);
		panel.setLayout(null);
        panel.setBackground(sideColor);
		contentPane.add(panel);

		JButton btnRetour = Design.createButton("Retour",10, 266, 193, 42);
		CtrlReservationClient.actionRetour(btnRetour, this);

		btnRetour.setFont(new Font("Arial", Font.BOLD, 14));
		panel.add(btnRetour);

		pReservation.setBounds(300, 11, 564, 189);
		contentPane.add(pReservation);

		pReservation.setVisible(false);
		JButton btnReservation = Design.createButton("+ RESERVATION",10, 32, 197, 42);
	
		btnReservation.setFont(new Font("Arial", Font.BOLD, 14));
		panel.add(btnReservation);
		pReservation.setLayout(null);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(288, 11, 250, 85);
		panel_1_1.setLayout(null);
		pReservation.add(panel_1_1);

		prenomC = new JTextField();
		prenomC.setFont(new Font("Tahoma", Font.BOLD, 12));
		prenomC.setEnabled(false);
		prenomC.setText(prenom);
		prenomC.setColumns(10);
		prenomC.setBounds(10, 0, 230, 25);
		panel_1_1.add(prenomC);

		

		JComboBox<String> enddayComboBox = new JComboBox<String>();
		enddayComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "01", "02", "03", "04", "05", "06",
				"07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
				"24", "25", "26", "27", "28", "29", "30", "31" }));
		enddayComboBox.setSelectedItem(String.valueOf(Date.getTodayday())); 
		enddayComboBox.setBounds(10, 49, 70, 25);
		panel_1_1.add(enddayComboBox);

		JComboBox<String> endmonthComboBox = new JComboBox<String>();
		endmonthComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
		endmonthComboBox.setSelectedItem(String.valueOf(Date.getTodaymonth()));
		endmonthComboBox.setBounds(90, 49, 70, 25);
		panel_1_1.add(endmonthComboBox);

		JComboBox<String> endyearComboBox = new JComboBox<String>();
		endyearComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "2024", "2025", "2026", "2027", "2028", "2029", "2030" , "2031" , "2032" , "2033" , "2034" , "2035" , "2036" , "2037" , "2038" , "2039" , "2040"}));
		endyearComboBox.setSelectedItem(String.valueOf(Date.getTodayyear()));
		endyearComboBox.setBounds(170, 49, 70, 25);
		panel_1_1.add(endyearComboBox);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 11, 255, 85);
		panel_3.setLayout(null);
		pReservation.add(panel_3);

		nomC = new JTextField();
		nomC.setFont(new Font("Tahoma", Font.BOLD, 12));
		nomC.setEnabled(false);
		nomC.setText(nom);
		nomC.setColumns(10);
		nomC.setBounds(10, 0, 230, 25);
		panel_3.add(nomC);

		JComboBox<String> startdayComboBox = new JComboBox<String>();
		startdayComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "01", "02", "03", "04", "05", "06",
				"07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
				"24", "25", "26", "27", "28", "29", "30", "31" }));
		startdayComboBox.setSelectedItem(String.valueOf(Date.getTodayday())); 
		startdayComboBox.setBounds(10, 51, 70, 25);		
		panel_3.add(startdayComboBox);

		JComboBox<String> startmonthComboBox = new JComboBox<String>();
		startmonthComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
		System.out.println(String.valueOf(Date.getTodaymonth()));
		startmonthComboBox.setSelectedItem(String.valueOf(Date.getTodaymonth()));
		startmonthComboBox.setBounds(90, 51, 70, 25);
		panel_3.add(startmonthComboBox);

		JComboBox<String> startyearComboBox = new JComboBox<String>();
		startyearComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "2024", "2025", "2026", "2027", "2028", "2029", "2030" , "2031" , "2032" , "2033" , "2034" , "2035" , "2036" , "2037" , "2038" , "2039" , "2040"}));
		startyearComboBox.setSelectedItem(String.valueOf(Date.getTodayyear()));
		startyearComboBox.setBounds(170, 51, 70, 25);
		panel_3.add(startyearComboBox);

		/*
		 * textField_4 = new JTextField();
		 * textField_4.setColumns(10);
		 * textField_4.setBounds(10, 86, 230, 25);
		 * panel_3.add(textField_4);
		 */

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(90, 80, 500, 50);
		panel_4.setLayout(null);
		pReservation.add(panel_4);

		etatChambreC.setModel(new DefaultComboBoxModel(TypeChambre.values()));
		etatChambreC.setBounds(120, 20, 150, 25);
		panel_4.add(etatChambreC);

		JButton btnEnregistrer = new JButton("Enregistrer");
		
		
		btnEnregistrer.setBounds(234, 142, 101, 23);

		CtrlReservationClient.actionMakeReservation(btnEnregistrer, startdayComboBox, startmonthComboBox, startyearComboBox, enddayComboBox, endmonthComboBox, endyearComboBox, etatChambreC, nomC.getText(), prenomC.getText());

		pReservation.add(btnEnregistrer);

		JPanel pMesReserv = new JPanel();
		pMesReserv.setBounds(300, 211, 564, 247);
		contentPane.add(pMesReserv);
		pMesReserv.setLayout(null);
		JButton btnModifier, btnAnnuler;

		pMesReserv.setVisible(false);

		// Créer le JTable avec le modèle de données
		tableMesReserv = new JTable(model);

		tableMesReserv.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// pMesReserv.add(tableClient);)
		tableMesReserv.setVisible(true);
		getContentPane().setLayout(null);

		// Créer le JScrollPane avec le JTable
		JScrollPane scrollPane = new JScrollPane(tableMesReserv);
		scrollPane.setBounds(10, 11, 452, 225);
		pMesReserv.add(scrollPane);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(465, 11, 92, 62);
		pMesReserv.add(panel_1);
		panel_1.setLayout(null);
		btnAnnuler = new JButton("Annuler");

		CtrlReservationClient.actionAnnulResrvation(btnAnnuler, nom, prenom, model, tableMesReserv, selectedReservationEtat);

		btnAnnuler.setBounds(6, 34, 83, 23);
		panel_1.add(btnAnnuler);
		btnAnnuler.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		btnModifier = new JButton("Modifier");
		
		
		//CtrlReservationClient.actionModifyResrvation(btnModifier, nom, prenom, model, tableMesReserv, selectedReservationEtat);
		
		btnModifier.setBounds(6, 4, 83, 23);
		panel_1.add(btnModifier);
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		
        
		JButton reservClient = Design.createButton("Mes Reservations",10, 107, 197, 42);
		CtrlReservationClient.actionMesReservations(reservClient, pMesReserv, pReservation, contentPane, panel_1, nom,
				prenom, model, tableMesReserv, selectedReservationId, selectedReservationEtat);

		JButton btnModif2 = new JButton("Modifier");
		btnModif2.setBounds(234, 142, 101, 23);
		
		
		pReservation.add(btnModif2);
		
	

		CtrlReservationClient.actionVisiblePanelReserv(btnReservation, pReservation, panel_1, pMesReserv
				, btnModif2, btnEnregistrer,etatChambreC);
		
		
		CtrlReservationClient.actionModifyResrvation(btnModifier, nom, prenom, model, tableMesReserv, selectedReservationEtat,
				btnReservation, pReservation,panel_1, pMesReserv,startdayComboBox,startmonthComboBox,startyearComboBox,enddayComboBox,endmonthComboBox,endyearComboBox
				,btnEnregistrer,btnModif2,etatChambreC );

		
		CtrlReservationClient.actionModify2Resrvation(btnModif2, nom, prenom, model, tableMesReserv, selectedReservationEtat,
				btnReservation, pReservation,panel_1, pMesReserv,startdayComboBox,startmonthComboBox,startyearComboBox,enddayComboBox,endmonthComboBox,endyearComboBox);


		reservClient.setFont(new Font("Arial", Font.BOLD, 14));
		panel.add(reservClient);

	}
}

