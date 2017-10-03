 package package1;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Label;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.Font;
import boutons.JCButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dialog.ModalExclusionType;

public class HomePage extends JFrame implements ActionListener{
	JButton signin ;
	private Container frame;
public static void main(String[] args) {
	//To use the library what u see is what u feel 
				try {	
					 com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Green", "INSERT YOUR LICENSE KEY HERE", "my company");
					 UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
					HomePage window = new HomePage();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}


	public HomePage() {
		
		getContentPane().setLayout(null);
		frame=this.getContentPane();
	    this.setBounds(100, 100, 860,510);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);	
		
		
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		JLabel lblNewLabel = new JLabel("home");
		lblNewLabel.setFont(new Font("Urdu Typesetting", Font.BOLD, 25));
		lblNewLabel.setBackground(new Color(230, 230, 250));
		lblNewLabel.setIcon(new ImageIcon("images/bgi.jpg"));
		lblNewLabel.setBounds(0, 0, 854, 481);
		getContentPane().add(lblNewLabel);
		
      signin = new JButton("Log In");
     
      signin.setForeground(new Color(0, 0, 51));
      signin.setFont(new Font("Urdu Typesetting", Font.BOLD, 24));
      signin.setBounds(253, 351, 237, 73);
		frame.add(signin);
		signin.addActionListener(this);	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == signin){
			 try {
				 com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Green", "INSERT YOUR LICENSE KEY HERE", "my company");
							 UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
         // when clicking on login  text 
			SignIn window = new SignIn();
			window.setVisible(true);
			this.setVisible(false);
			}
			 catch (Exception ex) {
		     } 					
		 } 
		}
	}
