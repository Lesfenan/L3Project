import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.util.ArrayList;

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
import java.awt.event.ActionEvent;

public class NouveauProjet extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextArea m_textArea_MotsCles;
	private JTextField m_textField_NomProjet;
	private ArrayList<Enseignant> ListeProvisoireEns;
	private ArrayList<Eleve> ListeEleves;
	private JTextField m_textField_AddEleve;
	@SuppressWarnings("rawtypes")
	private JComboBox m_ComboBox_Tuteur;
	@SuppressWarnings("rawtypes")
	private JComboBox m_ComboBox_ListeEleves;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NouveauProjet dialog = new NouveauProjet(null, false);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NouveauProjet(Frame parent, boolean modal) 
	{
		super(parent, modal);
		
		ListeEleves = new ArrayList<Eleve>();
		
		ListeProvisoireEns = new ArrayList<Enseignant>();
		ListeProvisoireEns.add(new Enseignant("Jean", "Dupont"));
		ListeProvisoireEns.add(new Enseignant("Judith", "Benzakki"));
		ListeProvisoireEns.add(new Enseignant("Fred", "Vasseur"));
		ListeProvisoireEns.add(new Enseignant("Jeanne", "Bertoux"));
		
		
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
		
		m_textField_AddEleve = new JTextField();
		m_textField_AddEleve.setBounds(154, 181, 130, 26);
		contentPanel.add(m_textField_AddEleve);
		m_textField_AddEleve.setColumns(10);
		
		JButton m_Button_AddEleve = new JButton("OK");
		m_Button_AddEleve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String[] content = m_textField_AddEleve.getText().split(" ");
				String prenom = content[0];
				String nom = content[1];
				ListeEleves.add(new Eleve(prenom, nom));
				m_ComboBox_ListeEleves.addItem(prenom +" "+ nom);
			}
		});
		m_Button_AddEleve.setBounds(302, 181, 48, 29);
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

	public void AddProjet()
	{
		Projet newProject = new Projet(m_textField_NomProjet.getText(), ListeProvisoireEns.get(m_ComboBox_Tuteur.getSelectedIndex()));
		
		for(Eleve e : ListeEleves)
		{
			System.out.println(e.getPrenom());
			newProject.addEleve(e);
		}
		
		newProject.addMotCle(m_textArea_MotsCles.getText());
		
		
		
		MainWindow.getM_listeProjet().add(newProject);
	}
	
	public ArrayList<String> getEnseignantNomPrenom(ArrayList<Enseignant> Ens)
	{
		ArrayList<String> result = new ArrayList<String>();
		for(Enseignant i : Ens)
		{
			result.add(i.getPrenom() + " " + i.getNom());
		}
		return result;
		
	}

}
