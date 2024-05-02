package Design;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import Administration.Administrateur;
import Administration.EtatReservation;
import Administration.Reservations;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class JAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	 

	/**
	 * Launch the application.
	 */
	
	static JAdmin frame = new JAdmin();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
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
	public JAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 798, 594);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(64, 128, 128));
		panel.setBounds(23, 51, 162, 478);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton Client = new JButton("CLIENTS");
		Client.setFont(new Font("Arial", Font.BOLD, 14));
		Client.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Client.setBounds(10, 11, 142, 42);
		panel.add(Client);
		
		JButton reservation = new JButton("RESERVATIONS");
		reservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JReservation f = new JReservation();
				JReservation windowToBeClosed = new JReservation();
				windowToBeClosed.setVisible(true);
				dispose();
			
		
			}
		});
		reservation.setFont(new Font("Arial", Font.BOLD, 14));
		reservation.setBounds(10, 157, 142, 42);
		panel.add(reservation);
		
		JButton chambre_1 = new JButton("CHAMBRES");
		chambre_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Administrateur r =new Administrateur();
			
				JChambre f =new JChambre();
				f.setVisible(true);
				dispose();
				
			}
			
		});
		chambre_1.setFont(new Font("Arial", Font.BOLD, 14));
		chambre_1.setBounds(10, 87, 142, 42);
		panel.add(chambre_1);
		
		JButton fermer = new JButton("RETOUR");
		fermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JMain windowToBeClosed = new JMain();
				windowToBeClosed.setVisible(true);
				dispose();
			}
		});
		fermer.setFont(new Font("Arial", Font.BOLD, 14));
		fermer.setBounds(10, 317, 142, 42);
		panel.add(fermer);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(190, 51, 582, 478);
		contentPane.add(panel_1);
		
		JLabel icone = new JLabel("");
		icone.setIcon(new ImageIcon("C:\\Users\\Administrateur\\Downloads\\hotel1.jpg"));
		panel_1.add(icone);
	}
}
