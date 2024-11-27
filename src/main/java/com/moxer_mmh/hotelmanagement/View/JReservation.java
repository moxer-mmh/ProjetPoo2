package com.moxer_mmh.hotelmanagement.View;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.moxer_mmh.hotelmanagement.Controller.*;
import java.awt.Font;
import java.awt.Color;

public class JReservation extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    static JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JReservation frame = new JReservation();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public JReservation() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        this.setTitle("THE WHITE SWaN HOTEL");
        ImageIcon icon = new ImageIcon(
                "C:\\Users\\TRETEC\\OneDrive\\Bureau\\ProjetPoo2-main (1)\\ProjetPoo2-main\\Program\\View\\logo.png");
        setIconImage(icon.getImage());

        JLabel background = new JLabel();
        background.setIcon(new ImageIcon(getClass().getResource("welcome.jpg")));
        background.setBounds(0, 0, 898, 565);
        contentPane.add(background);

        DefaultTableModel model = new DefaultTableModel(
                new Object[][] {},
                new String[] { "ID", "Client", "Chambre", "Date Début", "Date Fin", "État" } // Noms des colonnes
        );

        CtrlReservation.actionInitReservation(model);

        table = new JTable(model);
        table.setForeground(Color.WHITE);
        table.setBackground(new Color(0, 53, 113));

        // Créer le JScrollPane avec le JTable
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 760, 495); // Ajustez les dimensions et la position selon vos besoins

        // Ajouter le JScrollPane au JPanel principal
        background.add(scrollPane);
        CtrlReservation.actionSelectReservation(model, table);

        JButton retour = Design.createButton("RETOUR", 670, 520, 100, 30);
        background.add(retour);
        CtrlReservation.actionRetour(retour, this);

        JButton btnAcceptReserv = Design.createButton("ACCEPTER", 780, 40, 99, 23);
        btnAcceptReserv.setFont(new Font("Tahoma", Font.PLAIN, 11));

        CtrlReservation.actionAcceptReservation(btnAcceptReserv, model, table);

        background.add(btnAcceptReserv);

        JButton btndeclineReserv = Design.createButton("DECLINER", 780, 74, 99, 23);
        btndeclineReserv.setFont(new Font("Tahoma", Font.PLAIN, 11));
        CtrlReservation.actionDeclineReserv(btndeclineReserv, model, table);

        background.add(btndeclineReserv);
    }
}