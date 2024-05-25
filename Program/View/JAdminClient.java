package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



	import javax.swing.JButton;
	import javax.swing.JScrollPane;
	import javax.swing.JTable;
	import javax.swing.table.DefaultTableModel;

import Controller.CtrlClient;
import Controller.CtrlReservation;
import javax.swing.ListSelectionModel;

	public class JAdminClient extends JFrame {

	    private static final long serialVersionUID = 1L;
	    private JPanel contentPane;
	    static JTable table;

	    public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                    JAdminClient frame = new JAdminClient();
	                    frame.setVisible(true);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }

	    public JAdminClient() {
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 634, 493);
	        contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        setContentPane(contentPane);
	        contentPane.setLayout(null);

	        DefaultTableModel model = new DefaultTableModel(
	                new Object[][] {},
	                new String[] {"Id","Nom" ,"Prenom"} // Noms des colonnes
	        );

	        CtrlClient.actionInitClient(model);


	        table = new JTable(model);
	        table.setEnabled(false);
	        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        
	        // Créer le JScrollPane avec le JTable
	        JScrollPane scrollPane = new JScrollPane(table);
	        scrollPane.setBounds(10, 10, 545, 360); // Ajustez les dimensions et la position selon vos besoins

	        // Ajouter le JScrollPane au JPanel principal
	        contentPane.add(scrollPane);
	        JButton retour = new JButton("RETOUR");
	        retour.setBounds(455, 384, 100, 30);
	        contentPane.add(retour);
	        CtrlClient.actionRetour(retour, this);
	    }
	}

