package src;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

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

public class Authentification extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField m_textField_ID;
	private JPasswordField m_JPassword_Password;
	private Map<String, String> User;
	private JLabel m_Label_ErreurID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Authentification dialog = new Authentification(null, false);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Authentification(Frame mainWindow, boolean modal) {
		super(mainWindow, modal);
		setUndecorated(true);
		User = new HashMap<String, String>();
		User.put("pogoman23", "salutcava");
		User.put("a", "a");
		
		setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setTitle("Connexion");
		setBounds(100, 100, 314, 148);
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
	

	private void verifyConnect()
	{
		String pw = String.valueOf(m_JPassword_Password.getPassword());
		if(User.containsKey(m_textField_ID.getText()))
		{
			if(User.get(m_textField_ID.getText()).equals(pw))
			{
				dispose();
			}
			else
				m_Label_ErreurID.setVisible(true);
		}
		else
			m_Label_ErreurID.setVisible(true);
	}
}
