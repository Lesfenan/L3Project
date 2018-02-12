import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class Authentification extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Authentification frame = new Authentification();
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
	public Authentification() {
		setTitle("Authentification");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(173, 72, 189, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblUser = new JLabel("Identifiant");
		lblUser.setBounds(81, 75, 66, 14);
		contentPane.add(lblUser);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(173, 125, 189, 20);
		contentPane.add(passwordField);
		
		JLabel lblPassword = new JLabel("Mot de passe");
		lblPassword.setBounds(81, 128, 82, 14);
		contentPane.add(lblPassword);
	}
}
