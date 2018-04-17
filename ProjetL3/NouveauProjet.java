

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class NouveauProjet extends JDialog {
	
	/**
	 * serial ID pour swing
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Pannel où vont s'afficher les elements de la fenetre
	 */
	private final JPanel contentPanel = new JPanel();
	
	/**
	 * champ de texte mots cles
	 */
	private JTextArea m_textArea_MotsCles;
	/**
	 * champ de texte nom du projet
	 */
	private JTextField m_textField_NomProjet;
	/**
	 * Collection d'enseignants
	 */
	private ArrayList<Enseignant> ListeProvisoireEns;
	/**
	 * Collection des eleves dans la BD
	 */
	private ArrayList<Eleve> ListeProvisoireEleves;
	/**
	 * Collection d'eleves
	 */
	private ArrayList<Eleve> ListeEleves;
	/**
	 * Champ pour le nom de l'eleve
	 */
	private JComboBox m_comboBox_AddEleve;
	/**
	 * Combobox pour choisir un tuteur
	 */
	@SuppressWarnings("rawtypes")
	private JComboBox m_ComboBox_Tuteur;
	/**
	 * Combobox liste des eleves selectionnes
	 */
	@SuppressWarnings("rawtypes")
	private JComboBox m_ComboBox_ListeEleves;



	/** Creation de la fenetre 
	 * @param parent fenetre parent
	 * @param modal bloque la fenetre ou non
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NouveauProjet(Frame parent, boolean modal) 
	{
		super(parent, modal);
		
		ListeEleves = new ArrayList<Eleve>();
		
		EnseignantController Ens = new EnseignantController();
		ListeProvisoireEns = Ens.getListOfEnseignant();
		ListeProvisoireEleves = new EleveController().getListOfEleve();
		
		setBounds(100, 100, 380, 493);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel m_Label_NomProjet = new JLabel("Nom du Projet");
		m_Label_NomProjet.setBounds(22, 40, 102, 16);
		contentPanel.add(m_Label_NomProjet);
		
		m_textField_NomProjet = new JTextField();
		m_textField_NomProjet.setBounds(154, 35, 196, 26);
		contentPanel.add(m_textField_NomProjet);
		m_textField_NomProjet.setColumns(10);
		
		JLabel m_Label_Tuteur = new JLabel("Tuteur");
		m_Label_Tuteur.setBounds(22, 118, 61, 16);
		contentPanel.add(m_Label_Tuteur);
		
		m_ComboBox_Tuteur = new JComboBox();
		m_ComboBox_Tuteur.setModel(new DefaultComboBoxModel(getEnseignantNomPrenom(ListeProvisoireEns).toArray()));
		m_ComboBox_Tuteur.setBounds(154, 114, 196, 27);
		contentPanel.add(m_ComboBox_Tuteur);
		
		JLabel m_Label_Eleves = new JLabel("Eleves");
		m_Label_Eleves.setBounds(22, 186, 61, 16);
		contentPanel.add(m_Label_Eleves);
		
		m_comboBox_AddEleve = new JComboBox();
		m_comboBox_AddEleve.setModel(new DefaultComboBoxModel(getEleveNomPrenom(ListeProvisoireEleves).toArray()));
		m_comboBox_AddEleve.setBounds(154, 181, 130, 26);
		contentPanel.add(m_comboBox_AddEleve);
		
		/*
		JLabel m_lbl_Erreur = new JLabel("Erreur : Veuillez respectecter le format : Prénom Nom");
		m_lbl_Erreur.setForeground(Color.RED);
		m_lbl_Erreur.setBounds(22, 153, 352, 16);
		m_lbl_Erreur.setVisible(false);
		contentPanel.add(m_lbl_Erreur);
		*/
		
		JButton m_Button_AddEleve = new JButton("OK");
		m_Button_AddEleve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String[] content = m_comboBox_AddEleve.getSelectedItem().toString().split(" ");
				int ID =  Integer.parseInt(m_comboBox_AddEleve.getSelectedItem().toString().split(" | ")[m_comboBox_AddEleve.getSelectedItem().toString().split(" | ").length - 1]);
				try 
				{
					String prenom = content[0];
					String nom = content[1];
					
					EleveController ec = new EleveController();
					ArrayList<Eleve> searchEleve = ec.getListOfEleve();
					for(Eleve ele : searchEleve)
					{
						if(ID == ele.getId())
							ListeEleves.add(ele);
					}
					
					
					m_ComboBox_ListeEleves.addItem(prenom +" "+ nom);
					m_ComboBox_ListeEleves.setSelectedItem(m_ComboBox_ListeEleves.getItemAt(m_ComboBox_ListeEleves.getItemCount() - 1));
				} 
				catch (Exception e2) 
				{
					//m_lbl_Erreur.setVisible(true);
				}

			}
		});
		m_Button_AddEleve.setBounds(302, 181, 72, 29);
		contentPanel.add(m_Button_AddEleve);
		
		m_ComboBox_ListeEleves = new JComboBox();
		m_ComboBox_ListeEleves.setBounds(154, 226, 196, 27);
		contentPanel.add(m_ComboBox_ListeEleves);
		
		JLabel m_Label_MotsCles = new JLabel("Mots clés");
		m_Label_MotsCles.setBounds(22, 275, 61, 16);
		contentPanel.add(m_Label_MotsCles);
		
		m_textArea_MotsCles = new JTextArea();
		m_textArea_MotsCles.setBounds(22, 303, 326, 123);
		contentPanel.add(m_textArea_MotsCles);
		

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						AddProjet();
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	
	/**
	 * Ajout projet dans la memoire de la fenetre principale et dans la base de donnees
	 */
	public void AddProjet()
	{



		Projet newProject = new Projet(new Random().nextInt(10000), m_textField_NomProjet.getText(), Calendar.getInstance().get(Calendar.YEAR), ListeProvisoireEns.get(m_ComboBox_Tuteur.getSelectedIndex()), ListeEleves,m_textArea_MotsCles.getText());
		
		for(Eleve e : ListeEleves)
		{
			newProject.addEleve(e);
		}
		String[] mcArray = m_textArea_MotsCles.getText().split(",");
		
		
		List<String> mc = Arrays.asList(mcArray);
		for(String data : mc)
		{
			newProject.getMotsCles().add(data);
		}
		
		newProject.setMotCleRaw(m_textArea_MotsCles.getText());

		for(Jalon j : MainWindow.getM_listeJalon())
		{
			newProject.addJalon(j.getIntitule(), j.getDateFin(), Authentification.getClasse());
		}
		
		
		MainWindow.getM_listeProjet().add(newProject);
		ProjetController p = new ProjetController();
		p.addProjetToDB(newProject.getSujet(), Authentification.getClasse(), newProject.getEnseignant(), newProject.getCollectionEleves(), newProject.getMotCleRaw());
	}
	
	
	/**
	 * Permet d'avoir une chaine de caracteres avec le prenom et le nom
	 * @param Ens l'objet enseignant
	 * @return chaine de caracteres avec le nom et prenom
	 */
	public ArrayList<String> getEnseignantNomPrenom(ArrayList<Enseignant> Ens)
	{
		ArrayList<String> result = new ArrayList<String>();
		for(Enseignant i : Ens)
		{
			result.add(i.getPrenom() + " " + i.getNom());
		}
		return result;
		
	}
	
	public ArrayList<String> getEleveNomPrenom(ArrayList<Eleve> listeEleves)
	{
		ArrayList<String> result = new ArrayList<String>();
		for(Eleve i : listeEleves)
		{
			result.add(i.getPrenom() + " " + i.getNom() + " | " + i.getId());
		}
		return result;
		
	}

}
