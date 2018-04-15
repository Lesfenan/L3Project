package lib;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

public class RapportWindow extends JFrame {

	private JPanel contentPane;
	private JTextField m_textField_Path;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RapportWindow frame = new RapportWindow();
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
	public RapportWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 409, 116);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		m_textField_Path = new JTextField();
		m_textField_Path.setEditable(false);
		m_textField_Path.setBounds(6, 18, 262, 26);
		contentPane.add(m_textField_Path);
		m_textField_Path.setColumns(10);
		
		JButton m_btn_Parcourir = new JButton("Parcourir");
		m_btn_Parcourir.setBounds(286, 18, 117, 29);
		contentPane.add(m_btn_Parcourir);
		
		JButton m_btn_Ok = new JButton("OK");
		m_btn_Ok.setBounds(286, 51, 117, 29);
		contentPane.add(m_btn_Ok);
	}

}
