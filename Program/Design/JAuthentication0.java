package Design;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Main.*;
import Administration.*;
import Client.*;


public class JAuthentication0 extends JFrame implements ActionListener{

    private JTextField usernameField;
    private JPasswordField passwordField;
    private int roleChoice;

    public JAuthentication0(int roleChoice){
        super("Authentification");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.roleChoice = roleChoice;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        if(roleChoice == 1){
            JLabel usernameLabel = new JLabel("Nom d'utilisateur : ");
            panel.add(usernameLabel);
            usernameField = new JTextField();
            panel.add(usernameField);

            JLabel passwordLabel = new JLabel("Mot de passe : ");
            panel.add(passwordLabel);
            passwordField = new JPasswordField();
            panel.add(passwordField);
        }else if(roleChoice == 2){
            JLabel nom = new JLabel("nom : ");
            panel.add(nom);
            usernameField = new JTextField();
            panel.add(usernameField);

            JLabel prenom = new JLabel("prenom : ");
            panel.add(prenom);
            usernameField = new JTextField();
            panel.add(usernameField);
        }

        JButton submitButton = new JButton("Valider");
        submitButton.addActionListener(this);
        panel.add(submitButton);

        JButton cancelButton = new JButton("Annuler");
        cancelButton.addActionListener(this);
        panel.add(cancelButton);

        this.add(panel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("Valider")){
            if (roleChoice == 1){
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if(username.equals("admin") && password.equals("admin")){
                    this.dispose();
                    new JAdmin0();
                }else{
                    JOptionPane.showMessageDialog(this, "Nom d'utilisateur ou mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }else if(roleChoice == 2){
                String nom = usernameField.getText();
                String prenom = usernameField.getText();

                if(!Administrateur.getverifclient(nom, prenom)){
                    Client client = new Client(nom, prenom , Administrateur.getclientsmaxid()+1);
                    Administrateur.addClient(client);
                    new JClient0(client);
                }else{
                    Client client = new Client(nom, prenom , Administrateur.getclientid(nom, prenom));
                    new JClient0(client);
                }
            }
        }else if(e.getActionCommand().equals("Annuler")){
            this.dispose();
            new JMain0();
        }
    }

}
