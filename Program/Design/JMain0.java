package Design;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JMain0 extends JFrame implements ActionListener {

    public JMain0() {
        super("Bienvenue dans l'application de gestion d'hôtel !");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JButton adminButton = new JButton("Administrateur");
        adminButton.addActionListener(this);
        panel.add(adminButton);

        JButton clientButton = new JButton("Client");
        clientButton.addActionListener(this);
        panel.add(clientButton);

        JButton quitButton = new JButton("Quitter");
        quitButton.addActionListener(this);
        panel.add(quitButton);

        this.add(panel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Administrateur")) {
            this.dispose();
            new JAuthentication0(1);
        } else if (e.getActionCommand().equals("Client")) {
            this.dispose();
            new JAuthentication0(2);
        } else if (e.getActionCommand().equals("Quitter")) {
            System.exit(0);
        }
    }

}