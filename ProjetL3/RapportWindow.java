

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import javafx.stage.FileChooser;

import java.awt.event.ActionEvent;

public class RapportWindow extends JFrame {

	private JPanel contentPane;
	private JTextField m_textField_Path;
	
	JFileChooser fcSauvegarde = new JFileChooser();
	String repertoire;
	private JButton m_btn_Browse;

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
		

		
		JButton m_btn_Ok = new JButton("OK");
		m_btn_Ok.setBounds(286, 51, 117, 29);
		contentPane.add(m_btn_Ok);
		
		m_btn_Browse = new JButton("Browse");
		m_btn_Browse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("Parcours !");
				String repertoireSreduit;

					//fcSauvegarde.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int rVal = fcSauvegarde.showOpenDialog(null);
					
					 if (rVal == JFileChooser.APPROVE_OPTION) {
				          repertoire =fcSauvegarde.getSelectedFile().toString();
				          
				          //int end = repertoireS.lastIndexOf(' ', 10);
				          
						if (repertoire.length()>30)
				  			repertoire = repertoire.substring(0, 15) + "..." + repertoire.substring(repertoire.length()-15, repertoire.length()) ;
				          else 
				        	  repertoireSreduit = repertoire;
				          
				          System.out.println(repertoire);
				          //labelRepertoire.setText(repertoireSreduit);
					 }
			}
		});
		m_btn_Browse.setBounds(286, 18, 117, 29);
		contentPane.add(m_btn_Browse);

		
	}
	

}
