import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class JalonWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField m_textField_Intitule;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JalonWindow dialog = new JalonWindow();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JalonWindow() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			m_textField_Intitule = new JTextField();
			m_textField_Intitule.setBounds(105, 48, 130, 26);
			contentPanel.add(m_textField_Intitule);
			m_textField_Intitule.setColumns(10);
		}
		
		JLabel m_Label_Intitule = new JLabel("Intitulé");
		m_Label_Intitule.setBounds(32, 53, 61, 16);
		contentPanel.add(m_Label_Intitule);
		
		
		 

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
