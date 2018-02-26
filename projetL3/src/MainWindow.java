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
import java.util.Enumeration;
import java.util.Random;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
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
import javax.swing.JTree;
import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;
// Main
public class MainWindow 
{
	private JScrollPane m_SP;
	private JScrollPane m_SP_Tree;
	private JTree tree;
	private JFrame frame;
	private JTable m_Table_Frise;
	private DefaultMutableTreeNode root;

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
		
		root = new DefaultMutableTreeNode("Jalons");
		tree = new JTree(root);
		tree.setShowsRootHandles(true);
		tree.setBounds(0, 74, 229, 391);
		
		m_SP_Tree = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		frame.getContentPane().add(m_SP_Tree);
		m_SP_Tree.setBounds(0, 35, 229, 465);
		m_SP_Tree.setAutoscrolls(true);
		

		
		
		JScrollPane m_scrollPanel_Information = new JScrollPane(); //////
		m_scrollPanel_Information.setBounds(0, 510, 1274, 160);
		m_scrollPanel_Information.setAutoscrolls(true);
		frame.getContentPane().add(m_scrollPanel_Information);
		
		JLabel m_Label_Information = new JLabel("Informations");
		m_Label_Information.setBounds(10, 11, 86, 14);
		m_scrollPanel_Information.add((m_Label_Information));
		
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
				{null, null},
			},
			new String[] {
				"Projets", "Diagramme de classe - Date limite : 23/02/18"
			}
		));
		m_Table_Frise.getColumnModel().getColumn(0).setMinWidth(150);
		m_Table_Frise.getColumnModel().getColumn(1).setMinWidth(250);
		
		JDesktopPane m_Pannel_Information = new JDesktopPane();
		m_Pannel_Information.setBackground(Color.BLUE);
		m_Pannel_Information.setBounds(0, 510, 1274, 160);
		frame.getContentPane().add(m_Pannel_Information);
		m_Pannel_Information.setLayout(null);
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
		

		

	}
	public void addJalon()
	{
		DefaultTableModel model = (DefaultTableModel) m_Table_Frise.getModel();
		Vector<String> test = new Vector<>();
		test.add("test");
		
		model.setValueAt("Jeanne, Kilian", 0, 0);
		model.setValueAt("Envoyé", 0, 1);
		
		for(int i = 0; i<10; i++)
		{
			DefaultMutableTreeNode jalons = new DefaultMutableTreeNode(new Random().nextInt(2000));
			root.insert(jalons, root.getChildCount());
		}
		DefaultTreeModel modelTree = (DefaultTreeModel)tree.getModel();
		modelTree.reload();
	}
}
	
