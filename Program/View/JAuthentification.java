package View;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controller.CtrlAuthentification;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

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
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setMinimumSize(new Dimension(32767, 32767));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel background = new JLabel();
		background.setIcon(new ImageIcon(getClass().getResource("hotel1.png")));
		background.setBounds(400, 0, 386, 563);
		contentPane.add(background);

		JLabel textt = new JLabel("Entre votre username et mot de passe");
		textt.setFont(new Font("Tahoma", Font.BOLD, 18));
		textt.setForeground(Color.white);
		textt.setBounds(20, 120, 360, 57);
		background.add(textt);


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
		panel_1.setBounds(50, 200, 300, 148);
		panel_1.setLayout(null);
		background.add(panel_1);

		JButton btnRetour = Design.createButton("Retour", 220, 98, 80, 44);

		panel_1.add(btnRetour);
		CtrlAuthentification.actionRetour(btnRetour, this);

		utilisateur = new JTextField();
		utilisateur.setCaretColor(Color.white);
		utilisateur.setForeground(Color.white);
		utilisateur.setBackground(new Color(0, 21, 43));
		utilisateur.setBounds(129, 10, 161, 34);
		panel_1.add(utilisateur);
		utilisateur.setColumns(10);

		pwd = new JPasswordField();
		pwd.setCaretColor(Color.white);
		pwd.setForeground(Color.white);
		pwd.setBackground(new Color(0, 21, 43));
		pwd.setBounds(129, 54, 161, 34);
		panel_1.add(pwd);

		JButton btnLogin = Design.createButton("Login", 120, 98, 80, 44);

		CtrlAuthentification.actionLogin(btnLogin, pwd, utilisateur, this);
		panel_1.add(btnLogin);

		JLabel lblNewLabel = new JLabel("Utilisateur");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setForeground(Color.white);
		lblNewLabel.setBounds(0, 13, 98, 27);
		panel_1.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblMotPasse = new JLabel("Mot Passe");
		lblMotPasse.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMotPasse.setForeground(Color.white);
		lblMotPasse.setBounds(0, 50, 98, 35);
		panel_1.add(lblMotPasse);
		lblMotPasse.setHorizontalAlignment(SwingConstants.RIGHT);


	}
}