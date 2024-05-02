package Design;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	static JMain frame = new JMain();
	public static void main(String[] args) {
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	
	
	public JMain(){
		
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
		
		JButton btnNewButton_1 = new JButton("Administrateur");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			
			JAuthentification frame =new JAuthentification();
			frame.setVisible(true);
			dispose();

			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(30, 26, 154, 42);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Client");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				JClient frame =new JClient();
				frame.setVisible(true);
				dispose();
				

				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(30, 104, 154, 42);
		panel.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Quitter");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				dispose();
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2.setBounds(30, 186, 154, 42);
		panel.add(btnNewButton_2);
	}

}
