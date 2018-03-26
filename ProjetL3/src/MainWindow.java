import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.Cursor;
import javax.swing.JTree;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
// Main
public class MainWindow 
{
	private JScrollPane m_SP;
	private JScrollPane m_SP_Tree;
	private JTree tree;
	private JFrame frame;
	private JTable m_Table_Frise;
	private DefaultMutableTreeNode root;
	private JScrollPane m_scrollPanel_Information;
	private JPanel m_Pannel_Information;
	private JLabel m_Label_Information;
	private JPopupMenu m_ContextMenu_AddJalon;
	private JMenuItem m_MenuItem_AddJalons;
	public static ArrayList<Projet> m_listeProjet;



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
		m_listeProjet = new ArrayList<Projet>();
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(50, 50, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JDesktopPane m_Pannel_ListeDesTaches = new JDesktopPane();
		m_Pannel_ListeDesTaches.setBackground(Color.LIGHT_GRAY);
		m_Pannel_ListeDesTaches.setBounds(0, 35, 229, 465);
		
		
		root = new DefaultMutableTreeNode("Jalons");
		tree = new JTree(root);
		tree.setShowsRootHandles(true);
		tree.setBounds(0, 74, 229, 391);
		
		m_SP_Tree = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		m_ContextMenu_AddJalon = new JPopupMenu();
		addPopup(tree, m_ContextMenu_AddJalon);
		
		
		m_MenuItem_AddJalons = new JMenuItem("Ajouter Jalon");
		m_MenuItem_AddJalons.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				addJalon();
			}
		});
		m_ContextMenu_AddJalon.add(m_MenuItem_AddJalons);
		frame.getContentPane().add(m_SP_Tree);
		m_SP_Tree.setBounds(0, 35, 229, 465);
		m_SP_Tree.setAutoscrolls(true);
		
		
		
		m_Label_Information = new JLabel("Informations");
		m_Label_Information.setBounds(10, 10, 100, 14);
		
		m_Pannel_Information = new JPanel();
		m_Pannel_Information.setBounds(0, 0, 1274, 171);
		m_Pannel_Information.setLayout(null);
		m_Pannel_Information.add(m_Label_Information);		
		m_scrollPanel_Information = new JScrollPane(m_Pannel_Information,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); //////
		frame.getContentPane().add(m_scrollPanel_Information);
		m_scrollPanel_Information.setBounds(0, 499, 1274, 171);
		m_scrollPanel_Information.setAutoscrolls(true);


		
		


			
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
				NouveauProjet m_NouveauProjet = new NouveauProjet(frame, true);
				m_NouveauProjet.setVisible(true);
				addProject();
				// Afficher fenêtre
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
				{null, null},
			},
			new String[] {
				"Projets", "Diagramme de classe - Date limite : 23/02/18"
			}
		));
		m_Table_Frise.getColumnModel().getColumn(0).setMinWidth(150);
		m_Table_Frise.getColumnModel().getColumn(1).setMinWidth(250);
		m_Table_Frise.setBounds(0, 0, 1049, 500);
		m_Table_Frise.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		m_Pannel_Frise.add(m_Table_Frise);

		m_SP = new JScrollPane(m_Table_Frise, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(m_SP);
		m_SP.setBounds(229, 0, 1045, 500);
		m_SP.setAutoscrolls(true);

		//
		JLabel m_Label_NomDuProjet = new JLabel("Name");
		m_Label_NomDuProjet.setBounds(0, 0, 46, 14);
		frame.getContentPane().add(m_Label_NomDuProjet);
		

	///////START///////////
		showAuth();
	}
	
	public void addProject()
	{
		DefaultTableModel model = (DefaultTableModel) m_Table_Frise.getModel();
		//Vector<String> test = new Vector<>();
		
		Projet newProject = getM_listeProjet().get(getM_listeProjet().size() - 1);
		
		String Eleves = "";
		
		for(Eleve e : newProject.getCollectionEleves())
		{
			Eleves += e.getPrenom() + " " + e.getNom() + ", ";
		}
		Eleves=Eleves.replaceAll(", $","");
		
		model.setValueAt(Eleves, getM_listeProjet().size() - 1, 0);
	}
	
	public void addJalon()
	{
		DefaultMutableTreeNode jalons = new DefaultMutableTreeNode("Diagramme");
		root.insert(jalons, root.getChildCount());
		DefaultTreeModel modelTree = (DefaultTreeModel)tree.getModel();
		modelTree.reload();
		
		DefaultTableModel model = (DefaultTableModel) m_Table_Frise.getModel();
		model.addColumn("Diagramme");
		
		/*
		
		for(int i = 0; i<10; i++)
		{
			DefaultMutableTreeNode jalons = new DefaultMutableTreeNode(new Random().nextInt(2000));
			root.insert(jalons, root.getChildCount());
		}
		DefaultTreeModel modelTree = (DefaultTreeModel)tree.getModel();
		modelTree.reload();
		
		*/
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}


		});
	}

	private void showAuth()
	{
		Authentification test = new Authentification(this.frame, false);
		test.setVisible(true);
	}
	
	
	public static ArrayList<Projet> getM_listeProjet() {
		return m_listeProjet;
	}

	public static void setM_listeProjet(ArrayList<Projet> m_listeProjet) {
		MainWindow.m_listeProjet = m_listeProjet;
	}
}
	
