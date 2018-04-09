

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;

public class JalonWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField m_textField_Intitule;


	private DateTextField m_Date_Calendrier;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JalonWindow frame = new JalonWindow();
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
	public JalonWindow() 
	{
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
			}
			public void windowLostFocus(WindowEvent e) 
			{
				toFront();
				requestFocus();
			}
		});
		setResizable(false);
		
		setType(Type.UTILITY);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 257, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(100, 100, 282, 244));
		contentPane.setLayout(null);
		
		
		m_Date_Calendrier = new DateTextField();
		m_Date_Calendrier.setBounds(104, 97, 106, 33);
		contentPane.add(m_Date_Calendrier);

		
		setContentPane(contentPane);
		
		JLabel lblDateLimite = new JLabel("Date limite");
		lblDateLimite.setBounds(6, 105, 86, 16);
		contentPane.add(lblDateLimite);
		
		m_textField_Intitule = new JTextField();
		m_textField_Intitule.setBounds(104, 44, 106, 26);
		contentPane.add(m_textField_Intitule);
		m_textField_Intitule.setColumns(10);
		
		JLabel lblIntitul = new JLabel("Intitulé");
		lblIntitul.setBounds(6, 49, 61, 16);
		contentPane.add(lblIntitul);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				addJalon();
				windowClosing(null);
			}
		});
		btnOk.setBounds(182, 171, 75, 29);
		contentPane.add(btnOk);
	}
	
	public void addJalon()
	{
		Jalon nouveauJalon = new Jalon(m_textField_Intitule.getText(), m_Date_Calendrier.getDate());
		MainWindow.getM_listeJalon().add(nouveauJalon);
		System.out.println(MainWindow.getM_listeJalon().size());
	}
	
    public void windowClosing(WindowEvent arg0) {
        synchronized (MainWindow.lock) {
            setVisible(false);
            MainWindow.lock.notify();
        }
    }

}
