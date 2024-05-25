package View;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controller.CtrlMain;
import java.awt.Color;

import Model.Chambre;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.Timer;
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
		Chambre.initchambres();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */

	public JMain() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        contentPane.setLayout(null);

		// Couleur de fond
        Color backgroundColor = new Color(245, 245, 245);
        contentPane.setBackground(backgroundColor);

		// Titre avec animation
        JLabel titleLabel = new JLabel("Bienvenue dans notre Hôtel ");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setForeground(Color.white);
        titleLabel.setBounds(71, 50, 639, 90);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(titleLabel);

		/// Animation du titre avec effet de fondu, de zoom et de défilement
        Timer titleTimer = new Timer(50, new ActionListener() {
            int offset = 0;
            boolean increasing = true;
            float scale = 1.0f;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (increasing) {
                    offset += 1;
                    scale += 0.005f;
                    if (offset >= 10) {
                        increasing = false;
                    }
                } else {
                    offset -= 1;
                    scale -= 0.005f;
                    if (offset <= -10) {
                        increasing = true;
                    }
                }
                titleLabel.setBounds(150 - offset, 50, 500, 50);
                float alpha = 1.0f - Math.abs((float) offset / 10); // Calcul de l'opacité en fonction de l'offset
                titleLabel.setForeground(new Color(255, 255, 255, Math.round(alpha * 255))); // Appliquer l'opacité et la couleur blanche au titre
                titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 28 + (int) (scale * 5))); // Appliquer l'effet de zoom au titre
            }
        });
        titleTimer.start();

		
        // Image de fond avec effet de flou
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(new ImageIcon(getClass().getResource("welcome.jpg")));
        backgroundLabel.setBounds(0, 0, 800, 600);
        contentPane.add(backgroundLabel);

		// Panel pour les boutons avec effet de flou
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setBounds(250, 300, 300, 250);
		buttonPanel.setLayout(null);
		backgroundLabel.add(buttonPanel); // Ajout du buttonPanel sur backgroundLabel


		JButton btnAdministrateur = Design.createButton("Administrateur",50, 0, 200, 50);
		CtrlMain.actionAdministrateur(btnAdministrateur, this);
		buttonPanel.add(btnAdministrateur);


		JButton btnClient =  Design.createButton("Client",50, 80, 200, 50);
		CtrlMain.actionClient(btnClient, this);
		buttonPanel.add(btnClient);
		
		JButton btnExit =  Design.createButton("Quiter",50, 160, 200, 50);
		CtrlMain.actionExit(btnExit);
		buttonPanel.add(btnExit);
	}

}
