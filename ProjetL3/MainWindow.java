

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.Cursor;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
	 * Objet permettant d'afficher une scroll bar
	 */
	private JScrollPane m_scrollPanel_InfoJalon;
	/**
	 * panneau d'information
	 */
	private JPanel m_Pannel_Information;
	/**
	 * Objet permettant d'afficher une scroll bar
	 */
	private JPanel m_Pannel_InfoJalon;
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
	public static ArrayList<Projet> m_listeProjetRaw;
	/**
	 * liste de jalons
	 */
	public static ArrayList<Jalon> m_listeJalon;
	/**
	 * Objet permettant de bloquer la fenetre
	 */
	public static Object lock = new Object();
	/**
	 * Nom du projet
	 */
	private JLabel m_lbl_NomProjet;
	/**
	 * Nom enseignant
	 */
	private JLabel m_lbl_Enseignant;
	private JLabel m_lbl_ListeEleves;
	private JList<String> m_list_listeEleves;
	private JList<String> m_list_listeMotCles;
	private JLabel m_lbl_Annee;
	private JLabel m_lbl_MotCls;
	private JLabel m_lbl_NomDuJalon;
	private JLabel m_lbl_DateDeFin;
	private JLabel m_lbl_AvancementDuProjet;
	private JLabel m_lbl_NoteDuProjet;
	private JTextField m_textField_Description;
	
	private int selectedJalon;
	private JMenuItem m_mntm_RendreRapport;
	private JMenuItem m_mntm_RechercheProjet;
	private JMenuItem mntmEditerProgression;
	private JLabel m_Label_NomDuProjet;
	private JMenuItem m_MenuItem_Nouveau;


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
		selectedJalon = -1;
		
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
		
		m_Pannel_InfoJalon = new JPanel();
		m_Pannel_InfoJalon.setBounds(0, 0, 1274, 171);
		m_Pannel_InfoJalon.setLayout(null);
		
		JLabel m_Label_InfoJalon = new JLabel("Informations");
		m_Label_InfoJalon.setFont(new Font("Lao Sangam MN", Font.BOLD, 13));
		m_Label_InfoJalon.setBounds(10, 10, 100, 14);
		m_Pannel_InfoJalon.add(m_Label_InfoJalon);
		
		m_scrollPanel_InfoJalon = new JScrollPane(m_Pannel_InfoJalon,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		m_lbl_NomDuJalon = new JLabel("Nom du jalon :");
		m_lbl_NomDuJalon.setBounds(6, 54, 300, 16);
		m_Pannel_InfoJalon.add(m_lbl_NomDuJalon);
		
		m_lbl_DateDeFin = new JLabel("Date de fin : ");
		m_lbl_DateDeFin.setBounds(6, 113, 465, 16);
		m_Pannel_InfoJalon.add(m_lbl_DateDeFin);
		
		m_lbl_AvancementDuProjet = new JLabel("Avancement du projet : ");
		m_lbl_AvancementDuProjet.setBounds(598, 9, 507, 16);
		m_Pannel_InfoJalon.add(m_lbl_AvancementDuProjet);
		
		m_lbl_NoteDuProjet = new JLabel("Note du projet : ");
		m_lbl_NoteDuProjet.setBounds(598, 54, 232, 16);
		m_Pannel_InfoJalon.add(m_lbl_NoteDuProjet);
		
		m_textField_Description = new JTextField();
		m_textField_Description.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			{
				ChangeDescription();
			}
		});

		m_textField_Description.setBounds(710, 108, 206, 26);
		m_Pannel_InfoJalon.add(m_textField_Description);
		m_textField_Description.setColumns(10);
		
		JLabel m_lbl_Description = new JLabel("Description :");
		m_lbl_Description.setBounds(598, 113, 100, 16);
		m_Pannel_InfoJalon.add(m_lbl_Description);
		frame.getContentPane().add(m_scrollPanel_InfoJalon);
		m_scrollPanel_InfoJalon.setBounds(0, 499, 1274, 171);
		m_scrollPanel_InfoJalon.setAutoscrolls(true);
		m_scrollPanel_InfoJalon.setVisible(false);

		
		JMenuBar m_MenuBar_Main = new JMenuBar();
		frame.setJMenuBar(m_MenuBar_Main);
		
		JMenu m_Menu_Fichier = new JMenu("Fichier");
		m_MenuBar_Main.add(m_Menu_Fichier);
		
		JMenu m_Menu_CreateProject = new JMenu("Creer un projet");
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
		
		m_mntm_RechercheProjet = new JMenuItem("Recherche Projet");
		m_mntm_RechercheProjet.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) 
			{
				RechercheProjet();
			}
		});
		m_Menu_Fichier.add(m_mntm_RechercheProjet);
		
		JMenu m_Menu_Edition = new JMenu("Edition");
		m_MenuBar_Main.add(m_Menu_Edition);
		
		JMenuItem m_MenuItem_Parametre = new JMenuItem("Logout");
		m_MenuItem_Parametre.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) 
			{
				MainWindow login = new MainWindow();
				login.frame.setVisible(true);
				frame.dispose();
			}
		});
		m_Menu_Edition.add(m_MenuItem_Parametre);
		
		JDesktopPane m_Pannel_Frise = new JDesktopPane();
		m_Pannel_Frise.setBackground(Color.GREEN);
		m_Pannel_Frise.setBounds(229, 0, 1035, 500);

		
		
		
		m_Table_Frise = new JTable();
		m_Table_Frise.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) 
			{
				AfficherInformationTable();
			}
		});
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
				"Projets"
			}
		));
		m_Table_Frise.getColumnModel().getColumn(0).setMinWidth(150);
		m_Table_Frise.setBounds(0, 0, 1049, 500);
		m_Table_Frise.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	
		for (int c = 0; c < m_Table_Frise.getColumnCount(); c++)
		{
		    Class<?> col_class = m_Table_Frise.getColumnClass(c);
		    m_Table_Frise.setDefaultEditor(col_class, null);        // remove editor
		}
		


		m_SP = new JScrollPane(m_Table_Frise, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(m_SP);
		m_SP.setBounds(229, 0, 1045, 500);
		m_SP.setAutoscrolls(true);

		//
		m_Label_NomDuProjet = new JLabel("");
		m_Label_NomDuProjet.setBounds(0, 0, 229, 14);
		frame.getContentPane().add(m_Label_NomDuProjet);
		
		
		
		m_Label_Information = new JLabel("Informations");
		m_Label_Information.setFont(new Font("Lao Sangam MN", Font.BOLD, 13));
		m_Label_Information.setBounds(10, 10, 100, 14);
		
		m_Pannel_Information = new JPanel();
		m_Pannel_Information.setBounds(0, 0, 1274, 171);
		m_Pannel_Information.setLayout(null);
		m_Pannel_Information.add(m_Label_Information);		
		m_scrollPanel_Information = new JScrollPane(m_Pannel_Information,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); //////
		
		m_lbl_Enseignant = new JLabel("Enseignant : ");
		m_lbl_Enseignant.setBounds(10, 58, 276, 16);
		m_Pannel_Information.add(m_lbl_Enseignant);
		
		m_lbl_ListeEleves = new JLabel("Liste Eleves :");
		m_lbl_ListeEleves.setBounds(512, 9, 122, 16);
		m_Pannel_Information.add(m_lbl_ListeEleves);
		

		
		frame.getContentPane().add(m_scrollPanel_Information);
		m_scrollPanel_Information.setBounds(0, 499, 1274, 171);
		m_scrollPanel_Information.setAutoscrolls(true);
		
				
				m_list_listeEleves = new JList<String>();
				m_list_listeEleves.setBounds(600, 9, 238, 137);
				m_Pannel_Information.add(m_list_listeEleves);
				
				JScrollPane m_SP_listeEleves = new JScrollPane(m_list_listeEleves,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				m_SP_listeEleves.setBounds(600, 9, 238, 137);
				m_Pannel_Information.add(m_SP_listeEleves);
				m_SP_listeEleves.setAutoscrolls(true);
				
				m_list_listeMotCles = new JList<String>();
				m_list_listeMotCles.setBounds(950, 9, 238, 137);
				
				JScrollPane m_SP_listeMotCles = new JScrollPane(m_list_listeMotCles,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				m_SP_listeMotCles.setBounds(950, 9, 238, 137);
				m_Pannel_Information.add(m_SP_listeMotCles);
				m_SP_listeMotCles.setAutoscrolls(true);
				
				m_lbl_Annee = new JLabel("Annee : ");
				m_lbl_Annee.setBounds(10, 117, 372, 16);
				m_Pannel_Information.add(m_lbl_Annee);
				
				m_lbl_MotCls = new JLabel("Mot clés : ");
				m_lbl_MotCls.setBounds(886, 9, 100, 16);
				m_Pannel_Information.add(m_lbl_MotCls);
				
				m_lbl_NomProjet = new JLabel("Nom du projet : ");
				m_lbl_NomProjet.setBounds(128, 9, 282, 16);
				m_Pannel_Information.add(m_lbl_NomProjet);
				
				JMenuItem m_MenuItem_InfoJalon = new JMenuItem("Voir informations");
				m_MenuItem_InfoJalon.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) 
					{
						AfficherInformationTree();
					}
				});
				m_ContextMenu_AddJalon.add(m_MenuItem_InfoJalon);
				
				JMenuItem m_menuItem_Noter = new JMenuItem("Noter");
				m_menuItem_Noter.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) 
					{
						ChangeNote();
					}
				});
				m_ContextMenu_AddJalon.add(m_menuItem_Noter);
				
				m_mntm_RendreRapport = new JMenuItem("Rendre rapport");
				m_mntm_RendreRapport.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e)
					{
						AjouterRapport();
					}
				});
				m_ContextMenu_AddJalon.add(m_mntm_RendreRapport);
				
				mntmEditerProgression = new JMenuItem("Editer progression");
				mntmEditerProgression.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) 
					{
						ChangerProgression();
					}
				});
				m_ContextMenu_AddJalon.add(mntmEditerProgression);
	///////START///////////
		showAuth();
		ProjetController p = new ProjetController();
		if(!Authentification.getIsEleve())
		{
			m_mntm_RendreRapport.setEnabled(false);
			m_listeProjet = new ArrayList<Projet>();
			for(Projet data : p.getListOfProjet(Authentification.getClasse()))
			{
				m_listeProjet.add(data);
				addProject();
			}
			
		}
		else
		{
			m_menuItem_Noter.setEnabled(false);
			m_mntm_RechercheProjet.setEnabled(false);
			m_listeProjet = new ArrayList<Projet>();
			m_MenuItem_AddJalons.setEnabled(false);
			m_MenuItem_Nouveau.setEnabled(false);
			for(Projet data : m_listeProjetRaw)
			{
				m_listeProjet.add(data);
				addProject();
			}
		}
		m_listeJalon = new ArrayList<Jalon>();
		
		try 
		{
			for(Jalon data : m_listeProjet.get(0).getCollectionJalons())
			{
				m_listeJalon.add(data);
				addJalon();
			}
			
			int row = 0;
			int column = 1;
			DefaultTableModel model = (DefaultTableModel) m_Table_Frise.getModel();
			JalonController jc = new JalonController();
			for(Projet data : p.getListOfProjet(Authentification.getClasse()))
			{
				for(Jalon j : m_listeProjet.get(row).getCollectionJalons())
				{
					model.setValueAt(j.getDescription(), row, column);
					column++;
				}
				row++;
				column = 1;
				resizeColumnWidth(m_Table_Frise);
			}
		} catch (Exception e) 
		{

		}



	
		
	}
	
	/**
	 * Ajout un projet dans le tableur
	 */
	public void addProject()
	{
		DefaultTableModel model = (DefaultTableModel) m_Table_Frise.getModel();
		//Vector<String> test = new Vector<>();
		
		Projet newProject = getM_listeProjet().get(m_listeProjet.size() - 1);
		 Vector row = new Vector();
		 
		String Eleves = "";

		for(Eleve e : newProject.getCollectionEleves())
		{
			Eleves += e.getPrenom() + " " + e.getNom() + ", ";
		}
		Eleves=Eleves.replaceAll(", $","");
		row.add("Sujet : " + newProject.getSujet() + " - " + Eleves);
		model.addRow(row);
		//model.setValueAt(row, getM_listeProjet().size() - 1, 0);
		
		for (int c = 0; c < m_Table_Frise.getColumnCount(); c++)
		{
		    Class<?> col_class = m_Table_Frise.getColumnClass(c);
		    m_Table_Frise.setDefaultEditor(col_class, null);        // remove editor
		}
		
		resizeColumnWidth(m_Table_Frise);
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
		resizeColumnWidth(m_Table_Frise);
	}
	
	/**
	 * Permet d'ajouter un menu contextuel a un element
	 * @param component element sur lequel on veut avoir le menu contextuel
	 * @param popup menu contextuel que l'on veut ajouter
	 */
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {				
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
		Authentification login = new Authentification(this.frame, false);
		login.setVisible(true);
		Personne connectedPerson = login.getConnectedPerson();
		
		if (connectedPerson instanceof Eleve)
		{
			
			 m_listeProjetRaw = new ProjetController().getProjetFromEleve(connectedPerson.getId());
		}
		
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
	
	public void AfficherInformation()
	{
		m_Table_Frise.getSelectedRow();
		//System.out.println(m_Table_Frise.getSelectedRow());
		//System.out.println(m_Table_Frise.getSelectedColumn());
		TreePath[] paths = tree.getSelectionPaths();
		//System.out.println(paths[0].toString());
	}
	
	public void AfficherInformationTree()
	{
		
		
		m_scrollPanel_Information.setVisible(false);
		m_scrollPanel_InfoJalon.setVisible(true);
		
		   DefaultMutableTreeNode selectedNode = 
			       (DefaultMutableTreeNode)tree.getLastSelectedPathComponent(); 
		   selectedJalon = root.getIndex(selectedNode);
		   
			if(m_Table_Frise.getSelectedRow() < 0 || m_Table_Frise.getSelectedRow() > m_listeProjet.size() - 1 || root.getIndex(selectedNode) < 0 || root.getIndex(selectedNode) > m_listeJalon.size() - 1)
			{
				return;
			}
		   
		Jalon selected = m_listeJalon.get(root.getIndex(selectedNode)); 
		m_lbl_NomDuJalon.setText("Nom du jalon : " + selected.getIntitule());
		m_lbl_DateDeFin.setText("Date de fin : " + selected.getDateFin().toString());
		int progression = m_listeProjet.get(m_Table_Frise.getSelectedRow()).getCollectionJalons().get(root.getIndex(selectedNode)).getProgression();
		int note = m_listeProjet.get(m_Table_Frise.getSelectedRow()).getCollectionJalons().get(root.getIndex(selectedNode)).getNotation();
		m_lbl_AvancementDuProjet.setText("Avancement du projet : " +  progression);
		if(note >= 0)
		{
			m_lbl_NoteDuProjet.setText("Note du projet : " + note);
		}
		else
			m_lbl_NoteDuProjet.setText("Pas de note entrée");
		
		DefaultTableModel model = (DefaultTableModel) m_Table_Frise.getModel();
		m_textField_Description.setText("");
		try {
			m_textField_Description.setText(model.getValueAt(m_Table_Frise.getSelectedRow(), selectedJalon + 1).toString());
		} catch (Exception e) {
			// TODO: handle exception
		}


	}
	
	public void AfficherInformationTable()
	{
		m_scrollPanel_Information.setVisible(true);
		m_scrollPanel_InfoJalon.setVisible(false);
		
		
		if(m_Table_Frise.getSelectedRow() < 0 || m_Table_Frise.getSelectedRow() > m_listeProjet.size() - 1)
		{
			return;
		}
		
		Projet selected = m_listeProjet.get(m_Table_Frise.getSelectedRow());
		
		m_Label_NomDuProjet.setText(selected.getSujet());
		m_lbl_NomProjet.setText("Nom du projet : " + selected.getSujet());
		m_lbl_Enseignant.setText("Enseignant : " + selected.getEnseignant().getPrenom() + " " + selected.getEnseignant().getNom());
		m_lbl_Annee.setText("Annee : " + selected.getAnnee());
		
		DefaultListModel<String> nomPrenomEleves = new DefaultListModel<String>();
		for(Eleve data : selected.getCollectionEleves())
		{
			nomPrenomEleves.addElement(data.getPrenom() + " " + data.getNom());
		}
		
		m_list_listeEleves.setModel(nomPrenomEleves);
		
		DefaultListModel<String> mc = new DefaultListModel<String>();
		for(String data : selected.getMotsCles())
		{
			mc.addElement(data);
		}
		m_list_listeMotCles.setModel(mc);
	}
	
	public void ChangeDescription()
	{
		   DefaultMutableTreeNode selectedNode = 
			       (DefaultMutableTreeNode)tree.getLastSelectedPathComponent(); 
		if(m_Table_Frise.getSelectedRow() < 0 || m_Table_Frise.getSelectedRow() > m_listeProjet.size() - 1 || root.getIndex(selectedNode) < 0 || root.getIndex(selectedNode) > m_listeJalon.size() - 1)
		{
			return;
		}

			DefaultTableModel model = (DefaultTableModel) m_Table_Frise.getModel();
			model.setValueAt(m_textField_Description.getText(), m_Table_Frise.getSelectedRow(), selectedJalon + 1);

			int id = m_listeProjet.get(m_Table_Frise.getSelectedRow()).getCollectionJalons().get(root.getIndex(selectedNode)).getId();
			
			JalonController j = new JalonController();
			j.addDescription(id, m_textField_Description.getText());
	}
	
	public void ChangeNote()
	{
		 DefaultMutableTreeNode selectedNode = 
			       (DefaultMutableTreeNode)tree.getLastSelectedPathComponent(); 
		if(m_Table_Frise.getSelectedRow() < 0 || m_Table_Frise.getSelectedRow() > m_listeProjet.size() - 1 || root.getIndex(selectedNode) < 0 || root.getIndex(selectedNode) > m_listeJalon.size() - 1)
		{
			return;
		}
		
		NotationWindow note = new NotationWindow(m_listeJalon.get(root.getIndex(selectedNode)), m_listeProjet.get(m_Table_Frise.getSelectedRow()), root.getIndex(selectedNode));	
		note.setVisible(true);
		
		int id = m_listeProjet.get(m_Table_Frise.getSelectedRow()).getCollectionJalons().get(root.getIndex(selectedNode)).getId();
		JalonController j = new JalonController();
		j.addNotation(id, m_listeProjet.get(m_Table_Frise.getSelectedRow()).getCollectionJalons().get(root.getIndex(selectedNode)).getNotation());
	}
	
	public void AjouterRapport()
	{
		 DefaultMutableTreeNode selectedNode = 
			       (DefaultMutableTreeNode)tree.getLastSelectedPathComponent(); 

		   selectedJalon = root.getIndex(selectedNode);
		   
			if(m_Table_Frise.getSelectedRow() < 0 || m_Table_Frise.getSelectedRow() > m_listeProjet.size() - 1 || root.getIndex(selectedNode) < 0 || root.getIndex(selectedNode) > m_listeJalon.size() - 1)
			{
				return;
			}
		 
		if(m_Table_Frise.getSelectedRow() < 0 || m_Table_Frise.getSelectedRow() > m_listeProjet.size() - 1 || root.getIndex(selectedNode) < 0 || root.getIndex(selectedNode) > m_listeJalon.size() - 1)
		{
			return;
		}
		RapportWindow r = new RapportWindow(root.getIndex(selectedNode), m_Table_Frise.getSelectedRow(), Authentification.getClasse());
		
		r.setVisible(true);
	}
	
	public void RechercheProjet()
	{
		RechercheProjetWindow rp = new RechercheProjetWindow();
		rp.setVisible(true);
	}
	
	public void ChangerProgression()
	{
		
		 DefaultMutableTreeNode selectedNode = 
			       (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
		 
		   selectedJalon = root.getIndex(selectedNode);
		   
			if(m_Table_Frise.getSelectedRow() < 0 || m_Table_Frise.getSelectedRow() > m_listeProjet.size() - 1 || root.getIndex(selectedNode) < 0 || root.getIndex(selectedNode) > m_listeJalon.size() - 1)
			{
				return;
			}
		 
		 Jalon j = m_listeProjet.get(m_Table_Frise.getSelectedRow()).getCollectionJalons().get(root.getIndex(selectedNode));
		 Projet p = m_listeProjet.get(m_Table_Frise.getSelectedRow());
		ProgressionWindow prog = new ProgressionWindow(p,j);
		prog.setVisible(true);
	}
	
	public void resizeColumnWidth(JTable table) {
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 15; // Min width
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        if(width > 300)
	            width=300;
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	}
	
}
	
