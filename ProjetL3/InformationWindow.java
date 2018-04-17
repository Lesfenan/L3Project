import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;

public class InformationWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public InformationWindow(Projet p) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 233);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNomDuProjet = new JLabel("Nom du projet : " + p.getSujet());
		lblNomDuProjet.setBounds(41, 20, 354, 16);
		contentPane.add(lblNomDuProjet);
		
		JLabel lblTuteur = new JLabel("Tuteur : " + p.getEnseignant());
		lblTuteur.setBounds(41, 51, 401, 16);
		contentPane.add(lblTuteur);
		
		JLabel lblAnne = new JLabel("Année : " + p.getAnnee());
		lblAnne.setBounds(41, 79, 368, 16);
		contentPane.add(lblAnne);
		
		JList m_list_listeEleves = new JList();
		m_list_listeEleves.setBounds(132, 107, 286, 95);
		contentPane.add(m_list_listeEleves);
		
		JLabel lblListeEleves = new JLabel("Liste Eleves");
		lblListeEleves.setBounds(41, 107, 79, 16);
		contentPane.add(lblListeEleves);
		
		DefaultListModel<String> nomPrenomEleves = new DefaultListModel<String>();
		for(Eleve data : p.getCollectionEleves())
		{
			nomPrenomEleves.addElement(data.getPrenom() + " " + data.getNom());
		}
		
		m_list_listeEleves.setModel(nomPrenomEleves);
	}
}
