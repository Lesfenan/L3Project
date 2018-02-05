import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Font;
import java.awt.Canvas;
import java.awt.SystemColor;
import java.awt.Cursor;
import javax.swing.JList;
import javax.swing.AbstractListModel;
// Main
public class MainWindow 
{
	private JScrollPane m_SP;

	private JFrame frame;
	private JTable m_Table_Frise;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(50, 50, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JDesktopPane m_Pannel_ListeDesTaches = new JDesktopPane();
		m_Pannel_ListeDesTaches.setBackground(Color.LIGHT_GRAY);
		m_Pannel_ListeDesTaches.setBounds(0, 35, 229, 465);
		frame.getContentPane().add(m_Pannel_ListeDesTaches);
		
		JList list = new JList();
		list.setBounds(0, 0, 229, 465);
		m_Pannel_ListeDesTaches.add(list);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"UML", "Developpement", "Documentation"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		JDesktopPane m_Pannel_Information = new JDesktopPane();
		m_Pannel_Information.setBackground(Color.BLUE);
		m_Pannel_Information.setBounds(0, 510, 1274, 160);
		frame.getContentPane().add(m_Pannel_Information);
		
		JLabel m_Label_Information = new JLabel("Informations");
		m_Label_Information.setBounds(10, 11, 86, 14);
		m_Pannel_Information.add(m_Label_Information);
		
		JMenuBar m_MenuBar_Main = new JMenuBar();
		frame.setJMenuBar(m_MenuBar_Main);
		
		JMenu m_Menu_Fichier = new JMenu("Fichier");
		m_MenuBar_Main.add(m_Menu_Fichier);
		
		JMenu m_Menu_CreateProject = new JMenu("Cr\u00E9er un projet");
		m_Menu_Fichier.add(m_Menu_CreateProject);
		
		JMenuItem m_MenuItem_Nouveau = new JMenuItem("Nouveau");
		m_MenuItem_Nouveau.addMouseListener(new MouseAdapter() 
		{
			public void mousePressed(MouseEvent e) 
			{
				addJalon();
			}
		});
		m_Menu_CreateProject.add(m_MenuItem_Nouveau);
		
		JMenu m_Menu_Edition = new JMenu("Edition");
		m_MenuBar_Main.add(m_Menu_Edition);
		
		JMenuItem m_MenuItem_Parametre = new JMenuItem("Param\u00E8tre");
		m_Menu_Edition.add(m_MenuItem_Parametre);
		
		JDesktopPane m_Pannel_Frise = new JDesktopPane();
		m_Pannel_Frise.setBackground(Color.GREEN);
		m_Pannel_Frise.setBounds(229, 0, 1035, 500);
		
		
		m_Table_Frise = new JTable();
		m_Table_Frise.setRowHeight(38);
		m_Table_Frise.setFillsViewportHeight(true);
		m_Table_Frise.setDoubleBuffered(true);
		m_Table_Frise.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		m_Table_Frise.setBackground(SystemColor.menu);
		m_Table_Frise.setSurrendersFocusOnKeystroke(true);
		m_Table_Frise.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Jalons", "Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Ao\u00FBt", "Septembre", "Octobre"
			}
		));
		m_Table_Frise.getColumnModel().getColumn(1).setPreferredWidth(250);
		m_Table_Frise.getColumnModel().getColumn(1).setMinWidth(250);
		m_Table_Frise.getColumnModel().getColumn(2).setPreferredWidth(250);
		m_Table_Frise.getColumnModel().getColumn(2).setMinWidth(250);
		m_Table_Frise.getColumnModel().getColumn(3).setPreferredWidth(250);
		m_Table_Frise.getColumnModel().getColumn(3).setMinWidth(250);
		m_Table_Frise.getColumnModel().getColumn(4).setPreferredWidth(250);
		m_Table_Frise.getColumnModel().getColumn(4).setMinWidth(250);
		m_Table_Frise.getColumnModel().getColumn(5).setPreferredWidth(250);
		m_Table_Frise.getColumnModel().getColumn(5).setMinWidth(250);
		m_Table_Frise.getColumnModel().getColumn(6).setPreferredWidth(250);
		m_Table_Frise.getColumnModel().getColumn(6).setMinWidth(250);
		m_Table_Frise.getColumnModel().getColumn(7).setPreferredWidth(250);
		m_Table_Frise.getColumnModel().getColumn(7).setMinWidth(250);
		m_Table_Frise.getColumnModel().getColumn(8).setPreferredWidth(250);
		m_Table_Frise.getColumnModel().getColumn(8).setMinWidth(250);
		m_Table_Frise.getColumnModel().getColumn(9).setPreferredWidth(250);
		m_Table_Frise.getColumnModel().getColumn(9).setMinWidth(250);
		m_Table_Frise.getColumnModel().getColumn(10).setPreferredWidth(250);
		m_Table_Frise.getColumnModel().getColumn(10).setMinWidth(250);
		m_Table_Frise.setBounds(0, 0, 1049, 500);
		m_Table_Frise.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		m_Pannel_Frise.add(m_Table_Frise);

		m_SP = new JScrollPane(m_Table_Frise, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(m_SP);
		m_SP.setBounds(229, 0, 1045, 500);
		m_SP.setAutoscrolls(true);

		
		JLabel m_Label_NomDuProjet = new JLabel("Name");
		m_Label_NomDuProjet.setBounds(0, 0, 46, 14);
		frame.getContentPane().add(m_Label_NomDuProjet);
		

	}
	public void addJalon()
	{
		DefaultTableModel model = (DefaultTableModel) m_Table_Frise.getModel();
		Vector<String> test = new Vector<>();
		test.add("test");
		model.addRow(test);
	}
}
	
