package package1;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;
import javax.swing.JProgressBar;
import javax.swing.Box;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import java.awt.Dimension;

public class Espace extends JFrame {

	private Container frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Green", "INSERT YOUR LICENSE KEY HERE", "my company");
					 UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
					Espace window = new Espace();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Espace() {
		frame = this.getContentPane();
		this.setBounds(100, 100, 870,520);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(new Color(192, 192, 192));
		frame.setLayout(null);
	
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		
		
		
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 883, 61);
		frame.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Welcome");
		lblNewLabel_2.setForeground(new Color(0, 128, 128));
		lblNewLabel_2.setFont(new Font("Urdu Typesetting", Font.BOLD, 30));
		lblNewLabel_2.setBounds(421, 0, 127, 61);
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(169, 169, 169));
		panel_1.setBounds(550, 0, 351, 61);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("profileImage");
		lblNewLabel_1.setBounds(10, 11, 79, 44);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(90, 11, 87, 44);
		panel_1.add(lblName);
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(173, 0, 58, 61);
		panel_1.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Mariem\\workspace\\mini projet\\images\\bell-1096280_640.png"));
		
		
		JLabel lblNewLabel_11 = new JLabel("0");
		lblNewLabel_11.setForeground(new Color(255, 0, 0));
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_11.setBounds(217, 41, 37, 20);
		panel_1.add(lblNewLabel_11);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOneTouchExpandable(true);
		splitPane.setBounds(151, 172, 404, 203);
		JPanel file =new JPanel();
		//panel.setPreferredSize(preferredSize);
		JPanel panel_2 = new JPanel();
		splitPane.setRightComponent(panel_2);
		
		JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.setPopupSize(new Dimension(200, 50));
		addPopup(panel_2, popupMenu);
		
		JMenuItem mntmSqd = new JMenuItem("sqd");
		popupMenu.add(mntmSqd);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		popupMenu.add(mntmNewMenuItem);
		splitPane.setLeftComponent(new JPanel());
		getContentPane().add(splitPane);
		
		JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");
		internalFrame.setBounds(761, 60, 93, 102);
		getContentPane().add(internalFrame);
		internalFrame.setVisible(true);
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
}
