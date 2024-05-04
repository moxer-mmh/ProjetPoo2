package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import View.JAuthentification;
import View.JChambre;
import View.JClient;
import View.JMain;
import View.JReservation;

public class CtrlMain {
	

	public static void actionAdministrateur(JButton btnAdministrateur,JFrame frame) {
		
		btnAdministrateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JAuthentification frame1 =new JAuthentification();
				frame1.setVisible(true);
				frame.dispose();
				
			}
		});
		
		}
	
	public static void actionClient(JButton btnClient,JFrame frame) {
		
		btnClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JClient frame2 =new JClient();
				frame2.setVisible(true);
				frame.dispose();
			}
		});
		
		}
	
public static void actionExit(JButton btnExit) {
	
	btnExit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			
		}
	});
		
				
				
			}
		
	
}


