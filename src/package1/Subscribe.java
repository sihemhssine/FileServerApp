 package package1;

import javax.swing.*;

import javax.swing.filechooser.FileNameExtensionFilter;

import boutons.Fleche;
import boutons.JButtonColor;
import boutons.JCButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage; 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
 

import java.util.Map;
import javax.imageio.ImageIO;
import jcool.component.JCTextField;


public class Subscribe extends JFrame implements MouseListener, ActionListener {
	JLabel l1,l2,l3,l4,F,pic,browse , label ;
	Fleche fleche;
	JCTextField t1,t2,t3,t4;
	JPasswordField pass;
	Container c;
JLabel open;
	JCButton  add;
	BufferedImage Picture;
	String s; 
	 
	public Subscribe() throws IOException{


		this.setTitle("Subscribe");
		this.setBounds(100, 100, 870,520);
		c=this.getContentPane();
		c.setLayout(new BorderLayout(10,0));

		this.setBackground(new Color(192, 192, 192));

		l1=new JLabel("Name");
		l2=new JLabel("Email");
		l3=new JLabel("Password");
		l4=new JLabel("Profile photo");
		label = new JLabel();
	    label.setBounds(10,10,670,250); 
		l1.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		l1.setForeground(new Color(107,213,141));
		l2.setFont(new Font("Comic Sans MS",  Font.BOLD, 20));
		l2.setForeground(new Color(107,213,141));
		l3.setFont(new Font("Comic Sans MS",  Font.BOLD, 20));
		l3.setForeground(new Color(107,213,141));
		l4.setFont(new Font("Comic Sans MS",  Font.BOLD, 20));
		l4.setForeground(new Color(107,213,141));
		t1=new JCTextField();
		t2=new JCTextField();
		pass = new JPasswordField() ; 
		t4=new JCTextField();


		JPanel south= new JPanel();
		JPanel north= new JPanel();
		JPanel centre= new JPanel();
		south.setPreferredSize(new Dimension(800,80));
		south.setLayout(new GridLayout(1,2,200,2));
		JPanel p01= new JPanel();
		JPanel p02= new JPanel();
		JPanel p021= new JPanel();
		JPanel p022= new JPanel();

		F= new JLabel("you have already an account ? ");
		F.addMouseListener(this);

		F.setFont(new Font("Comic Sans MS",  Font.PLAIN, 20));
		F.setForeground(new Color(46, 139, 87));
		p022.add(F);
		p02.setLayout(new GridLayout(2,1));
		p02.add(p021);
		p02.add(p022);
		JPanel  p001= new JPanel();
		p001.setLayout(new GridLayout(1,1));
		JPanel p011= new JPanel();
		JPanel p012= new JPanel();		 
		JPanel p013= new JPanel();
		JPanel p014= new JPanel();	
		JPanel p015= new JPanel();		
		JPanel p016= new JPanel();

		fleche= new Fleche();
		fleche.addMouseListener(this);
		p01.setLayout(new GridLayout(2,6));
		p001.add(fleche);

		p01.add(p011);
		p01.add(p012);
		p01.add(p013);
		p01.add(p014);
		p01.add(p001);
		p01.add(p015);
		p01.add(p016);
		south.add(p01);
		south.add(p02);



		BufferedImage myPicture = ImageIO.read(new File("images/subscribe.png"));
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		north.add(picLabel);
		open= new JLabel(new ImageIcon("images/browse.png"));
		open.addMouseListener(this); 
		
		 
		JPanel p1= new JPanel();
		JPanel p11= new JPanel();
		JPanel p2= new JPanel();
		JPanel p22= new JPanel();
		JPanel p3= new JPanel();
		JPanel p33= new JPanel();
		JPanel p4= new JPanel();
		JPanel p44= new JPanel();
		JPanel p5= new JPanel();
		JPanel p55= new JPanel();
		 
		
		add = new JCButton("Add");
		add.setForeground(new Color(46, 139, 87));
		add.setFont(new Font("Urdu Typesetting", Font.BOLD, 30));
		add.setBackground(new Color(135, 206, 235));
		
		
		centre.setLayout(new GridLayout(5,4,30,5));
		centre.add(p1);
		centre.add(l1);
		centre.add(t1);
		centre.add(p11);
		centre.add(p2);
		centre.add(l2);
		centre.add(t2);
		centre.add(p22);
		centre.add(p3);
		centre.add(l3);
		centre.add(pass);
		centre.add(p33);
		centre.add(p4);
		centre.add(l4);
		centre.add(open);
		centre.add(p44);
		 centre.add( p5);
		centre.add(p55);
		centre.add(add);


		c.add(centre, BorderLayout.CENTER);
		c.add(north,BorderLayout.NORTH);
		c.add(south,BorderLayout.SOUTH);
      
		add.addActionListener(this);
		open.addMouseListener(this); }

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
		Object source = e.getSource();

		if(source== open )
		{
			System.out.println("No Data"); 

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg","gif","png");
			fileChooser.addChoosableFileFilter(filter);
			int result = fileChooser.showSaveDialog(null);
			if(result == JFileChooser.APPROVE_OPTION){
				File selectedFile = fileChooser.getSelectedFile();
				String path = selectedFile.getAbsolutePath();
				label.setIcon(ResizeImage(path));
				s = path;
			}
			else if(result == JFileChooser.CANCEL_OPTION){
				System.out.println("No Data");
			} 	

		}
		if(source==fleche)
		{	try {

			com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Green", "INSERT YOUR LICENSE KEY HERE", "my company");
			UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
			HomePage window = new HomePage();
			window.setVisible(true);
			this.setVisible(false);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		}
		if(source==F)
		{
			try {

				com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Green", "INSERT YOUR LICENSE KEY HERE", "my company");
				UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
				SignIn window = new SignIn();
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
	 
		
		if(source==open)
		{ open.setIcon(new ImageIcon ("images/browse1.png"));
		}
		
	}
	@Override
	public void mouseExited(MouseEvent e) {

		Object source = e.getSource();
		if(source==F)
		{
			Font font = F.getFont();
			Map attributes = font.getAttributes();
			attributes.put(TextAttribute.UNDERLINE , TextAttribute.FONT);
			F.setFont(font.deriveFont(attributes));
			
			if(source==open)
			{ open.setIcon(new ImageIcon ("images/browse.png"));
			}
			
		}

	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		 

		if( e.getSource() == add)
		{
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 


			try{ 
				String pw= getMD5Hash( pass.getText()) ; 

				Connection con = DriverManager.getConnection("jdbc:mysql://mysql-sihem.alwaysdata.net/sihem_mydrive","sihem","sob7anaALLAH");
				PreparedStatement ps = con.prepareStatement("insert into membre(name,email,pw,profilephoto) values(?,?,?,?)");
				FileInputStream is = new FileInputStream(new File(s));
				ps.setString(1, t1.getText());
				ps.setString(2, t2.getText());
				ps.setString(3,  pw);  
				ps.setBinaryStream(4,   (InputStream)is, (int)( new File(s).length() ));
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "Registration with success");
				workspace w = new workspace( t2.getText()) ;
				w.setVisible(true);
			}catch(Exception ex){
				ex.printStackTrace();
			}

			this.dispose(); 

		}


	}

	public ImageIcon ResizeImage(String imgPath){
		ImageIcon MyImage = new ImageIcon(imgPath);
		Image img = MyImage.getImage();
		Image newImage = img.getScaledInstance(label.getWidth(), label.getHeight(),Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newImage);
		return image;
	}
}



