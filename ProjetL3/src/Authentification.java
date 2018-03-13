import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Authentification extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField m_textField_ID;
	private JPasswordField m_JPassword_Password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Authentification dialog = new Authentification();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Authentification() {
		setTitle("Connexion");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel m_Label_ID = new JLabel("Identifiant");
		m_Label_ID.setBounds(40, 61, 103, 16);
		contentPanel.add(m_Label_ID);
		
		m_textField_ID = new JTextField();
		m_textField_ID.setBounds(187, 56, 165, 26);
		contentPanel.add(m_textField_ID);
		m_textField_ID.setColumns(10);
		
		JLabel m_label_Password = new JLabel("Mot de passe");
		m_label_Password.setBounds(40, 137, 83, 16);
		contentPanel.add(m_label_Password);
		
		m_JPassword_Password = new JPasswordField();
		m_JPassword_Password.setBounds(187, 132, 165, 26);
		contentPanel.add(m_JPassword_Password);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}