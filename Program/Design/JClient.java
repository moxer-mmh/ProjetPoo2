package Design;

import java.awt.EventQueue;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Administration.Chambres;
import Client.Client;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JClient extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField nomClient;
	private static JTextField prenomClient;

	/**
	 * Launch the application.
	 */
	
	 private static Map<Integer, Client> clients = new TreeMap<>();
	 static  int row=1;
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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(59, 32, 290, 130);
		contentPane.add(panel);
		panel.setLayout(null);
		
		nomClient = new JTextField();
		nomClient.setBounds(21, 11, 186, 37);
		panel.add(nomClient);
		nomClient.setColumns(10);
		
		prenomClient = new JTextField();
		prenomClient.setColumns(10);
		prenomClient.setBounds(21, 59, 186, 37);
		panel.add(prenomClient);
		
		JButton btnNewButton = new JButton("Entrer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addClient();
				dispose();
			}
		});
		btnNewButton.setBounds(220, 173, 104, 37);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Retour");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JMain windowToBeClosed = new JMain();
				windowToBeClosed.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton_1.setBounds(50, 197, 89, 23);
		contentPane.add(btnNewButton_1);
	}
	
	 public static void addClient() {
	        String nom = nomClient.getText();
	        String prenom = prenomClient.getText();

	        // Vérifier si le client existe déjà dans la map
	        for (Client c : clients.values()) {
	            if (c.getNom().equals(nom) && c.getPrenom().equals(prenom)) {
	                System.out.println("Le client existe déjà.");
	                JMenuClient f = new JMenuClient(nom, prenom);
	                f.setVisible(true);
	                return; // Sortir de la méthode après avoir ouvert la fenêtre JMenuClient
	            }
	        }

	        // Si le client n'existe pas, créer un nouveau client
	        Client client = new Client(nom, prenom, row);
	        clients.put(row, client);
	        System.out.println("Nouveau client créé avec l'ID : " + row);
	        ++row;

	        JMenuClient f = new JMenuClient(nom, prenom);
	        f.setVisible(true);
	    }
	 
	 public static void listeClient() {
		 int rowIndex = 0;
         
   
		 
		 for (Client rowList : clients.values()) {
			 
			 Object[] data = {rowList.getId(), rowList.getNom(), rowList.getPrenom()} ;
			  System.out.println(rowList.getNom());
			// modelT.addRow(data);
				    rowIndex++;
				}
		      
	        // Création d'un JTable
	     //   table.setModel(modelT);
	  	
		
	 }


}
