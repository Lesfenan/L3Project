

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
import java.util.Random;
import java.awt.event.WindowEvent;

public class JalonWindow extends JFrame {

	/**
	 * serial ID pour swing
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Pannel où vont s'afficher les elements de la fenetre
	 */
	private JPanel contentPane;
	
	/**
	 * champ de texte de l'intitule
	 */
	private JTextField m_textField_Intitule;


	/**
	 * Element permettant d'afficher un calendrier
	 */
	private DateTextField m_Date_Calendrier;
	


	/**
	 * Creation de la fenetre
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
	
	
	/**
	 * Ajout d'un jalon dans la memoire de la fenetre principale en fonction des champs
	 */
	public void addJalon()
	{
		Jalon nouveauJalon = new Jalon(new Random().nextInt(10000), m_textField_Intitule.getText(), m_Date_Calendrier.getDate(), 0, -1, Authentification.getClasse());
		MainWindow.getM_listeJalon().add(nouveauJalon);
		
		for(Projet p : MainWindow.getM_listeProjet())
		{
			p.addJalon(m_textField_Intitule.getText(), m_Date_Calendrier.getDate(), Authentification.getClasse());
		}
	}
	
    /**
     * permet de reveiller la fenetre principale et de fermer cette fenetre
     * @param arg0 argument evenement
     */
    public void windowClosing(WindowEvent arg0) {
        synchronized (MainWindow.lock) {
            setVisible(false);
            MainWindow.lock.notify();
        }
    }

}
