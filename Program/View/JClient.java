package View;

import java.awt.EventQueue;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Client;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JClient extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField nomClient;
	private static JTextField prenomClient;

	/**
	 * Launch the application.
	 */

	private static Map<Integer, Client> clients = new TreeMap<>();
	static int row = 1;
	private JButton btnNewButton_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JClient frame = new JClient();
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
	public JClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 676, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(156, 106, 321, 130);
		contentPane.add(panel);
		panel.setLayout(null);

		nomClient = new JTextField();
		nomClient.setBounds(77, 11, 186, 37);
		panel.add(nomClient);
		nomClient.setColumns(10);

		prenomClient = new JTextField();
		prenomClient.setColumns(10);
		prenomClient.setBounds(77, 59, 186, 37);
		panel.add(prenomClient);

		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(10, 11, 43, 37);
		panel.add(lblNewLabel);

		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrenom.setBounds(10, 59, 57, 37);
		panel.add(lblPrenom);

		JButton btnNewButton = new JButton("Entrer");
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				addClient();
				dispose();
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addClient();
				dispose();
			}
		});
		btnNewButton.setBounds(240, 247, 81, 37);
		contentPane.add(btnNewButton);

		btnNewButton_1 = new JButton("Retour");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JMain windowToBeClosed = new JMain();
				windowToBeClosed.setVisible(true);
				dispose();

			}
		});
		btnNewButton_1.setBounds(319, 247, 89, 37);
		contentPane.add(btnNewButton_1);
	}

	public static void addClient() {
		String nom = nomClient.getText();
		String prenom = prenomClient.getText();

		// Vérifier si le client existe déjà dans la map
		for (Client c : clients.values()) {
			if (c.getNom().equals(nom) && c.getPrenom().equals(prenom)) {
				System.out.println("Le client existe déjà.");
				JResrvationClient f = new JResrvationClient(nom, prenom);
				f.setVisible(true);
				return; // Sortir de la méthode après avoir ouvert la fenêtre JMenuClient
			}
		}

		// Si le client n'existe pas, créer un nouveau client
		Client client = new Client(nom, prenom, row);
		clients.put(row, client);
		System.out.println("Nouveau client créé avec l'ID : " + row);
		++row;

		JResrvationClient f = new JResrvationClient(nom, prenom);
		f.setVisible(true);
	}

	public static void listeClient() {
		for (Client rowList : clients.values()) {

			System.out.println(rowList.getNom());
		}

		// Création d'un JTable
		// table.setModel(modelT);

	}
}
