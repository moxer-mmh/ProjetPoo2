package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.CtrlMain;
import Model.Chambre;

import javax.swing.JButton;
import java.awt.Font;

public class JMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;

	/**
	 * Launch the application.
	 */

	static JMain frame = new JMain();

	public static void main(String[] args) {
		Chambre.initchambres();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */

	public JMain() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 676, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(251, 79, 218, 273);
		contentPane.add(panel);

		JButton btnAdministrateur = new JButton("Administrateur");
		CtrlMain.actionAdministrateur(btnAdministrateur, this);

		btnAdministrateur.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdministrateur.setBounds(30, 26, 154, 42);
		panel.add(btnAdministrateur);

		JButton btnClient = new JButton("Client");
		CtrlMain.actionClient(btnClient, this);

		btnClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnClient.setBounds(30, 104, 154, 42);
		panel.add(btnClient);

		JButton btnExit = new JButton("Quitter");
		CtrlMain.actionExit(btnExit);

		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExit.setBounds(30, 186, 154, 42);
		panel.add(btnExit);
	}

}
