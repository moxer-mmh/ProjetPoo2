package View;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.CtrlAdmin;
import javax.swing.JLabel;
import javax.swing.JButton;
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

		Client.setBounds(10, 155, 142, 42);
		panel.add(Client);

		JButton reservation = new JButton("RESERVATIONS");
		CtrlAdmin.actionReservation(reservation, this);

		reservation.setFont(new Font("Arial", Font.BOLD, 14));
		reservation.setBounds(10, 87, 142, 42);
		panel.add(reservation);

		JButton chambre = new JButton("CHAMBRES");

		CtrlAdmin.actionChambre(chambre, this);

		chambre.setFont(new Font("Arial", Font.BOLD, 14));
		chambre.setBounds(10, 21, 142, 42);
		panel.add(chambre);

		JButton fermer = new JButton("RETOUR");

		CtrlAdmin.actionFermer(fermer, this);

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
