import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSlider;
import javax.swing.JLabel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProgressionWindow extends JFrame {

	private JPanel contentPane;
	private Projet projet;
	private Jalon jalon;

	
	private JLabel m_lbl_Progression;
	private JSlider m_slider_Progression;
	/**
	 * Create the frame.
	 */
	public ProgressionWindow(Projet p, Jalon j) 
	{
		
		projet = p;
		jalon = j;
		
		setTitle("Progression");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 147);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		m_slider_Progression = new JSlider();
		m_slider_Progression.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				ChangerValeur();
			}
		});
		m_slider_Progression.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) 
			{
				
			}
		});
		m_slider_Progression.setBounds(210, 80, 234, 29);
		contentPane.add(m_slider_Progression);
		
		JLabel m_lbl_NomDuProjet = new JLabel("Nom du Projet : " + p.getSujet());
		m_lbl_NomDuProjet.setBounds(102, 27, 316, 16);
		contentPane.add(m_lbl_NomDuProjet);
		
		JLabel m_lbl_NomJalon = new JLabel("Nom du Jalon : "+ j.getIntitule());
		m_lbl_NomJalon.setBounds(102, 54, 316, 16);
		contentPane.add(m_lbl_NomJalon);
		
		m_lbl_Progression = new JLabel("Progression : " + j.getProgression());
		m_lbl_Progression.setBounds(6, 82, 192, 16);
		contentPane.add(m_lbl_Progression);
		
		m_slider_Progression.setValue(j.getProgression());
		
	}
	
	
	public void ChangerValeur()
	{
		JalonController j = new JalonController();
		j.addProgression(jalon.getId(), m_slider_Progression.getValue());
		jalon.setProgression(m_slider_Progression.getValue());
		m_lbl_Progression.setText("Progression : " + jalon.getProgression());
	}
	
}
