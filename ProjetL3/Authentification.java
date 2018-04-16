

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class Authentification extends JDialog 
{

	/**
	 * serial ID pour swing
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Pannel où vont s'afficher les elements de la fenetre
	 */
	private final JPanel contentPanel = new JPanel();
	/**
	 * champ de texte de l'ID
	 */
	private JTextField m_textField_ID;
	/**
	 * champ de texte pour le mot de passe
	 */
	private JPasswordField m_JPassword_Password;
	/**
	 * Collection d'utilisateurs
	 */
	private Map<String, String> User;
	/**
	 * label qui s'affiche quand la connexion a echoue
	 */
	private JLabel m_Label_ErreurID;
	
	private JRadioButton m_Radio_Enseignant;
	
	private JRadioButton m_Radio_Eleve;
	
	private  ButtonGroup group ;



	/**
	 * Creation de la fenetre
	 * @param mainWindow la fenetre parent
	 * @param modal permet de bloquer ou non la fenetre parent
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Authentification(Frame mainWindow, boolean modal) {
		
		super(mainWindow, modal);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		User = new HashMap<String, String>();
		User.put("pogoman23", "salutcava");
		User.put("a", "a");
		group = new ButtonGroup();
		setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setTitle("Connexion");
		setBounds(100, 100, 401, 238);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel m_Label_ID = new JLabel("Identifiant");
		m_Label_ID.setBounds(20, 22, 103, 16);
		contentPanel.add(m_Label_ID);
		
		m_textField_ID = new JTextField();
		m_textField_ID.setBounds(135, 17, 165, 26);
		contentPanel.add(m_textField_ID);
		m_textField_ID.setColumns(10);
		
		JLabel m_label_Password = new JLabel("Mot de passe");
		m_label_Password.setBounds(20, 50, 83, 16);
		contentPanel.add(m_label_Password);
		
		m_JPassword_Password = new JPasswordField();
		m_JPassword_Password.setBounds(135, 55, 165, 26);
		contentPanel.add(m_JPassword_Password);
		
		m_Label_ErreurID = new JLabel("Identifiants incorrects");
		m_Label_ErreurID.setForeground(Color.RED);
		m_Label_ErreurID.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		m_Label_ErreurID.setBounds(86, 87, 159, 16);
		m_Label_ErreurID.setVisible(false);
		contentPanel.add(m_Label_ErreurID);
		
		JComboBox m_comboBox_Classe = new JComboBox();
		m_comboBox_Classe.setBounds(20, 121, 83, 27);
		contentPanel.add(m_comboBox_Classe);
		m_comboBox_Classe.addItem("L3");
		m_comboBox_Classe.addItem("M1");
		m_comboBox_Classe.addItem("M2");
		
		m_Radio_Eleve = new JRadioButton("Eleve");
		m_Radio_Eleve.setSelected(true);
		m_Radio_Eleve.setBounds(292, 108, 64, 23);
		m_Radio_Eleve.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				m_comboBox_Classe.setVisible(true);
			}
		});
		contentPanel.add(m_Radio_Eleve);
		group.add(m_Radio_Eleve);
		
		m_Radio_Enseignant = new JRadioButton("Enseignant");
		m_Radio_Enseignant.setBounds(292, 143, 103, 23);
		m_Radio_Enseignant.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				m_comboBox_Classe.setVisible(false);
			}
		});
		group.add(m_Radio_Enseignant);
		contentPanel.add(m_Radio_Enseignant);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						verifyConnect();
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
						
						System.exit(0);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	

	
	/**
	 * Permet de voir si l'ID et le mot de passe sont valides et permettent de se connecter à l'interface de gestion de projet
	 */
	private void verifyConnect()
	{
		Personne connectedPerson;
		String pw = String.valueOf(m_JPassword_Password.getPassword());
		String login = String.valueOf(m_textField_ID.getText());
		
		if (m_Radio_Eleve.isSelected()){
			connectedPerson = new LoginController().login(login, pw, "Eleve");
		}
		else {
			connectedPerson = new LoginController().login(login, pw, "Enseignant");
		}
		
		
		if (connectedPerson.getId() != 0){
			dispose();
		}
		else {
			m_Label_ErreurID.setVisible(true);
		}
				
		
	}
}
