import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Authentification extends JFrame {


	private static final long serialVersionUID = 1L;
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
		setResizable(false);
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
		
		JButton btnConnection = new JButton("Connexion");
		btnConnection.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				test();
			}
		});
		btnConnection.setBounds(256, 180, 106, 23);
		contentPane.add(btnConnection);
	}
	
	public void test()
	{
		System.out.println(passwordField.getPassword());
	}
}
