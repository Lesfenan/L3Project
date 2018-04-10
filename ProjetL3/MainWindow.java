

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
/**
 * @author marwanoriginals
 *Fenetre principale
 */
public class MainWindow 
{
	/**
	 * Scroll Bar principale
	 */
	private JScrollPane m_SP;
	/**
	 * Scroll bar de l'arbre
	 */
	private JScrollPane m_SP_Tree;
	/**
	 * Arbre des jalons
	 */
	private JTree tree;
	/**
	 * Fenetre
	 */
	private JFrame frame;
	/**
	 * Tableur
	 */
	private JTable m_Table_Frise;
	/**
	 * racine de l'arbre jalon
	 */
	private DefaultMutableTreeNode root;
	/**
	 * Objet permettant d'afficher une scroll bar
	 */
	private JScrollPane m_scrollPanel_Information;
	/**
	 * panneau d'information
	 */
	private JPanel m_Pannel_Information;
	/**
	 * label d'information
	 */
	private JLabel m_Label_Information;
	/**
	 * Menu contextuelle pour gerer un jalon
	 */
	private JPopupMenu m_ContextMenu_AddJalon;
	/**
	 * Objet menu pour ajouter un jalon
	 */
	private JMenuItem m_MenuItem_AddJalons;
	/**
	 * liste de projets
	 */
	public static ArrayList<Projet> m_listeProjet;
	/**
	 * liste de jalons
	 */
	public static ArrayList<Jalon> m_listeJalon;
	/**
	 * Objet permettant de bloquer la fenetre
	 */
	public static Object lock = new Object();


	/**
	 * Launch the application.
	 * @param args Arguments main
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
	 * Initialise les elements de la fenetre
	 */
	private void initialize() 
	{
		m_listeProjet = new ArrayList<Projet>();
		m_listeJalon = new ArrayList<Jalon>();
		
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
  				JalonWindow nouveauJalon = new JalonWindow();
				nouveauJalon.setVisible(true);
				nouveauJalon.setAlwaysOnTop(true);
				
			    Thread t = new Thread() {
			        public void run() {
						synchronized(lock) {
			                while (nouveauJalon.isVisible())
			                    try {
			                        lock.wait();
			                    } catch (InterruptedException e) {
			                        e.printStackTrace();
			                    }
			                addJalon();
			            }
			        }
			    };
			    t.start();
			
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
				m_NouveauProjet.setModal(true);
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
	
	/**
	 * Ajout un projet dans le tableur
	 */
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
	
	/**
	 * Ajoute un jalon dans la treelist
	 */
	public void addJalon()
	{
		DefaultMutableTreeNode jalons = new DefaultMutableTreeNode(getM_listeJalon().get(getM_listeJalon().size() - 1).getIntitule());
		root.insert(jalons, root.getChildCount());
		DefaultTreeModel modelTree = (DefaultTreeModel)tree.getModel();
		modelTree.reload();
		
		DefaultTableModel model = (DefaultTableModel) m_Table_Frise.getModel();
		model.addColumn(getM_listeJalon().get(getM_listeJalon().size() - 1).getIntitule());
		
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
	
	/**
	 * Permet d'ajouter un menu contextuelle a un element
	 * @param component element sur lequelle on veut avoir le menu contextuelle
	 * @param popup menu contextuelle que l'on veut ajouter
	 */
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

	/**
	 * Affiche la fenetre d'authentification des le lancement du programme
	 */
	private void showAuth()
	{
		Authentification test = new Authentification(this.frame, false);
		test.setVisible(true);
	}
	
	
	/**Get dans la liste des projets
	 * @return liste des projets
	 */
	public static ArrayList<Projet> getM_listeProjet() {
		return m_listeProjet;
	}

	/**Set dans la liste des projets
	 * @param m_listeProjet liste des projets
	 */
	public static void setM_listeProjet(ArrayList<Projet> m_listeProjet) {
		MainWindow.m_listeProjet = m_listeProjet;
	}
	
	/**Get dans la liste des jalons
	 * @return liste des jalons
	 */
	public static ArrayList<Jalon> getM_listeJalon() {
		return m_listeJalon;
	}

	/**Set dans la liste des jalons
	 * @param m_listeJalon liste des jalons
	 */
	public static void setM_listeJalon(ArrayList<Jalon> m_listeJalon) {
		MainWindow.m_listeJalon = m_listeJalon;
	}
}
	
