  package package1;

import java.awt.EventQueue;


import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import jcool.component.JCTextField;
 
import boutons.JCButton;
import javax.swing.JPasswordField;


public class SignIn extends JFrame implements MouseListener, ActionListener{

 Container frame;
 JLabel F;
 JCButton btnLogin ; 
 JCTextField textField; 
 
 private JPasswordField passwordField;
 public SignIn() {
		this.setBackground(new Color(192, 192, 192));
		this.setLayout(null);
		frame=this.getContentPane();
		this.setBounds(100, 100, 870,520);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		initialize();
		F.addMouseListener(this);
	}
	public void initialize() {
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon("images/13020073_485438264985127_193907364_n.jpg"));
		lblNewLabel.setBounds(-10, -10, 650, 500);
		frame.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Log In");
		lblNewLabel_1.setFont(new Font("Urdu Typesetting", Font.BOLD, 49));
		lblNewLabel_1.setForeground(new Color(46, 139, 87));
		lblNewLabel_1.setBounds(650, 11, 323, 73);
		frame.add(lblNewLabel_1);
		  textField = new JCTextField();
		  
		textField.setBounds(630, 155, 206, 53);
		textField.setText("Email");
		frame.add(textField);
		textField.addMouseListener(this);
		 btnLogin = new JCButton("Let's GO");
		btnLogin.setForeground(new Color(46, 139, 87));
		btnLogin.setFont(new Font("Urdu Typesetting", Font.BOLD, 30));
		btnLogin.setBackground(new Color(135, 206, 235));
		btnLogin.setText("Let's GO");
		btnLogin.setBounds(630, 306, 206, 61);
		frame.add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setText("Password");
		passwordField.addMouseListener(this);
		passwordField.setBounds(630, 230, 202, 53);
		frame.add(passwordField);
		
		F = new JLabel("you don't have an account ?");
		F.setForeground(new Color(46, 139, 87));
		F.setFont(new Font("Verdana", Font.PLAIN, 13));
		F.setBounds(630, 401, 218, 33);
		frame.add(F);
		
		 btnLogin.addActionListener(this  );
		
	}
	
	

	public   String getMD5Hash(String s) throws NoSuchAlgorithmException {
		String result = s;
		if (s != null) {
			MessageDigest md = MessageDigest.getInstance("MD5"); // or "SHA-1"
			md.update(s.getBytes());
			BigInteger hash = new BigInteger(1, md.digest());
			result = hash.toString(16);
			while (result.length() < 32) { // 40 for SHA-1
				result = "0" + result;
			}
		}
		return result; }  
	@Override
	public void mouseClicked(MouseEvent e) {
	Object source=e.getSource();
	if(source == passwordField )
	{
		passwordField.setText("");
	}
	if(source == textField )
	{
    textField.setText("");
	}
	if(source==F)
	{
		//To use the library what u see is what u feel 
 
		try {
			 com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Green", "INSERT YOUR LICENSE KEY HERE", "my company");
			 UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
			Subscribe   window = new Subscribe ();
		    window.setVisible(true);
			this.setVisible(false);
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		
	}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Object source = e.getSource();
		if(source==F)
		{Font font = F.getFont();
	    Map attributes = font.getAttributes();
	    attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
	    F.setFont(font.deriveFont(attributes));}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
		Object source = e.getSource();
		if(source==F)
		{
		Font font = F.getFont();
	    Map attributes = font.getAttributes();
	    attributes.put(TextAttribute.UNDERLINE , TextAttribute.FONT);
	    F.setFont(font.deriveFont(attributes));}
		}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()== btnLogin )
		{      
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
			Connection connection;
			PreparedStatement ps , pspw ;
			String s;
			try {
				// Use of MD5 algorith to secure login form  by using getMD5Hash function
				s = getMD5Hash(passwordField.getText());
				System.out.println(s);
				// Am using  remoted database on myAlwaysData server 
				Connection con = DriverManager.getConnection("jdbc:mysql://mysql-sihem.alwaysdata.net/sihem_mydrive","sihem","sob7anaALLAH");
				// preparedStatemdnt 
				pspw = con.prepareStatement("SELECT   `name`, `email`, `pw`  FROM `membre` WHERE `pw` = ? AND   `email` = ?");
				pspw.setString(1, s );
				pspw.setString(2,   textField.getText()  );			
				ResultSet result1 = pspw.executeQuery();
				if(result1.next())
				{ // if login ok 
					workspace   home = new  workspace(result1.getString(2 ) ) ; 
					home.setVisible(true);	
					dispose();
					// dispose to hide this form 
				}
				else{
					JOptionPane.showMessageDialog(this, "User not found ");
				 textField.setText("");
					passwordField.setText("");
				}
			}
			catch ( Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}
	}
}