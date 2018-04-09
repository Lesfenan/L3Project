

import java.awt.BorderLayout;
import java.awt.EventQueue;
import com.mysql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RechercheProjetWindow extends JFrame {

	/**
	 * Objet où vont s'afficher les elements graphiques
	 */
	private JPanel contentPane;
	/**
	 * Champ de texte du nom recherche
	 */
	private JTextField m_textField_NomProjet;
	/**
	 * Combobox des resultats
	 */
	private JComboBox m_comboBox_Result;
	/**
	 * Objet recherche pour rechercher dans la BD
	 */
	private Recherche SearchDB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RechercheProjetWindow frame = new RechercheProjetWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RechercheProjetWindow() {
		SearchDB = new Recherche();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel m_label_NomProjet = new JLabel("Nom du projet");
		m_label_NomProjet.setBounds(37, 57, 103, 16);
		contentPane.add(m_label_NomProjet);
		
		m_textField_NomProjet = new JTextField();
		m_textField_NomProjet.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			{
				RechercheNom(m_textField_NomProjet.getText());
			}
		});

		m_textField_NomProjet.setBounds(202, 52, 130, 26);
		contentPane.add(m_textField_NomProjet);
		m_textField_NomProjet.setColumns(10);
		
		JLabel m_label_Result = new JLabel("Résultat recherche");
		m_label_Result.setBounds(37, 119, 130, 16);
		contentPane.add(m_label_Result);
		
		JButton m_Button_Restaurer = new JButton("Restaurer");
		m_Button_Restaurer.setBounds(327, 243, 117, 29);
		contentPane.add(m_Button_Restaurer);
		
		JButton m_Button_VoirInformations = new JButton("Voir informations");
		m_Button_VoirInformations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Recherche test = new Recherche();
				ArrayList<String> res = new ArrayList<String>();
				res = test.rechercher("c");
				System.out.println(res.size());
				
			}
		});
		m_Button_VoirInformations.setBounds(170, 243, 145, 29);
		contentPane.add(m_Button_VoirInformations);
		
		m_comboBox_Result = new JComboBox();
		m_comboBox_Result.setBounds(202, 115, 130, 27);
		contentPane.add(m_comboBox_Result);
	}
	
	/**
	 * Permet de rechercher une chaine de caracteres parmi les noms des etudiants
	 * @param req chaine de caracteres a rechercher
	 */
	public void RechercheNom(String req)
	{
		System.out.println(SearchDB.rechercher(req).size() + " " + req);
		m_comboBox_Result.setModel(new DefaultComboBoxModel(SearchDB.rechercher(req).toArray()));
	}
}
