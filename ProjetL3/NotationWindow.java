

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.ScrollPane;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class NotationWindow extends JFrame {

	private JPanel contentPane;
	private JTextField m_textField_Note;
	private JLabel m_lbl_NomDuJalon;
	private JLabel m_lbl_ProjetSlectionn;
	private Jalon jalon;
	private Projet projet;
	private JList<String> m_list_listeEleves;
	private int index;

	/**
	 * Create the frame.
	 */
	public NotationWindow(Jalon J, Projet p, int i) {
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) 
			{
				toFront();
				requestFocus();
			}
		});
		
		jalon = J;
		projet = p;
		index = i;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane m_scrollPanel_listeEleves = new JScrollPane(m_list_listeEleves,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		getContentPane().add(m_scrollPanel_listeEleves);
		m_scrollPanel_listeEleves.setBounds(145, 133, 299, 95);
		m_scrollPanel_listeEleves.setAutoscrolls(true);
		m_scrollPanel_listeEleves.setVisible(false);
		
		m_lbl_NomDuJalon = new JLabel("Nom du jalon : ");
		m_lbl_NomDuJalon.setBounds(26, 45, 418, 16);
		contentPane.add(m_lbl_NomDuJalon);
		
		m_lbl_ProjetSlectionn = new JLabel("Projet sélectionné : ");
		m_lbl_ProjetSlectionn.setBounds(26, 89, 418, 16);
		contentPane.add(m_lbl_ProjetSlectionn);
		
		JLabel m_lbl_listeEleves = new JLabel("Liste des élèves :");
		m_lbl_listeEleves.setBounds(26, 133, 122, 16);
		contentPane.add(m_lbl_listeEleves);
		
		JLabel m_lbl_Note = new JLabel("Note : ");
		m_lbl_Note.setBounds(26, 239, 61, 16);
		contentPane.add(m_lbl_Note);
		
		m_textField_Note = new JTextField();
		m_textField_Note.setBounds(71, 234, 51, 26);
		contentPane.add(m_textField_Note);
		m_textField_Note.setColumns(10);
		
		JLabel m_label_twenty = new JLabel("/20");
		m_label_twenty.setBounds(126, 239, 61, 16);
		contentPane.add(m_label_twenty);
		
		m_list_listeEleves = new JList();
		m_list_listeEleves.setBounds(145, 133, 299, 95);
		contentPane.add(m_list_listeEleves);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				VerifierNote();
			}
		});
		btnOk.setBounds(327, 234, 117, 29);
		contentPane.add(btnOk);
		
		ajouterNote();
	}
	
	public void ajouterNote()
	{
		m_lbl_NomDuJalon.setText("Nom du jalon : " + jalon.getIntitule());
		m_lbl_ProjetSlectionn.setText("Projet sélectionné : " + projet.getSujet());
		
		DefaultListModel<String> nomPrenomEleves = new DefaultListModel<String>();
		for(Eleve data : projet.getCollectionEleves())
		{
			nomPrenomEleves.addElement(data.getPrenom() + " " + data.getNom());
		}
		
		m_list_listeEleves.setModel(nomPrenomEleves);
	}
	
	public void VerifierNote()
	{
		String noteString = m_textField_Note.getText();
		int note = -1;
		if(!noteString.equals(""))
		{
			try 
			{
				note = Integer.parseInt(noteString);
			} catch (Exception e) {
				return;
			}
		}
		
		if(note <= 20 || note >= 0)
		{
			projet.getCollectionJalons().get(index).setNotation(note);
			JalonController jc = new JalonController();
			jc.addNotation(projet.getCollectionJalons().get(index).getId(), note);
			dispose();
		}
	}
}
