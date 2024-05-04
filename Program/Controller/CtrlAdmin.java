package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import View.JChambre;
import View.JMain;
import View.JReservation;

public class CtrlAdmin {
	

	public static void actionReservation(JButton reservation,JFrame frame) {
		
		reservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JReservation();
				JReservation windowToBeClosed = new JReservation();
				windowToBeClosed.setVisible(true);
				frame.dispose();
				
			}
		});
		
		}
	
	public static void actionChambre(JButton chambre,JFrame frame) {
		
		chambre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JChambre windowToBeClosed = new JChambre();
				windowToBeClosed.setVisible(true);
				frame.dispose();
				
			}
		});
		
		}
	
public static void actionFermer(JButton fermer,JFrame frame) {
	
	fermer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JMain windowToBeClosed = new JMain();
			windowToBeClosed.setVisible(true);
		   frame.dispose();
			
		}
	});		
				
			}
		
	
}


