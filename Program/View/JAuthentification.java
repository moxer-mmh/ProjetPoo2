package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controller.CtrlAuthentification;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 676, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(164, 71, 121, 268);
		contentPane.add(panel);
		panel.setLayout(null);
		
	
		
		JLabel lblNewLabel = new JLabel("Utilisateur");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 66, 101, 35);
		panel.add(lblNewLabel);
		
		JLabel lblMotPasse = new JLabel("Mot Passe");
		lblMotPasse.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMotPasse.setBounds(10, 112, 101, 35);
		panel.add(lblMotPasse);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(295, 71, 181, 268);
		contentPane.add(panel_1);
		
		JButton btnRetour = new JButton("Retour");
		
		btnRetour.setBounds(79, 200, 78, 44);
		panel_1.add(btnRetour);
		 CtrlAuthentification.actionRetour(btnRetour,this);
		
		utilisateur = new JTextField();
		utilisateur.setBounds(10, 65, 161, 34);
		panel_1.add(utilisateur);
		utilisateur.setColumns(10);
		
		pwd = new JPasswordField();
		pwd.setBounds(10, 110, 161, 34);
		panel_1.add(pwd);
		
		JButton btnLogin = new JButton("Login");
		
			CtrlAuthentification.actionLogin(btnLogin, pwd, utilisateur, this);
		
		
		btnLogin.setBounds(10, 200, 69, 44);
		panel_1.add(btnLogin);
		
		
		
		
		}
}

