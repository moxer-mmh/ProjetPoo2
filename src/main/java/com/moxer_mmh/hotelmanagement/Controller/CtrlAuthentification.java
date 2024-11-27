package com.moxer_mmh.hotelmanagement.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.moxer_mmh.hotelmanagement.View.*;

public class CtrlAuthentification {

	public static void actionLogin(JButton btnLogin, JPasswordField pwd, JTextField utilisateur, JFrame frame) {

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] value2 = pwd.getPassword();
				String motDePasse = String.valueOf(value2);
				if (utilisateur.getText().equals("admin") && motDePasse.equals("admin")) {

					JAdmin frame33 = new JAdmin();
					frame33.setVisible(true);
					frame.dispose();

				} else {
					JOptionPane.showMessageDialog(frame, "Nom d'utilisateur ou mot de passe incorrect", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char[] value2 = pwd.getPassword();
				String motDePasse = String.valueOf(value2);
				if (utilisateur.getText().equals("admin") && motDePasse.equals("admin")) {

					JAdmin frame33 = new JAdmin();
					frame33.setVisible(true);
					frame.dispose();

				} else {
					JOptionPane.showMessageDialog(frame, "Nom d'utilisateur ou mot de passe incorrect", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

	}

	public static void actionRetour(JButton btnRetour, JFrame frame) {
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JMain windowToBeClosed = new JMain();
				windowToBeClosed.setVisible(true);
				frame.dispose();

			}
		});

	}

}
