package View;

import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.CtrlReservation;
import java.awt.Font;

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

        DefaultTableModel model = new DefaultTableModel(
                new Object[][] {},
                new String[] { "ID", "Client", "Chambre", "Date Début", "Date Fin", "État" } // Noms des colonnes
        );

        CtrlReservation.actionInitReservation(model);

        table = new JTable(model);

        // Créer le JScrollPane avec le JTable
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 760, 495); // Ajustez les dimensions et la position selon vos besoins

        // Ajouter le JScrollPane au JPanel principal
        contentPane.add(scrollPane);
        CtrlReservation.actionSelectReservation(model, table);

        JButton retour = new JButton("RETOUR");
        retour.setBounds(670, 520, 100, 30);
        contentPane.add(retour);
        CtrlReservation.actionRetour(retour, this);

        JButton btnAcceptReserv = new JButton("ACCEPTER");
        btnAcceptReserv.setFont(new Font("Tahoma", Font.PLAIN, 11));
      
        CtrlReservation.actionAcceptReservation(btnAcceptReserv, model, table);

        btnAcceptReserv.setBounds(780, 40, 99, 23);
        contentPane.add(btnAcceptReserv);

        JButton btndeclineReserv = new JButton("DECLINER");
        btndeclineReserv.setFont(new Font("Tahoma", Font.PLAIN, 11));
        CtrlReservation.actionDeclineReserv(btndeclineReserv, model, table);

        btndeclineReserv.setBounds(780, 74, 99, 23);
        contentPane.add(btndeclineReserv);
    }
}