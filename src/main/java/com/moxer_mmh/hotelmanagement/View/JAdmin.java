package com.moxer_mmh.hotelmanagement.View;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.moxer_mmh.hotelmanagement.Controller.*;

import javax.swing.JLabel;
import javax.swing.JButton;
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
		setBounds(100, 100, 970, 633);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		this.setTitle("THE WHITE SWAN HOTEL");
		ImageIcon icon = new ImageIcon(
				"C:\\Users\\TRETEC\\OneDrive\\Bureau\\ProjetPoo2-main (1)\\ProjetPoo2-main\\Program\\View\\logo.png");
		setIconImage(icon.getImage());

		Color sideColor = new Color(44, 62, 80);

		JButton clientButton = Design.createButton("CLIENTS", 10, 155, 180, 42);
		CtrlAdmin.actionClient(clientButton, this);

		JButton reservationButton = Design.createButton("RESERVATIONS", 10, 87, 180, 42);
		CtrlAdmin.actionReservation(reservationButton, this);

		JButton chambreButton = Design.createButton("CHAMBRES", 10, 21, 180, 42);
		CtrlAdmin.actionChambre(chambreButton, this);

		JButton fermerButton = Design.createButton("RETOUR", 10, 317, 180, 42);
		CtrlAdmin.actionRetour(fermerButton, this);

		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(236, 240, 241));
		mainPanel.setBounds(202, 11, 841, 580); // Redimensionnement du mainPanel pour s'adapter à la largeur et à la
												// hauteur de la fenêtre
		contentPane.add(mainPanel);

		JLabel backgroundLabel = new JLabel();
		mainPanel.add(backgroundLabel);

		JLabel icone = new JLabel("");
		icone.setIcon(new ImageIcon("C:\\Users\\TRETEC\\OneDrive\\Images\\AdminIMG.jpg"));
		icone.setBounds(0, 0, 500, 500); // Redimensionnement de l'icône pour s'adapter à la largeur et à la hauteur du
											// mainPanel
		mainPanel.add(icone);

		JPanel sidePanel = new JPanel();
		contentPane.add(sidePanel);
		sidePanel.setBackground(sideColor);
		sidePanel.setBounds(0, 0, 205, 598);
		sidePanel.setLayout(null);
		sidePanel.add(clientButton);
		sidePanel.add(reservationButton);
		sidePanel.add(chambreButton);
		sidePanel.add(fermerButton);
	}

}
