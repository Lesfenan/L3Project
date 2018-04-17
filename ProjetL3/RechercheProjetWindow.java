

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class RechercheProjetWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Objet où vont s'afficher les elements graphiques
	 */
	private JPanel contentPane;
	/**
	 * Champ de texte du nom recherche
	 */
	private JTextField m_textField_NomProjet;
	/**
	 * liste	 des resultats
	 */
	private JList<String> m_Jlist_Result;
	/**
	 * Objet recherche pour rechercher dans la BD
	 */
	private Recherche SearchDB;
	

	
	
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
		
		
		m_Jlist_Result = new JList<String>();
		m_Jlist_Result.setBounds(170, 119, 274, 153);
		contentPane.add(m_Jlist_Result);
		
		JScrollPane m_SP_Tree = new JScrollPane(m_Jlist_Result, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		getContentPane().add(m_SP_Tree);
		m_SP_Tree.setBounds(170, 119, 274, 153);
		m_SP_Tree.setAutoscrolls(true);
		
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
		
		JButton m_Button_VoirInformations = new JButton("Voir informations");
		m_Button_VoirInformations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String idString = m_Jlist_Result.getSelectedValue().toString().split(" - ")[2];
				int id = Integer.parseInt(idString);
				
				for(Projet data : MainWindow.getM_listeProjet())
				{
					if(id == data.getId())
					{
						InformationWindow info = new InformationWindow(data);
						info.setVisible(true);
					}
				}
				
			}
		});
		m_Button_VoirInformations.setBounds(0, 243, 145, 29);
		contentPane.add(m_Button_VoirInformations);
		

	}
	
	/**
	 * Permet de rechercher une chaine de caracteres parmi les noms des etudiants
	 * @param req chaine de caracteres a rechercher
	 */
	public void RechercheNom(String req)
	{
		ArrayList<String> raw = new ArrayList<String>();
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		
		for(String ele : SearchDB.rechercherProjetMotCle(req))
		{
			raw.add(ele);
		}
		
		for(String ele : SearchDB.rechercherProjetMotCle(req))
		{
			raw.add(ele);
		}
		
		
		ArrayList<String> result = removeDuplicates(raw);
		for(String ele : result)
		{
			listModel.addElement(ele);
		}
		m_Jlist_Result.setModel(listModel);
	}
	
    private ArrayList<String> removeDuplicates(ArrayList<String> list) {
        ArrayList<String> r = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        for (String item : list) {
            if (!set.contains(item)) {
                r.add(item);
                set.add(item);
            }
        }
        return r;
    }
}
