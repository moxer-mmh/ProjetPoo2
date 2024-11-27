package com.moxer_mmh.hotelmanagement.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.moxer_mmh.hotelmanagement.Controller.*;

public class JAuthentification extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	static JAuthentification frame = new JAuthentification();
	private JTextField utilisateur;
	private JPasswordField pwd;

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
	public JAuthentification() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setMinimumSize(new Dimension(35000, 35000));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.setTitle("THE WHITE SWAN HOTEL");

		ImageIcon icon = new ImageIcon(
				"C:\\Users\\TRETEC\\OneDrive\\Bureau\\ProjetPoo2-main (1)\\ProjetPoo2-main\\Program\\View\\logo.png");
		setIconImage(icon.getImage());

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel background = new JLabel();
		background.setIcon(new ImageIcon(getClass().getResource("Hotel.jpg")));
		background.setBounds(341, -12, 445, 575);
		contentPane.add(background);

		JLabel background2 = new JLabel();
		background2.setBounds(0, 0, 401, 563);
		background2.setOpaque(true);
		background2.setBackground(Color.white);
		contentPane.add(background2);

		JLabel logo = new JLabel();
		logo.setIcon(new ImageIcon(getClass().getResource("logo.png")));
		logo.setBounds(70, 136, 236, 256);
		background2.add(logo);

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setBounds(50, 200, 350, 148);
		panel_1.setLayout(null);
		background.add(panel_1);

		JButton btnRetour = Design.createButton("Retour", 240, 98, 80, 44);
		panel_1.add(btnRetour);
		CtrlAuthentification.actionRetour(btnRetour, this);

		utilisateur = new JTextField();
		utilisateur.setCaretColor(Color.white);
		utilisateur.setForeground(Color.white);
		utilisateur.setBackground(new Color(0, 21, 43));
		utilisateur.setBounds(129, 10, 206, 34);
		panel_1.add(utilisateur);
		utilisateur.setColumns(10);

		pwd = new JPasswordField();
		pwd.setCaretColor(Color.white);
		pwd.setForeground(Color.white);
		pwd.setBackground(new Color(0, 21, 43));
		pwd.setBounds(129, 54, 206, 34);
		panel_1.add(pwd);

		JButton btnLogin = Design.createButton("Login", 120, 98, 80, 44);
		CtrlAuthentification.actionLogin(btnLogin, pwd, utilisateur, this);
		panel_1.add(btnLogin);

		JLabel lblNewLabel = new JLabel("Utilisateur");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setForeground(new Color(0, 0, 64));
		lblNewLabel.setBounds(0, 21, 98, 27);
		panel_1.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblMotPasse = new JLabel("Mot Passe");
		lblMotPasse.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblMotPasse.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMotPasse.setForeground(new Color(0, 0, 64));
		lblMotPasse.setBounds(0, 50, 98, 35);
		panel_1.add(lblMotPasse);
		lblMotPasse.setHorizontalAlignment(SwingConstants.RIGHT);

	}
}
