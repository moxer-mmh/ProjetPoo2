package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class Design {
	public static JButton createButton(String text, int x, int y, int width, int height) {
		JButton button = new JButton(text);
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Segoe UI", Font.BOLD, 14));
		button.setBounds(x, y, width, height);
		button.setBackground(new Color(52, 73, 94));
		button.setBorderPainted(false);
		button.setFocusPainted(false);

		// Animations lors du survol de la souris
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// Changement de couleur
				button.setBackground(new Color(62, 83, 104));
				// Animation de mouvement
				button.setLocation(button.getX(), button.getY() - 2);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// Retour à la couleur initiale
				button.setBackground(new Color(52, 73, 94));
				// Animation de retour à la position initiale
				button.setLocation(button.getX(), button.getY() + 2);
			}
		});

		// Animation lors du clic
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// Changement de couleur
				button.setBackground(new Color(42, 63, 84));
				// Animation de zoom
				button.setSize(button.getWidth() + 4, button.getHeight() + 4);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// Retour à la couleur initiale
				button.setBackground(new Color(62, 83, 104));
				// Animation de retour à la taille initiale
				button.setSize(button.getWidth() - 4, button.getHeight() - 4);
			}
		});

		return button;
	}
}
