package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import Controller.CtrlClient;
import Model.Client;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
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
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		this.setTitle("THE WHITE SWAN HOTEL");
		ImageIcon icon = new ImageIcon("C:\\Users\\TRETEC\\OneDrive\\Bureau\\ProjetPoo2-main (1)\\ProjetPoo2-main\\Program\\View\\logo.png");
		setIconImage(icon.getImage());
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel background = new JLabel();
		background.setBounds(341, -12, 445, 575);
		contentPane.add(background);
		background.setIcon(new ImageIcon(getClass().getResource("Hotel.jpg")));
		
		JLabel background2 = new JLabel();
		background2.setBounds(0, 0, 401, 563);
		background2.setOpaque(true);
		background2.setBackground(Color.white);
		contentPane.add(background2);
		
		JLabel logo = new JLabel();
		logo.setIcon(new ImageIcon(getClass().getResource("logo.png")));
		logo.setBounds(70, 136, 236, 256);
		background2.add(logo);
		

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(50, 200, 350, 148);
		panel.setLayout(null);
		background.add(panel);
		
		nomClient = new JTextField();
		nomClient.setCaretColor(Color.white);
		nomClient.setForeground(Color.white);
		nomClient.setBackground(new Color(0, 21, 43));
		nomClient.setBounds(129, 10, 206, 34);
		panel.add(nomClient);
		nomClient.setColumns(10);

		prenomClient = new JTextField();
		prenomClient.setCaretColor(Color.white);
		prenomClient.setForeground(Color.white);
		prenomClient.setBackground(new Color(0, 21, 43));
		prenomClient.setBounds(129, 54, 206, 34);
		panel.add(prenomClient);

		JLabel lblNomLabel = new JLabel("Nom");
		lblNomLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNomLabel.setForeground(new Color(0, 0, 64));
		lblNomLabel.setBounds(0, 21, 98, 27);
		panel.add(lblNomLabel);
		lblNomLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPrenom.setForeground(new Color(0, 0, 64));
		lblPrenom.setBounds(0, 50, 98, 35);
		panel.add(lblPrenom);
		lblPrenom.setHorizontalAlignment(SwingConstants.RIGHT);
		
	
		JButton btnNewButton = Design.createButton("Entrer", 120, 98, 80, 44);
		panel.add(btnNewButton);
		CtrlClient.actionAddClient(btnNewButton,this,nomClient, prenomClient);
				
		
	
		JButton btnNewButton_1 = Design.createButton("Retour", 240, 98, 80, 44);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JMain windowToBeClosed = new JMain();
				windowToBeClosed.setVisible(true);
				dispose();

			}
		});
		panel.add(btnNewButton_1);
		
		

		background.add(panel);
	}

	


}
