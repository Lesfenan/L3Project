

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class NouveauProjet extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NouveauProjet frame = new NouveauProjet();
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
	public NouveauProjet() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 274, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNomDuProjet = new JLabel("Nom du projet");
		lblNomDuProjet.setBounds(10, 34, 79, 14);
		contentPane.add(lblNomDuProjet);
		
		JLabel lblNewLabel = new JLabel("Tuteur");
		lblNewLabel.setBounds(10, 113, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblElves = new JLabel("El\u00E8ves");
		lblElves.setBounds(10, 160, 46, 14);
		contentPane.add(lblElves);
		
		JLabel lblMotsCls = new JLabel("Mots cl\u00E9s");
		lblMotsCls.setBounds(10, 240, 79, 14);
		contentPane.add(lblMotsCls);
		
		textField = new JTextField();
		textField.setBounds(98, 31, 150, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(98, 110, 150, 20);
		contentPane.add(comboBox);
		
		textField_1 = new JTextField();
		textField_1.setBounds(98, 157, 150, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(98, 188, 150, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(98, 219, 150, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 261, 238, 54);
		contentPane.add(textArea);
		
		JButton btnCrer = new JButton("Cr\u00E9er");
		btnCrer.setBounds(159, 350, 89, 23);
		contentPane.add(btnCrer);
	}
}
